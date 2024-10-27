package com.maxprods.applocker.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;


import com.maxprods.applocker.activities.MaxApps_SplashActivity;
import com.maxprods.applocker.R;
import com.maxprods.applocker.MyApplication;
import com.maxprods.applocker.ui.model.MaxApps_AppList;
import com.maxprods.applocker.ui.pattern.MaxApps_PatternPasscodeActivity;
import com.maxprods.applocker.ui.pin.MaxApps_PinPasscodeActivity;
import com.maxprods.applocker.ui.setting.MaxApps_PasswordSetting;
import com.maxprods.applocker.ui.util.MaxApps_GlobalMethods;
import com.maxprods.applocker.ui.util.MaxApps_MyConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class


MaxApps_MyBlockService extends Service {
    String play_fof_maxappss_foregroundApp = null;
    boolean isAlreadyOpened =false;
    String previousApp ="";
    public static final int NOTIFICATION_ID = 100;
    public static  String ScreenNAme="splash";
    public  static  Boolean FLAG1 =false;
    public  static  Boolean FLAG2 =false;

    public  static  String ENTERy ="UNCHECK1";



    public static List<MaxApps_AppList> apps =new ArrayList<>();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        maxappsashes_checkAppRunning();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            maxappsashes_displayNotification();
        }
        return START_NOT_STICKY;

    }
   
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void maxappsashes_displayNotification() {
        Intent intent = new Intent(getApplicationContext() , MaxApps_SplashActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext() , 0  , intent , PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(getApplicationContext(), MyApplication.NOTIFICATION_CHANNEL)
                .setContentTitle("App Lock")
                .setContentText("Best App Locker for Gallery.")
                .setSmallIcon(R.drawable.notification_small_icon)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(NOTIFICATION_ID, notification);
    }

    private void maxappsashes_checkAppRunning() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    String fof_apps_foregroundApp = getRecentApps();
                    stateDialog(fof_apps_foregroundApp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);


    }

    private void maxappsashes_garbageCollector() {
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }
    public String getRecentApps() {
        String topPackageName = "";
        UsageStatsManager mUsageStatsManager = (UsageStatsManager) getApplicationContext().getSystemService(Context.USAGE_STATS_SERVICE);
        long time = System.currentTimeMillis();
        UsageEvents usageEvents = mUsageStatsManager.queryEvents(time - 10000, time);
        UsageEvents.Event event = new UsageEvents.Event();
        while (usageEvents.hasNextEvent()) {
            usageEvents.getNextEvent(event);
            if (event.getEventType() == UsageEvents.Event.MOVE_TO_FOREGROUND) {
                topPackageName = event.getPackageName();
            }

        }
        Log.d("AYESHABATOOl","" + topPackageName);

        return topPackageName;
    }
    private void stateDialog(String name) {
        ArrayList<String> blockList = MaxApps_GlobalMethods.maxapps_ashes_getBlockList(getApplicationContext());


        if(!isAlreadyOpened){
            if (blockList.contains(name) && !previousApp.equals(name)){
              /*  Intent intent = new Intent();
                intent.setAction("com.key.myapplockerlightpro");
                intent.putExtra("lock" , name);
                sendBroadcast(intent);*/
                maxappsashes_maxappss_displayLocker(getApplicationContext());
                previousApp = name;
                isAlreadyOpened = true;
                Log.d("AYESHAAA ","isopn");
            }
        }else{
            if (!name.equals(getPackageName()) && !previousApp.equals(name) && !name.isEmpty()) {
                isAlreadyOpened = false;
                previousApp = name;
                Log.d("AAAAAAADIL ", previousApp);
            }
        }


    }

    void maxappsashes_maxappss_displayLocker(Context context) {
        if (MaxApps_PasswordSetting.getPasswordMode(getApplicationContext()).equals(MaxApps_MyConstant.PATTERN)) {
            Intent intent = new Intent(context, MaxApps_PatternPasscodeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS |Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NO_ANIMATION);
              context.startActivity(intent);
              Log.d("sdvdsvs","call");
            // ScreenNAme="NOAd";
        } else {
            Intent intent = new Intent(context, MaxApps_PinPasscodeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS |Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NO_ANIMATION);
            context.startActivity(intent);
            Log.d("sdvdsvs","call2");
            // ScreenNAme="NOAd";

        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loadApps();
    }

    private void maxappsashes_maxappss_stateDialog(String name) {
        if (name != null) {
            ArrayList<String> blockList = MaxApps_GlobalMethods.maxapps_ashes_getBlockList(MaxApps_MyBlockService.this);
            if (!blockList.isEmpty()) {
                for (int j = 0; j < blockList.size(); j++) {

                    if (name.equals(blockList.get(j)) && !MaxApps_GlobalMethods.maxapps_ashes_getStatusUnLockedApp(getApplicationContext())) {
                        MaxApps_GlobalMethods.maxapps_ashes_saveLastUnLockedApp(getApplicationContext(), blockList.get(j));
                        MaxApps_GlobalMethods.maxapps_ashes_saveStatusLastUnLockedApp(getApplicationContext(), false);
                        maxappsashes_maxappss_displayLocker(getApplicationContext());
                        MaxApps_GlobalMethods.maxapps_ashes_saveLastTag(this , "default");
                        return;
                    } else {
                        if (!name.equals(getPackageName()) && !name.equals(MaxApps_GlobalMethods.maxapps_ashes_getUnLockedApp(getApplicationContext()))) {
                            MaxApps_GlobalMethods.maxapps_ashes_saveStatusLastUnLockedApp(getApplicationContext(), false);
                        }
                    }

                    //play_fof_maxappss_fblocker = blockList.get(j);

                }
            }
        }
    }





    @Override
    public void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(new PlayFofBootReceiver());
        /*unregisterReceiver(new InnerReceiver());*/

    }
    private void loadApps(){
        ExecutorService installedApps = Executors.newSingleThreadExecutor();
        ExecutorService systemApps = Executors.newSingleThreadExecutor();



        installedApps.execute(new Runnable() {
            @Override
            public void run() {
                getInstalledApps();

            }
        });
        systemApps.execute(new Runnable() {
            @Override
            public void run() {
                getSystemApps();

            }
        });
    }
    public void getSystemApps(){
        try {
            List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
            for (int i = 0; i < packs.size(); i++) {
                PackageInfo p = packs.get(i);
                if ((isSystemPackage(p))) {

                    String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                    Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                    String packages = p.applicationInfo.packageName;

                    if (packages.equals(MaxApps_MyConstant.CHROME)){
                        MaxApps_MyConstant.appsList.add(new MaxApps_AppList(appName, icon, packages));
                    }else if (packages.equals(MaxApps_MyConstant.YOUTUBE)){
                        MaxApps_MyConstant.appsList.add(new MaxApps_AppList(appName, icon, packages));
                    }else if (packages.equals(MaxApps_MyConstant.PLAY_MUSIC)){
                        MaxApps_MyConstant.appsList.add(new MaxApps_AppList(appName, icon, packages));
                    }else if (packages.equals(MaxApps_MyConstant.PLAY_MOVIE)){
                        MaxApps_MyConstant.appsList.add(new MaxApps_AppList(appName, icon, packages));
                    }else if (appName.equals("Message")){
                        MaxApps_MyConstant.appsList.add(new MaxApps_AppList(appName, icon, packages));
                    }else if (appName.equals("Settings")){
                        MaxApps_MyConstant.appsList.add(new MaxApps_AppList(appName, icon, packages));
                    }else if (appName.equals("Camera")){
                        MaxApps_MyConstant.appsList.add(new MaxApps_AppList(appName, icon, packages));
                    }else if (appName.equals("Google Play Store")){
                        MaxApps_MyConstant.appsList.add(new MaxApps_AppList(appName, icon, packages));
                    }else if (appName.equals("com.sec.phone")){
                        MaxApps_MyConstant.appsList.add(new MaxApps_AppList(appName, icon, packages));
                    }else if (appName.equals("Phone")){
                        MaxApps_MyConstant.appsList.add(new MaxApps_AppList(appName, icon, packages));
                    }else if (appName.equals("Dialer")){
                        MaxApps_MyConstant.appsList.add(new MaxApps_AppList(appName, icon, packages));
                    }else if (appName.equals("Phone Manager")){
                        MaxApps_MyConstant.appsList.add(new MaxApps_AppList(appName, icon, packages));
                    }else if (appName.equals("Calculator")){
                        MaxApps_MyConstant.appsList.add(new MaxApps_AppList(appName, icon, packages));
                    }else if (appName.equals("Notes")){
                        MaxApps_MyConstant.appsList.add(new MaxApps_AppList(appName, icon, packages));
                    }else if (appName.equals("Calender")){
                        MaxApps_MyConstant.appsList.add(new MaxApps_AppList(appName, icon, packages));
                    }else if (appName.equals("Gallery")){
                        MaxApps_MyConstant.appsList.add(new MaxApps_AppList(appName, icon, packages));
                    }
                }
            }

        }catch (Exception e){

        }

    }

    public void getInstalledApps(){
        try {
            List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
            for (int i = 0; i < packs.size(); i++) {
                PackageInfo p = packs.get(i);
                if ((!isSystemPackage(p))) {
                    String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                    Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                    String packages = p.applicationInfo.packageName;
                    if (!packages.equals(getPackageName())){
                        MaxApps_MyConstant.appsList.add(new MaxApps_AppList(appName, icon, packages));
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static boolean isSystemPackage(PackageInfo pkgInfo) {
        return (pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }

}

