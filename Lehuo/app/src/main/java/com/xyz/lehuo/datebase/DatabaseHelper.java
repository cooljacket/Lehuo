package com.xyz.lehuo.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.xyz.lehuo.global.Constant;

/**
 * Created by xyz on 16/1/1.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, Constant.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Constant.ACTIVITY_TABLE_NAME
                 + "(rowid INTEGER PRIMARY KET AUTOINCREMENT, "
                 + "activityId TEXT, title TEXT, readnum INTEGER, enddate TEXT, organizer TEXT, imgUrl TEXT, detailUrl TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Constant.CLUB_TABLE_NAME
                 + "rowid INTEGER PRIMARY KEY AUTOINCREMENT, "
                 + "clubId TEXT, name TEXT, imgUrl TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void dropTable(SQLiteDatabase db,String tableName){
        db.execSQL("DROP TABLE "+tableName);
    }

}
