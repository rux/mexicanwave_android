package com.yell.labs.mexicanwave;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;


public class MexicanwaveActivity extends Activity {
    
	private boolean isLightOn = false;
	private Button button;
	private RoarHandler roarHandler;
	private WaveHandler waveHandler;
	private Context context;
	private LinearLayout view;
	
	
	@Override
    protected void onStop() {
		super.onStop();
		roarHandler.calmDown();
	}
	

    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        context = this;
        view = (LinearLayout) findViewById(R.id.overallLayout);
        button = (Button) findViewById(R.id.buttonForWave);
        roarHandler = (RoarHandler) new RoarHandler(context, view);
  
        
        button.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View arg0) {
	        	
				if (isLightOn) {
	        		isLightOn = false;
	        		roarHandler.calmDown();
	        		
	        	} else {
	        		isLightOn = true;
	        		roarHandler.goWild();
	        	}
        	}
        	
        });
               
    }
}