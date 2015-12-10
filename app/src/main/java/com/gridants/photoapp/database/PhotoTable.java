package com.gridants.photoapp.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by vikas-pc on 11/12/15.
 */
public class PhotoTable implements BaseColumns {

    private static final String TAG = "PhotoTable";

    public static final String TABLE_NAME = "photo_table";

    public static final String PHOTO_ID = "photo_id";
    public static final String PHOTO_NAME = "photo_name";
    public static final String PHOTO_PATH = "photo_path";
    public static final String PHOTO_TIMESTAMP = "timestamp";


    public static String[] PEOJECTION_ALL = {
            PHOTO_ID,
            PHOTO_NAME,
            PHOTO_NAME,
            PHOTO_TIMESTAMP
    };


    public static final String DATABASE_CREATE =
            "create table if not exists " + TABLE_NAME
                    + "( "
                    + PHOTO_ID + " text primary key,"
                    + PHOTO_NAME + " text,"
                    + PHOTO_PATH + " text,"
                    + PHOTO_TIMESTAMP + " text)";

    static final String DROP_STATEMENT =
            "drop table if exists " + TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        Log.d(TAG, DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_STATEMENT);
        onCreate(db);

    }

    public static void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
