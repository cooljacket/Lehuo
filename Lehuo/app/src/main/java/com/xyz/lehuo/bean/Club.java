package com.xyz.lehuo.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by xyz on 15/12/29.
 */
public class Club implements Serializable {

    private String id;
    private String imgUrl;
    private String name;
    private String intro;
    private ArrayList<Activity> activities;
    private ArrayList<Activity> recentActivities;


    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Activity> getRecentActivities() {
        return recentActivities;
    }

    public void setRecentActivities(ArrayList<Activity> recentActivities) {
        this.recentActivities = recentActivities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
