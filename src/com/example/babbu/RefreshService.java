package com.example.babbu;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created with IntelliJ IDEA.
 * User: sukhmeet
 * Date: 08/09/13
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class RefreshService extends IntentService {
    static final String TAG = "RefreshService"
    public RefreshService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
