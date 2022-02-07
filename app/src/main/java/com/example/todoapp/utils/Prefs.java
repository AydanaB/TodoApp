package com.example.todoapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private SharedPreferences prefs;

    public Prefs(Context context){
        prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void saveName(String name){
        prefs.edit().putString("name", name).apply();
    }

    public String getName(){
        return prefs.getString("name", "");
    }

    public void saveSurName(String surname){
        prefs.edit().putString("surname", surname).apply();
    }

    public String getSurname(){
        return prefs.getString("surname", "");
    }

    public void setPrefs(){
        prefs.edit().putBoolean("key", true).apply();
    }

    public Boolean isShow(){
        return prefs.getBoolean("key", false);
    }
}
