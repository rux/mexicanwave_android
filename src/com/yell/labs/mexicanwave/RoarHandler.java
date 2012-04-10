package com.yell.labs.mexicanwave;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

class RoarHandler {
	private Vibrator vibrator;
	private Camera camera;
	private Parameters p;
	private LinearLayout theLayout;
	
	private boolean currentlyRoaring;
	

	RoarHandler(Context c, View v) {
        PackageManager pm = c.getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
        	Log.e("err", "This device has no flash");
        	return;
        }
        if (camera == null) {
        	camera = Camera.open();
        }
        p = camera.getParameters();
		vibrator = (Vibrator) c.getSystemService(Context.VIBRATOR_SERVICE);  
        theLayout = (LinearLayout) v;
        currentlyRoaring = false;
	}
	
	public boolean getCurrentlyRoaring() {
		return currentlyRoaring;
	}
	
	
	public void goWild() {
		if (currentlyRoaring != true) {
			p.setFlashMode(Parameters.FLASH_MODE_TORCH);
			camera.setParameters(p);
			camera.startPreview();
			// vibrator.vibrate(1000);
			theLayout.setBackgroundColor(Color.WHITE);
			Log.i("info", "roaring ssss " + String.valueOf(currentlyRoaring));
		} else {
			Log.i("info", "already roaring");
		}
		
		currentlyRoaring = true;
	}	
	

	public void calmDown() {
		if (currentlyRoaring == true) {
			p.setFlashMode(Parameters.FLASH_MODE_OFF);
			camera.setParameters(p);
			camera.stopPreview();    
			theLayout.setBackgroundColor(Color.BLACK);
		}
		currentlyRoaring = false;
	}
}