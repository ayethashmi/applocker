package com.maxprods.applocker.ui.fragments.pattern;

import android.content.Context;

import com.maxprods.applocker.ui.util.MaxApps_MyConstant;


public class MaxApps_PatternHelper {

    public static void play_fof_maxappss_makePasswordVisible(Context context , boolean visible){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putBoolean(MaxApps_MyConstant.PASSWORD_VISIBLE , visible)
                .apply();
    }
    public static Boolean maxappsashes_getPasswordVisible(Context context){
        return context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getBoolean(MaxApps_MyConstant.PASSWORD_VISIBLE , true);
    }

    public static void maxappsashes_touchVibrate(Context context , boolean vibrate){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putBoolean(MaxApps_MyConstant.PATTERN_VIBRATE , vibrate)
                .apply();

    }
    public static Boolean maxappsashes_getTouchVibrate(Context context){

        return context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getBoolean(MaxApps_MyConstant.PATTERN_VIBRATE , false);

    }

    public static void maxappsashes_hideKeyboard(Context context , boolean vibrate){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putBoolean(MaxApps_MyConstant.PATTERN_HIDE_KEYBOARD , vibrate)
                .apply();

    }

    public static Boolean maxappsashes_getHideKeyBoard(Context context){
        return context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getBoolean(MaxApps_MyConstant.PATTERN_HIDE_KEYBOARD , true);

    }
}

