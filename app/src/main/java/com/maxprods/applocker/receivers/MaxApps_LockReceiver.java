package com.maxprods.applocker.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.maxprods.applocker.ui.pattern.MaxApps_PatternPasscodeActivity;
import com.maxprods.applocker.ui.pin.MaxApps_PinPasscodeActivity;
import com.maxprods.applocker.ui.setting.MaxApps_PasswordSetting;
import com.maxprods.applocker.ui.util.MaxApps_GlobalMethods;
import com.maxprods.applocker.ui.util.MaxApps_MyConstant;


import java.util.ArrayList;

public class MaxApps_LockReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.maxappsashes.locker")){
            String name = intent.getStringExtra("lock");
            displayLock(name , context);

        }

    }

    private void displayLock(String name , Context context){


        if (name != null) {
            ArrayList<String> blockList = MaxApps_GlobalMethods.maxapps_ashes_getBlockList(context);
            if (!blockList.isEmpty()) {
                for (int j = 0; j < blockList.size(); j++) {

                    if (name.equals(blockList.get(j)) && !MaxApps_GlobalMethods.maxapps_ashes_getStatusUnLockedApp(context)) {
                        MaxApps_GlobalMethods.maxapps_ashes_saveLastUnLockedApp(context, blockList.get(j));
                        MaxApps_GlobalMethods.maxapps_ashes_saveStatusLastUnLockedApp(context, false);
                        apps_trend_displayLocker(context);
                        MaxApps_GlobalMethods.maxapps_ashes_saveLastTag(context , "default");
                        return;
                    } else {
                        if (!name.equals(context.getPackageName()) && !name.equals(MaxApps_GlobalMethods.maxapps_ashes_getUnLockedApp(context))) {
                            MaxApps_GlobalMethods.maxapps_ashes_saveStatusLastUnLockedApp(context, false);
                        }
                    }

                    // play_fof_maxappss_fblocker = blockList.get(j);

                }


            }
        }

    }

    void apps_trend_displayLocker(Context context) {


        if (MaxApps_PasswordSetting.getPasswordMode(context).equals(MaxApps_MyConstant.PATTERN)) {
            Intent intent = new Intent(context, MaxApps_PatternPasscodeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS |Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NO_ANIMATION);


            context.startActivity(intent);
            // ScreenNAme="NOAd";
        } else {
            Intent intent = new Intent(context, MaxApps_PinPasscodeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS |Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NO_ANIMATION);
            context.startActivity(intent);
            // ScreenNAme="NOAd";
        }
    }

}
