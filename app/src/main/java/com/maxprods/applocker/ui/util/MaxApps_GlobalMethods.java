package com.maxprods.applocker.ui.util;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.Context.MODE_PRIVATE;
import static android.os.Build.VERSION.SDK_INT;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.maxprods.applocker.services.MaxApps_MyBlockService;
import com.maxprods.applocker.ui.model.MaxApps_AppsTinyDB;
import com.maxprods.applocker.ui.model.MaxApps_AppList;
import com.maxprods.applocker.ui.model.MaxApps_Block;
import  com.maxprods.applocker.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class MaxApps_GlobalMethods {
    static int count = 0;
    static boolean isRunningTimer = false;
    static CountDownTimer timer;
    public static boolean isDialogOpen = false;
    public static boolean isAdOpen=true;

    public static List<MaxApps_AppList> maxapps_ashes_getInstalledApps(Context context) {
        //PackageManager pm = context.getPackageManager();
        List<MaxApps_AppList> apps = new ArrayList<>();
       // List<AppList> systemApps = zz_getSystemApps(context);
        List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((!maxapps_ashes_isSystemPackage(p))) {
                String appName = p.applicationInfo.loadLabel(context.getPackageManager()).toString();
                Drawable icon = p.applicationInfo.loadIcon(context.getPackageManager());
                String packages = p.applicationInfo.packageName;
                if (!packages.equals(context.getPackageName())){
                    apps.add(new MaxApps_AppList(appName, icon, packages));
                }
                //apps.add(new AppList(appName, icon, packages));
            }
        }
       // apps.addAll(systemApps);
        return apps;
    }
    public static boolean checkPermission(Context context) {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int result = ContextCompat.checkSelfPermission(context, READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
        }
    }
    public static boolean canDrawOverlays(Context context) {
        if (SDK_INT < Build.VERSION_CODES.M) return true;
        else if (SDK_INT >= Build.VERSION_CODES.O_MR1) {
            return Settings.canDrawOverlays(context);
        } else {
            if (Settings.canDrawOverlays(context)) return true;
            try {
                WindowManager mgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                if (mgr == null) return false; //getSystemService might return null
                View viewToAdd = new View(context);
                WindowManager.LayoutParams params = new WindowManager.LayoutParams(0, 0, SDK_INT >= Build.VERSION_CODES.O ?
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY : WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSPARENT);
                viewToAdd.setLayoutParams(params);
                mgr.addView(viewToAdd, params);
                mgr.removeView(viewToAdd);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }
    public static List<MaxApps_AppList> zz_getSystemApps(Context context){
        List<MaxApps_AppList> apps = new ArrayList<>();
        List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);
        //List<PackageInfo> packs = getPackageManager().getInstalledPackages(PackageManager.GET_PERMISSIONS);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((maxapps_ashes_isSystemPackage(p))) {

                String appName = p.applicationInfo.loadLabel(context.getPackageManager()).toString();
                Drawable icon = p.applicationInfo.loadIcon(context.getPackageManager());
                String packages = p.applicationInfo.packageName;
                //apps.add(new AppList(appName, icon, packages));
                Log.d("ADIL_KHAN" , appName);

                if (packages.equals(MaxApps_MyConstant.CHROME)){
                    apps.add(new MaxApps_AppList(appName, icon, packages));
                }else if (packages.equals(MaxApps_MyConstant.YOUTUBE)){
                    apps.add(new MaxApps_AppList(appName, icon, packages));
                }else if (packages.equals(MaxApps_MyConstant.PLAY_MUSIC)){
                    apps.add(new MaxApps_AppList(appName, icon, packages));
                }else if (packages.equals(MaxApps_MyConstant.PLAY_MOVIE)){
                    apps.add(new MaxApps_AppList(appName, icon, packages));
                }else if (appName.equals("Message")){
                    apps.add(new MaxApps_AppList(appName, icon, packages));
                }else if (appName.equals("Settings")){
                    apps.add(new MaxApps_AppList(appName, icon, packages));
                }else if (appName.equals("Camera")){
                    apps.add(new MaxApps_AppList(appName, icon, packages));
                }else if (appName.equals("Google Play Store")){
                    apps.add(new MaxApps_AppList(appName, icon, packages));
                }else if (appName.equals("com.sec.phone")){
                    apps.add(new MaxApps_AppList(appName, icon, packages));
                }else if (appName.equals("Phone")){
                    apps.add(new MaxApps_AppList(appName, icon, packages));
                }else if (appName.equals("Dialer")){
                    apps.add(new MaxApps_AppList(appName, icon, packages));
                }else if (appName.equals("Phone Manager")){
                    apps.add(new MaxApps_AppList(appName, icon, packages));
                }else if (appName.equals("Calculator")){
                    apps.add(new MaxApps_AppList(appName, icon, packages));
                }else if (appName.equals("Notes")){
                    apps.add(new MaxApps_AppList(appName, icon, packages));
                }else if (appName.equals("Calender")){
                    apps.add(new MaxApps_AppList(appName, icon, packages));
                }else if (appName.equals("Gallery")){
                    apps.add(new MaxApps_AppList(appName, icon, packages));
                }
            }
        }
        return apps;
    }

    private static boolean maxapps_ashes_isSystemPackage(PackageInfo pkgInfo) {
        return (pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }

    public static ArrayList<String> maxapps_ashes_getBlockList(Context context){
        MaxApps_AppsTinyDB tinyDB = new MaxApps_AppsTinyDB(context);
        return tinyDB.getListString(MaxApps_MyConstant.LOCKED);

    }

    public static ArrayList<Object> getObjBlockList(Context context){
        MaxApps_AppsTinyDB tinyDB = new MaxApps_AppsTinyDB(context);
        return tinyDB.getListObject(MaxApps_MyConstant.LOCKED , MaxApps_Block.class);
    }
    public static void addObjAppToBlock(Context context , Object object){
        MaxApps_AppsTinyDB tinyDB = new MaxApps_AppsTinyDB(context);
        ArrayList<Object> listData = new ArrayList<>();
        ArrayList<Object> list =  tinyDB.getListObject(MaxApps_MyConstant.LOCKED , MaxApps_Block.class);

        if (list.isEmpty()){
            listData.add(object);
            tinyDB.putObject(MaxApps_MyConstant.LOCKED , listData);

        }else{
            listData.add(object);
            list.addAll(listData);
            tinyDB.putObject(MaxApps_MyConstant.LOCKED , list);

        }


    }
    public static void maxapps_ashes_addAppToBlock(Context context , String app){
        MaxApps_AppsTinyDB tinyDB = new MaxApps_AppsTinyDB(context);
        ArrayList<String> listData = new ArrayList<>();
        ArrayList<String> list = tinyDB.getListString(MaxApps_MyConstant.LOCKED);

        if (list.isEmpty()){
            listData.add(app);
            tinyDB.putListString(MaxApps_MyConstant.LOCKED , listData);

        }else{
            listData.add(app);
            list.addAll(listData);
            tinyDB.putListString(MaxApps_MyConstant.LOCKED , list);

        }


    }

    public static void maxapps_ashes_removeAppFromBlock(Context context , String packageName){
        MaxApps_AppsTinyDB tinyDB = new MaxApps_AppsTinyDB(context);
        ArrayList<String> list = tinyDB.getListString(MaxApps_MyConstant.LOCKED);

        if (!list.isEmpty()) {
           // list.size();
            try{
                for (int i = 0; i < list.size(); i++){
                    if (list.get(i).equals(packageName)){
                        list.remove(i);
                        tinyDB.putListString(MaxApps_MyConstant.LOCKED, list);
                        return;
                    }
                }



            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }

        }

    }

    public static boolean isAppRunning(final Context context, final String packageName) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
        if (procInfos != null)
        {
            for (final ActivityManager.RunningAppProcessInfo processInfo : procInfos) {
                if (processInfo.processName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void requestUsageStatsPermission(Context context) {
        if(!hasUsageStatsPermission(context)) {
            context.startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        }
    }

    public static void maxapps_ashes_appOverlayPermissionDialog(Context context){
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.maxapps_ashes_app_overlay_permission);


        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.allow);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + context.getPackageName()));
                        context.startActivity(intent);                    }
                },600);

                dialog.dismiss();
            }
        });

        dialog.show();



    }

    public static void maxapps_ashes_usageStatePermissionDialog(Context context){
        final Dialog dialog = new Dialog(context);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.maxapps_ashes_usagestate_permission);

        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.allow);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MaxApps_GlobalMethods.requestUsageStatsPermission(context);
                    }
                },600);

                dialog.dismiss();
            }
        });

        dialog.show();



    }
    public  void maxapps_ashesmanageFileDialog(Context context){

        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.maxapps_ashes_filemanage_permission);


        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.allow);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                            intent.addCategory("android.intent.category.DEFAULT");
                            intent.setData(Uri.parse(String.format("package:%s",context.getPackageName())));
                            context.startActivity(intent);
                        } catch (Exception e) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                            context.startActivity(intent);
                        }                    }
                },600);

                dialog.dismiss();
            }
        });
        dialog.show();

    }
    public static boolean hasUsageStatsPermission(Context context) {
        AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow("android:get_usage_stats",
                android.os.Process.myUid(), context.getPackageName());
        return mode == AppOpsManager.MODE_ALLOWED;
    }
    public static String printForegroundTask(Context context) {
        String currentApp = "NULL";
        UsageStatsManager usm = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            usm = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        }
        long time = System.currentTimeMillis();
        List<UsageStats> appList = null;
        if (usm != null) {
            appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,  time - 1000*1000, time);
        }
        if (appList != null && appList.size() > 0) {
            SortedMap<Long, UsageStats> mySortedMap = new TreeMap<>();
            for (UsageStats usageStats : appList) {
                mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
            }
            if (!mySortedMap.isEmpty()) {
                currentApp = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
            }
        }

        Log.d("adil", "Current App in foreground is: " + currentApp);
        return currentApp;
    }


    public static void maxapps_ashes_saveLastUnLockedApp(Context context , String packageName){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putString(MaxApps_MyConstant.UNLOCK_APP , packageName)
                .apply();

    }

    public static String maxapps_ashes_getUnLockedApp(Context context){
        return context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getString(MaxApps_MyConstant.UNLOCK_APP , "com.adil");
    }


    public static void maxapps_ashes_saveStatusLastUnLockedApp(Context context , boolean status){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putBoolean(MaxApps_MyConstant.STATUS , status)
                .apply();

    }

    public static boolean maxapps_ashes_getStatusUnLockedApp(Context context){
        return context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getBoolean(MaxApps_MyConstant.STATUS , false);
    }

    public static void maxapps_ashes_saveQuestion(Context context , String question){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putString(MaxApps_MyConstant.SECURITY_QUESTION , question)
                .apply();
    }
    public static String maxapps_ashes_getSaveQuestion(Context context){
        return context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getString(MaxApps_MyConstant.SECURITY_QUESTION , null);
    }

    public static void maxapps_ashes_saveQuestionAnswer(Context context , String answer){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putString(MaxApps_MyConstant.SECURITY_ANSWER , answer)
                .apply();
    }
    public static String maxapps_ashes_getQuestionAnswer(Context context){
        return context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getString(MaxApps_MyConstant.SECURITY_ANSWER , null);
    }

    public static void maxapps_ashes_saveSecurityEmail(Context context , String email){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putString(MaxApps_MyConstant.SECURITY_EMAIL , email)
                .apply();
    }

    public static String maxapps_ashes_getSecurityEmail(Context context){
        return context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getString(MaxApps_MyConstant.SECURITY_EMAIL , null);
    }

    public static void maxapps_ashes_RateUs(Context context){
        try{
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+context.getPackageName())));
        }
        catch (ActivityNotFoundException e){
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+context.getPackageName())));
        }
    }

    public static void saveUnlockFeature(Context context , boolean feature){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putBoolean(MaxApps_MyConstant.UNLOCK_FEATURES , feature)
                .apply();
    }

    public static boolean maxapps_ashes_getUnLockFeatures(Context context){
        return context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getBoolean(MaxApps_MyConstant.UNLOCK_FEATURES , false);
    }

    public static void saveTheme(Context context , int theme){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putInt(MaxApps_MyConstant.THEME , theme)
                .apply();
    }

    public static int maxapps_ashes_getTheme(Context context){
        return context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getInt(MaxApps_MyConstant.THEME , R.drawable.fofapps2_gradient_default);
    }

    public static void maxapps_ashes_saveLastTag(Context context , String tag){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putString("tag" , tag)
                .apply();

    }

    public static String maxapps_ashes_getLastTag(Context context){
        return context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getString("tag" , "default");
    }

    public static void displayWatchDialog(Activity context , Intent intent){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.maxapps_watch_dialog);

    }


    public static void maxapps_ashessaveRequestMode(Context context , String modeRequest){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putString(MaxApps_MyConstant.REQUEST_MODE , modeRequest)
                .apply();

    }

    public static String maxapps_ashesgetRequestMode(Context context){
        return context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getString(MaxApps_MyConstant.REQUEST_MODE , MaxApps_MyConstant.DEFAULT_REQUEST);
    }



public static void saveData(Context context,boolean bp){
    context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
            .edit()
            .putBoolean(MaxApps_MyConstant.MYPLAN ,bp )
            .apply();

}
    public static void maxapps_ashessubscribe(Context context , boolean subscribe){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putBoolean(MaxApps_MyConstant.SUBSCRIBE , subscribe)
                .apply();
    }
    public static boolean maxapps_ashes_getSubscribe(Context context){
        return context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getBoolean(MaxApps_MyConstant.SUBSCRIBE , false);
    }
    public static boolean maxapps_ashes_getData(Context context){
        return  context.getSharedPreferences(MaxApps_MyConstant.PREF,Context.MODE_PRIVATE)
                .getBoolean(MaxApps_MyConstant.MYPLAN,false);


    }
    public static void maxapps_ashes_saveOneTimeRating(Context context, Boolean flag){
         context.getSharedPreferences(MaxApps_MyConstant.MyPREF,context.MODE_PRIVATE)
                .edit()
                .putBoolean(MaxApps_MyConstant.ONE_RATING,flag)
                .apply();

    }
    public static boolean maxapps_ashes_getOneTimeRating(Context context){
       return context.getSharedPreferences(MaxApps_MyConstant.MyPREF,context.MODE_PRIVATE)
               .getBoolean(MaxApps_MyConstant.ONE_RATING,false);

    }
    public static void maxapps_ashes_saveRating(Context context, Boolean flag){
        context.getSharedPreferences(MaxApps_MyConstant.MyPREF,context.MODE_PRIVATE)
                .edit()
                .putBoolean(MaxApps_MyConstant.RATING,flag)
                .apply();

    }
    public static boolean maxapps_ashes_getRating(Context context){
        return context.getSharedPreferences(MaxApps_MyConstant.MyPREF,context.MODE_PRIVATE)
                .getBoolean(MaxApps_MyConstant.RATING,false);

    }
    public static void OnceFingerPrintSet(Context context, Boolean flag){
        context.getSharedPreferences(MaxApps_MyConstant.MyPREF,context.MODE_PRIVATE)
                .edit()
                .putBoolean(MaxApps_MyConstant.finger_once,flag)
                .apply();

    }
    public static boolean getFingerPrint(Context context){
        return context.getSharedPreferences(MaxApps_MyConstant.MyPREF,context.MODE_PRIVATE)
                .getBoolean(MaxApps_MyConstant.finger_once,false);

    }
    public static void maxapps_ashes_bottomShieldDialogue(Context context){

        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(context,R.style.TransparentDialog);

        //bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        bottomSheetDialog.setContentView(R.layout.maxapps_bottomshield_dialog);
       // Window window = bottomSheetDialog.getWindow();
        //bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

       // window.setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        ImageView unselect1 =bottomSheetDialog.findViewById(R.id.unselcet1);
        ImageView unselect2 =bottomSheetDialog.findViewById(R.id.unselect2);
        ImageView select =bottomSheetDialog.findViewById(R.id.select1);
        ImageView select2 =bottomSheetDialog.findViewById(R.id.select2);
        if (MaxApps_GlobalMethods.hasUsageStatsPermission(context)) {

                select.setVisibility(View.VISIBLE);
                unselect1.setVisibility(View.GONE);


            // GameAshes2_GlobalMethods.play_fof_usageStatePermissionDialog(context);

        }
        else {
            select.setVisibility(View.GONE);
            unselect1.setVisibility(View.VISIBLE);

        }
        unselect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isDialogOpen = false;
                 // GameAshes2MyBlockService.FLAG1=true;
                if (!MaxApps_GlobalMethods.hasUsageStatsPermission(context)) {
                    if(!hasUsageStatsPermission(context)) {
                        context.startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
                    }
                    // GameAshes2_GlobalMethods.play_fof_usageStatePermissionDialog(context);

                }
                else {
                    select.setVisibility(View.VISIBLE);
                    unselect1.setVisibility(View.GONE);

                }
                bottomSheetDialog.dismiss();
            }
        });
        if (MaxApps_GlobalMethods.canDrawOverlays(context)) {

            select2.setVisibility(View.VISIBLE);
            unselect2.setVisibility(View.GONE);


            // GameAshes2_GlobalMethods.play_fof_usageStatePermissionDialog(context);

        }
        else {
            select2.setVisibility(View.GONE);
            unselect2.setVisibility(View.VISIBLE);

        }
        unselect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isDialogOpen = false;
                MaxApps_MyBlockService.FLAG2=true;

                if (!MaxApps_GlobalMethods.canDrawOverlays(context)) {

                    unselect2.setVisibility(View.VISIBLE);
                    select2.setVisibility(View.GONE);
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + context.getPackageName()));
                    context.startActivity(intent);
                    // GameAshes2_GlobalMethods.play_fof_appOverlayPermissionDialog(context);


                }
                else {
                    unselect2.setVisibility(View.GONE);
                    select2.setVisibility(View.VISIBLE);
                }

                bottomSheetDialog.dismiss();
            }
        });
        if (!isDialogOpen){
            bottomSheetDialog.show();
        }
        isDialogOpen = true;
      bottomSheetDialog.setCancelable(false);
        SharedPreferences prefs = context.getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();

    }


}
