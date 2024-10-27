package com.maxprods.applocker.ui.fragments.pin;

import android.content.Context;

import com.maxprods.applocker.ui.util.MaxApps_MyConstant;


public class MaxApps_PinHelper {


    public static void play_fof_maxappss_hideKeyboard(Context context , boolean vibrate){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putBoolean(MaxApps_MyConstant.PIN_HIDE_KEYBOARD , vibrate)
                .apply();

    }

    public static Boolean play_fof_maxappss_getHideKeyBoard(Context context){
        return context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getBoolean(MaxApps_MyConstant.PIN_HIDE_KEYBOARD , true);
    }


    public static void play_fof_maxappss_touchVibrate(Context context , boolean vibrate){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putBoolean(MaxApps_MyConstant.PIN_VIBRATE , vibrate)
                .apply();

    }
    public static Boolean play_fof_maxappss_getTouchVibrate(Context context){

        return context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getBoolean(MaxApps_MyConstant.PIN_VIBRATE , false);

    }

    public static void play_fof_maxappss_printHint(Context context , String hint){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putString(MaxApps_MyConstant.PIN_HINT , hint)
                .apply();

    }
    public static String play_fof_maxappss_getPrintHint(Context context){

        return context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getString(MaxApps_MyConstant.PIN_HINT , "");

    }

}
