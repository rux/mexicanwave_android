package com.yell.labs.mexicanwave;


import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.widget.ImageView;

public class WaveCompass extends ImageView{

	float direction = (float) 0.0;
	
	public WaveCompass(Context context) {
		super(context);
		
		this.setImageResource(R.drawable.wave_disc);
	}

	
	@Override
	public void onDraw(Canvas canvas) {
		int height = this.getHeight();
		int width = this.getWidth();
		
		canvas.rotate(direction, width/2, height/2);
		
		super.onDraw(canvas);
		
	}
	
	public void setDirection(float direction) {
		this.direction = direction;
		this.invalidate();
	}
}