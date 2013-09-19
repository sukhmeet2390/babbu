package com.example.babbu;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sukhmeet
 * Date: 08/09/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class BabbuApp extends Application implements SharedPreferences.OnSharedPreferenceChangeListener {
    static final String TAG = "BabbuApp";
    private Twitter twitter;
    SharedPreferences preferences;
    //StatusData statusData;
    public static final String ACTION_NEW_STATUS = "com.example.babbu.NEW_STATUS";
    public static final String ACTION_REFRESH_ALARM = "com.example.babbu.REFRESH_ALARM";
    long last_Seen_At = -1;
    int count = 0;
    static final Intent refreshAlarm = new Intent(ACTION_REFRESH_ALARM);

    @Override
    public void onCreate() {
        super.onCreate();
        // Prefs stuff
        //statusData = new StatusData(this);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.registerOnSharedPreferenceChangeListener(this);

        Log.d(TAG, "onCreate");
    }

    public Twitter getTwitter() {
        if (twitter == null) {
            String username = preferences.getString(getString(R.string.username), ""); // default value
            String password = preferences.getString("password", "");
            String server = preferences.getString("server", "");
            // Twitter   stuff
            twitter = new Twitter(username, password);
            twitter.setAPIRootUrl(server);
        }
        return twitter;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences newPreferences, String key) {
        //To change body of implemented methods use File | Settings | File Templates.
        twitter = null;
        sendBroadcast(refreshAlarm);
        this.preferences = newPreferences;
        Log.d(TAG, "On sharedPreferenceChanged for key " + key);
    }

    public int pullAndInsert() {
        try {
            List<Twitter.Status> timeline = getTwitter().getPublicTimeline();
            for (Twitter.Status status : timeline) {
                getContentResolver().insert(StatusProvider.CONTENT_URI,StatusProvider.statusToValues(status));
                //statusData.insert(status);
                if (status.createdAt.getTime() > last_Seen_At) {
                    count++;
                    last_Seen_At = status.createdAt.getTime();
                }

                Log.d(TAG, String.format("%s %s", status.user.name, status.text));
            }
        } catch (TwitterException e) {
            Log.e(TAG, "Failed to pull time-line");
        } catch (Exception e){
            Log.e(TAG, "Network exception");
        }
        if (count > 0) {
            sendBroadcast(new Intent(ACTION_NEW_STATUS).putExtra("count", count));
        }
        return count;
    }

}
