package com.maxprods.applocker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.maxprods.applocker.ui.fragments.MaxApps_AppSettingFragment;
import com.maxprods.applocker.ui.themes.MaxApps_ThemesActivity;
import com.maxprods.applocker.ui.util.MaxApps_MyConstant;
import com.maxprods.applocker.R;
import com.maxprods.applocker.databinding.MaxappsAppSettingBinding;

import java.lang.ref.WeakReference;

public class MaxApps_AppSettingActivity extends AppCompatActivity {
    private MaxappsAppSettingBinding maxapps_ashes_binding;
    private boolean pattern = false;
    private final String TAG = MaxApps_AppSettingFragment.class.getSimpleName();
    private boolean flag = false;
    private Intent intent;
    private WeakReference<Activity> weakReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        maxapps_ashes_binding = DataBindingUtil.setContentView(this, R.layout.maxapps_app_setting);

        weakReference=new WeakReference<>(MaxApps_AppSettingActivity.this);

        maxapps_ashes_eventHandling();
   }


    private void maxapps_ashes_eventHandling() {
        maxapps_ashes_binding.back.setOnClickListener(v->{
            onBackPressed();
        });
        maxapps_ashes_binding.themes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   AdManager.theme_loadInterstitial(maxapps_ashes_binding.getRoot().getContext());

                intent = new Intent(getApplicationContext() , MaxApps_ThemesActivity.class);
                startActivity(intent);


            }
        });
        maxapps_ashes_binding.unlockSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
              //  AdManager.unLockSettings_loadInterstitial(maxapps_ashes_binding.getRoot().getContext());
                intent = new Intent(getApplicationContext(), MaxApps_UnlockSettingActivity.class);
                startActivity(intent);



            }
        });

        maxapps_ashes_binding.switchOff.setOnClickListener(v->{
            if (play_fof_games_isApplyFingerPrint()) {
                maxapps_ashes_binding.switchOff.setVisibility(View.GONE);
                maxapps_ashes_binding.switchOn.setVisibility(View.VISIBLE);
                //GameAshes2_FingerprintHandler.gameAshes_games_saveFingerprint(getApplicationContext(), true);
                // play_fof_games_enterCongratulationScreen();
            }


        });
        maxapps_ashes_binding.switchOn.setOnClickListener(v->{

            maxapps_ashes_binding.switchOff.setVisibility(View.VISIBLE);
            maxapps_ashes_binding.switchOn.setVisibility(View.GONE);

           // GameAshes2_FingerprintHandler.gameAshes_games_saveFingerprint(getApplicationContext(), false);

        });
        maxapps_ashes_binding.fingerprintSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (play_fof_games_isApplyFingerPrint()) {
                       // GameAshes2_FingerprintHandler.gameAshes_games_saveFingerprint(getApplicationContext(), isChecked);
                        gameAshes_enterCongratulationScreen();


                    }
                }

            }
        });

        maxapps_ashes_binding.securitySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), MaxApps_SecuritySettingActivity.class);
                startActivity(intent);
              //  AdManager.SecuritySettings_loadInterstitial(getApplicationContext());
                // AdManager.showInterstitialDialog( play_fof_games_intent,activity);



            }
        });
        maxapps_ashes_binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                    String shareMessage = "\nLet me recommend you use this app blocker to secure your apps.\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + maxapps_ashes_binding.getRoot().getContext().getPackageName() + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });

        maxapps_ashes_binding.rateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("market://details?id=" + maxapps_ashes_binding.getRoot().getContext().getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id" + maxapps_ashes_binding.getRoot().getContext().getPackageName())));

                }
            }
        });
    }
    private void gameAshes_enterCongratulationScreen(){
        Intent intent = new Intent(getApplicationContext(), MaxApps_CongratulationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("message" , "Fingerprint Applied");



    }

    private boolean play_fof_games_isApplyFingerPrint() {
        // ---------------------------------------------- Fingerprint -------------------//
        try {
            KeyguardManager keyguardManager = (KeyguardManager) getApplicationContext()
                    .getSystemService(KEYGUARD_SERVICE);
            FingerprintManager fingerprintManager;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                fingerprintManager =
                        (FingerprintManager) getApplicationContext().getSystemService(FINGERPRINT_SERVICE);
                if (fingerprintManager != null) {
                    if (!fingerprintManager.isHardwareDetected()) {
                        Log.d(TAG, "Your Device does not have a Fingerprint Sensor");
                        maxapps_ashes_binding.fingerprintSwitch.setChecked(false);
                        //  displayDialog("Device Sensor", "Your Device does not have a Fingerprint Sensor");
                        return false;
                    } else if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                        Log.d(TAG, "Fingerprint authentication permission not enabled");
                        maxapps_ashes_binding.fingerprintSwitch.setChecked(false);
                        gameAshes_displayDialog("Fingerprint Permission", "Fingerprint authentication permission not enabled");
                        return false;
                    } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                        Log.d(TAG, "To use fingerprint lock, please open phone Settings, add fingerprint first.");
                        maxapps_ashes_binding.fingerprintSwitch.setChecked(false);
                        gameAshes_displayFingerDialog("Register Fingerprint", "To use fingerprint lock, please open phone Settings, add fingerprint first.");
                        return false;
                    } else if (!keyguardManager.isKeyguardSecure()) {
                        Log.d(TAG, "Lock screen security not enabled in Settings");
                        maxapps_ashes_binding.fingerprintSwitch.setChecked(false);
                        gameAshes_displayFingerDialog("Lock Screen", "Lock screen security not enabled in Settings");
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    maxapps_ashes_binding.fingerprintSwitch.setChecked(false);
                    Log.d(TAG, "Your Device does not have a Fingerprint Sensor");
                    gameAshes_displayDialog("Device Sensor", "Your Device does not have a Fingerprint Sensor");

                    return false;

                }
            } else {
                maxapps_ashes_binding.fingerprintSwitch.setChecked(false);
                Log.d(TAG, "Your Device does not have a Fingerprint Sensor");
                gameAshes_displayDialog("Device Sensor", "Your Device does not have a Fingerprint Sensor");
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }





    }

    private void gameAshes_displayFingerDialog(String title, String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });
        alert.show();
    }

    private void gameAshes_displayDialog(String title, String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        gameAshes_games_defaultState();
        MaxApps_MyConstant.firsttime=true;



    }

    private void gameAshes_games_defaultState() {


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
       // AdManager.appSetting_mInterstitialAd=null;
    }
}