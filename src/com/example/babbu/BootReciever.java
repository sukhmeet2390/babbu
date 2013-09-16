package com.example.babbu;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.*;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: sukhmeet
 * Date: 09/09/13
 * Time: 10:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class BootReciever extends BroadcastReceiver {
    static PendingIntent lastOperation;
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);


        long interval = Long.parseLong(sharedPreferences.getString("delay", "90000"));
        PendingIntent pendingIntent = PendingIntent.getService(context, -1, new Intent(context, RefreshService.class),
                PendingIntent.FLAG_UPDATE_CURRENT);


        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(lastOperation);

        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, System.currentTimeMillis(), interval, pendingIntent);
        lastOperation = pendingIntent;

        Log.d("BootReceiver", "OnReceive");
        Log.d("BootReceiver", "Params "+ interval);

        //   context.startService(new Intent(context, UpdaterService.class));
    }
}
