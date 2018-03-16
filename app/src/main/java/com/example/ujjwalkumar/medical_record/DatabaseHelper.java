package com.example.ujjwalkumar.medical_record;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ujjwal Kumar on 11-03-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="medical_records.db";
    public static final String TABLE_NAME="medical_record_table";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";
    public static final String COL_3="ADDRESS";
    public static final String COL_4="PHONE_NUMBER";
    public static final String COL_5="AGE";
    public static final String COL_6="IMAGE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,ADDRESS TEXT,PHONE_NUMBER TEXT,AGE TEXT,IMAGE BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String name,String Add,String phone,String age,byte [] image)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,Add);
        contentValues.put(COL_4,phone);
        contentValues.put(COL_5,age);
        contentValues.put(COL_6,image);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result ==-1)
            return  false;
        else
            return true;
    }

    String []args={"NAME","ADDRESS","AGE","PHONE_NUMBER"};



    public Cursor fetchData(String name,String Phone)
    {
        String sql="select * from " + TABLE_NAME + " where NAME = '"+name + "' and PHONE_NUMBER = '"+Phone +"'";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor ret=db.rawQuery(sql,null);//args,null);
        return ret;
    }
}
