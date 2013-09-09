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
    private static final int DELAY = 3;
    private static boolean running;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        running = true;
        final int delay = Integer
                .parseInt( ( (BabbuApp)getApplication()).preferences
                .getString("delay","30") );
        Log.d(TAG,"----------------delay -----------"+delay);
        new Thread() {
            public void run() {
                try {
                    while (running) {
                        List<Twitter.Status> timeline = ((BabbuApp) getApplication()).getTwitter()
                                .getPublicTimeline();

                        for (Twitter.Status status : timeline) {
                            Log.d(TAG, String.format("%s %s", status.user.name, status.text));
                        }
                        Thread.sleep(delay * 1000);
                    }
                } catch (TwitterException e) {
                    Log.e(TAG, "Failed to access twitter service", e);
                      e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);    //To change body of overridden methods use File | Settings | File Templates.

    }

    @Override
    public void onDestroy() {
        running = false;
        super.onDestroy();    //To change body of overridden methods use File | Settings | File Templates.
        Log.d(TAG, "onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) { // used for bound Service
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
