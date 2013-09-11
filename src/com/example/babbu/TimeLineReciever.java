package com.example.babbu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: sukhmeet
 * Date: 11/09/13
 * Time: 6:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class TimeLineReciever extends BroadcastReceiver {
    Cursor cursor;
    SimpleCursorAdapter simpleCursorAdapter;
    @Override
    public void onReceive(Context context, Intent intent) {

        cursor = ((BabbuApp) context.getApplicationContext()).statusData.query();
        simpleCursorAdapter.changeCursor(cursor);
        Log.d("TimeLineReciever", "cursorChanged");

    }
}
