package edu.virginia.cs2110;

public class Ghost {
	
	private double locx;
	private double locy;
	private double targetlocx;
	private double targetlocy;
	private Person player;
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
	public Ghost(Person p, double locx, double locy) {
		this.locx = locx;
		this.locy = locy;
		this.player = p;
		this.targetlocx = p.getLat();
		this.targetlocy = p.getLon();
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
	public Ghost(Person p, double locx, double locy, int speed, double killradius, int money) {
		this.locx = locx;
		this.locy = locy;
		this.player = p;
		this.targetlocx = p.getLat();
		this.targetlocy = p.getLon();
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
	
	public double getLocx() {
		return locx;
	}

	public void setLocx(double locx) {
		this.locx = locx;
	}

	public double getLocy() {
		return locy;
	}

	public void setLocy(double locy) {
		this.locy = locy;
	}

	public double getTargetlocx() {
		return targetlocx;
	}

	public void setTargetlocx(double targetlocx) {
		this.targetlocx = targetlocx;
	}

	public double getTargetlocy() {
		return targetlocy;
	}

	public void setTargetlocy(double targetlocy) {
		this.targetlocy = targetlocy;
	}

	public Person getPlayer() {
		return player;
	}

	public void setPerson(Person player) {
		this.player = player;
	}

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
		
	
}
