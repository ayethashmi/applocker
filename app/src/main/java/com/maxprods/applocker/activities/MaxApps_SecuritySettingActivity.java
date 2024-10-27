package com.maxprods.applocker.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.maxprods.applocker.databinding.MaxappsActivitySecuritySettingBinding;
import com.maxprods.applocker.services.MaxApps_MyBlockService;
import com.maxprods.applocker.ui.util.MaxApps_MyConstant;
import com.maxprods.applocker.R;

import java.lang.ref.WeakReference;

public class MaxApps_SecuritySettingActivity extends AppCompatActivity   {
    private MaxappsActivitySecuritySettingBinding play_fof_binding;
    private WeakReference<Activity> weakReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        play_fof_binding = DataBindingUtil.setContentView(this , R.layout.maxapps_activity_security_setting);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            weakReference=new WeakReference<>(MaxApps_SecuritySettingActivity.this);



        maxapps_ashes_eventHandling();

    }

 private void maxapps_ashes_eventHandling(){
        play_fof_binding.buttonQuestionSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String securityQuestion = play_fof_binding.inputSecurityQuestion.getText().toString();
                String securityAnswer = play_fof_binding.inputSecurityAnswer.getText().toString();

                if (TextUtils.isEmpty(securityQuestion) && TextUtils.isEmpty(securityAnswer)){
                    Toast.makeText(getApplicationContext() , "Required Security Fields" , Toast.LENGTH_LONG).show();
                }else{
                    maxapps_ashes_saveQuestion( securityQuestion);
                   maxapps_ashes_saveQuestionAnswer( securityAnswer);

                    Toast.makeText(getApplicationContext() , "Saved" , Toast.LENGTH_LONG).show();

                    /*if (IronSource.isInterstitialReady()){
                        IronSource.showInterstitial();
                    }*/
                }
            }
        });

        play_fof_binding.buttonEmailSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String securityEmail = play_fof_binding.inputSecurityEmail.getText().toString();
                if (TextUtils.isEmpty(securityEmail)){
                    Toast.makeText(getApplicationContext() , "Required Email Field" , Toast.LENGTH_LONG).show();
                }else{
                  maxapps_ashes_saveSecurityEmail( securityEmail);
                    Toast.makeText(getApplicationContext() , "Saved" , Toast.LENGTH_LONG).show();


                    /*if (IronSource.isInterstitialReady()){
                        IronSource.showInterstitial();
                    }*/
                }
            }
        });

        play_fof_binding.back.setOnClickListener(v->{
            onBackPressed();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        MaxApps_MyBlockService.ScreenNAme="main";
        MaxApps_MyConstant.firsttime=true;

        maxappsashes_bindData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
/*
        AdManager.SecuritySetting_mInterstitialAd=null;
*/
        //IronSource.destroyBanner(banner);
    }
    private void maxappsashes_bindData(){
        if (maxapps_ashes_getSecurityEmail() != null){
            play_fof_binding.inputSecurityEmail.setText(maxapps_ashes_getSecurityEmail());
        }
        if (maxapps_ashes_getQuestionAnswer() != null){
            play_fof_binding.inputSecurityQuestion.setText(maxapps_ashes_getSaveQuestion());
            play_fof_binding.inputSecurityAnswer.setText(maxapps_ashes_getQuestionAnswer());
        }
    }
    public  void maxapps_ashes_saveQuestion( String question){
       getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putString(MaxApps_MyConstant.SECURITY_QUESTION , question)
                .apply();
    }
    public  void maxapps_ashes_saveQuestionAnswer( String answer){
  getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putString(MaxApps_MyConstant.SECURITY_ANSWER , answer)
                .apply();
    }
    public  void maxapps_ashes_saveSecurityEmail( String email){
       getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putString(MaxApps_MyConstant.SECURITY_EMAIL , email)
                .apply();
    }
    public  String maxapps_ashes_getSecurityEmail(){
        return getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getString(MaxApps_MyConstant.SECURITY_EMAIL , null);
    }
    public  String maxapps_ashes_getQuestionAnswer(){
        return getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getString(MaxApps_MyConstant.SECURITY_ANSWER , null);
    }
    public  String maxapps_ashes_getSaveQuestion(){
        return getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .getString(MaxApps_MyConstant.SECURITY_QUESTION , null);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}