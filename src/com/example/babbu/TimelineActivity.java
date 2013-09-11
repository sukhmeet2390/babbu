package com.example.babbu;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: sukhmeet
 * Date: 10/09/13
 * Time: 9:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class TimelineActivity extends Activity {
    static final String[] FROM = {StatusData.COL_USER, StatusData.COL_CREATED_AT, StatusData.COL_STATUS_TEXT};
    static final int[] TO = {R.id.text_user, R.id.text_time, R.id.text_status};
    ListView listView;
    StatusData statusData;
    Cursor cursor;
    SimpleCursorAdapter simpleCursorAdapter;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);

        listView = (ListView) findViewById(R.id.list);

        cursor = ((BabbuApp) getApplication()).statusData.query();

        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.row, cursor,FROM, TO  );
        listView.setAdapter(simpleCursorAdapter);
//        while (cursor.moveToNext()){
//            String user = cursor.getString(cursor.getColumnIndex(StatusData.COL_USER));
//            String status = cursor.getString(cursor.getColumnIndex(StatusData.COL_STATUS_TEXT));
//            textView.append(String.format("%s : %s\n",user, status));
//        }

    }

}