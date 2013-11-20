package edu.virginia.cs2110;

import java.io.Serializable;
import java.util.ArrayList;

public class GhostsDataWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Ghost> ghosts;

	public GhostsDataWrapper(ArrayList<Ghost> data) {
		this.ghosts = data;
	}

	public ArrayList<Ghost> getGhosts() {
		return this.ghosts;
	}

}
