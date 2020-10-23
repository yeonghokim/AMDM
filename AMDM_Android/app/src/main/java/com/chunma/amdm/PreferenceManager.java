package com.chunma.amdm;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private static final String PREFERENCES_NAME = "AMDM_DATA";

    public static PreferenceManager getInstance(){
        return new PreferenceManager();
    }

    private SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void setLockHistory(Context context, Boolean value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("LockHistory", value);
        editor.commit();
    }
    public void removeLockHistory(Context context) {
        removeKey(context,"LockHistory");
    }
    public Boolean getLockHistory(Context context){
        SharedPreferences prefs = getPreferences(context);
        boolean value = prefs.getBoolean("LockHistory", false);
        return value;
    }
    public void setLoginHistory(Context context, Boolean value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("LoginHistory", value?"true":"false");
        editor.commit();
    }
    public void removeLoginHistory(Context context) {
        removeKey(context,"LoginHistory");
    }

    public void removeKey(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();
        edit.remove(key);
        edit.commit();
    }

    public void clear(Context context) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();
        edit.clear();
        edit.commit();
    }
}
