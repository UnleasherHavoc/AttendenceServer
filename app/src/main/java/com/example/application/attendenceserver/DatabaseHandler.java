package com.example.application.attendenceserver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by BAIG1995 on 2/14/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="attendence.db";
    private static final String TABLE_NAME1="SLOTa";
    private static final String TABLE_NAME2="SLOTb";
    private static final String TABLE_NAME3="SLOTc";
    private static final String TABLE_NAME4="SLOTd";
    private static final String TABLE_NAME5="SLOTe";
    private static final String TABLE_NAME6="SLOTf";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_DATE="date";
    private static final String COLUMN_NAME="person";



    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_ACCOUNT_TABLE1="CREATE TABLE " + TABLE_NAME1 + "("
                + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , "
                + COLUMN_DATE + " TEXT,"
                + COLUMN_NAME + " TEXT NOT NULL UNIQUE "
                + ");";
        String CREATE_ACCOUNT_TABLE2="CREATE TABLE " + TABLE_NAME2 + "("
                + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , "
                + COLUMN_DATE + " TEXT,"
                + COLUMN_NAME + " TEXT NOT NULL UNIQUE "
                + ");";
        String CREATE_ACCOUNT_TABLE3="CREATE TABLE " + TABLE_NAME3 + "("
                + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , "
                + COLUMN_DATE + " TEXT,"
                + COLUMN_NAME + " TEXT NOT NULL UNIQUE "
                + ");";
        String CREATE_ACCOUNT_TABLE4="CREATE TABLE " + TABLE_NAME4 + "("
                + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , "
                + COLUMN_DATE + " TEXT,"
                + COLUMN_NAME + " TEXT NOT NULL UNIQUE "
                + ");";
        String CREATE_ACCOUNT_TABLE5="CREATE TABLE " + TABLE_NAME5 + "("
                + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , "
                + COLUMN_DATE + " TEXT,"
                + COLUMN_NAME + " TEXT NOT NULL UNIQUE "
                + ");";
        String CREATE_ACCOUNT_TABLE6="CREATE TABLE " + TABLE_NAME6 + "("
                + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , "
                + COLUMN_DATE + " TEXT,"
                + COLUMN_NAME + " TEXT NOT NULL UNIQUE "
                + ");";


        sqLiteDatabase.execSQL(CREATE_ACCOUNT_TABLE1);
        sqLiteDatabase.execSQL(CREATE_ACCOUNT_TABLE2);
        sqLiteDatabase.execSQL(CREATE_ACCOUNT_TABLE3);
        sqLiteDatabase.execSQL(CREATE_ACCOUNT_TABLE4);
        sqLiteDatabase.execSQL(CREATE_ACCOUNT_TABLE5);
        sqLiteDatabase.execSQL(CREATE_ACCOUNT_TABLE6);

    }
















    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF ALREADY EXIST"+TABLE_NAME1);
        onCreate(sqLiteDatabase);

    }
    public void onDELETE() {
        SQLiteDatabase db=getWritableDatabase();



        db.delete(TABLE_NAME1, null, null);
        db.delete(TABLE_NAME2, null, null);
        db.delete(TABLE_NAME3, null, null);
        db.delete(TABLE_NAME4, null, null);
        db.delete(TABLE_NAME5, null, null);
        db.delete(TABLE_NAME6, null, null);




        db.close();


    }
    public void on_SLOT_DELETE(String a) {
        SQLiteDatabase db=getWritableDatabase();




        db.delete(a, null, null);

        db.close();


    }

    public void addinfo(String attend,String table_name){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_NAME,attend);
        values.put(COLUMN_DATE,getDATE());
        db.insert(table_name,null,values);
        db.close();
    }


    public String getDATE_FROM_DATABASE(String table_name1){

        SQLiteDatabase db=getReadableDatabase();
             String a="aaaa";
       String sqlQuery = "select * from "+table_name1;

        Cursor cursor=db.rawQuery(sqlQuery  ,null);
        cursor.moveToFirst();

       a=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)));
        return "DATE:"+a;
    }

    public String getDATE(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date=new Date();
        return dateFormat.format(date);

    }




    public Cursor getAllData1 (String a) {
        SQLiteDatabase db=getReadableDatabase();
       String sqlQuery = "select * from "+a+" "+ "order by person ASC ";




      return db.rawQuery(sqlQuery,null);



    }

    public String count (String a) {
        SQLiteDatabase db=getReadableDatabase();
           String count1="";

        String sqlQuery = "select count(person) from "+a;


           Cursor cursor=db.rawQuery(sqlQuery  ,null);
          cursor.moveToFirst();

          count1=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0)));

       return  count1;

    }



}
