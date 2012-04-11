package com.yell.labs.mexicanwave;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Vibrator;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;

class RoarHandler {
	private Vibrator vibrator;
	private Camera camera;
	private Parameters p;
	private LinearLayout theLayout;
	private SurfaceView dummy;
	
	private boolean currentlyRoaring;
	
	private float azimuth;
	
	
	

	RoarHandler(Context c, View v) {
        PackageManager pm = c.getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
        	Log.e("err", "This device has no flash");
        	return;
        }
        if (camera == null) {
        	camera = Camera.open();
            dummy = new SurfaceView(c);
            try {
            	camera.setPreviewDisplay(dummy.getHolder());
            } catch (IOException e) {
				e.printStackTrace();
			} 
            
        }
        p = camera.getParameters();
        
        
		vibrator = (Vibrator) c.getSystemService(Context.VIBRATOR_SERVICE);  
        theLayout = (LinearLayout) v;
        currentlyRoaring = false;
	}
	
	public boolean getCurrentlyRoaring() {
		return currentlyRoaring;
	}
	
	private void setAzimuth(float a) {
		double oldAzimuth = (double) azimuth;
		// azimuth = (a + 9*oldAzimuth) / 10;
		double newAzimuth = (double) a;
		
		double oldx = Math.sin(oldAzimuth);
		double oldy = Math.cos(oldAzimuth);
		
		double newx = Math.sin(newAzimuth);
		double newy = Math.cos(newAzimuth);

		double x = 9*oldx + newx;
		double y = 9*oldy + newy;
		
		azimuth = (float) Math.atan2(x, y);  // upside down x and y.  do not be afraid.
	}
	
	private float getAzimuth() {
		// TODO Auto-generated method stub
		return azimuth;
	}	
	
	
    void check(float azimuth) {
		this.setAzimuth(azimuth);
		
		float averageAzimuth = this.getAzimuth();
    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("ss");
		Date date = new Date();
		int seconds = Integer.parseInt(dateFormat.format(date));
		
		//String azimuthText = (String) String.valueOf(azimuth * 180 / Math.PI);
		//button.setText(azimuthText);
		
		Log.i("info", String.valueOf(averageAzimuth) + " and absolute is " + String.valueOf(azimuth) + " and currentlyRoaring is " + String.valueOf(currentlyRoaring));
		

		
		
		if (averageAzimuth < 0.5 && averageAzimuth > -0.5) {
			goWild();
		} else {
			calmDown();
		}
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
			// Log.i("info", "already roaring");
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