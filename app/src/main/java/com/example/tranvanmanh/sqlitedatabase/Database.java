package com.example.tranvanmanh.sqlitedatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tranvanmanh on 4/14/2018.
 */

public class Database extends SQLiteOpenHelper {
    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //CREATE, INSERT, UPDATE, SELECT
    public void QueryData(String sql){

        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    //INSERT data
    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
       return database.rawQuery(sql, null);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
