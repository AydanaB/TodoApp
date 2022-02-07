package com.example.todoapp;

import android.app.Application;

import androidx.room.Room;

import com.example.todoapp.database.local.AppDataBase;
import com.example.todoapp.utils.Prefs;

public class App extends Application {

    public static Prefs prefs;
    public static AppDataBase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        prefs = new Prefs(this);
        dataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class,"dataBase")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
}
