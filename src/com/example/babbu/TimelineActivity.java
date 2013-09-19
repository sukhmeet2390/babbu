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

    SimpleCursorAdapter simpleCursorAdapter;
    TimeLineReceiver timeLineReciever;
    static  final int STATUS_LOADER = 123; // random

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // listView = (ListView) findViewById(android.R.id.list);
        listView = getListView();


        // cursor = getManageduery                    // manages ourslef but on UI thread
        //cursor = getContentResolver().query(StatusProvider.CONTENT_URI,null, null, null, StatusData.COL_CREATED_AT + " DESC" );  // here i need to manage cursor
        //cursor = ((BabbuApp) getApplication()).statusData.query();
        setTitle("TIMELINE");
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.row, null, FROM, TO, 0);
        simpleCursorAdapter.setViewBinder(VIEW_BINDER);

        getLoaderManager().initLoader(STATUS_LOADER, null, this); // start calling callbacks
        // here null is saved bundle args to pass

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
    // ---- Loader Manager loader callbacks---   also see TimeLineReceiver swap cursor
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // initialize the cursor loader
        return new CursorLoader(this, StatusProvider.CONTENT_URI, null, null, null, StatusData.COL_CREATED_AT+ " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
       simpleCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        simpleCursorAdapter.swapCursor(null );
    }

    public class TimeLineReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            getLoaderManager().restartLoader(STATUS_LOADER, null, TimelineActivity.this); // callback is parent whyy???
            //cursor = getContentResolver().query(StatusProvider.CONTENT_URI,null, null, null, StatusData.COL_CREATED_AT + " DESC" );
            //cursor = ((BabbuApp) context.getApplicationContext()).statusData.query();

            //simpleCursorAdapter.changeCursor(cursor);
            Log.d("TimeLineReciever", "cursorChanged with count " + intent.getIntExtra("count", 0));

        }
    }

}