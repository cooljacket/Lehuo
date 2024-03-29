package com.xyz.lehuo.datebase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xyz.lehuo.bean.Activity;
import com.xyz.lehuo.bean.Club;
import com.xyz.lehuo.global.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xyz on 16/1/1.
 */
public class DatabaseManager {

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDatabase;

    public DatabaseManager(Context context) {
        mHelper = new DatabaseHelper(context);
        mDatabase = mHelper.getWritableDatabase();
    }

    public void addActivity(Activity activity) {
        ContentValues cv = new ContentValues();
        cv.put("activityId", activity.getId());
        cv.put("title", activity.getTitle());
        cv.put("readnum", activity.getReadNum());
        cv.put("endDate", activity.getEndDate());
        cv.put("startDate", activity.getStartDate());
        cv.put("endTime", activity.getEndTime());
        cv.put("startTime", activity.getStartTime());
        cv.put("organizer", activity.getOrganizer());
        cv.put("imgUrl", activity.getImgUrl());
        cv.put("detailUrl", activity.getDetailUrl());
        cv.put("type", activity.getType());
        mDatabase.insert(Constant.ACTIVITY_TABLE_NAME, null, cv);
    }

    public void addActivities(List<Activity> activities) {
        mDatabase.beginTransaction();
        try {
            for (Activity activity: activities) {
                addActivity(activity);
            }
            mDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mDatabase.endTransaction();
        }
    }

    public List<Activity> getAllActivities() {
        List<Activity> activities = new ArrayList<Activity>();
        Cursor c = mDatabase.rawQuery("SELECT * FROM " + Constant.ACTIVITY_TABLE_NAME, null);
        while (c.moveToNext()) {
            Activity activity = new Activity();
            activity.setId(c.getString(c.getColumnIndex("activityId")));
            activity.setTitle(c.getString(c.getColumnIndex("title")));
            activity.setReadNum(c.getInt(c.getColumnIndex("readnum")));
            activity.setEndDate(c.getString(c.getColumnIndex("endDate")));
            activity.setOrganizer(c.getString(c.getColumnIndex("organizer")));
            activity.setImgUrl(c.getString(c.getColumnIndex("imgUrl")));
            activity.setDetailUrl(c.getString(c.getColumnIndex("detailUrl")));
            activity.setEndTime(c.getString(c.getColumnIndex("endTime")));
            activity.setStartDate(c.getString(c.getColumnIndex("startDate")));
            activity.setStartTime(c.getString(c.getColumnIndex("startTime")));
            activity.setType(c.getString(c.getColumnIndex("type")));
            activities.add(activity);
        }
        c.close();
        return activities;
    }

    public void clearActivityTable() {
        mDatabase.execSQL("DELETE FROM " + Constant.ACTIVITY_TABLE_NAME);
    }

    public void addClub(Club club) {
        ContentValues cv = new ContentValues();
        cv.put("clubId", club.getId());
        cv.put("name", club.getName());
        cv.put("imgUrl", club.getImgUrl());
        mDatabase.insert(Constant.CLUB_TABLE_NAME, null, cv);
    }

    public void addClubs(List<Club> clubs) {
        mDatabase.beginTransaction();
        try {
            for (Club c : clubs) {
                addClub(c);
            }
            mDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mDatabase.endTransaction();
        }
    }

    public List<Club> getAllClubs() {
        List<Club> clubs = new ArrayList<Club>();
        Cursor c = mDatabase.rawQuery("SELECT * FROM " + Constant.CLUB_TABLE_NAME, null);
        while (c.moveToNext()) {
            Club club = new Club();
            club.setId(c.getString(c.getColumnIndex("clubId")));
            club.setName(c.getString(c.getColumnIndex("name")));
            club.setImgUrl(c.getString(c.getColumnIndex("imgUrl")));
            clubs.add(club);
        }
        c.close();
        return clubs;
    }

    public void clearClubTable() {
        mDatabase.execSQL("DELETE FROM " + Constant.CLUB_TABLE_NAME);
    }
}
