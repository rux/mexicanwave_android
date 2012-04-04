package com.yell.labs.mexicanwave;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

class HardwareHandler {

	private Vibrator vibrator;
		  
	
	public void goWild(Context c) {
		Log.i("info", "Going wild now!  Woohoo!");
		
		
		
		vibrator = (Vibrator) c.getSystemService(Context.VIBRATOR_SERVICE);  
		vibrator.vibrate(1000);
        
	}

}

