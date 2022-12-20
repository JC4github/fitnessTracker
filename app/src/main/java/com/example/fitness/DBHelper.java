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
        DB.execSQL("create Table if not exists Workout (id integer primary key autoincrement, muscle TEXT, weekday TEXT, date TEXT, year TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Workout");
    }

    public Boolean insertdata(String muscle, String weekday, String date, String year){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("muscle", muscle);
        contentValues.put("weekday", weekday);
        contentValues.put("date", date);
        contentValues.put("year", year);
        long result = DB.insert("Workout", null, contentValues);
        return result != -1;
    }

    public void deletedata(String muscle, String date, String year){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Workout where muscle = ? and date = ? and year = ?", new String[]{muscle, date, year});
        if (cursor.getCount() > 0) {
            DB.delete("Workout", "muscle=? and date=? and year=?", new String[]{muscle, date, year});
        }
        cursor.close();
    }

    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
//        Cursor cursor = DB.rawQuery("Select * from Workout", null);
        return DB.rawQuery("Select muscle, weekday, date from Workout limit 30", null);
    }

    public Cursor getNameFromDate(String today, String yesterday, String beforeYesterday, String year){
        SQLiteDatabase DB = this.getWritableDatabase();
//        Cursor cursor = DB.rawQuery("Select muscle from Workout where date = ? or date = ? or date= ?", new String[]{today, yesterday, beforeYesterday});
        return DB.rawQuery("Select muscle from Workout where date = ? or date = ? or date= ? and year= ?", new String[]{today, yesterday, beforeYesterday, year});
    }

    public Cursor findLast(String muscle){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select date, year from Workout where muscle = ? order by id DESC limit 1", new String[]{muscle});
        return cursor;
    }

    public Cursor findLatestEntry(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select muscle, date, year from Workout order by id DESC limit 1", null);
        return cursor;
    }
}
