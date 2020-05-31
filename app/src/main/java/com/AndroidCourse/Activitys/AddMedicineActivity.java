package com.AndroidCourse.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.AndroidCourse.POJO.Medicine;
import com.AndroidCourse.R;
import com.AndroidCourse.Utils.DB.DBhelper;
import com.AndroidCourse.Utils.DB.MedicineDBA;

public class AddMedicineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        Medicine m = new Medicine("阿司匹林","1","10:00:00");
        MedicineDBA.addMedicine(m,this);

    }
}
