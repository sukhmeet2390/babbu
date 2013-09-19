package com.example.babbu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    public static final String sql = String.format("create table %s ( %s int primary key , %s int, %s text, %s text ) ",
            TABLE_NAME, COL_ID, COL_CREATED_AT, COL_USER, COL_STATUS_TEXT);

}

