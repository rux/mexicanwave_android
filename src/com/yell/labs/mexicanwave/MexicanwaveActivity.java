package com.yell.labs.mexicanwave;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;



public class MexicanwaveActivity extends Activity {
    
	private boolean isLightOn = false;
	
	private Button button;
	private RoarHandler roarHandler;
	
	
	
	@Override
    protected void onStop() {
		super.onStop();
		
		roarHandler.calmDown( this.getApplicationContext() );
		
	}
	
    
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        button = (Button) findViewById(R.id.buttonForWave);
        
        roarHandler = (RoarHandler) new RoarHandler();
        
        final Context context = this;
        
        
        
        
        button.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View arg0) {
	        	
	        	if (isLightOn) {
	        		isLightOn = false;
	        		roarHandler.calmDown( context );
	        		
	        	} else {
	        		roarHandler.goWild( context );
	        		isLightOn = true;
	        	}
        	}
        	
        });
               
    }
}