package com.maxprods.applocker.ui.pin;

import android.content.Context;

import com.maxprods.applocker.ui.util.MaxApps_MyConstant;


public class MaxApps_PinUtil {
    public static final String PREF = "MyPreference";
    public static final String PASSWORD = "Pin_Password";


    /***
     * For password
     */
    public static void play_fof_maxappss_savePinPassword(Context context , String password){
        context.getSharedPreferences(PREF , Context.MODE_PRIVATE)
                .edit()
                .putString(PASSWORD , password)
                .apply();
    }
    public static String play_fof_maxappss_getPinPassword(Context context){
        return context.getSharedPreferences(PREF , Context.MODE_PRIVATE)
                .getString(PASSWORD , null);
    }
    public static void play_fof_maxappss_setShuffle(Context context , boolean enable){
        context.getSharedPreferences(PREF , Context.MODE_PRIVATE)
                .edit()
                .putBoolean(MaxApps_MyConstant.SHUFFLE , enable)
                .apply();
    }
    public static boolean play_fof_maxappss_getShuffle(Context context){
        return context.getSharedPreferences(PREF , Context.MODE_PRIVATE)
                .getBoolean(MaxApps_MyConstant.SHUFFLE , false);
    }

    public static void setIntruder(Context context , boolean enable){
        context.getSharedPreferences(PREF , Context.MODE_PRIVATE)
                .edit()
                .putBoolean(MaxApps_MyConstant.INTRUDER , enable)
                .apply();
    }
    public static boolean getIntruder(Context context){
        return context.getSharedPreferences(PREF , Context.MODE_PRIVATE)
                .getBoolean(MaxApps_MyConstant.INTRUDER , false);
    }







}
