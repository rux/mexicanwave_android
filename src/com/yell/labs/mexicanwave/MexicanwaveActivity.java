package com.yell.labs.mexicanwave;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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
	private SensorManager mySensorManager;
	private Sensor accelerometer;
	private Sensor magnetometer;
	private float[] myGravities;
	private float[] myMagnetics;
	private float azimuth;
	
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
        roarHandler = new RoarHandler(context, view);
        
        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = mySensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        
  
        
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
    
    
    
    protected void onResume() {
    	super.onResume();
    	mySensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST );
    	mySensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_FASTEST );
    }
 
    protected void onPause() {
    	super.onPause();
    	mySensorManager.unregisterListener(this);
    	roarHandler.calmDown();
    }
    
    




	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}


	

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			myGravities = event.values;
		}
		if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
			myMagnetics = event.values;
		}
		
		if (myGravities != null && myMagnetics != null) {
			float R[] = new float[9];
			float I[] = new float[9];
			boolean success = SensorManager.getRotationMatrix(R, I, myGravities, myMagnetics);
			if (success) {
				float orientation[] = new float[3];
				SensorManager.getOrientation(R, orientation);
				azimuth = orientation[0];
				
				roarHandler.check(azimuth);

			}
			
		}
		
	}
}