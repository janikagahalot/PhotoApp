package com.gridants.photoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prats on 10/12/15.
 */
public class PhotoGallery extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ImageDb";
    private static final String TABLE_IMAGES = "images";
    private static final String _ID = "_id";
    private static final String FILE_PATH = "filePath";
    private static final String LAST_MODIFIED = "lastModified";

    public PhotoGallery(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_IMAGE_TABLE = "CREATE TABLE " + TABLE_IMAGES + "("
                + _ID + " INTEGER PRIMARY KEY," + FILE_PATH + " TEXT" + LAST_MODIFIED + "INTEGER)";
        db.execSQL(CREATE_IMAGE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGES);
        onCreate(db);
    }
    public void addImage(File file) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FILE_PATH, file.getAbsolutePath());
//        Integer time  = Integer.parseInt(String.valueOf(file.lastModified()));
    //    contentValues.put(LAST_MODIFIED, time);
        db.insert(TABLE_IMAGES, null, contentValues);
    }

    public List<PhotoModel> getAllImages(){
        List<PhotoModel> list = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_IMAGES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                PhotoModel photoModel = new PhotoModel();
                photoModel.setFilePath(cursor.getString(cursor.getColumnIndex(FILE_PATH)));
                photoModel.setLastModified(cursor.getInt(cursor.getColumnIndex(LAST_MODIFIED)));
                list.add(photoModel);
            } while (cursor.moveToNext());
        }
        return list;
    }

}


