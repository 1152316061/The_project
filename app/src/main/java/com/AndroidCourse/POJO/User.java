package com.AndroidCourse.POJO;

public class User {
    private String UID;
    private String pwd;
    private String address;
    private String phone;

    public User(String UID, String pwd) {
        this.UID = UID;
        this.pwd = pwd;
    }

    public User(String UID, String pwd, String address, String phone) {
        this.UID = UID;
        this.pwd = pwd;
        this.address = address;
        this.phone = phone;
    }

    public User() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
