package com.example.babbu;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sukhmeet
 * Date: 08/09/13
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class RefreshService extends IntentService {
    static final String TAG = "RefreshService";

    public RefreshService() {
        super(TAG);
    }

    @Override
    public void onCreate() {

        super.onCreate();    //To change body of overridden methods use File | Settings | File Templates.
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try{
            List<Twitter.Status> timeline = ((BabbuApp) getApplication()).getTwitter()
                    .getPublicTimeline();

            for (Twitter.Status status : timeline) {
                Log.d(TAG, String.format("%s %s", status.user.name, status.text));
            }
        }catch (TwitterException e){
            Log.e(TAG, "Failed to access twitter service", e);
            e.printStackTrace();
        }

        Log.d(TAG, "onHandleIntent");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();    //To change body of overridden methods use File | Settings | File Templates.
        Log.d(TAG, "onDestroy");
    }

}
