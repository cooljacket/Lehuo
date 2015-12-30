package com.xyz.lehuo.global;

import android.os.Environment;

/**
 * Created by xyz on 15/12/23.
 */
public class Constant {
    public static final String SERVER_ADDRESS = "http://115.28.32.28:8000";
    public static final String LOGIN_API = SERVER_ADDRESS + "/login";
    public static final String REGISTER_API = SERVER_ADDRESS + "/register";
    public static final String UPDATE_USER_API = SERVER_ADDRESS + "/user/update";
    public static final String UPDATE_USER_LOGOL_API = SERVER_ADDRESS + "/user/upload";

    public static final String DATABAST_NAME = "lehuo.db";
    public static final String ACTIVITY_TABLE_NAME = "activity";
    public static final String CLUB_TABLE_NAME = "club";
    public static final String APP_PATH = Environment.getExternalStorageDirectory() + "/lehuo/";
    public static final String FILE_NAME = "config";

    public static final String [] majors = {"软件学院", "环境卫生学院", "中山医学院", "传播与设计学院", "移动信息工程学院",
                                            "管理学院", "心理学院", "信息科学与计算机学院"};
    public static final String [] grades = {"大一", "大二", "大三", "大四"};

    public static String majorNum(String major) {
        for (int i = 0; i < majors.length; i++) {
            if (majors[i].equals(major)) {
                return new String("" + i);
            }
        }
        return new String("");
    }

    public static String gradeNum(String grade) {
        for (int i = 0; i < grades.length; i++) {
            if (grades[i].equals(grade)) {
                return new String("" + i);
            }
        }
        return new String("");
    }
}