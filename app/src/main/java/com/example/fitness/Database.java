package com.example.fitness;

import android.content.Context;

/**
 * this class uses singleton design pattern to ensure only one database is created
 */
public class Database {

    private static DBHelper DB;

    public static DBHelper getInstance(Context context){
        if (DB == null){
            DB = new DBHelper(context);
            return DB;
        }else{
            return DB;
        }
    }

    public static DBHelper getDatabase(){
        return DB;
    }
}
