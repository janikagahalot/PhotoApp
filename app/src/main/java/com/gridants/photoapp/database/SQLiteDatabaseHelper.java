package com.gridants.photoapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vikas-pc on 11/12/15.
 */
public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLiteDatabaseHelper";
    private static final String DATABASE_NAME = "photoApp.db";
    private static final int DATABASE_VERSION = 1;
    Context context;

    public SQLiteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        PhotoTable.onCreate(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        PhotoTable.onUpgrade(db,oldVersion,newVersion);

    }
}
