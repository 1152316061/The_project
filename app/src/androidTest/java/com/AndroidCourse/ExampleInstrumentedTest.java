package com.AndroidCourse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.AndroidCourse.POJO.Medicine;
import com.AndroidCourse.Utils.DB.DBhelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        Medicine m = new Medicine("阿司匹林","1","10:00:00");
        DBhelper dBhelper = new DBhelper(appContext,"Medicine",null,1);
        SQLiteDatabase DB = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",m.getmName());
        values.put("time",m.getTime());
        values.put("dosage",m.getDosage());
        DB.insert("Medicine",null,values);

        Cursor cursor = DB.query("Medicine", null, null, null, null, null, null);
        System.out.println("12315");
        while (cursor.moveToNext()){
            Medicine m3 = new Medicine(cursor.getString(0),cursor.getString(2),cursor.getString(1));
            System.out.println(m3);

        }
    }
}
