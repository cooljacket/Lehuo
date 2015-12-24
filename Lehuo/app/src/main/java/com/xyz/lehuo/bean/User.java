package com.xyz.lehuo.bean;

/**
 * Created by xyz on 15/12/18.
 */
public class User {

    private String name;
    private String pwd;
    private String major;
    private String grade;
    private String avatar;

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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
