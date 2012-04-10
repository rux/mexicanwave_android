package com.yell.labs.mexicanwave;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

public class Wave implements SensorEventListener{

	public Wave() {

		
		
	}


			
	
	public boolean getState() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("ss");
		Date date = new Date();
		
		int seconds = Integer.parseInt(dateFormat.format(date));
		
		boolean waving;
		if (seconds % 5 == 0 ) {
			waving = true;
			Log.i("info", "we are waving!");
		} else {
			Log.i("info", "**NOT WAVING");
			waving = false;
		}
				
		return waving;
	}



	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
	}
}