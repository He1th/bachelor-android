package com.example.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.androidproject.entity.Likes;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "likes";
    private static final String TABLE_LIKES = "likes";
    private static final String KEY_ID = "postId";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_LIKES + "("
                + KEY_ID + " INTEGER)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIKES);

        // Create tables again
        onCreate(db);
    }

    // code to add the new like
    void addLike(Likes like) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, like.get_postId()); // Contact Name

        // Inserting Row
        db.insert(TABLE_LIKES, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }


    // code to get a like
    Likes getLike(int post_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LIKES, new String[] { KEY_ID }, KEY_ID + "=?",
                new String[] { String.valueOf(post_id) }, null, null, null, null);

        if(cursor == null){
            return null;
        }

        if (cursor.moveToFirst() == false){
            return null;
        }

        if(cursor == null){
            return null;
        }

        Likes like = new Likes(Integer.parseInt(cursor.getString(0)));
        return like;
    }


    // Deleting single like
    public void deleteLike(int post_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LIKES, KEY_ID + " = ?",
                new String[] { String.valueOf(post_id) });
        db.close();
    }


}