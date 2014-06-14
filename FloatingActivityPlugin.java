package com.phonegap.plugins.FloatingActivityPlugin;

import org.apache.cordova.api.*;
import org.apache.cordova.example.ChatHeadService;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

public class FloatingActivityPlugin extends CordovaPlugin {
	FloatingActivityPlugin _theApp;
	ComponentName _checkerService;
	
@Override
public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
	// TODO Auto-generated method stub
	 Context context = cordova.getActivity().getApplicationContext();
	 PackageManager pm = cordova.getActivity().getPackageManager();
	 _checkerService = null;
	 String packageName;
	 Boolean result = true;
	 
	 try {
	      packageName = args.getString(0);
	    } catch (JSONException e) {
	      return false;
	    }
	 result = launch(pm,context,packageName, context);
	  if (result == true) {
	      callbackContext.success(0);
	      return true;
	    } else {
	      callbackContext.error(0);
	      return false;
	    }
}
public boolean launch(PackageManager pm, Context c, String packname , final Context con)
{
	if(_checkerService==null){
		
	Thread t = new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			_checkerService = cordova.getActivity().startService(new Intent(con, ChatHeadService.class));
		}
	});
	t.start();
}
 	return true;
	
}

}
