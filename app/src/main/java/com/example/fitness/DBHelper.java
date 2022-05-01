package com.example.fitness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Workout(muscle TEXT primary key, weekday TEXT, date TEXT)");
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
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean deletedata(String muscle, String date){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Workout where muscle = ? and date = ?", new String[]{muscle, date});
        if (cursor.getCount() > 0){
            long result = DB.delete("Workout", "muscle=? and date=?", new String[]{muscle, date});
            if(result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Workout", null);
        return cursor;
    }

    public Cursor getNameFromDate(String day, String daybefore, String daydaybefore){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select muscle from Workout where date = ? or date = ? or date= ?", new String[]{day, daybefore, daydaybefore});
        return cursor;
    }
}
