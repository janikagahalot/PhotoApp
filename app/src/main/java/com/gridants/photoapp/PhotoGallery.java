package com.gridants.photoapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by prats on 10/12/15.
 */
public class PhotoGallery extends SQLiteOpenHelper {
    private static String DATABASE_NAME= "photoDatabase";


    public PhotoGallery (Context context){
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
