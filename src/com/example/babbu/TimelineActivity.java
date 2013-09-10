package com.example.babbu;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: sukhmeet
 * Date: 10/09/13
 * Time: 9:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class TimelineActivity extends Activity {
    TextView textView;
    StatusData statusData;
    Cursor cursor;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);

        textView = (TextView) findViewById(R.id.status_data);

        cursor = ((BabbuApp) getApplication()).statusData.query();

        while (cursor.moveToNext()){
            String user = cursor.getString(cursor.getColumnIndex(StatusData.COL_USER));
            String status = cursor.getString(cursor.getColumnIndex(StatusData.COL_STATUS_TEXT));
            textView.append(String.format("%s : %s\n",user, status));
        }
    }

}