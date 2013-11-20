package edu.virginia.cs2110;

import java.util.ArrayList;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

public class RunThisTown extends AsyncTask<ArrayList<Object>, int[], int[]> {

	private Activity myActivity;
	private int[] stats = null;
	
	public RunThisTown(Activity myActivity) {
		super();
		this.myActivity = myActivity;
	}
	
	protected void onPreExecute() {
		this.getStats();
	}
	
	protected int[] doInBackground(ArrayList<Object>... stuff) {
		//updating all the locations
		while(!this.isCancelled()) {
		Person player = (Person)stuff[0].get(0);
		ArrayList<Ghost> ghosts = (ArrayList<Ghost>)stuff[0].get(1);
		player.uScore();
		//check to see if in kill radius
//		if(player.detectAllGhosts() != null){
//			Toast.makeText(myActivity.getApplicationContext(), "Ghost within kill range", Toast.LENGTH_SHORT).show();
//		}
		
		//check for a collision
		for(int i = 0; i<ghosts.size();i++){
			if(player.collision(ghosts.get(i))){
//				Toast.makeText(myActivity.getApplicationContext(), "Oh no! you lost a life :(", Toast.LENGTH_SHORT).show();
				break;
			}
		}
		
		//publish stats
		int[] var = {player.getScore(), player.getMoney(), player.getLives(), player.getProtonPack().getBattery()};
		if(player.getScore()%100 == 0) {
			publishProgress(var);
		}
		return var;
		}
		return null;
	}

	protected void onProgressUpdate(int[] vars) {
		System.out.println("Into asynctask");
		TextView tvScore = (TextView)myActivity.findViewById(R.id.Scores);
		tvScore.setText("Score: " + vars[0]);
		TextView tvMoney = (TextView)myActivity.findViewById(R.id.Money);
		tvMoney.setText("Money: " + vars[1]);
		TextView tvLives = (TextView)myActivity.findViewById(R.id.Lives);
		tvLives.setText("Lives: " + vars[2]);
		TextView tvBattery = (TextView)myActivity.findViewById(R.id.Battery);
		tvBattery.setText("Money: " + vars[3]);
	}
	
	protected void onPostExecute(int[] returnValue) {
		this.setStats(returnValue);
	}
	
	protected void onCancelled() {
		
	}

	public int[] getStats() {
		return stats;
	}

	public void setStats(int[] stats) {
		this.stats = stats;
	}

	
}

