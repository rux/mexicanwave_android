package com.yell.labs.mexicanwave;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;



public class MexicanwaveActivity extends Activity {
    
	private boolean isLightOn = false;
	
	private Camera camera;
	
	
	private Button button;
	
	private HardwareHandler hardwareHandler;
	
	
	
	@Override
    protected void onStop() {
		super.onStop();
		
		if (camera != null) {
			camera.release();
		}
	}
	
    
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        button = (Button) findViewById(R.id.buttonForWave);
        
        hardwareHandler = (HardwareHandler) new HardwareHandler();
        
        final Context context = this;
        PackageManager pm = context.getPackageManager();
        
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
        	Log.e("err", "This device has no flash");
        	return;
        }
        camera = Camera.open();
        final Parameters p = camera.getParameters();
        
        
        
        button.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View arg0) {
	        	
	        	if (isLightOn) {
	        		Log.i("info", "Wave is no longer passing");
	        		p.setFlashMode(Parameters.FLASH_MODE_OFF);
	        		camera.setParameters(p);
	        		camera.stopPreview();
	        		
	        		
	        		
	        		isLightOn = false;
	        		
	        	} else {
	        		Log.i("info", "wave is passing");
	        		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
	        		camera.setParameters(p);
	        		camera.startPreview();
	        			        		

	        		hardwareHandler.goWild( context );
	        		
	        		
	        		isLightOn = true;
	        	}
        	}
        	
        });
               
    }
}