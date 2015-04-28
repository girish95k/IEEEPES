package com.pesu.ieeepes;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.SaveCallback;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
	
	@Override
    public void onCreate() {
        super.onCreate();
        System.out.println("sup");
        Parse.initialize(this, "N6dGYSHFpEdrXvRHzpUrej6HQ958bUyQxil1CLss", "3rX8VSDqefFxAGg3Rw0BlA9kuHLKgPqBLIlVskk0");
        // Also in this method, specify a default Activity to handle push notifications
        //PushService.setDefaultPushCallback(this, MainActivity.class);
        
        ParsePush.subscribeInBackground("", new SaveCallback() {
        	  @Override
        	  public void done(ParseException e) {
        	    if (e != null) {
        	      Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
        	    } else {
        	      Log.e("com.parse.push", "failed to subscribe for push", e);
        	    }
        	  }
        	});
    }

}
