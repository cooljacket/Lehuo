package com.xyz.lehuo.bean;

import android.content.Context;

import com.xyz.lehuo.util.SPUtil;

/**
 * Created by xyz on 15/12/18.
 */
public class User {

    private String id;
    private String name;
    private String pwd;
    private String major;
    private String grade;
    private String avatar;
    private String sex;

    public User() {

    }

    public User(String avatar, String grade, String major, String name, String pwd) {
        this.avatar = avatar;
        this.grade = grade;
        this.major = major;
        this.name = name;
        this.pwd = pwd;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static void save(Context context, User user) {
        SPUtil.put(context, "major", user.getMajor());
        SPUtil.put(context, "name", user.getName());
        SPUtil.put(context, "grade", user.getGrade());
        SPUtil.put(context, "sex", user.getSex());
        SPUtil.put(context, "avatar_url", user.getAvatar());
        SPUtil.put(context, "pwd", user.getPwd());
    }

    public static User load(Context context) {
        User user = new User();
        user.setName((String) SPUtil.get(context, "name", ""));
        user.setMajor((String) SPUtil.get(context, "major", ""));
        user.setGrade((String) SPUtil.get(context, "grade", ""));
        user.setAvatar((String) SPUtil.get(context, "avatar", ""));
        user.setSex((String) SPUtil.get(context, "sex", ""));
        user.setPwd((String) SPUtil.get(context, "pwd", ""));
        return user;
    }

    public static void clear(Context context) {
        SPUtil.remove(context, "name");
        SPUtil.remove(context, "major");
        SPUtil.remove(context, "grade");
        SPUtil.remove(context, "sex");
        SPUtil.remove(context, "avatar");
        SPUtil.remove(context, "pwd");
    }

}
