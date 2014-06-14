package com.phonegap.plugins.FloatingActivityPlugin;

import java.util.List;

import org.apache.cordova.example.R;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class ChatHeadService extends Service {

	private WindowManager windowManager;
	private ImageView chatHead;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

		    chatHead = new ImageView(this);
		    chatHead.setImageResource(R.drawable.logo);
		   
		    final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
		        WindowManager.LayoutParams.WRAP_CONTENT,
		        WindowManager.LayoutParams.WRAP_CONTENT,
		        WindowManager.LayoutParams.TYPE_PHONE,
		        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
		        PixelFormat.TRANSLUCENT);

		    params.gravity = Gravity.TOP | Gravity.LEFT;
		    params.x = 0;
		    params.y = 100;

		    windowManager.addView(chatHead, params);
		    
		    chatHead.setOnTouchListener(new View.OnTouchListener() {
		    	  private int initialX;
		    	  private int initialY;
		    	  private float initialTouchX;
		    	  private float initialTouchY;
		    	  int flag = 0;

		    	  @Override public boolean onTouch(View v, MotionEvent event) {
		    	    switch (event.getAction()) {
		    	      case MotionEvent.ACTION_DOWN:
		    	        initialX = params.x;
		    	        initialY = params.y;
		    	        initialTouchX = event.getRawX();
		    	        initialTouchY = event.getRawY();
		    	       
		    	        return true;
		    	      case MotionEvent.ACTION_UP:
		    	    	if(flag==0){
		    	    	startApplication("org.apache.cordova.example");
		    	    	}
		    	    	flag = 0;
		    	        return true;
		    	      case MotionEvent.ACTION_MOVE:
		    	    	flag = 1;
		    	        params.x = initialX + (int) (event.getRawX() - initialTouchX);
		    	        params.y = initialY + (int) (event.getRawY() - initialTouchY);
		    	        windowManager.updateViewLayout(chatHead, params);
		    	        return true;
		    	      case MotionEvent.ACTION_CANCEL:
		    	    	return true;
		    	    }
		    	    return false;
		    	  }
		    	  

		    	});
	}
	 @Override
	  public void onDestroy() {
	    super.onDestroy();
	    if (chatHead != null) windowManager.removeView(chatHead);
	  }
	 public void startApplication(String packageName)
	 {
	     try
	     {
	         Intent intent = new Intent("android.intent.action.MAIN");
	         intent.addCategory("android.intent.category.LAUNCHER");

	         intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	         List<ResolveInfo> resolveInfoList = getPackageManager().queryIntentActivities(intent, 0);

	         for(ResolveInfo info : resolveInfoList)
	             if(info.activityInfo.packageName.equalsIgnoreCase(packageName))
	             {
	                 launchComponent(info.activityInfo.packageName, info.activityInfo.name);
	                 return;
	             }

	         // No match, so application is not installed
	         showInMarket(packageName);
	     }
	     catch (Exception e) 
	     {
	         showInMarket(packageName);
	     }
	 }
	 private void launchComponent(String packageName, String name)
	 {
	     Intent intent = new Intent("android.intent.action.MAIN");
	     intent.addCategory("android.intent.category.LAUNCHER");
	     intent.setComponent(new ComponentName(packageName, name));
	     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	     startActivity(intent);
	 }

	 private void showInMarket(String packageName)
	 {
	     Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));
	     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	     startActivity(intent);
	 }
	  

}
