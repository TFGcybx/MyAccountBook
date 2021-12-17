package com.example.myaccountbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static int DB_VERSION = 3;
    private static String DB_NAME = "account_daily.db";

    public DBHelper(Context context) {
        super(context, DB_NAME ,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE account(_id integer PRIMARY KEY AUTOINCREMENT," +
                "Title varchar(20)," +
                "Note vaechar(20),"+
                "Date varchar(20)," +
                "Money vaechar(20))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}