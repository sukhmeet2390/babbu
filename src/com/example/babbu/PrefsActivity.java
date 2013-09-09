package com.example.babbu;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created with IntelliJ IDEA.
 * User: sukhmeet
 * Date: 08/09/13
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class PrefsActivity extends PreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}