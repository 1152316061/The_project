package com.AndroidCourse.POJO;

public class Medicine {
    private String mName;
    private String dosage;
    private String time;

    public Medicine(String mName, String dosage, String time) {
        this.mName = mName;
        this.dosage = dosage;
        this.time = time;
    }

    public Medicine() {}

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "mName='" + mName + '\'' +
                ", dosage='" + dosage + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
