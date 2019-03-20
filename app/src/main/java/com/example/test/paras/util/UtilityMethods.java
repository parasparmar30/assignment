package com.example.test.paras.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
public class UtilityMethods {



    /**
     * This method save  a String in Application prefs
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveStringInPref(Context context, String key, String value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * This method returns the string value stored in Application prefs against a key
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getStringInPref(Context context, String key, String defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(key, defaultValue);

    }

    /**
     *  This method save  a Int in Application prefs
     * @param context
     * @param key
     * @param value
     */
    public static void saveIntInPref(Context context, String key, int value){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key,value);
        editor.commit();
    }
    /**
     *  This method returns the int value stored in Application prefs against a key
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getIntInPref(Context context, String key, int defaultValue){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt(key, defaultValue);

    }

    /**
     *  This method save  a Long in Application prefs
     * @param context
     * @param key
     * @param value
     */
    public static void saveLongInPref(Context context, String key, long value){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(key, value);
        editor.commit();
    }
    /**
     *  This method returns the Long value stored in Application prefs against a key
     * @param context
     * @param key
     * @param defaultValue
     */
    public static long getLongInPref(Context context, String key, long defaultValue){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getLong(key, defaultValue);

    }

    public static void saveFloatInPref(Context context, String key, float value){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(key, value);
        editor.commit();
    }
    /**
     *  This method returns the Long value stored in Application prefs against a key
     * @param context
     * @param key
     * @param defaultValue
     */
    public static float getFloatInPref(Context context, String key, float defaultValue){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getFloat(key, defaultValue);

    }


    public static boolean getBooleanInPref(Context context, String key, boolean defaultValue){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean(key, defaultValue);

    }

    public static void saveBooleanInPref(Context context, String key, boolean value){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

}


