package edu.virginia.cs2110;

import java.io.Serializable;
import java.util.ArrayList;

import android.location.Location;

public class LocationDataWrapper implements Serializable{
	
	private Location location; 
	
	public LocationDataWrapper(Location data) {
		this.location = data;
	}

	public Location getLocation() {
		return this.location;
	}

}
