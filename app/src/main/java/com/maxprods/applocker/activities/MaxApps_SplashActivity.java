package com.maxprods.applocker.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.maxprods.applocker.databinding.MaxappsActivitySplashBinding;
import com.maxprods.applocker.services.MaxApps_MyBlockService;
import com.maxprods.applocker.ui.pattern.MaxApps_PatternPasscodeActivity;
import com.maxprods.applocker.ui.pattern.MaxApps_PatternUtil;
import com.maxprods.applocker.ui.pin.MaxApps_PinPasscodeActivity;
import com.maxprods.applocker.ui.pin.MaxApps_PinUtil;
import com.maxprods.applocker.ui.util.MaxApps_MyConstant;
import com.maxprods.applocker.R;

import java.io.File;
import java.lang.ref.WeakReference;


@SuppressLint("CustomSplashScreen")
public class MaxApps_SplashActivity extends AppCompatActivity  {
    private WeakReference<Activity> weakReference;

    private MaxappsActivitySplashBinding binding;

    CountDownTimer time;
    private int lastCount = 0;
    int count=0;
    private boolean mFlag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        binding = DataBindingUtil.setContentView(this, R.layout.maxapps_activity_splash);
        weakReference=new WeakReference<>(MaxApps_SplashActivity.this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
      MaxApps_MyBlockService.ScreenNAme="NOAd";
        eventHandling();
        startMyService();
        makeFolderIntruderSelfie();

    }

    private void eventHandling(){

        binding.buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("kkkkkkkk", "Splash");
//                binding.loadAnimLayout.setVisibility(View.VISIBLE);
//                binding.main.setVisibility(View.GONE);
//                maxappsashes_splash();
                goMain();
            }
        });


        }

    private void maxappsashes_splash() {
       time = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                count++;
//                if (!mFlag){
//                    lastCount = count;
//                    mFlag = true;
//                }
//
//                if (lastCount + 2 == count){
                    time.cancel();
                    time.onFinish();
//                }


            }

                  @Override
            public void onFinish() {
                      goMain();


            }
        }.start();

    }


    private void goMain(){
        Log.d("kkkkkkkk", "" + MaxApps_PatternUtil.getPasswordMode(getApplicationContext()));
        if (MaxApps_PatternUtil.getPasswordMode(getApplicationContext()).equals(MaxApps_MyConstant.PATTERN)) {
            Log.d("kkkkkkkk", "   1");

            if (MaxApps_PatternUtil.getPatternPassword(getApplicationContext()) == null) {
                Log.d("kkkkkkkk", "   2");
                Intent intent = new Intent(getApplicationContext(), MaxApps_PatternPasscodeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

            } else {
                Log.d("kkkkkkkk", "   3");
                Intent intent = new Intent(getApplicationContext(), MaxApps_PatternPasscodeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("tag", "splash");
                startActivity(intent);
            }
        } else {
            Log.d("kkkkkkkk", "   4");
            if (MaxApps_PinUtil.play_fof_maxappss_getPinPassword(getApplicationContext()) == null) {
                Log.d("kkkkkkkk", "   5");
                Intent intent = new Intent(getApplicationContext(), MaxApps_PinPasscodeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

            } else {
                Log.d("kkkkkkkk", "   6");
                Intent intent = new Intent(getApplicationContext(), MaxApps_PinPasscodeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("tag", "splash");
                startActivity(intent);
            }

        }
    }

    private void makeFolder(){
        File createRootDirectory = new File(getExternalFilesDir(MaxApps_MyConstant.ROOT_FOLDER), MaxApps_MyConstant.SUBDIRECTORY_FOLDER);
        File imageDirectory = new File(getExternalFilesDir(MaxApps_MyConstant.IMAGES_PATH), MaxApps_MyConstant.PHOTO);
        File videDirectory = new File(getExternalFilesDir(MaxApps_MyConstant.VIDEOS_PATH) , MaxApps_MyConstant.VIDEO);
        File audioDirectory = new File(getExternalFilesDir(MaxApps_MyConstant.AUDIO_PATH) , MaxApps_MyConstant.AUDIO);
        File fileDirectory = new File(getExternalFilesDir(MaxApps_MyConstant.FILE_PATH) , MaxApps_MyConstant.FILE);

        if (!createRootDirectory.exists()){
            createRootDirectory.mkdirs();

        }
       if (!imageDirectory.exists()){
            imageDirectory.mkdirs();
        }
        if (!videDirectory.exists()){
            videDirectory.mkdirs();
        }
        if (!audioDirectory.exists()){
            audioDirectory.mkdirs();
        }
        if (!fileDirectory.exists()){
            fileDirectory.mkdirs();
        }


    }




    @Override
    protected void onResume() {
        super.onResume();

        startMyService();
        MaxApps_MyConstant.firsttime=true;
      makeFolder();

        MaxApps_MyBlockService.ScreenNAme="NOAd";

    }
    private void startMyService() {
        try {
            Intent intent = new Intent(getApplicationContext(), MaxApps_MyBlockService.class);

            if (!checkServiceRunning(MaxApps_MyBlockService.class)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Log.d("TAG", "Service Started");
                    startForegroundService(intent);
                } else {
                    Log.d("TAG", "Service Stopped");
                    startService(intent);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private boolean checkServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    private void makeFolderIntruderSelfie(){
        File file=new File(MaxApps_MyConstant.DOWNLOAD_PATH);
        boolean Success=file.exists();
        if (!Success){
            file.mkdir();

        }else {
            Log.d("TAG", "Folder name alreay exist");

        }
        Log.d("TAG", "Folder name"+file);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}