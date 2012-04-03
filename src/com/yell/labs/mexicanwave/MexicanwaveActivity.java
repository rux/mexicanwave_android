package com.yell.labs.mexicanwave;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;



public class MexicanwaveActivity extends Activity {
    
	private boolean isLightOn = false;
	
	private Camera camera;
	
	private Vibrator vibrator;
	
	private Button button;
	
	
	@Override
    protected void onStop() {
		super.onStop();
		
		if (camera != null) {
			camera.release();
		}
		if (vibrator != null) {
			vibrator.cancel();
		}
	}
	
    
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        button = (Button) findViewById(R.id.buttonForWave);
        
        Context context = this;
        PackageManager pm = context.getPackageManager();
        
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
        	Log.e("err", "This device has no flash");
        	return;
        }
        camera = Camera.open();
        final Parameters p = camera.getParameters();
        
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        
        
        button.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View arg0) {
	        	
	        	if (isLightOn) {
	        		Log.i("info", "Wave is no longer passing");
	        		p.setFlashMode(Parameters.FLASH_MODE_OFF);
	        		camera.setParameters(p);
	        		camera.stopPreview();
	        		
	        		vibrator.cancel();
	        		
	        		isLightOn = false;
	        		
	        	} else {
	        		Log.i("info", "wave is passing");
	        		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
	        		camera.setParameters(p);
	        		camera.startPreview();
	        		
	        		vibrator.vibrate(1000);
	        		
	        		isLightOn = true;
	        	}
        	}
        	
        });
               
    }
}