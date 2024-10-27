package com.maxprods.applocker.ui.themes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;


import com.maxprods.applocker.databinding.MaxappsActivityApplyThemeBinding;
import com.maxprods.applocker.services.MaxApps_MyBlockService;
import com.bumptech.glide.Glide;


import com.maxprods.applocker.activities.MaxApps_CongratulationActivity;
import  com.maxprods.applocker.R;

public class MaxApps_ApplyThemeActivity extends AppCompatActivity {
    private MaxappsActivityApplyThemeBinding play_fof_maxappss_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        play_fof_maxappss_binding = DataBindingUtil.setContentView(this , R.layout.maxapps_activity_apply_theme);
        //AdManager.loadInterstitialMultiTime(getApplicationContext());


        maxappsashes_bindPhoto();
        maxappsashes_eventHandling();


    }


    private void maxappsashes_eventHandling(){
        play_fof_maxappss_binding.buttonApplyTheme.setOnClickListener(v->{
            String name = getIntent().getStringExtra("name");
            //loadInterstitial();
            Intent intent = new Intent(getApplicationContext() , MaxApps_WatchApplyThemeActivity.class);
            intent.putExtra("name" , name);
            intent.putExtra("message" , "Congratulation, Theme Applied");
           // AdManager.showInterstitialDialog(intent,this);
            startActivity(intent);


        });
    }


    private void maxappsashes_bindPhoto() {
        String path = getIntent().getStringExtra("path");
        Glide.with(getApplicationContext()).load(path).into(play_fof_maxappss_binding.displayImage);
    }
    private void congratulationDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
        alert.setTitle("Theme Applied");
        alert.setMessage("Congratulation, Lock has been changed.");
        alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alert.setCancelable(false);
        alert.show();
    }
    private void goCongratulationActivity(){
        Intent intent = new Intent(getApplicationContext() , MaxApps_CongratulationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("message" , "Congratulation, Theme Applied");
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MaxApps_MyBlockService.ScreenNAme="main";

    }





    }


