package com.maxprods.applocker.ui.pattern;

import android.content.Context;

import com.maxprods.applocker.ui.util.MaxApps_MyConstant;


public class MaxApps_PatternUtil {
    public static final String fPREF = "MyPreference";
    public static final String fPASSWORD = "Pattern_Password";


    public static void savePatternPassword(Context context , String password){
        context.getSharedPreferences(fPREF , Context.MODE_PRIVATE)
                .edit()
                .putString(fPASSWORD , password)
                .apply();
    }
    public static String getPatternPassword(Context context){
        return context.getSharedPreferences(fPREF , Context.MODE_PRIVATE)
                .getString(fPASSWORD , null);
    }

    public static void savePasswordMode(Context context , String mode){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putString(MaxApps_MyConstant.PASSWORD_MODE , mode)
                .apply();
    }

    public static String getPasswordMode(Context context){
        return context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getString(MaxApps_MyConstant.PASSWORD_MODE , MaxApps_MyConstant.PIN);
    }

    public static void play_fof_makePasswordVisible(Context context , boolean visible){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putBoolean(MaxApps_MyConstant.PASSWORD_VISIBLE , visible)
                .apply();
    }
    public static Boolean play_fof_getPasswordVisible(Context context){
        return context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getBoolean(MaxApps_MyConstant.PASSWORD_VISIBLE , true);
    }

    public static void setIntruder(Context context , boolean enable){
        context.getSharedPreferences(MaxApps_MyConstant.PREF  , Context.MODE_PRIVATE)
                .edit()
                .putBoolean(MaxApps_MyConstant.INTRUDER_PATTERN , enable)
                .apply();
    }
    public static boolean getIntruder(Context context){
        return context.getSharedPreferences(MaxApps_MyConstant.PREF  , Context.MODE_PRIVATE)
                .getBoolean(MaxApps_MyConstant.INTRUDER_PATTERN , false);
    }
}
