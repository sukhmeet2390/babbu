package com.example.babbu;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created with IntelliJ IDEA.
 * User: sukhmeet
 * Date: 19/09/13
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class StatusProvider extends ContentProvider {
    public static final String AUTHORITY = "content://com.example.babbu.StatusProvider";
    public static final Uri CONTENT_URI = Uri.parse(AUTHORITY);

    DbHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getType(Uri uri) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        //db.insertOrThrow(TABLE_NAME, null, values); coStly
        //db.insert(TABLE_NAME, null, values);
        long id = sqLiteDatabase.insertWithOnConflict(StatusData.TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
        if (id != -1) Uri.withAppendedPath(uri, Long.toString(id));

        return uri;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        return sqLiteDatabase.query(StatusData.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);// select * from status;
    }


    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
