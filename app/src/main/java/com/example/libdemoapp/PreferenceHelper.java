package com.example.libdemoapp;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    public static final String PREF = "pref";
    public static void setValueString(Context context, String key, String value) {
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void setValueBoolean(Context context, String key, Boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void setValueInt(Context context, String key, int value) {
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getValueInt(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return prefs.getInt(key, 0);
    }

    public static String getValueString(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return prefs.getString(key, null);
    }

    public static boolean getValueBoolean(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return prefs.getBoolean(key, false);
    }

    public static void removeStringValue(Context context,String key){
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.commit();
    }
}
