package com.AndroidCourse.POJO;

import java.sql.Timestamp;

public class HealthInformation {
    private String UID;
    private int HR;
    private int SP;//收缩压
    private int DP;//舒张压
    private Timestamp date;
    private String other;

    public HealthInformation(String UID, int HR, int SP, int DP, Timestamp date, String other) {
        this.UID=UID;
        this.HR = HR;
        this.SP = SP;
        this.DP = DP;
        this.date = date;
        this.other = other;
    }

    public HealthInformation() {}

    public int getHR() {
        return HR;
    }

    public void setHR(int HR) {
        this.HR = HR;
    }

    public int getSP() {
        return SP;
    }

    public void setSP(int SP) {
        this.SP = SP;
    }

    public int getDP() {
        return DP;
    }

    public void setDP(int DP) {
        this.DP = DP;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    @Override
    public String toString() {
        return "HealthInformation{" +
                "UID='" + UID + '\'' +
                ", HR=" + HR +
                ", SP=" + SP +
                ", DP=" + DP +
                ", date=" + date +
                ", other='" + other + '\'' +
                '}';
    }
    public void Judge(){
        if(HR>100 || SP >140 || DP>90) other += "#异常#";
    }
}
