package edu.virginia.cs2110;

public class ProtonPack {

	//fully charged battery/max for that int is 5
	private int battery;
	//max number of bombs is 3
	private int bombs;
	
	/**
	 * default constructor
	 * @param p
	 */
	public ProtonPack(Person p) {
		this.battery = 5;
		this.bombs = 0;
	}
	
	/**
	 * constructor
	 * @param p
	 * @param battery
	 * @param bombs
	 */
	public ProtonPack(Person p, int battery, int bombs) {
		this.battery = battery;
		this.bombs = bombs;
	}
	
	public ProtonPack(int battery, int bombs) {
		this.battery = battery;
		this.bombs = bombs;
	}
	
	/**
	 * adds one unit to battery power
	 * returns true if successful
	 */
	public boolean addBattery() {
		if(battery<6) {
			battery+=1;
			return true;
		}
		return false;
	}
	
	/**
	 * adds a specified amount of power to the battery
	 * returns true if successful
	 * @param b
	 */
	public boolean addBattery(int b) {
		if(battery<6) {
			if(battery+b<6) battery+=b;
			else battery=5;
			return true;
		}
		return false;
	}
	
	/**
	 * decreases the battery by one unit
	 * returns true if successful
	 */
	public boolean useBattery() {
		if(battery>0) {
			battery-=1;
			return true;
		}
		return false;
	}
	
	/**
	 * decreases the battery power by the amount specified
	 * returns true if successful
	 * @param b
	 */
	public boolean useBattery(int b) {
		if(battery>0) {
			if(battery-b>0) battery-=b;
			else battery=0;
			return true;
		}
		return false;
	}
	
	/**
	 * adds one bomb
	 * returns true if successful
	 */
	public boolean addBomb () {
		if(bombs<3) {
			bombs+=1;
			return true;
		}
		return false;
	}
	
	/**
	 * adds a specified amount of bombs
	 * returns true if successful
	 * @param b
	 */
	public boolean addBomb(int b) {
		if(bombs<3) {
			if(bombs+b<3) battery+=b;
			else battery=3;
			return true;
		}
		return false;
	}
	
	/**
	 * removes one bomb
	 * returns true if successful
	 */
	public boolean useBomb() {
		if(bombs>0) {
			bombs-=1;
			return true;
		}
		return false;
	}
	
	/**
	 * removes the specified number of bombs
	 * returns true if successful
	 * @param b
	 */
	public boolean useBomb(int b) {
		if(bombs>0) {
			if(bombs-b>0) bombs-=b;
			else bombs=0;
			return true;
		}
		return false;
	}
	
	//getters and setters

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public int getBombs() {
		return bombs;
	}

	public void setBombs(int bombs) {
		this.bombs = bombs;
	}	
	
}
