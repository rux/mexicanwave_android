package com.yell.labs.mexicanwave;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.hardware.Camera;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;



public class MexicanwaveActivity extends Activity {
    
	private boolean isLightOn = false;
	
	private Button button;
	private HardwareHandler hardwareHandler;
	
	
	
	@Override
    protected void onStop() {
		super.onStop();
		
		hardwareHandler.calmDown( this.getApplicationContext() );
		
	}
	
    
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        button = (Button) findViewById(R.id.buttonForWave);
        
        hardwareHandler = (HardwareHandler) new HardwareHandler();
        
        final Context context = this;
        
        
        
        
        button.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View arg0) {
	        	
	        	if (isLightOn) {
	        		isLightOn = false;
	        		hardwareHandler.calmDown( context );
	        		
	        	} else {
	        		hardwareHandler.goWild( context );
	        		isLightOn = true;
	        	}
        	}
        	
        });
               
    }
}