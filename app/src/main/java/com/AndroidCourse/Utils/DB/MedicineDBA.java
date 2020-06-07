package com.AndroidCourse.Utils.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.AndroidCourse.POJO.Medicine;
import com.xq.fasterdialog.dialog.NormalDialog;

import java.util.ArrayList;
import java.util.List;

public class MedicineDBA {
    private final String TABLE = "Medicine";
    private final String[] COL = {"name","time","dosage"};


    public static void addMedicine(Medicine m, Context context){
        DBhelper dBhelper = new DBhelper(context,"Medicine",null,1);
        SQLiteDatabase DB = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",m.getmName());
        values.put("time",m.getTime());
        values.put("dosage",m.getDosage());
        try{
            DB.insert("Medicine",null,values);
        }catch (Exception e){
            NormalDialog dialog = new NormalDialog(context);
            dialog.setTitle("错误").setContent("添加失败,请勿重复添加").setNeutralText("确定").show();
        }finally {
            DB.close();
            dBhelper.close();
        }
    }
    public static List<Medicine> getMedicine(Context context){
        List<Medicine> list = new ArrayList<>();
        DBhelper dBhelper = new DBhelper(context,"Medicine",null,1);
        SQLiteDatabase DB = dBhelper.getReadableDatabase();

        Cursor cursor = DB.query("Medicine", null, null, null, null, null, null);
        while (cursor.moveToNext()){
            Medicine m = new Medicine(cursor.getString(0),cursor.getString(2),cursor.getString(1));
            list.add(m);
        }

        cursor.close();
        DB.close();
        dBhelper.close();

        return list;
    }

    public static void delMedicine(Medicine m, Context context){
        DBhelper dBhelper = new DBhelper(context,"Medicine",null,1);
        SQLiteDatabase DB = dBhelper.getWritableDatabase();
        DB.delete("Medicine","name = ? and time = ?",new String[]{m.getmName(),m.getTime()});
    }
    public static void delAllMedicine(Context context){
        DBhelper dBhelper = new DBhelper(context,"Medicine",null,1);
        SQLiteDatabase DB = dBhelper.getWritableDatabase();
        DB.delete("Medicine",null,null);
    }
}
