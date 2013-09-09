package com.example.babbu;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: sukhmeet
 * Date: 09/09/13
 * Time: 10:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class BootReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("BootReceiver", "OnReceive");
         context.startService(new Intent(context, UpdaterService.class));
    }
}
