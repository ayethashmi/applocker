package com.maxprods.applocker.ui.setting;

import android.content.Context;

import com.maxprods.applocker.ui.util.MaxApps_MyConstant;


public class MaxApps_PasswordSetting {

    public static void savePasswordMode(Context context , String mode){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putString(MaxApps_MyConstant.PASSWORD_MODE , mode)
                .apply();
    }

    public static String getPasswordMode(Context context){
        return context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getString(MaxApps_MyConstant.PASSWORD_MODE , MaxApps_MyConstant.PATTERN);
    }

}
