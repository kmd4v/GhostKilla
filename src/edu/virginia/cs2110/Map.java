package edu.virginia.cs2110;

import java.util.ArrayList;
import java.util.Random;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends FragmentActivity implements OnTouchListener, 
GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener, LocationListener {

	private LatLng userLocation = new LatLng(0,0);
	static final LatLng KIEL = new LatLng(53.551, 9.993);
	private GoogleMap map;
	private Person player;
	private final static int
	CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
	private LocationClient mLocationClient;
	private Location currentLocation;
	static final int INTENT_RESULT = 0;
	private ArrayList<Ghost> ghosts;
	private RunThisTown task;
	static final LocationRequest REQUEST = LocationRequest.create()
			.setInterval(5000)      // 0.5 seconds
			.setFastestInterval(1000) // 0.1 seconds
			.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

	//these are comments that I'm making to test uploading code to github

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		View b1 = findViewById(R.id.custom_pause_button);       
		b1.setOnTouchListener(this);

		View b2 = findViewById(R.id.custom_store_button);
		b2.setOnTouchListener(this);
		
		View b3 = findViewById(R.id.killButton);
		b3.setOnTouchListener(this);

		mLocationClient = new LocationClient(this, this, this);
		//GooglePlayServicesClient.ConnectionCallbacks listener = this;
		//mLocationClient.registerConnectionCallbacks(listener);


		this.ghosts = new ArrayList<Ghost>();
		Location l = null;
		Ghost g1 = new Ghost(l);
		Ghost g2 = new Ghost(l);
		Ghost g3 = new Ghost(l);
		ghosts.add(g1);
		ghosts.add(g2);
		ghosts.add(g3);
		player = new Person(ghosts, l);

	}

	public void pause(View view) {
		Intent intent = new Intent(Map.this, GamePaused.class);
		Map.this.startActivity(intent);
	}

	public void store(View view) {
		Intent intent = new Intent(Map.this, Store.class); 
		intent.putExtra("range", player.getRange());
		intent.putExtra("lives", player.getLives());
		intent.putExtra("money", player.getMoney());
		intent.putExtra("score", player.getScore());
		intent.putExtra("pp battery", player.getProtonPack().getBattery());
		intent.putExtra("pp bombs", player.getProtonPack().getBombs());
		//		intent.putExtra("location", new LocationDataWrapper(this.player.getPlayerLocation()));
		//		intent.putExtra("ghost array", new GhostsDataWrapper(player.getGhosts()));
		startActivityForResult(intent, INTENT_RESULT);    
		//Map.this.startActivity(intent);
	}

	public void kill(View v){
		if (player.useVacuum() == false){
			Toast.makeText(getApplicationContext(), "No ghosts in range", Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(getApplicationContext(), "You killed the ghost!", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch(v.getId()) {
		case R.id.custom_store_button:
			store(v);
			break;

		case R.id.custom_pause_button:
			pause(v);
			break;

		case R.id.killButton:
			kill(v);
			break;
		}
		return false;
	}

	/*
	 * Called by Location Services when the request to connect the
	 * client finishes successfully. At this point, you can
	 * request the current location or start periodic updates
	 */
	@Override
	public void onConnected(Bundle dataBundle) {
		// Display the connection status
		System.out.println("Got into onConnected");

		mLocationClient.requestLocationUpdates(REQUEST, this);
		currentLocation = mLocationClient.getLastLocation();

		userLocation = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());


		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		map.setMyLocationEnabled(true);
		// Move the camera instantly to userLocation with a zoom of 18.
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 18));

		// Zoom in, animating the camera.
		map.animateCamera(CameraUpdateFactory.zoomTo(18), 2000, null);

		this.player.setPlayerLocation(currentLocation);
		this.player.updateGhostLocations(this.player.getGhosts()); //updates locations ghosts


		//draws each ghost to the map
		for (Ghost g : this.player.getGhosts()){
			Location ghostLoc = g.getGhostLocation();
			if(ghostLoc != null){
				LatLng latlng = new LatLng(ghostLoc.getLatitude(), ghostLoc.getLongitude());
				Marker ghost = map.addMarker(new MarkerOptions()
				.position(latlng)
				.title("Ghost")
				.snippet("This is a ghost")
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.ghost_icon)));
			}
			else{
				System.out.print("Not adding ghost due to null location");
			}
		}

	}

	/*
	 * Called by Location Services if the connection to the
	 * location client drops because of an error.
	 */
	@Override
	public void onDisconnected() {
		// Display the connection status
		Toast.makeText(this, "Disconnected. Please re-connect.",
				Toast.LENGTH_SHORT).show();
	}

	/*
	 * Called by Location Services if the attempt to
	 * Location Services fails.
	 */
	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		/*
		 * Google Play services can resolve some errors it detects.
		 * If the error has a resolution, try sending an Intent to
		 * start a Google Play services activity that can resolve
		 * error.
		 */
		if (connectionResult.hasResolution()) {
			try {
				// Start an Activity that tries to resolve the error
				connectionResult.startResolutionForResult(
						this,
						CONNECTION_FAILURE_RESOLUTION_REQUEST);
				/*
				 * Thrown if Google Play services canceled the original
				 * PendingIntent
				 */
			} catch (IntentSender.SendIntentException e) {
				// Log the error
				e.printStackTrace();
			}
		} else {
			/*
			 * If no resolution is available, display a dialog to the
			 * user with the error.
			 */
		}
	}
	/*
	 * Called when the Activity becomes visible.
	 */
	@Override
	protected void onStart() {
		super.onStart();
		// Connect the client.
		mLocationClient.connect();
	}

	/*
	 * Called when the Activity is no longer visible.
	 */
	@Override
	protected void onStop() {
		// Disconnecting the client invalidates it.
		if (mLocationClient.isConnected()) {
			/*
			 * Remove location updates for a listener.
			 * The current Activity is the listener, so
			 * the argument is "this".
			 */
			//  removeLocationUpdates(this); //not sure how to set argument of removeLocationUpdates() to LocationListener
		}
		/*
		 * After disconnect() is called, the client is
		 * considered "dead".
		 */
		mLocationClient.disconnect();
		super.onStop();
	}

	@Override
	public void onResume() {
		super.onResume();
		task = new RunThisTown(this);
		ArrayList<Object> temp = new ArrayList<Object>();
		temp.add(player);
		temp.add(ghosts);
		task.execute(temp);
	}

	@Override
	public void onPause(){
		super.onPause();
		task.cancel(true);
	}

	@Override
	public void onLocationChanged(Location location) {
		// Report to the UI that the location was updated
		map.clear(); // clears map of all previous marker locations

		player.setPlayerLocation(location);
		ConnectLocationServices c = new ConnectLocationServices(this);
		c.execute(this);
	}

	public static class ErrorDialogFragment extends DialogFragment {
		// Global field to contain the error dialog
		private Dialog mDialog;
		// Default constructor. Sets the dialog field to null
		public ErrorDialogFragment() {
			super();
			mDialog = null;
		}
		// Set the dialog to display
		public void setDialog(Dialog dialog) {
			mDialog = dialog;
		}
		// Return a Dialog to the DialogFragment.
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return mDialog;
		}
	}

	/*
	 * Handle results returned to the FragmentActivity
	 * by Google Play services
	 */
	@Override
	protected void onActivityResult(
			int requestCode, int resultCode, Intent data) {
		// Decide what to do based on the original request code
		switch (requestCode) {

		case CONNECTION_FAILURE_RESOLUTION_REQUEST :
			/*
			 * If the result code is Activity.RESULT_OK, try
			 * to connect again
			 */
			switch (resultCode) {
			case Activity.RESULT_OK :
				/*
				 * Try the request again
				 */

				break;
			}

		}

	}

	public Person getPerson() {
		return player;
	}

	public void setPerson(Person player) {
		this.player = player;
	}

	public GoogleMap getMap() {
		return map;
	}

	public void setMap(GoogleMap map) {
		this.map = map;
	}

}

