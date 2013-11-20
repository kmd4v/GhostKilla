package edu.virginia.cs2110;

import android.os.AsyncTask;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ConnectLocationServices extends AsyncTask<Map, Void , Map> {
	
	private Map theMap;
	
	public ConnectLocationServices(Map m){
		theMap = m;
	}

	@Override
	protected Map doInBackground(Map...params) { //updates ghost locations in background
		theMap = params[0];
		Person p = theMap.getPerson();
		p.updateGhostLocations(p.getGhosts());
		return theMap;

	}

	@Override
	protected void onPostExecute(Map m){ //draws new locations of ghosts when done updating
		LatLng updatedGhostLocation;

		for(Ghost ghost : m.getPerson().getGhosts()){ //redraws ghosts to updated locations
			updatedGhostLocation = new LatLng(ghost.getGhostLocation().getLatitude(), ghost.getGhostLocation().getLongitude());
			Marker ghostMarker = m.getMap().addMarker(new MarkerOptions()
			.position(updatedGhostLocation)
			.title("Ghost")
			.snippet("This is a ghost")
			.icon(BitmapDescriptorFactory
					.fromResource(R.drawable.ghost_icon)));
		}

		
	}
	/*protected Boolean onProgressUpdate(LocationClient y) {
		if (y.isConnected() == true) {
			return true;
		}

		else {
		return false;
		}

	}*/

}
