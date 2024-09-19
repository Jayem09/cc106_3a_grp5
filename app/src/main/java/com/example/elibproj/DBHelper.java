package com.example.elibproj;

import android.content.ContentValues;
import android.content.Context;
import android.content.MutableContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.BreakIterator;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.bd"

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDb) {
      MyDb.execSQL("create Table users(username TEXT primary key, password TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDb, int i, int i1) {
      MyDb.execSQL("drop table if exists users");
    }

    public Boolean inserData(String username, String password) {
        SQLiteDatabase MyDb = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);

        long result =MyDb.insert("users", null ,contentValues);
        if(result == -1) return false;
        else
            return true;

    }

    public Boolean checkusername(String username){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        Cursor cursor = MyDb.rawQuery("Select * from users where username = ?", new String[] {username});

        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        Cursor cursor = MyDb.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});

        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

}
