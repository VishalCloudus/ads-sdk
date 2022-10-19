package com.livechat.friendvideo.calltalk.extras;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Pref {
    private volatile static Pref mInstance;
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    public Pref(Context context) {
        appSharedPrefs = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        prefsEditor = appSharedPrefs.edit();
    }

    public Pref() {
    }

    public static Pref getInstance() {
        if (null == mInstance) {
            synchronized (Pref.class) {
                if (null == mInstance) {
                    mInstance = new Pref();
                }
            }
        }
        return mInstance;
    }

    public static void setStr(String key, String value) {
        Pref.getInstance().setStringPreference(key, value);
    }

    public static String getStr(String key) {
        return Pref.getInstance().getStringPreference(key);
    }

    public static void setInt(String key, int value) {
        Pref.getInstance().setIntPreference(key, value);
    }

    public static int getInt(String key) {
        return Pref.getInstance().getIntPreference(key);
    }

    public static void setBool(String key, boolean value) {
        Pref.getInstance().setBooleanPreference(key, value);
    }

    public static Boolean getBool(String key) {
        return Pref.getInstance().isBooleanPreference(key);
    }

    public void init(Context context) {
        if (context == null) {
            appSharedPrefs = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            prefsEditor = appSharedPrefs.edit();
        }

        if (appSharedPrefs == null) {
            appSharedPrefs = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            prefsEditor = appSharedPrefs.edit();
        }
    }

    public boolean checkPreferenceSet(String key_value) {
        return appSharedPrefs.contains(key_value);
    }

    public boolean isBooleanPreference(String key_value) {
        return appSharedPrefs.getBoolean(key_value, false);
    }

    public void setBooleanPreference(String key_value, boolean default_value) {
        prefsEditor.putBoolean(key_value, default_value).commit();
    }

    public int getIntPreference(String key_value) {
        return appSharedPrefs.getInt(key_value, 0);
    }

    public void setIntPreference(String key_value, int default_value) {
        prefsEditor.putInt(key_value, default_value).commit();
    }

    public String getStringPreference(String key_value, String default_value) {
        return appSharedPrefs.getString(key_value, default_value);
    }

    public String getStringPreference(String key_value) {
        return appSharedPrefs.getString(key_value, "");
    }

    public void setStringPreference(String key_value, String default_value) {
        prefsEditor.putString(key_value, default_value).commit();
    }

    public long getLongPreference(String key_value) {
        return appSharedPrefs.getLong(key_value, -1);
    }

    public void setLongPreference(String key_value, Long default_value) {
        prefsEditor.putLong(key_value, default_value).commit();
    }
}
