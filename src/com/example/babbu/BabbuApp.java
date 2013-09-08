package com.example.babbu;

import android.app.Application;
import android.util.Log;
import winterwell.jtwitter.Twitter;

/**
 * Created with IntelliJ IDEA.
 * User: sukhmeet
 * Date: 08/09/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class BabbuApp extends Application {
    static final String TAG = "BabbuApp";
     private Twitter twitter;

    public Twitter getTwitter() {
        return twitter;
    }

    @Override
    public void onCreate() {
        twitter = new Twitter("student", "password");
        twitter.setAPIRootUrl("http://yamba.marakana.com/api");

        super.onCreate();    //To change body of overridden methods use File | Settings | File Templates.
        Log.d(TAG, "onCreate");
    }
}
