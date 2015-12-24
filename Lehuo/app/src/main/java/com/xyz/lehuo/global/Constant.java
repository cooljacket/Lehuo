package com.xyz.lehuo.global;

import android.os.Environment;

/**
 * Created by xyz on 15/12/23.
 */
public class Constant {
    public static final String SERVER_ADDRESS = "http://115.28.32.28:8000";
    public static final String LOGIN_API = "/login";
    public static final String REGISTER_API = "/register";
    public static final String UPDATE_USER_API = "/user/update";

    public static final String DATABAST_NAME = "lehuo.db";
    public static final String ACTIVITY_TABLE_NAME = "activity";
    public static final String CLUB_TABLE_NAME = "club";
    public static final String APP_PATH = Environment.getExternalStorageDirectory() + "/lehuo/";
    public static final String FILE_NAME = "config";

    public static final String [] majors = {"软件学院", "环境卫生学院", "中山医学院", "传播与设计学院", "移动信息工程学院",
                                            "管理学院", "心理学院", "信息科学与计算机学院"};
    public static final String [] grades = {"大一", "大二", "大三", "大四"};
}