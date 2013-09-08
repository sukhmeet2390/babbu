package com.example.babbu;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sukhmeet
 * Date: 08/09/13
 * Time: 1:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class UpdaterService extends Service {
    public static final String TAG = "UpdaterService";
    Twitter twitter;
    private static final int DELAY = 3;

    @Override
    public void onCreate() {
        twitter = new Twitter("student", "password");
        twitter.setAPIRootUrl("http://yamba.marakana.com/api");

        super.onCreate();    //To change body of overridden methods use File | Settings | File Templates.
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        List<Twitter.Status> timeline = twitter.getPublicTimeline();

                        for (Twitter.Status status : timeline) {
                            Log.d(TAG, String.format("%s %s", status.user.name, status.text));
                        }
                        Thread.sleep(DELAY * 1000);
                    } catch (TwitterException e) {
                        Log.d(TAG,"Network exception");
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        Log.d(TAG, " Updater Interrupt", e);
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.

                    }
                }
            }
        }.start();


        Log.d(TAG, "onStarCommand");
        return super.onStartCommand(intent, flags, startId);    //To change body of overridden methods use File | Settings | File Templates.

    }


    @Override
    public void onDestroy() {
        super.onDestroy();    //To change body of overridden methods use File | Settings | File Templates.
        Log.d(TAG, "onDestroy");
    }


    @Override
    public IBinder onBind(Intent intent) { // used for bound Service
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
