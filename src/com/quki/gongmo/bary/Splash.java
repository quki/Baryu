package com.quki.gongmo.bary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {
	
	// Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		new Handler().postDelayed(new Runnable(){

			@Override
			public void run() {
				
				
				 // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(Splash.this, Home.class);
                startActivity(i);
 
                // close this activity
                finish();
             

				
			}
			
		}, SPLASH_TIME_OUT);
	}

}
