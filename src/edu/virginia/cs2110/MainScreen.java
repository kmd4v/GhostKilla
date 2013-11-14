package edu.virginia.cs2110;

import com.google.android.gms.common.GooglePlayServicesUtil;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
		
		MediaPlayer mediaPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.mission_impossible);
		mediaPlayer.start(); 
		
		new Handler().postDelayed(new Runnable() { //stackoverload article on how to change screen by timing
            @Override
            public void run() {
                Intent i = new Intent(MainScreen.this, Map.class);
                MainScreen.this.startActivity(i);
                MainScreen.this.finish();
            }
        }, 5000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_screen, menu);
		return true;
	}

}
