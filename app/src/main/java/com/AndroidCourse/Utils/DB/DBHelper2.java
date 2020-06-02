package com.AndroidCourse.Utils.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper2 extends SQLiteOpenHelper {
    public DBHelper2(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE table OL (" +
                "GID CHAR(32) PRIMARY KEY NOT NULL," +
                "name VARCHAR(50) NOT NULL," +
                "price DECIMAL(8,2) NOT NULL," +
                "ImgUrl varchar(255) not null);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
/*
    create table goods
(
	GID char(32) not null
		primary key,
	name varchar(50) not null,
	price decimal(8,2) not null,
	imgUrl varchar(255) not null
);
 */