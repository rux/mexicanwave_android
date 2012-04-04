package com.yell.labs.mexicanwave;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Vibrator;
import android.util.Log;

class HardwareHandler {

	private Vibrator vibrator;
	private Camera camera;		  
	
	public void goWild(Context c) {
		Log.i("info", "Going wild now!  Woohoo!");

        PackageManager pm = c.getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
        	Log.e("err", "This device has no flash");
        	return;
        }
        if (camera == null) {
        	camera = Camera.open();
        }
        final Parameters p = camera.getParameters();
        
		Log.i("info", "wave is passing");
		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
		camera.setParameters(p);
		camera.startPreview();        
        
		
		vibrator = (Vibrator) c.getSystemService(Context.VIBRATOR_SERVICE);  
		vibrator.vibrate(1000);
	}	
	
	public void calmDown(Context c) {
		Log.i("info", "stopping");

        PackageManager pm = c.getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
        	Log.e("err", "This device has no flash");
        	return;
        }
        
        if (camera == null) {
        	camera = Camera.open();
        }
        
        final Parameters p = camera.getParameters();
        
		p.setFlashMode(Parameters.FLASH_MODE_OFF);
		camera.setParameters(p);
		camera.stopPreview();      
	}
}