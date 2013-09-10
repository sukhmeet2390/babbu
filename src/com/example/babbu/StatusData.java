package com.example.babbu;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import winterwell.jtwitter.Twitter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sukhmeet
 * Date: 09/09/13
 * Time: 9:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class StatusData {
    public final String TAG = "StatusData";

    public static final String DB_NAME = "timeline.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "status";
    public static final String COL_ID = BaseColumns._ID;
    public static final String COL_CREATED_AT = "created_at";
    public static final String COL_USER = "user";
    public static final String COL_STATUS_TEXT = "status_text";
    private final String sql = String.format("create table %s ( %s int primary key , %s int, %s text, %s text ) ",
            TABLE_NAME, COL_ID, COL_CREATED_AT, COL_USER, COL_STATUS_TEXT);

    Context context;
    DbHelper dbHelper;
    SQLiteDatabase db;

    public StatusData(Context context) {
        this.context = context;
        dbHelper = new DbHelper();
    }

    public void insert(Twitter.Status status){
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_ID,status.id);
        values.put(COL_CREATED_AT, status.createdAt.getTime());
        values.put(COL_USER, status.user.name);
        values.put(COL_STATUS_TEXT,status.text);

        //db.insertOrThrow(TABLE_NAME, null, values); coStly
        //db.insert(TABLE_NAME, null, values);
        db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }


    class DbHelper extends SQLiteOpenHelper{

        public DbHelper() {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
            Log.d(TAG,"onCreate with sql"+sql);
            database.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            database.execSQL("Drop if exists"+TABLE_NAME);
            onCreate(database);
            Log.d(TAG, "onUpdate");
        }
    }
}
