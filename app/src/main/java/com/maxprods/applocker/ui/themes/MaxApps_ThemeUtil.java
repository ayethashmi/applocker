package com.maxprods.applocker.ui.themes;

import android.content.Context;

public class MaxApps_ThemeUtil {
    public static final String PREF = "MyPreference";
    public static final String LOCK_TYPE = "type";
    public static final String PIN = "pin";
    public static final String PATTERN = "pattern";
    public static final String THEME_NAME = "ThemeName";

    public static void maxappsashes_saveThemeName(Context context , String themeName){
        context.getSharedPreferences(PREF , Context.MODE_PRIVATE)
                .edit()
                .putString(THEME_NAME , themeName)
                .apply();
    }
    public static String maxappsashes_getThemeName(Context context){
        return context.getSharedPreferences(PREF , Context.MODE_PRIVATE)
                .getString(THEME_NAME , null);
    }

}
