package com.example.sid.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import javax.security.auth.Subject;

public class DB_Manager {
    private DB_Helper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DB_Manager(Context c) {
        context = c;


    }

    public DB_Manager open() throws SQLException {
        dbHelper = new DB_Helper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DB_Helper.SUBJECT, name);
        contentValue.put(DB_Helper.DESC, desc);
        database.insert(DB_Helper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[]
                {DB_Helper._ID, DB_Helper.SUBJECT, DB_Helper.DESC};
        Cursor cursor = database.query(DB_Helper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_Helper.SUBJECT, name);
        contentValues.put(DB_Helper.DESC, desc);
        int i = database.update(DB_Helper.TABLE_NAME, contentValues, DB_Helper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DB_Helper.TABLE_NAME, DB_Helper._ID + "=" + _id, null);
    }
}
