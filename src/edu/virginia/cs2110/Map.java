package edu.virginia.cs2110;

import java.util.ArrayList;

import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends FragmentActivity implements OnTouchListener, 
GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener {

	private LatLng userLocation = new LatLng(0,0);
	static final LatLng KIEL = new LatLng(53.551, 9.993);
	private GoogleMap map;
	private Person player;
	private ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
	private final static int
	CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
	private LocationClient mLocationClient;
	private Location currentLocation;
	
	//these are comments that I'm making to test uploading code to github

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		View b1 = findViewById(R.id.custom_pause_button);       
		b1.setOnTouchListener(this);

		View b2 = findViewById(R.id.custom_store_button);
		b2.setOnTouchListener(this);

		mLocationClient = new LocationClient(this, this, this);

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		// Move the camera instantly to hamburg with a zoom of 15.
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));

		// Zoom in, animating the camera.
		map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
	}

	public void pause(View view) {
		Intent intent = new Intent(Map.this, GamePaused.class);
		Map.this.startActivity(intent);
	}

	public void store(View view) {
		Intent intent = new Intent(Map.this, Store.class);
		Map.this.startActivity(intent);
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
		Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();

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

            	if (mLocationClient.isConnected() == true) {
            		System.out.println("Inside locationclient connected");
					currentLocation = mLocationClient.getLastLocation();
					double currentLat = currentLocation.getLatitude();
					double currentLong = currentLocation.getLongitude();
					userLocation = new LatLng(currentLat, currentLong);
				}

		Marker user_loc = map.addMarker(new MarkerOptions().position(userLocation)
				.title("Player1"));
		Marker kiel = map.addMarker(new MarkerOptions()
		.position(KIEL)
		.title("Kiel")
		.snippet("Kiel is cool")
		.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.ghost_icon)));
	}

	/*
	 * Called when the Activity is no longer visible.
	 */
	@Override
	protected void onStop() {
		// Disconnecting the client invalidates it.
		mLocationClient.disconnect();
		super.onStop();
	}
}

//  @Override
//  public boolean onCreateOptionsMenu(Menu menu) {
//    getMenuInflater().inflate(R.menu.activity_main, menu);
//    return true;
//  } 
