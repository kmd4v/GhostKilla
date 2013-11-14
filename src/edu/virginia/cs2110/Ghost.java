package edu.virginia.cs2110;

import android.location.Location;

public class Ghost {
	
	private Location ghostLocation;
	private boolean isAlive;
	private int speed;
	private double killradius;
	private int money;

	
	/**
	 * constructor
	 * @param p
	 * @param locx
	 * @param locy
	 */
	public Ghost(Location l) {
		
		this.setGhostLocation(l);
		this.isAlive = true;
		this.speed = 5;
		this.killradius = 30;
		this.money = 50;	
	}
	
	/**
	 * constructor
	 * @param p
	 * @param locx
	 * @param locy
	 * @param speed
	 * @param killradius
	 * @param money
	 */
	public Ghost(Location l, int speed, double killradius, int money) {
		this.ghostLocation = l;
		this.isAlive = true;
		this.speed = speed;
		this.killradius = killradius;
		this.money = money;
	}
	
	/**
	 * moves the ghost
	 */
	public void move() {
		
	}
	
	/**
	 * returns true if the player is in the ghost's kill zone
	 */
	public boolean checkKillZone() {
		return isAlive;
		
	}
	
	/**
	 * returns true if the player and the ghost have collided
	 */
	public boolean collide() {
		return isAlive;
		
	}

	//getters and setters

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public double getKillradius() {
		return killradius;
	}

	public void setKillradius(double killradius) {
		this.killradius = killradius;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Location getGhostLocation() {
		return ghostLocation;
	}

	public void setGhostLocation(Location ghostLocation) {
		this.ghostLocation = ghostLocation;
	}
		
	
}
