package edu.virginia.cs2110;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;

public class GamePaused extends Activity implements OnTouchListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pause);

		View b1 = findViewById(R.id.custom_button);       
		b1.setOnTouchListener(this);

		View b2 = findViewById(R.id.custom_audio_button);
		b2.setOnTouchListener(this);

		View b3 = findViewById(R.id.custom_quit_button);
		b3.setOnTouchListener(this);

		View b4 = findViewById(R.id.custom_save_button);
		b4.setOnTouchListener(this);

	}

	/*public void resume(View view) {
	    Intent intent = new Intent(GamePaused.this, Store.class);
	    GamePaused.this.startActivity(intent);
        GamePaused.this.finish();
	}*/
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		GamePaused.this.finish();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// Perform action on click
		switch(v.getId()) {
		case R.id.custom_button:
		//	resume(v);
			break;

		case R.id.custom_audio_button:
			/*Intent j = new Intent(this, MainScreen.class);
			this.startActivity(j);*/
			break; 

		case R.id.custom_quit_button:
			/*Intent k = new Intent(this, MainScreen.class);
			this.startActivity(k);*/
			break;

		case R.id.custom_save_button:
			/*Intent l = new Intent(this, MainScreen.class);
			this.startActivity(l);*/
			break;

		}
		return false;

	}
	
}