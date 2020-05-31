package com.AndroidCourse.POJO;

import java.sql.Timestamp;

public class Myloc {
    private String UID;
    private Timestamp time;
    private double longitude;
    private double latitude;

    public Myloc(String UID, Timestamp time, double longitude, double latitude) {
        this.UID = UID;
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Myloc() { }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Myloc{" +
                "UID='" + UID + '\'' +
                ", time=" + time +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
