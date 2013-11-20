package edu.virginia.cs2110;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class Store extends Activity implements OnTouchListener{

	Person player;
	final static int INTENT_RESULT = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.store);

		//		GhostsDataWrapper ghostData = (GhostsDataWrapper) getIntent().getSerializableExtra("ghost array");
		//		ArrayList<Ghost> ghosts = ghostData.getGhosts();
		//		player.setGhosts(ghosts);
		//		LocationDataWrapper playerLoc = (LocationDataWrapper) getIntent().getSerializableExtra("location");
		//		Location location = playerLoc.getLocation();
		//		player.setPlayerLocation(location);

		//		PlayerDataWrapper playerData = (PlayerDataWrapper) getIntent().getSerializableExtra("player");

		//= (Person)i.getSerializableExtra("player");

		View b1 = findViewById(R.id.buy_item1);       
		b1.setOnTouchListener(this);

		View b2 = findViewById(R.id.buy_item2);
		b2.setOnTouchListener(this);

		View b3 = findViewById(R.id.buy_item3);
		b3.setOnTouchListener(this);

		View b4 = findViewById(R.id.store_back_button);
		b4.setOnTouchListener(this);


		/*View b4 = findViewById(R.id.life_icon);

		View b5 = findViewById(R.id.battery_icon);

		View b6 = findViewById(R.id.bomb_icon);

		TextView t1 = (TextView) findViewById(R.id.store_life_cost);

		TextView t2 = (TextView) findViewById(R.id.store_battery_cost);

		TextView t3 = (TextView) findViewById(R.id.store_bomb_cost);*/

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == INTENT_RESULT) {
			if(resultCode == RESULT_OK){                
				
				Person returnPlayer = new Person();
				ProtonPack p = new ProtonPack(0,0);
				int battery = (Integer) data.getSerializableExtra("battery");
				p.setBattery(battery);
				p.setBombs((Integer) data.getSerializableExtra("bombs"));
				returnPlayer.setLives((Integer) data.getSerializableExtra("lives"));
				returnPlayer.setMoney((Integer) data.getSerializableExtra("money"));
				returnPlayer.setRange((Double) data.getSerializableExtra("range"));
				returnPlayer.setScore((Integer) data.getSerializableExtra("score"));
				returnPlayer.setPp(p);
				
				Intent returnIntent = new Intent(Store.this, Map.class);
				returnIntent.putExtra("range", player.getRange());
				returnIntent.putExtra("lives", player.getLives());
				returnIntent.putExtra("money", player.getMoney());
				returnIntent.putExtra("score", player.getScore());
				returnIntent.putExtra("pp battery", player.getProtonPack().getBattery());
				returnIntent.putExtra("pp bombs", player.getProtonPack().getBombs());
				startActivity(returnIntent);
			}
			else{
				//whatever we want to do if errors out
			}
		}
	}    

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Store.this.finish();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch(v.getId()) {
		case R.id.buy_item1:
			if(player.getMoney()-100>=0 && player.getProtonPack().addBattery()){
				player.uMoney(-100);
				Toast.makeText(getApplicationContext(), "Purchase successful", Toast.LENGTH_SHORT).show();
			}

			break;

		case R.id.buy_item2:
			if(player.getMoney()-500>=0 && player.getLives()<4){
				player.uMoney(-500);
				player.uLives(1);
				Toast.makeText(getApplicationContext(), "Purchase successful", Toast.LENGTH_SHORT).show();
			}
			break; 

		case R.id.buy_item3:
			if(player.getMoney()-200>=0 && player.getProtonPack().addBomb()){
				double m = player.getMoney();
				player.uMoney(-200);
				Toast.makeText(getApplicationContext(), "Purchase successful", 1).show();
			}
			break;

		case R.id.store_back_button:
			Intent intent = new Intent(Store.this, Map.class);
			intent.putExtra("player", player);
			Store.this.startActivity(intent);
			break;
		}

		return false;
	}
}
