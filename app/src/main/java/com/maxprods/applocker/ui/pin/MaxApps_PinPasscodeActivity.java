package com.maxprods.applocker.ui.pin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.maxprods.applocker.activities.MaxApps_MainActivity;
import com.maxprods.applocker.databinding.MaxappsActivityPinPasscodeBinding;
import com.maxprods.applocker.services.MaxApps_MyBlockService;

import com.maxprods.applocker.ui.fragments.pin.MaxApps_PinHelper;
import com.maxprods.applocker.ui.setting.MaxApps_PasswordSetting;
import com.bumptech.glide.Glide;


import com.maxprods.applocker.activities.MaxApps_UnlockSettingActivity;
import  com.maxprods.applocker.R;
import com.maxprods.applocker.ui.pattern.MaxApps_PatternUtil;
import com.maxprods.applocker.ui.themes.MaxApps_ThemeUtil;
import com.maxprods.applocker.ui.util.MaxApps_GlobalMethods;
import com.maxprods.applocker.ui.util.MaxApps_MyConstant;


import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collections;

import javax.crypto.Cipher;

public class MaxApps_PinPasscodeActivity extends AppCompatActivity {
    private int counter = 15;
    private boolean lockButton = false;
    private MaxappsActivityPinPasscodeBinding play_fof_maxappss_binding;

    private String play_fof_maxappss_ag = "";

    private String play_fof_maxappss_setPassword = "";
    private String play_fof_maxappss_confirmPassword = "";
    private boolean play_fof_maxappss_confirmFlag = false;
    private ImageView highlightone,getHighlightboth;



    private  final String fTAG = MaxApps_PinPasscodeActivity.class.getName();

    String tag = "default";

    public  final int MEDIA_TYPE_VIDEO = 2;
    public  final String TAG = "CameraUtil";
    public  final int MEDIA_TYPE_IMAGE = 1;
    private int wrongPasswordCounter = 0;
    private WeakReference<Activity> passwordActivityWeakReference;
    private int attempts = 0;
    private FingerprintManager mFingerprintManager = null;
    private KeyStore mKeyStore;
    private Cipher mCipher;
    private static final String KEY_NAME = "AppsTrendMediaApps";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        play_fof_maxappss_binding = DataBindingUtil.setContentView(this , R.layout.maxapps_activity_pin_passcode);
        Log.d("kkkkkkkk", "   10");
        highlightone=findViewById(R.id.highlighone);
        getHighlightboth=findViewById(R.id.highlighBoth);
        passwordActivityWeakReference=new WeakReference<>(MaxApps_PinPasscodeActivity.this);
        maxappsashes_setupPin();
        maxappsashes_setupButtonTags();
        maxappsashes_applyBackgroundTheme();

    }


    //-------------------------------------------- Fingerprint ------------------------------//


    private void maxappsashes_setupButtonTags(){
        if (MaxApps_PinUtil.play_fof_maxappss_getShuffle(getApplicationContext())){
            ArrayList<String> list = play_fof_maxappss_getShuffleButtons();
            play_fof_maxappss_binding.buttonZero.setText(list.get(0));
            play_fof_maxappss_binding.buttonZero.setTag(list.get(0));

            play_fof_maxappss_binding.buttonOne.setText(list.get(1));
            play_fof_maxappss_binding.buttonOne.setTag(list.get(1));

            play_fof_maxappss_binding.buttonTwo.setText(list.get(2));
            play_fof_maxappss_binding.buttonTwo.setTag(list.get(2));

            play_fof_maxappss_binding.buttonThree.setText(list.get(3));
            play_fof_maxappss_binding.buttonThree.setTag(list.get(3));

            play_fof_maxappss_binding.buttonFour.setText(list.get(4));
            play_fof_maxappss_binding.buttonFour.setTag(list.get(4));

            play_fof_maxappss_binding.buttonFive.setText(list.get(5));
            play_fof_maxappss_binding.buttonFive.setTag(list.get(5));

            play_fof_maxappss_binding.buttonSix.setText(list.get(6));
            play_fof_maxappss_binding.buttonSix.setTag(list.get(6));

            play_fof_maxappss_binding.buttonSeven.setText(list.get(7));
            play_fof_maxappss_binding.buttonSeven.setTag(list.get(7));

            play_fof_maxappss_binding.buttonEight.setText(list.get(8));
            play_fof_maxappss_binding.buttonEight.setTag(list.get(8));

            play_fof_maxappss_binding.buttonNine.setText(list.get(9));
            play_fof_maxappss_binding.buttonNine.setTag(list.get(9));
        }




    }
    private ArrayList<String> play_fof_maxappss_getShuffleButtons(){
        ArrayList<String> buttons = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            buttons.add(String.valueOf(i));
        }
        Collections.shuffle(buttons);
        return buttons;
    }

    private void play_fof_maxappss_displayDialog() {
        maxappsAshes_vibrate();
        play_fof_maxappss_shakeAnimation();

    }

    private void play_fof_maxappss_shakeAnimation() {
        Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_view);
        play_fof_maxappss_binding.dotLayout.startAnimation(shake);
    }
    private void maxappsashes_applyBackgroundTheme(){

        if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()) != null){
            //fbinding.parentView.setBackgroundResource(GlobalMethods.getTheme(this));
            if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_01.webp")){
                String buttonPath = "pin/" + "pin01.webp";
                fofapps_changeButtonShape(buttonPath);
                String completePath = "file:///android_asset/background/" + "bg_01.webp";
                Glide.with(getApplicationContext()).load(completePath).into(play_fof_maxappss_binding.lockBackground);
            }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_02.webp")){
                String buttonPath = "pin/" + "pin02.webp";
                fofapps_changeButtonShape(buttonPath);
                String completePath = "file:///android_asset/background/" + "bg_02.webp";
                Glide.with(getApplicationContext()).load(completePath).into(play_fof_maxappss_binding.lockBackground);
            }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_03.webp")){
                String buttonPath = "pin/" + "pin03.webp";
                fofapps_changeButtonShape(buttonPath);
                String completePath = "file:///android_asset/background/" + "bg_03.webp";
                Glide.with(getApplicationContext()).load(completePath).into(play_fof_maxappss_binding.lockBackground);
            }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_04.webp")){
                String buttonPath = "pin/" + "pin04.webp";
                fofapps_changeButtonShape(buttonPath);
                String completePath = "file:///android_asset/background/" + "bg_04.webp";
                Glide.with(getApplicationContext()).load(completePath).into(play_fof_maxappss_binding.lockBackground);
            }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_05.webp")){
                String buttonPath = "pin/" + "pin05.webp";
                fofapps_changeButtonShape(buttonPath);
                String completePath = "file:///android_asset/background/" + "bg_05.webp";
                Glide.with(getApplicationContext()).load(completePath).into(play_fof_maxappss_binding.lockBackground);
            } else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_06.webp")){
                String buttonPath = "pin/" + "pin06.webp";
                fofapps_changeButtonShape(buttonPath);
                String completePath = "file:///android_asset/background/" + "bg_06.webp";
                Glide.with(getApplicationContext()).load(completePath).into(play_fof_maxappss_binding.lockBackground);
            }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_07.webp")){
                String buttonPath = "pin/" + "pin07.webp";
                fofapps_changeButtonShape(buttonPath);
                String completePath = "file:///android_asset/background/" + "bg_07.webp";
                Glide.with(getApplicationContext()).load(completePath).into(play_fof_maxappss_binding.lockBackground);
            }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_08.webp")){
                String buttonPath = "pin/" + "pin08.webp";
                fofapps_changeButtonShape(buttonPath);
                String completePath = "file:///android_asset/background/" + "bg_08.webp";
                Glide.with(getApplicationContext()).load(completePath).into(play_fof_maxappss_binding.lockBackground);
            }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_09.webp")){
                String buttonPath = "pin/" + "pin09.webp";
                fofapps_changeButtonShape(buttonPath);
                String completePath = "file:///android_asset/background/" + "bg_09.webp";
                Glide.with(getApplicationContext()).load(completePath).into(play_fof_maxappss_binding.lockBackground);
            }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_10.webp")){
                String buttonPath = "pin/" + "pin10.webp";
                fofapps_changeButtonShape(buttonPath);
                String completePath = "file:///android_asset/background/" + "bg_10.webp";
                Glide.with(getApplicationContext()).load(completePath).into(play_fof_maxappss_binding.lockBackground);
            }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_11.webp")){
                String buttonPath = "pin/" + "pin11.webp";
                fofapps_changeButtonShape(buttonPath);
                String completePath = "file:///android_asset/background/" + "bg_11.webp";
                Glide.with(getApplicationContext()).load(completePath).into(play_fof_maxappss_binding.lockBackground);
            }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_12.webp")){
                String buttonPath = "pin/" + "pin12.webp";
                fofapps_changeButtonShape(buttonPath);
                String completePath = "file:///android_asset/background/" + "bg_12.webp";
                Glide.with(getApplicationContext()).load(completePath).into(play_fof_maxappss_binding.lockBackground);
            }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_13.webp")){
                String buttonPath = "pin/" + "pin13.webp";
                fofapps_changeButtonShape(buttonPath);
                String completePath = "file:///android_asset/background/" + "bg_13.webp";
                Glide.with(getApplicationContext()).load(completePath).into(play_fof_maxappss_binding.lockBackground);
            }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_14.webp")){
                String buttonPath = "pin/" + "pin14.webp";
                fofapps_changeButtonShape(buttonPath);
                String completePath = "file:///android_asset/background/" + "bg_14.webp";
                Glide.with(getApplicationContext()).load(completePath).into(play_fof_maxappss_binding.lockBackground);
            }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_15.webp")){
                String buttonPath = "pin/" + "pin15.webp";
                fofapps_changeButtonShape(buttonPath);
                String completePath = "file:///android_asset/background/" + "bg_15.webp";
                Glide.with(getApplicationContext()).load(completePath).into(play_fof_maxappss_binding.lockBackground);
            }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_16.webp")){
                String buttonPath = "pin/" + "pin16.webp";
                fofapps_changeButtonShape(buttonPath);
                String completePath = "file:///android_asset/background/" + "bg_16.webp";
                Glide.with(getApplicationContext()).load(completePath).into(play_fof_maxappss_binding.lockBackground);
            }

        }
    }




    private void fofapps_changeButtonShape(String path){
        try {
            AssetManager assetManager = getAssets();
            InputStream is = assetManager.open(path);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
            play_fof_maxappss_binding.buttonOne.setBackground(drawable);
            play_fof_maxappss_binding.buttonTwo.setBackground(drawable);
            play_fof_maxappss_binding.buttonThree.setBackground(drawable);
            play_fof_maxappss_binding.buttonFour.setBackground(drawable);
            play_fof_maxappss_binding.buttonFive.setBackground(drawable);
            play_fof_maxappss_binding.buttonSix.setBackground(drawable);
            play_fof_maxappss_binding.buttonSeven.setBackground(drawable);
            play_fof_maxappss_binding.buttonEight.setBackground(drawable);
            play_fof_maxappss_binding.buttonNine.setBackground(drawable);
            play_fof_maxappss_binding.buttonZero.setBackground(drawable);
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

    }



    private void maxappsashes_setupPin(){
        if (fofapps_checkUserIsFirstTime()){
            highlightone.setVisibility(View.VISIBLE);
            getHighlightboth.setVisibility(View.GONE);
            play_fof_maxappss_binding.fingerPrintDefaultIcon.setVisibility(View.GONE);
            play_fof_maxappss_binding.displayHeader.setText("Enter new pin");
        }else{
            highlightone.setVisibility(View.GONE);
            getHighlightboth.setVisibility(View.GONE);
            //play_fof_maxappss_applyFingerPrint();
            play_fof_maxappss_binding.displayHeader.setText("Enter Passcode");
        }
    }

    private boolean fofapps_checkUserIsFirstTime(){



        return MaxApps_PinUtil.play_fof_maxappss_getPinPassword(getApplicationContext()) == null || MaxApps_GlobalMethods.maxapps_ashesgetRequestMode(getApplicationContext()).equals(MaxApps_MyConstant.PIN_REQUEST);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        MaxApps_GlobalMethods.maxapps_ashessaveRequestMode(getApplicationContext() , MaxApps_MyConstant.DEFAULT_REQUEST);
    }
    public void buttonClick(View view) {
        switch (view.getId()){
            case R.id.buttonOne:
                fofapps_appendTag(play_fof_maxappss_binding.buttonOne);
                break;
            case R.id.buttonTwo:
                fofapps_appendTag(play_fof_maxappss_binding.buttonTwo);
                break;
            case R.id.buttonThree:
                fofapps_appendTag(play_fof_maxappss_binding.buttonThree);
                break;
            case R.id.buttonFour:
                fofapps_appendTag(play_fof_maxappss_binding.buttonFour);
                break;
            case R.id.buttonFive:
                fofapps_appendTag(play_fof_maxappss_binding.buttonFive);
                break;
            case R.id.buttonSix:
                fofapps_appendTag(play_fof_maxappss_binding.buttonSix);
                break;
            case R.id.buttonSeven:
                fofapps_appendTag(play_fof_maxappss_binding.buttonSeven);
                break;
            case R.id.buttonEight:
                fofapps_appendTag(play_fof_maxappss_binding.buttonEight);
                break;
            case R.id.buttonNine:
                fofapps_appendTag(play_fof_maxappss_binding.buttonNine);
                break;
            case R.id.buttonZero:
                fofapps_appendTag(play_fof_maxappss_binding.buttonZero);
                break;
            case R.id.buttonClear:
                backSpace();
                break;
            case R.id.buttonForgot:

                Intent intent = new Intent(getApplicationContext() , MaxApps_UnlockSettingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
                break;
        }
    }

    private String fofapps_appendTag(TextView textView){
        maxappsAshes_vibrate();
        if (wrongPasswordCounter < 3){
            if (play_fof_maxappss_ag.length()<4){
                play_fof_maxappss_ag = play_fof_maxappss_ag + textView.getTag().toString();
                Log.d("ADIL" , play_fof_maxappss_ag);
                if (!isVisible(play_fof_maxappss_binding.imageViewActiveOne)){
                    play_fof_maxappss_binding.imageViewInActiveOne.setVisibility(View.GONE);
                    play_fof_maxappss_binding.imageViewActiveOne.setVisibility(View.VISIBLE);
                }else if (!isVisible(play_fof_maxappss_binding.imageViewActiveTwo)){
                    play_fof_maxappss_binding.imageViewInActiveTwo.setVisibility(View.GONE);
                    play_fof_maxappss_binding.imageViewActiveTwo.setVisibility(View.VISIBLE);
                }else if (!isVisible(play_fof_maxappss_binding.imageViewActiveThree)){
                    play_fof_maxappss_binding.imageViewInActiveThree.setVisibility(View.GONE);
                    play_fof_maxappss_binding.imageViewActiveThree.setVisibility(View.VISIBLE);
                }else if (!isVisible(play_fof_maxappss_binding.imageViewActiveFour)){
                    play_fof_maxappss_binding.imageViewInActiveFour.setVisibility(View.GONE);
                    play_fof_maxappss_binding.imageViewActiveFour.setVisibility(View.VISIBLE);
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (play_fof_maxappss_ag.length() == 4){
                            /***
                             * When user enter first time
                             */

                            if (fofapps_checkUserIsFirstTime()) {
                                enterUserFirstTime();

                            } else {
                                /***
                                 * It will run when already set pin
                                 */
                                enterUserMultiTime();



                            }


                        }
                    }
                },600);

            }
        }else {
            if (!lockButton){
                play_fof_maxappss_binding.displayHeader.setVisibility(View.GONE);
                play_fof_maxappss_binding.displayDelayCounter.setVisibility(View.VISIBLE);
                new CountDownTimer(15000, 1000) {
                    @Override
                    public void onTick(long l) {
                        //String counter = "Wait" + l + "Second";
                        counter--;
                        play_fof_maxappss_binding.displayDelayCounter.setText("Wait " + counter + " Second");
                        lockButton = true;
                    }

                    @Override
                    public void onFinish() {
                        play_fof_maxappss_binding.displayHeader.setVisibility(View.VISIBLE);
                        play_fof_maxappss_binding.displayDelayCounter.setVisibility(View.GONE);
                        wrongPasswordCounter = 0;
                        lockButton = false;
                        counter = 15;
                        play_fof_maxappss_binding.displayHeader.setText("Type again !");
                    }
                }.start();
            }

        }



        return play_fof_maxappss_ag;
    }
    private void enterUserMultiTime() {
        tag = getIntent().getStringExtra("tag");
        if (tag != null) {
            if (tag.equals("splash")) {
                if (play_fof_maxappss_ag.equals(MaxApps_PinUtil.play_fof_maxappss_getPinPassword(getApplicationContext()))) {
                    Log.d("ADIL", "Right Password");
                    tag = "default";
                    MaxApps_GlobalMethods.maxapps_ashes_saveStatusLastUnLockedApp(getApplicationContext(), true);
                    Intent intent = new Intent(getApplicationContext(), MaxApps_MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.putExtra("tag", "main");
                    startActivity(intent);
                    finishAffinity();
                   // GameAshes2_GlobalMethods.maxapps_ashes_saveLastTag(getApplicationContext(), "default");
                } else {
                    play_fof_maxappss_binding.displayHeader.setText("Wrong Try again!");
                    Log.d("ADIL", "Wrong Password");
                    play_fof_clearPassword();
                    maxappsAshes_vibrate();
                    attempts= attempts+1;

//                    CaptureFrontPhoto();

                    wrongPasswordCounter++;

                }

            } else {
                if (play_fof_maxappss_ag.equals(MaxApps_PinUtil.play_fof_maxappss_getPinPassword(getApplicationContext()))) {
                    Log.d("ADIL", "Right Password");
                    tag = "default";

                    MaxApps_GlobalMethods.maxapps_ashes_saveStatusLastUnLockedApp(getApplicationContext(), true);
                    finish();
                } else {
                    play_fof_maxappss_binding.displayHeader.setText("Wrong Try again!");
                    Log.d("ADIL", "Wrong Password");
                    play_fof_clearPassword();
                    maxappsAshes_vibrate();
                    attempts= attempts+1;

//                    CaptureFrontPhoto();

                    wrongPasswordCounter++;
                }
            }

        }else{
            if (play_fof_maxappss_ag.equals(MaxApps_PinUtil.play_fof_maxappss_getPinPassword(getApplicationContext()))) {
                Log.d("ADIL", "Right Password");
                tag = "default";
                MaxApps_GlobalMethods.maxapps_ashes_saveStatusLastUnLockedApp(getApplicationContext(), true);


/*
                PackageManager pm = getPackageManager();
                Intent launchIntent = pm.getLaunchIntentForPackage("com.google.android.youtube");
                startActivity(launchIntent);
*/


                finishAffinity();

            } else {
                play_fof_maxappss_binding.displayHeader.setText("Wrong Try again!");
                Log.d("ADIL", "Wrong Password");
                play_fof_clearPassword();
                maxappsAshes_vibrate();
                attempts= attempts+1;

//                CaptureFrontPhoto();

                wrongPasswordCounter++;
            }
        }
    }

    private void enterUserFirstTime() {
        if (play_fof_maxappss_confirmFlag) {
            /***
             * Go screen for successful
             */
            play_fof_maxappss_binding.highlighBoth.setVisibility(View.VISIBLE);
            play_fof_maxappss_binding.highlighone.setVisibility(View.GONE);
            play_fof_maxappss_confirmPassword = play_fof_maxappss_ag;
            if (play_fof_maxappss_setPassword.equals(play_fof_maxappss_confirmPassword)) {
                //Both password are equal
                fofapps_goCreatedPinScreen();

            } else {
                play_fof_maxappss_binding.highlighBoth.setVisibility(View.GONE);
                play_fof_maxappss_binding.highlighone.setVisibility(View.VISIBLE);
                //password not equal clear all things and retry again
                play_fof_maxappss_binding.displayHeader.setText("Try again! Create new pin");
                play_fof_clearPassword();
                play_fof_maxappss_setPassword = "";
                play_fof_maxappss_confirmPassword = "";
                play_fof_maxappss_confirmFlag = false;
                maxappsAshes_vibrate();
            }
        } else {
            play_fof_maxappss_binding.highlighBoth.setVisibility(View.VISIBLE);
            play_fof_maxappss_binding.highlighone.setVisibility(View.GONE);
            play_fof_maxappss_setPassword = play_fof_maxappss_ag;
            play_fof_maxappss_binding.displayHeader.setText("Confirm again passcode");
            play_fof_clearPassword();
            play_fof_maxappss_confirmFlag = true;
        }
    }

    private void fofapps_goCreatedPinScreen(){
        Intent intent = new Intent(getApplicationContext() , MaxApps_MainActivity.class);
       // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("message" , getString(R.string.pin_created));
        startActivity(intent);
        if (MaxApps_GlobalMethods.maxapps_ashesgetRequestMode(getApplicationContext()).equals(MaxApps_MyConstant.DEFAULT_REQUEST)
                || MaxApps_GlobalMethods.maxapps_ashesgetRequestMode(getApplicationContext()).equals(MaxApps_MyConstant.PIN_REQUEST)
        ){
            MaxApps_GlobalMethods.maxapps_ashessaveRequestMode(getApplicationContext() , MaxApps_MyConstant.DEFAULT_REQUEST);

            MaxApps_PinUtil.play_fof_maxappss_savePinPassword(getApplicationContext() , play_fof_maxappss_confirmPassword);
            //   GameAshes2_PinUtil.play_fof_maxappss_savePasswordMode(getApplicationContext() , GameAshes2_MyConstant.PIN);
            MaxApps_PasswordSetting.savePasswordMode(getApplicationContext() , MaxApps_MyConstant.PIN);


            MaxApps_PatternUtil.savePatternPassword(getApplicationContext(), null);



        }






    }
    private boolean isVisible(ImageView activeDot){
        return activeDot.getVisibility() == View.VISIBLE;
    }
    public  String removeLastChar(String str) {
        return removeLastChars(str, 1);
    }

    public  String removeLastChars(String str, int chars) {
        return str.substring(0, str.length() - chars);
    }
    private void backSpace() {
        if (isVisible(play_fof_maxappss_binding.imageViewActiveFour)) {
            play_fof_maxappss_ag = removeLastChar(play_fof_maxappss_ag);
            play_fof_maxappss_binding.imageViewActiveFour.setVisibility(View.GONE);
            play_fof_maxappss_binding.imageViewInActiveFour.setVisibility(View.VISIBLE);
        } else if (isVisible(play_fof_maxappss_binding.imageViewActiveThree)) {
            play_fof_maxappss_ag = removeLastChar(play_fof_maxappss_ag);
            play_fof_maxappss_binding.imageViewActiveThree.setVisibility(View.GONE);
            play_fof_maxappss_binding.imageViewInActiveThree.setVisibility(View.VISIBLE);
        }else if (isVisible(play_fof_maxappss_binding.imageViewActiveTwo)) {
            play_fof_maxappss_ag = removeLastChar(play_fof_maxappss_ag);
            play_fof_maxappss_binding.imageViewActiveTwo.setVisibility(View.GONE);
            play_fof_maxappss_binding.imageViewInActiveTwo.setVisibility(View.VISIBLE);
        }else if (isVisible(play_fof_maxappss_binding.imageViewActiveOne)) {
            play_fof_maxappss_ag = "";
            play_fof_maxappss_binding.imageViewActiveOne.setVisibility(View.GONE);
            play_fof_maxappss_binding.imageViewInActiveOne.setVisibility(View.VISIBLE);
        }
    }
    private void play_fof_clearPassword(){
        play_fof_maxappss_ag = "";

        play_fof_maxappss_binding.imageViewActiveOne.setVisibility(View.GONE);
        play_fof_maxappss_binding.imageViewInActiveOne.setVisibility(View.VISIBLE);

        play_fof_maxappss_binding.imageViewActiveTwo.setVisibility(View.GONE);
        play_fof_maxappss_binding.imageViewInActiveTwo.setVisibility(View.VISIBLE);

        play_fof_maxappss_binding.imageViewActiveThree.setVisibility(View.GONE);
        play_fof_maxappss_binding.imageViewInActiveThree.setVisibility(View.VISIBLE);

        play_fof_maxappss_binding.imageViewActiveFour.setVisibility(View.GONE);
        play_fof_maxappss_binding.imageViewInActiveFour.setVisibility(View.VISIBLE);

    }
    private void maxappsAshes_vibrate(){
        try {
            if (MaxApps_PinHelper.play_fof_maxappss_getTouchVibrate(getApplicationContext())){
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(100);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        //not null
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            play_fof_hideSystemUI();
        }
    }

    private void play_fof_hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        MaxApps_MyBlockService.ScreenNAme="NOAd";

    }

}