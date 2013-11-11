package edu.virginia.cs2110;

import java.util.ArrayList;

public class Person {
	
	//Fields: Location, Direction, Range to kill ghost, Lives, Money, Score
	private double lat;
	private double lon;
	private double range;
	private int lives;
	private double money;
	private int score;
	private ProtonPack pp;
	private ArrayList<Ghost> ghosts;
	
	public Person(ArrayList<Ghost> g, double latitude, double longitude)
	{
		lat = latitude;
		lon = longitude;
		range = 10;
		lives = 3;
		money = 100;
		score = 0;
		pp = new ProtonPack (this);
		ghosts = g;
	}
	
	//Methods: Update movement, draw, detect ghost, collision, use vacuum, update score, pickup money/loot, update money, update lives, getters/setters
	
	public void uMove ()
	{
		// update lat and lon coordinates based on GPS position
		draw();
	}
	
	public void draw()
	{
		// Draws the person repeatedly on the screen
	}
	
	public Ghost detectAllGhosts() {
//		ArrayList<Ghost> temp = new ArrayList<Ghost>();
//		for(int i = 0; i<ghosts.size();i++) {
//			if(detectGhost(ghosts.get(i))){
//				temp.add(ghosts.get(i));
//			}
//		}
//		Ghost t = temp.get(0);
//		if (temp.size() == 0) return null;
//		else
//		{
//			for (int k = 1; k < temp.size(); k++)
//			{
//				if (distanceTo(t) < distanceTo(temp.get(k)))
//				{
//					t = temp.get(k);
//				}
//			}
//			
//			return t;
//		}
//		
		return null;
	}
	
	public boolean detectGhost(Ghost a)
	{
		// Return true if ghost a in range to kill of person
//		if (distanceTo(a.getLocation) <= range)
//		{
//			return true;
//		}
//		else return false;
		return false;
	}
	
	public boolean collision (Ghost a)
	{
//		if (distanceTo(a.getLocation) <= .01)
//		{
//			uLives(-1);
//			return true;
//		}
//		else return false;
//		// Return true if ghost a is essentially at the same location as t
		return false;
	}
	
	public boolean useVacuum (Ghost a)
	{
		// return true if ghost a is sucked into the vacuum.
		// Also removes said ghost from the map if returned true
		Ghost g = detectAllGhosts();
		if (g != null && pp.useBattery() == true)
		{
			pickup(g);
			ghosts.remove(g);
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
	
	public void uMoney(double amount)
	{
		money = money + amount;
	}
	
	public void uLives (int elives)
	{
		lives = lives + elives;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public double getRange() {
		return range;
	}

	public int getLives() {
		return lives;
	}

	public double getMoney() {
		return money;
	}

	public int getScore() {
		return score;
	}
	
	
	
}

