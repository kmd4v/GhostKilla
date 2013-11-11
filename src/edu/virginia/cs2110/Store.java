package edu.virginia.cs2110;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Store extends Activity implements OnTouchListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.store);

		View b1 = findViewById(R.id.buy_item1);       
		b1.setOnTouchListener(this);

		View b2 = findViewById(R.id.buy_item2);
		b2.setOnTouchListener(this);

		View b3 = findViewById(R.id.buy_item3);
		b3.setOnTouchListener(this);
		
		
		/*View b4 = findViewById(R.id.life_icon);
		
		View b5 = findViewById(R.id.battery_icon);
		
		View b6 = findViewById(R.id.bomb_icon);
		
		TextView t1 = (TextView) findViewById(R.id.store_life_cost);
		
		TextView t2 = (TextView) findViewById(R.id.store_battery_cost);
		
		TextView t3 = (TextView) findViewById(R.id.store_bomb_cost);*/

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
			/*Intent i = new Intent(this, MainScreen.class);
			this.startActivity(i);*/
			break;

		case R.id.buy_item2:
			/*Intent j = new Intent(this, MainScreen.class);
			this.startActivity(j);*/
			break; 

		case R.id.buy_item3:
			/*Intent k = new Intent(this, MainScreen.class);
			this.startActivity(k);*/
			break;

		}
		return false;
	}

}
