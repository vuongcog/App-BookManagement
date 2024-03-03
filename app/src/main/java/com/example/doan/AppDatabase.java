package com.example.doan;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class,PhanLoaiBook.class} , version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract BookDao bookDao();
    public abstract PhanLoaiBookDao PhanLoaiDao();
    public static AppDatabase getAppDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, DBConfigUtil.DATABASE_NAME).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
    public static void desTroyInstance(){
        INSTANCE = null;
    }

}
