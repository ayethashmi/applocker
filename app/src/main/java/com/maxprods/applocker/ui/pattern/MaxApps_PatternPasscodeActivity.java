package com.maxprods.applocker.ui.pattern;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.maxprods.applocker.activities.MaxApps_MainActivity;
import com.maxprods.applocker.activities.MaxApps_UnlockSettingActivity;
import com.maxprods.applocker.databinding.MaxappsPatternOneBinding;
import com.maxprods.applocker.services.MaxApps_MyBlockService;

import com.maxprods.applocker.ui.fragments.pattern.MaxApps_PatternHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.maxprods.applocker.activities.MaxApps_PatternLockActivity;
import  com.maxprods.applocker.R;
import com.maxprods.applocker.ui.pin.MaxApps_PinUtil;
import com.maxprods.applocker.ui.themes.MaxApps_ThemeUtil;
import com.maxprods.applocker.ui.util.MaxApps_GlobalMethods;
import com.maxprods.applocker.ui.util.MaxApps_MyConstant;
import com.reginald.patternlockview.PatternLockView;

import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;
import java.security.KeyStore;

import javax.crypto.Cipher;

public class MaxApps_PatternPasscodeActivity extends AppCompatActivity {
    private MaxappsPatternOneBinding binding;
    private String play_fof_tag = "";
    private int counter = 15;
    private boolean lockButton = false;
    private int wrongPasswordCounter = 0;
    private WeakReference<Activity> passwordActivityWeakReference;


    private String play_fof_setPassword = "";
    private String play_fof_confirmPassword = "";
    private boolean play_fof_confirmFlag = false;
    private FingerprintManager mFingerprintManager = null;
    private KeyStore mKeyStore;
    private Cipher mCipher;
    private  final String KEY_NAME = "GAMEASHES";
    private  final String TAG = MaxApps_PatternLockActivity.class.getName();
    private ImageView play_fof_mFingerPrintView;
    private TextView play_fof_mDisplayHeader , play_fof_mDisplayCounter;
    private ImageView highlightone,getHighlightboth;
    private PatternLockView play_fof_mNewLockView;
    private ImageView mBackground;
    String tag = "default";
    public  final int MEDIA_TYPE_VIDEO = 2;
    public  final int MEDIA_TYPE_IMAGE = 1;
    private int attempts = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=MaxappsPatternOneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        changeTheme();
        maxappsAshes_initView();
        maxappsAshes_setupPattern();
        maxappsAshes_setPatternPassword();

        maxappsAshes_makePassVisibility();

    }




    private byte[] rotateImage(byte[] data, int angle) {
        Log.d("labot_log_info","CameraActivity: Inside rotateImage");
        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length, null);
        Matrix mat = new Matrix();
        mat.postRotate(angle);
        bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), mat, true);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }
    private void maxappsAshes_displayDialog() {
        //Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_text);
        //binding.displayError.setVisibility(View.VISIBLE);
        //binding.displayError.startAnimation(shake);
        maxappsAshes_vibrate();

    }



    @Override
    public void onBackPressed() {
        // return null
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            maxappsAshes_hideSystemUI();
        }
    }

    private void maxappsAshes_hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    private void changeTheme(){
        try {
            Log.d("sdgfvdsgv","themename :" + MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()) );
            if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()) != null){
                if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_01.webp")){
                    String completePath = "file:///android_asset/background/" + "bg_01.webp";
                    Glide.with(getApplicationContext())
                            .load(completePath)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.setBackground);
                }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_02.webp")){
                    String completePath = "file:///android_asset/background/" + "bg_02.webp";
                    Glide.with(getApplicationContext())
                            .load(completePath)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.setBackground);
                }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_03.webp")){
                    String completePath = "file:///android_asset/background/" + "bg_03.webp";
                    Glide.with(getApplicationContext())
                            .load(completePath)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.setBackground);
                }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_04.webp")){
                    String completePath = "file:///android_asset/background/" + "bg_04.webp";
                    Glide.with(getApplicationContext())
                            .load(completePath)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.setBackground);
                }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_05.webp")){
                    String completePath = "file:///android_asset/background/" + "bg_05.webp";
                    Glide.with(getApplicationContext())
                            .load(completePath)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.setBackground);
                } else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_06.webp")){
                    String completePath = "file:///android_asset/background/" + "bg_06.webp";
                    Glide.with(getApplicationContext())
                            .load(completePath)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.setBackground);
                }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_07.webp")){
                    String completePath = "file:///android_asset/background/" + "bg_07.webp";
                    Glide.with(getApplicationContext())
                            .load(completePath)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.setBackground);
                }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_08.webp")){
                    String completePath = "file:///android_asset/background/" + "bg_08.webp";
                    Glide.with(getApplicationContext())
                            .load(completePath)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.setBackground);
                }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_09.webp")){
                    String completePath = "file:///android_asset/background/" + "bg_09.webp";
                    Glide.with(getApplicationContext())
                            .load(completePath)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.setBackground);
                }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_10.webp")){
                    String completePath = "file:///android_asset/background/" + "bg_10.webp";
                    Glide.with(getApplicationContext())
                            .load(completePath)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.setBackground);
                }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_11.webp")){
                    String completePath = "file:///android_asset/background/" + "bg_11.webp";
                    Glide.with(getApplicationContext())
                            .load(completePath)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.setBackground);
                }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_12.webp")){
                    String completePath = "file:///android_asset/background/" + "bg_12.webp";
                    Glide.with(getApplicationContext())
                            .load(completePath)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.setBackground);
                }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_13.webp")){
                    String completePath = "file:///android_asset/background/" + "bg_13.webp";
                    Glide.with(getApplicationContext())
                            .load(completePath)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.setBackground);
                }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_14.webp")){
                    String completePath = "file:///android_asset/background/" + "bg_14.webp";
                    Glide.with(getApplicationContext())
                            .load(completePath)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.setBackground);
                }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_15.webp")){
                    String completePath = "file:///android_asset/background/" + "bg_15.webp";
                    Glide.with(getApplicationContext())
                            .load(completePath)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.setBackground);
                }else if (MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()).equals("Theme_16.webp")){
                    String completePath = "file:///android_asset/background/" + "bg_16.webp";
                    Glide.with(getApplicationContext())
                            .load(completePath)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.setBackground);
                }else{
                    Log.d("ADIL_THEME" , "Theme Name: " + MaxApps_ThemeUtil.maxappsashes_getThemeName(getApplicationContext()));

                }


            }


            maxappsAshes_setPatternPassword();
        }catch (Exception e){
            e.printStackTrace();
        }



    }
    private void maxappsAshes_initView(){
        play_fof_mFingerPrintView = findViewById(R.id.fingerPrintDefaultIcon);
        play_fof_mDisplayHeader = findViewById(R.id.displayHeader);
        play_fof_mDisplayCounter = findViewById(R.id.displayDelayCounter);

        play_fof_mNewLockView = findViewById(R.id.lock_view_dot);

        highlightone=findViewById(R.id.highlighone);
        getHighlightboth=findViewById(R.id.highlighBoth);




    }


    private void maxappsAshes_setupPattern(){
        if (maxapps_ashes_checkUserIsFirstTime()){
            play_fof_mFingerPrintView.setVisibility(View.GONE);
            play_fof_mDisplayHeader.setText("Draw an unlock Pattern");
        }else{
            highlightone.setVisibility(View.GONE);
            getHighlightboth.setVisibility(View.GONE);
            //play_fof_applyFingerPrint();
            play_fof_mDisplayHeader.setText("Enter Passcode");
        }
    }

    private void maxappsAshes_setPatternPassword(){

        play_fof_mNewLockView.setCallBack(new PatternLockView.CallBack() {
            @Override
            public int onFinish(PatternLockView.Password password) {
                Log.d(TAG, "password is " + password.string);

                play_fof_tag = play_fof_tag + password.string;
                if (password.string.equals(play_fof_tag)) {
                    // password is correct
                    maxappsAshes_setupPasscode();
                    return PatternLockView.CODE_PASSWORD_CORRECT;

                } else {
                    // password is error
                    return PatternLockView.CODE_PASSWORD_ERROR;
                }


            }
        });


    }
    private void maxappsAshes_setupPasscode(){
        /***
         * When user enter first time
         */
        if (wrongPasswordCounter < 3){

        }else{
            if (!lockButton){
                new CountDownTimer(15000, 1000) {
                    @Override
                    public void onTick(long l) {
                        //String counter = "Wait" + l + "Second";
                        counter--;
                        play_fof_mDisplayCounter.setText("Wait " + counter + " Second");
                        lockButton = true;
                    }

                    @Override
                    public void onFinish() {
                        play_fof_mDisplayHeader.setVisibility(View.VISIBLE);
                        play_fof_mDisplayCounter.setVisibility(View.GONE);
                        wrongPasswordCounter = 0;
                        lockButton = false;
                        counter = 15;
                        play_fof_mDisplayHeader.setText("Type again !");
                    }
                }.start();
            }
        }

        if (maxappsAshes_checkUserIsFirstTime()) {
                 enterUserFirstTime();
        } else {
            enterUserMultiTime();
        }

    }
    private void enterUserFirstTime() {
        if (play_fof_confirmFlag) {
            /***
             * Go screen for successful
             */
            play_fof_confirmPassword = play_fof_tag;
            if (play_fof_setPassword.equals(play_fof_confirmPassword)) {
                //Both password are equal
                maxappsAshes_goCreatedPinScreen();

            } else {
                //password not equal clear all things and retry again
                play_fof_mDisplayHeader.setText("Try again! Draw new Pattern");
                maxappsAshes_clearPassword();
                play_fof_setPassword = "";
                play_fof_confirmPassword = "";
                play_fof_confirmFlag = false;
                maxappsAshes_vibrate();
            }
        } else {
             play_fof_setPassword =  play_fof_tag;
             play_fof_mDisplayHeader.setText("Confirm again pattern");
             maxappsAshes_clearPassword();
             play_fof_confirmFlag = true;
        }
    }
    private void enterUserMultiTime() {
        tag = getIntent().getStringExtra("tag");


        if (tag != null) {
            if (tag.equals("splash")) {
                if (play_fof_tag.equals(MaxApps_PatternUtil.getPatternPassword(getApplicationContext()))) {
                    Log.d("ADIL", "Right Password");
                    tag = "default";
                    MaxApps_GlobalMethods.maxapps_ashes_saveStatusLastUnLockedApp(getApplicationContext(), true);
                    Intent intent = new Intent(getApplicationContext(), MaxApps_MainActivity.class);
                    intent.putExtra("tag", "splash");
                    startActivity(intent);
                    finishAffinity();

                } else {
                    play_fof_mDisplayHeader.setText("Wrong Try again!");
                    Log.d("ADIL", "Wrong Password");
                    maxappsAshes_clearPassword();
                    maxappsAshes_vibrate();
                    attempts= attempts+1;

//                    CaptureFrontPhoto();
                }

            }

        } else {
            if (play_fof_tag.equals(MaxApps_PatternUtil.getPatternPassword(getApplicationContext()))) {
                Log.d("ADIL", "Right Password");
                tag = "default";
                MaxApps_GlobalMethods.maxapps_ashes_saveStatusLastUnLockedApp(getApplicationContext(), true);
                finishAffinity();
            } else {
                play_fof_mDisplayHeader.setText("Wrong Try again!");
                Log.d("ADIL", "Wrong Password");
                maxappsAshes_clearPassword();
                maxappsAshes_vibrate();
                attempts= attempts+1;

//                CaptureFrontPhoto();
            }
        }
    }

    private boolean maxappsAshes_checkUserIsFirstTime(){
        return MaxApps_PatternUtil.getPatternPassword(getApplicationContext()) == null;
    }
    private void maxappsAshes_goCreatedPinScreen(){

        Intent intent;
        String navigationRoot = getIntent().getStringExtra(MaxApps_MyConstant.NAVIGATION_ROOT);
        if (navigationRoot != null && navigationRoot.equals(MaxApps_MyConstant.UNLOCK_SETTING)) {
            intent = new Intent(getApplicationContext(), MaxApps_UnlockSettingActivity.class);

        } else {
            intent = new Intent(getApplicationContext(), MaxApps_MainActivity.class);
        }
        intent.putExtra("message", getString(R.string.pattern_created));
        startActivity(intent);

        if (MaxApps_GlobalMethods.maxapps_ashesgetRequestMode(getApplicationContext()).equals(MaxApps_MyConstant.DEFAULT_REQUEST)
                || MaxApps_GlobalMethods.maxapps_ashesgetRequestMode(getApplicationContext()).equals(MaxApps_MyConstant.PATTERN_REQUEST)
        ){
            MaxApps_PatternUtil.savePatternPassword(getApplicationContext() , play_fof_confirmPassword);
            MaxApps_PatternUtil.savePasswordMode(getApplicationContext() , MaxApps_MyConstant.PATTERN);

            MaxApps_PinUtil.play_fof_maxappss_savePinPassword(getApplicationContext(), null);

            MaxApps_GlobalMethods.maxapps_ashessaveRequestMode(getApplicationContext() , MaxApps_MyConstant.DEFAULT_REQUEST);

        }







    }
    private void maxappsAshes_clearPassword(){
        play_fof_tag = "";

    }
    private void maxappsAshes_vibrate(){
        if (MaxApps_PatternHelper.maxappsashes_getTouchVibrate(getApplicationContext())){
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                v.vibrate(500);
            }
        }

    }
    private boolean maxapps_ashes_checkUserIsFirstTime(){

        return MaxApps_PatternUtil.getPatternPassword(getApplicationContext()) == null || MaxApps_GlobalMethods.maxapps_ashesgetRequestMode(getApplicationContext()).equals(MaxApps_MyConstant.PATTERN_REQUEST);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        MaxApps_MyBlockService.ScreenNAme="NOAd";

        MaxApps_GlobalMethods.maxapps_ashessaveRequestMode(getApplicationContext() , MaxApps_MyConstant.DEFAULT_REQUEST);





    }

    @Override
    protected void onStop() {
        super.onStop();
        MaxApps_MyBlockService.ScreenNAme="NOAd";

    }

    private void maxappsAshes_makePassVisibility(){
        if (!MaxApps_PatternUtil.play_fof_getPasswordVisible(getApplicationContext())){
            play_fof_mNewLockView.setPatternVisible(false);
        }
    }
//    private void CaptureFrontPhoto() {
//        int number=   getSharedPreferences(Game_IntruderHelper.In_PREF,MODE_PRIVATE)
//                .getInt(Game_IntruderHelper.NUMB,0);
//
//        if (Game_IntruderHelper.getIntruder(getApplicationContext())){
//
//
//            try {
//                if (attempts >= number){
//                    Game_CameraManager cameraManager = Game_CameraManager.getInstance(getApplicationContext());
//                    cameraManager.takePhoto();
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//        }
//
//    }


}