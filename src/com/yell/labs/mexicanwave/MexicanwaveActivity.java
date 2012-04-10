package com.yell.labs.mexicanwave;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;


public class MexicanwaveActivity extends Activity implements SensorEventListener {
    
	private boolean isLightOn = false;
	private Button button;
	private RoarHandler roarHandler;
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
    
    
    
    
    
    private boolean areWeRoaring() {
    	boolean waving = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("ss");
		Date date = new Date();
		
		int seconds = Integer.parseInt(dateFormat.format(date));
		
		if (seconds % 5 == 0 ) {
			waving = true;
			Log.i("info", "we are waving!");
		} else {
			Log.i("info", "**NOT WAVING");
			waving = false;
		}
				
		return waving;
    }
}