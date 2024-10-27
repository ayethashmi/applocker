package com.maxprods.applocker.ui.themes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import  com.maxprods.applocker.R;
import com.maxprods.applocker.databinding.MaxappsActivityWatchApplyThemeBinding;
import com.maxprods.applocker.services.MaxApps_MyBlockService;
import com.maxprods.applocker.ui.setting.MaxApps_PasswordSetting;
import com.maxprods.applocker.ui.util.MaxApps_MyConstant;

import java.lang.ref.WeakReference;

public class MaxApps_WatchApplyThemeActivity extends AppCompatActivity {
    private MaxappsActivityWatchApplyThemeBinding maxapps_ashes_binding;
    private WeakReference<Activity> weakReference;

 private Boolean FLAG=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        maxapps_ashes_binding = DataBindingUtil.setContentView(this, R.layout.maxapps_activity_watch_apply_theme);
        weakReference=new WeakReference<>(MaxApps_WatchApplyThemeActivity.this);


        maxapps_ashes_eventHandling();


    }



        private void maxapps_ashes_savePasswordMode(String name){
        if (name.equals("Theme_01.webp")){
            MaxApps_PasswordSetting.savePasswordMode(getApplicationContext() , MaxApps_MyConstant.PATTERN);
        }else if (name.equals("Theme_02.webp")){
            MaxApps_PasswordSetting.savePasswordMode(getApplicationContext() , MaxApps_MyConstant.PATTERN);
        }else if (name.equals("Theme_03.webp")){
            MaxApps_PasswordSetting.savePasswordMode(getApplicationContext() , MaxApps_MyConstant.PATTERN);
        }else if (name.equals("Theme_04.webp")){
            MaxApps_PasswordSetting.savePasswordMode(getApplicationContext() , MaxApps_MyConstant.PATTERN);
        }else if (name.equals("Theme_05.webp")){
            MaxApps_PasswordSetting.savePasswordMode(getApplicationContext() , MaxApps_MyConstant.PATTERN);
        }else if (name.equals("Theme_06.webp")){
            MaxApps_PasswordSetting.savePasswordMode(getApplicationContext() , MaxApps_MyConstant.PATTERN);
        }else if (name.equals("Theme_07.webp")){
            MaxApps_PasswordSetting.savePasswordMode(getApplicationContext() , MaxApps_MyConstant.PATTERN);
        }else if (name.equals("Theme_08.webp")){
            MaxApps_PasswordSetting.savePasswordMode(getApplicationContext() , MaxApps_MyConstant.PATTERN);
        }else if (name.equals("Theme_09.webp")){
            MaxApps_PasswordSetting.savePasswordMode(getApplicationContext() , MaxApps_MyConstant.PATTERN);
        }else if (name.equals("Theme_10.webp")){
            MaxApps_PasswordSetting.savePasswordMode(getApplicationContext() , MaxApps_MyConstant.PATTERN);
        }else if (name.equals("Theme_11.webp")){
            MaxApps_PasswordSetting.savePasswordMode(getApplicationContext() , MaxApps_MyConstant.PATTERN);
        }else if (name.equals("Theme_12.webp")){
            MaxApps_PasswordSetting.savePasswordMode(getApplicationContext() , MaxApps_MyConstant.PATTERN);
        }else if (name.equals("Theme_13.webp")){
            MaxApps_PasswordSetting.savePasswordMode(getApplicationContext() , MaxApps_MyConstant.PATTERN);
        }else if (name.equals("Theme_14.webp")){
            MaxApps_PasswordSetting.savePasswordMode(getApplicationContext() , MaxApps_MyConstant.PATTERN);
        }else if (name.equals("Theme_15.webp")){
            MaxApps_PasswordSetting.savePasswordMode(getApplicationContext() , MaxApps_MyConstant.PATTERN);
        }else if (name.equals("Theme_16.webp")){
            MaxApps_PasswordSetting.savePasswordMode(getApplicationContext() , MaxApps_MyConstant.PATTERN);
        }else{
            MaxApps_PasswordSetting.savePasswordMode(getApplicationContext() , MaxApps_MyConstant.PIN);
        }
    }
    private void maxapps_ashes_eventHandling(){
        maxapps_ashes_binding.imageViewCancel.setOnClickListener(v->{
            onBackPressed();
        });




        maxapps_ashes_binding.buttonWatchNow.setOnClickListener(v->{
            String name = getIntent().getStringExtra("name");

                MaxApps_ThemeUtil.maxappsashes_saveThemeName(getApplicationContext() , name);
                finish();
                    Intent intent = new Intent(getApplicationContext(), MaxApps_ThemesActivity.class);
                    startActivity(intent);


        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        MaxApps_MyBlockService.ScreenNAme="main";

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}