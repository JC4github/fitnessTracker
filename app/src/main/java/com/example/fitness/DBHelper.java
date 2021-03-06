package com.example.fitness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table if not exists Workout (id integer primary key autoincrement, muscle TEXT, weekday TEXT, date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Workout");
    }

    public Boolean insertdata(String muscle, String weekday, String date){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("muscle", muscle);
        contentValues.put("weekday", weekday);
        contentValues.put("date", date);
        long result = DB.insert("Workout", null, contentValues);
        return result != -1;
    }

    public void deletedata(String muscle, String date){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Workout where muscle = ? and date = ?", new String[]{muscle, date});
        if (cursor.getCount() > 0) {
            DB.delete("Workout", "muscle=? and date=?", new String[]{muscle, date});
        }
        cursor.close();
    }

    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
//        Cursor cursor = DB.rawQuery("Select * from Workout", null);
        return DB.rawQuery("Select muscle, weekday, date from Workout", null);
    }

    public Cursor getNameFromDate(String today, String yesterday, String beforeYesterday){
        SQLiteDatabase DB = this.getWritableDatabase();
//        Cursor cursor = DB.rawQuery("Select muscle from Workout where date = ? or date = ? or date= ?", new String[]{today, yesterday, beforeYesterday});
        return DB.rawQuery("Select muscle from Workout where date = ? or date = ? or date= ?", new String[]{today, yesterday, beforeYesterday});
    }
}
