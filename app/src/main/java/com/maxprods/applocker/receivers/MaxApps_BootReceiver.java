package com.maxprods.applocker.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.content.ContextCompat;


public class MaxApps_BootReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action != null) {
            if (action.equals(Intent.ACTION_BOOT_COMPLETED) ) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Log.d("TAG", "Service Started");
                    ContextCompat.startForegroundService(context, intent);
                } else {
                    Log.d("TAG", "Service Stopped");
                    context.startService(intent);
                }
            }
        }
    }


}
