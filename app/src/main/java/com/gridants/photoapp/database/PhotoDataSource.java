package com.gridants.photoapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by vikas-pc on 11/12/15.
 */
public class PhotoDataSource {

    private static final String TAG = "PhotoDataSource";
    private SQLiteDatabase database;
    private SQLiteDatabaseHelper dbHelper;

    public PhotoDataSource(Context context) {
        dbHelper = new SQLiteDatabaseHelper(context);
    }
    private void open() {
        database = dbHelper.getWritableDatabase();
    }
    private void close() {
        database.close();
    }

    public long createPhoto(photoDetails photoDetails) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PhotoTable.PHOTO_ID, System.currentTimeMillis());
        contentValues.put(PhotoTable.PHOTO_NAME, photoDetails.getPhotoName());
        contentValues.put(PhotoTable.PHOTO_PATH, photoDetails.getPhotoPath());
        contentValues.put(PhotoTable.PHOTO_TIMESTAMP, photoDetails.getTimeTaken());
        open();
        long row_id = database.insert(PhotoTable.TABLE_NAME, null, contentValues);
        close();
        return row_id;
    }

    public int deletePhoto(int photoId) {
        String where = PhotoTable.PHOTO_ID + "="+photoId;
        open();
        int del_row = database.delete(PhotoTable.TABLE_NAME, where, null);
        close();
        return del_row;
    }
}
