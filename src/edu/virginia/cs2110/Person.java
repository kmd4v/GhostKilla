package edu.virginia.cs2110;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import android.location.Location;
import android.widget.Toast;

public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5461185376189491759L;
	//Fields: Location, Direction, Range to kill ghost, Lives, Money, Score
	private Location playerLocation;
	private double range;
	private int lives;
	private int money;
	private int score;
	private ProtonPack pp;
	private ArrayList<Ghost> ghosts;

	public Person(ArrayList<Ghost> g, Location l)
	{
		setPlayerLocation(l);
		range = 10;
		lives = 3;
		money = 100;
		score = 0;
		pp = new ProtonPack (this);
		ghosts = g;
	}
	
	public Person(ArrayList<Ghost> g)
	{
		setPlayerLocation(null);
		range = 10;
		lives = 3;
		money = 100;
		score = 0;
		pp = new ProtonPack (this);
		ghosts = g;
	}
	
	//Used for store (location is NOT serializable so passing user to store without location data)
	public Person(double r, int l, int m, int s, ProtonPack p){
		range = r;
		lives = l;
		money = m;
		score = s;
		pp = p;
	}

	public Person(){
		range = 10;
		lives = 3;
		money = 100;
		score = 0;
		pp = new ProtonPack(this);
	}
	//Methods: Update movement, draw, detect ghost, collision, use vacuum, update score, pickup money/loot, update money, update lives, getters/setters

	public ProtonPack getPp() {
		return pp;
	}

	public void setPp(ProtonPack pp) {
		this.pp = pp;
	}
	
	public void setPp(int battery, int bombs){
		ProtonPack p = new ProtonPack(battery, bombs);
		this.pp = p;
	}

	public void setRange(double range) {
		this.range = range;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void uMove ()
	{
		// update lat and lon coordinates based on GPS position
		draw();
	}

	public void draw()
	{
		// Draws the person repeatedly on the screen
	}

	public Ghost detectClosestGhost() {
		ArrayList<Ghost> temp = new ArrayList<Ghost>();
		for(int i = 0; i<ghosts.size();i++) {
			if(detectGhost(ghosts.get(i))){
				temp.add(ghosts.get(i));
			}
		}
		Ghost t = temp.get(0);
		if (temp.size() == 0) 
			return null;
		else
		{
			for (int k = 1; k < temp.size(); k++)
			{
				if (playerLocation.distanceTo(t.getGhostLocation()) < playerLocation.distanceTo(temp.get(k).getGhostLocation()))
				{
					t = temp.get(k);
				}
			}

			return t;
		}
	}
	public Ghost detectAllGhosts() {
				ArrayList<Ghost> temp = new ArrayList<Ghost>();
				for(int i = 0; i<ghosts.size();i++) {
					if(detectGhost(ghosts.get(i))){
						temp.add(ghosts.get(i));
					}
				}
				if (temp.size() == 0) return null;
				
				else
				{
					Ghost t = temp.get(0);
					for (int k = 1; k < temp.size(); k++)
					{
						if (playerLocation.distanceTo(t.getGhostLocation()) < playerLocation.distanceTo(temp.get(k).getGhostLocation()))
						{
							t = temp.get(k);
						}
					}
					
					return t;
				}
	}

	public boolean detectGhost(Ghost a)
	{
		// Return true if ghost a in range to kill of person
		if (playerLocation.distanceTo(a.getGhostLocation()) <= range)
		{
			return true;
		}
		else 
			return false;
	}

	public boolean collision (Ghost a)
	{
		if (a.collide())
		{
			uLives(-1);
			return true;
		}
		else 
			return false;
		// Return true if ghost a is essentially at the same location as t
	}
	

	public boolean useVacuum ()
	{
		// return true if ghost a is sucked into the vacuum.
		// Also removes said ghost from the map if returned true
		Ghost g = detectAllGhosts();
		if (g != null && pp.useBattery() == true)
		{
			pickup(g);
			ghosts.remove(g);
		//	Toast.makeText(myActivity.getApplicationContext(), "Oh no! you lost a life :(", Toast.LENGTH_SHORT).show();
			return true;
		}
		else return false;
	}

	public void uScore()
	{
		score++;
		//called however often you want the score incremented by 1
	}

	public void pickup(Ghost a)
	{
		int m = a.getMoney();
		uMoney(m);
	}

	public void uMoney(int amount)
	{
		money = money + amount;
	}

	public void uLives (int elives)
	{
		lives = lives + elives;
	}

	public ArrayList<Ghost> getGhosts() {
		return ghosts;
	}

	public void setGhosts(ArrayList<Ghost> ghosts) {
		this.ghosts = ghosts;
	}

	public double getRange() {
		return range;
	}

	public int getLives() {
		return lives;
	}

	public int getMoney() {
		return money;
	}

	public int getScore() {
		return score;
	}

	public Location getPlayerLocation() {
		return playerLocation;
	}

	public void setPlayerLocation(Location playerLocation) {
		this.playerLocation = playerLocation;
	}
	
	
	public ProtonPack getProtonPack() {
		return this.pp;
	}

	public void updateGhostLocations(ArrayList<Ghost> ghosts){  //updates locations of ghosts randomly based initially on proximity to the player and off their own location there after

		Random ghostLoc = new Random();

		//sets every new ghost to a randomized location based off of the player's location
		for (Ghost g : ghosts){
			if ( g.getGhostLocation() == null){

				Location l = new Location(this.getPlayerLocation());
				Double quadrantSelection = ghostLoc.nextDouble();

				if(quadrantSelection < 0.25){
					l.setLatitude( l.getLatitude() + (ghostLoc.nextFloat() / 1000.0) );
					l.setLongitude( l.getLongitude() + (ghostLoc.nextFloat() / 1000.0) );
					g.setGhostLocation(l);
				}
				else if(quadrantSelection >= 0.25 && quadrantSelection < 0.50){
					l.setLatitude( l.getLatitude() + (ghostLoc.nextFloat() / 1000.0) );
					l.setLongitude( l.getLongitude() - (ghostLoc.nextFloat() / 1000.0) );
					g.setGhostLocation(l);
				}
				if(ghostLoc.nextDouble() >= 0.50 && quadrantSelection < 0.75){
					l.setLatitude( l.getLatitude() - (ghostLoc.nextFloat() / 1000.0) );
					l.setLongitude( l.getLongitude() - (ghostLoc.nextFloat() / 1000.0) );
					g.setGhostLocation(l);
				}
				if(ghostLoc.nextDouble() >= 0.75){
					l.setLatitude( l.getLatitude() - (ghostLoc.nextFloat() / 1000.0) );
					l.setLongitude( l.getLongitude() + (ghostLoc.nextFloat() / 1000.0) );
					g.setGhostLocation(l);
				}
			}
			else {

				Location l = g.getGhostLocation();
				Double quadrantSelection = ghostLoc.nextDouble();

				if(quadrantSelection < 0.25){
					l.setLatitude( l.getLatitude() + (ghostLoc.nextFloat() / 10000.0) );
					l.setLongitude( l.getLongitude() + (ghostLoc.nextFloat() / 10000.0) );
					g.setGhostLocation(l);
				}
				else if(quadrantSelection >= 0.25 && quadrantSelection < 0.50){
					l.setLatitude( l.getLatitude() + (ghostLoc.nextFloat() / 10000.0) );
					l.setLongitude( l.getLongitude() - (ghostLoc.nextFloat() / 10000.0) );
					g.setGhostLocation(l);
				}
				if(ghostLoc.nextDouble() >= 0.50 && quadrantSelection < 0.75){
					l.setLatitude( l.getLatitude() - (ghostLoc.nextFloat() / 10000.0) );
					l.setLongitude( l.getLongitude() - (ghostLoc.nextFloat() / 10000.0) );
					g.setGhostLocation(l);
				}
				if(ghostLoc.nextDouble() >= 0.75){
					l.setLatitude( l.getLatitude() - (ghostLoc.nextFloat() / 10000.0) );
					l.setLongitude( l.getLongitude() + (ghostLoc.nextFloat() / 10000.0) );
					g.setGhostLocation(l);
				}
			}
		}

	}

}

