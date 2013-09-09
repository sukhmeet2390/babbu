package com.example.babbu;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import winterwell.jtwitter.Twitter;

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


    @Override
    public void onCreate() {
        super.onCreate();
        // Prefs stuff
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
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //To change body of implemented methods use File | Settings | File Templates.
        twitter = null;
        Log.d(TAG, "On sharedPreferenceChanged for key "+ key);
    }
}
