package com.maxprods.applocker;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


import androidx.appcompat.app.AppCompatDelegate;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.maxprods.applocker.activities.MaxApps_MyWork;

import java.util.concurrent.TimeUnit;


public class MyApplication extends Application{
    public static final String NOTIFICATION_CHANNEL = "AppLock";
    private static final String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();



        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        final PeriodicWorkRequest periodicWorkRequest1 = new PeriodicWorkRequest.Builder(MaxApps_MyWork.class, 24, TimeUnit.HOURS)
                .build();
        WorkManager workManager = WorkManager.getInstance(MyApplication.this);
        workManager.enqueue(periodicWorkRequest1);

        gameashes_createNotificationChannel();



    }

    private void gameashes_createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_blocker_name);
            String description = getString(R.string.app_blocker_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL, name, importance);
            channel.setDescription(description);
            channel.setShowBadge(false);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }


}
