package com.AndroidCourse.Utils.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.AndroidCourse.POJO.Goods;

import java.util.ArrayList;
import java.util.List;

public class OLDBA {
    public static void addToOL(Goods g , Context context){
        DBHelper2 dbHelper2 = new DBHelper2(context,"OL",null,1);
        SQLiteDatabase db = dbHelper2.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("GID",g.getGID());
        values.put("name",g.getName());
        values.put("price",g.getPrice());
        values.put("ImgUrl",g.getImgUrl());
        db.insert("OL",null,values);
        db.close();
        dbHelper2.close();
    }
    public static List<Goods> getOL(Context context){
        DBHelper2 dbHelper2 = new DBHelper2(context,"OL",null,1);
        SQLiteDatabase db = dbHelper2.getReadableDatabase();

        Cursor cursor = db.query("OL", null, null, null, null, null, null);
        List<Goods>list = new ArrayList<>();

        while (cursor.moveToNext()){
            list.add(new Goods(cursor.getString(0),cursor.getString(1),
                    cursor.getDouble(2),cursor.getString(3)));
        }

        cursor.close();
        db.close();
        dbHelper2.close();
        return list;
    }
    public static void delOL(Goods g,Context context){
        DBHelper2 dbHelper2 = new DBHelper2(context,"OL",null,1);
        SQLiteDatabase db = dbHelper2.getWritableDatabase();
        db.delete("OL","GID=?", new String[]{g.getGID()});
        db.close();
        dbHelper2.close();
    }
}
