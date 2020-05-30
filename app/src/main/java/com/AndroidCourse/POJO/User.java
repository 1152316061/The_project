package com.AndroidCourse.POJO;

public class User {
    private String UID;
    private String pwd;

    public User(String UID, String pwd) {
        this.UID = UID;
        this.pwd = pwd;
    }

    public User() {
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "UID='" + UID + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
