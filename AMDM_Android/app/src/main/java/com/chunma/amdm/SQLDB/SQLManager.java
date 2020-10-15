package com.chunma.amdm.SQLDB;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLManager extends SQLiteOpenHelper {

    public SQLManager(Context context){
        super(context,"AMDM.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        String create_sql ="";

        db.execSQL(create_sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    // 자신의 휴대폰 사용시간 or 잠금시간(일별)
    // 휴대폰 언락 시
    //
    //
    //
    //
    //
    //
    //
    //
}