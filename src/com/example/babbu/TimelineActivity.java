package com.example.babbu;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.*;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
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
public class TimelineActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    static final String[] FROM = {StatusData.COL_USER, StatusData.COL_CREATED_AT, StatusData.COL_STATUS_TEXT};
    static final int[] TO = {R.id.text_user, R.id.text_time, R.id.text_status};
    ListView listView;
    //StatusData statusData;
    Cursor cursor;
    SimpleCursorAdapter simpleCursorAdapter;
    TimeLineReceiver timeLineReciever;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // listView = (ListView) findViewById(android.R.id.list);
        listView = getListView();

        cursor = getContentResolver().query(StatusProvider.CONTENT_URI,null, null, null, StatusData.COL_CREATED_AT + " DESC" );
        //cursor = ((BabbuApp) getApplication()).statusData.query();
        setTitle("TIMELINE");
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.row, cursor, FROM, TO);
        simpleCursorAdapter.setViewBinder(VIEW_BINDER);
        listView.setAdapter(simpleCursorAdapter);
//        while (cursor.moveToNext()){
//            String user = cursor.getString(cursor.getColumnIndex(StatusData.COL_USER));
//            String status = cursor.getString(cursor.getColumnIndex(StatusData.COL_STATUS_TEXT));
//            textView.append(String.format("%s : %s\n",user, status));
//        }

    }

    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
        if(timeLineReciever == null) timeLineReciever = new TimeLineReceiver();

        IntentFilter filter = new IntentFilter(BabbuApp.ACTION_NEW_STATUS);
        registerReceiver(timeLineReciever,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();    //To change body of overridden methods use File | Settings | File Templates.
        unregisterReceiver(timeLineReciever);
    }


    static final SimpleCursorAdapter.ViewBinder VIEW_BINDER = new SimpleCursorAdapter.ViewBinder() {
        @Override
        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
            if (view.getId() != R.id.text_time) return false;
            //if(cursor.getColumnIndex(StatusData.COL_CREATED_AT) != columnIndex) return false;
            long time = cursor.getLong(cursor.getColumnIndex(StatusData.COL_CREATED_AT));
            CharSequence relativeTime = DateUtils.getRelativeTimeSpanString(time);
            ((TextView) view).setText(relativeTime);

            return true;
        }
    };

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public class TimeLineReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            cursor = getContentResolver().query(StatusProvider.CONTENT_URI,null, null, null, StatusData.COL_CREATED_AT + " DESC" );
            //cursor = ((BabbuApp) context.getApplicationContext()).statusData.query();
            simpleCursorAdapter.changeCursor(cursor);
            Log.d("TimeLineReciever", "cursorChanged with count " + intent.getIntExtra("count", 0));

        }
    }

}