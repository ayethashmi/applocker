package com.maxprods.applocker.ui.fragments;

import static android.content.Context.FINGERPRINT_SERVICE;
import static android.content.Context.KEYGUARD_SERVICE;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.maxprods.applocker.activities.MaxApps_CongratulationActivity;
import com.maxprods.applocker.activities.MaxApps_SecuritySettingActivity;
import com.maxprods.applocker.activities.MaxApps_UnlockSettingActivity;
import com.maxprods.applocker.databinding.MaxappsFragmentAppSettingBinding;
import com.maxprods.applocker.ui.themes.MaxApps_ThemesActivity;
import com.maxprods.applocker.R;

public class MaxApps_AppSettingFragment extends Fragment  {
    private MaxappsFragmentAppSettingBinding maxapps_ashes_binding;
    private boolean pattern = false;



    private Intent mFacebookIntent;
    private final String TAG = MaxApps_AppSettingFragment.class.getSimpleName();

    private boolean flag = false;

    private Intent play_fof_maxappss_intent;
    private Activity mContext;
    public MaxApps_AppSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        maxapps_ashes_binding = DataBindingUtil.inflate(inflater, R.layout.maxapps_fragment_app_setting, container, false);

        /****
         * Methods
         */

        maxapps_ashes_eventHandling();









        return maxapps_ashes_binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void maxapps_ashes_eventHandling() {
        maxapps_ashes_binding.themes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   AdManager.theme_loadInterstitial(maxapps_ashes_binding.getRoot().getContext());

                play_fof_maxappss_intent = new Intent(requireContext() , MaxApps_ThemesActivity.class);
                startActivity(play_fof_maxappss_intent);


            }
        });
        maxapps_ashes_binding.unlockSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
             //   AdManager.unLockSettings_loadInterstitial(maxapps_ashes_binding.getRoot().getContext());
                play_fof_maxappss_intent = new Intent(requireContext(), MaxApps_UnlockSettingActivity.class);
               startActivity(play_fof_maxappss_intent);



            }
        });

        maxapps_ashes_binding.switchOff.setOnClickListener(v->{
            if (play_fof_maxappss_isApplyFingerPrint()) {
                maxapps_ashes_binding.switchOff.setVisibility(View.GONE);
                maxapps_ashes_binding.switchOn.setVisibility(View.VISIBLE);
                //GameAshes2_FingerprintHandler.maxappsAshes_maxappss_saveFingerprint(requireContext(), true);
               // play_fof_maxappss_enterCongratulationScreen();
            }


        });
        maxapps_ashes_binding.switchOn.setOnClickListener(v->{

            maxapps_ashes_binding.switchOff.setVisibility(View.VISIBLE);
            maxapps_ashes_binding.switchOn.setVisibility(View.GONE);

          //  GameAshes2_FingerprintHandler.maxappsAshes_maxappss_saveFingerprint(requireContext(), false);

        });
        maxapps_ashes_binding.fingerprintSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (play_fof_maxappss_isApplyFingerPrint()) {
                      //  GameAshes2_FingerprintHandler.maxappsAshes_maxappss_saveFingerprint(requireContext(), isChecked);
                        play_fof_maxappss_enterCongratulationScreen();


                    }
                }

            }
        });

        maxapps_ashes_binding.securitySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play_fof_maxappss_intent = new Intent(requireContext(), MaxApps_SecuritySettingActivity.class);
                startActivity(play_fof_maxappss_intent);
               // AdManager.showInterstitialDialog( play_fof_maxappss_intent,activity);



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
    private void play_fof_maxappss_enterCongratulationScreen(){
        Intent intent = new Intent(requireActivity(), MaxApps_CongratulationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("message" , "Fingerprint Applied");



    }

    private boolean play_fof_maxappss_isApplyFingerPrint() {
        // ---------------------------------------------- Fingerprint -------------------//
        try {
            KeyguardManager keyguardManager = (KeyguardManager) requireActivity()
                    .getSystemService(KEYGUARD_SERVICE);
            FingerprintManager fingerprintManager;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                fingerprintManager =
                        (FingerprintManager) requireContext().getSystemService(FINGERPRINT_SERVICE);
                if (fingerprintManager != null) {
                    if (!fingerprintManager.isHardwareDetected()) {
                        Log.d(TAG, "Your Device does not have a Fingerprint Sensor");
                        maxapps_ashes_binding.fingerprintSwitch.setChecked(false);
                        //  displayDialog("Device Sensor", "Your Device does not have a Fingerprint Sensor");
                        return false;
                    } else if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                        Log.d(TAG, "Fingerprint authentication permission not enabled");
                        maxapps_ashes_binding.fingerprintSwitch.setChecked(false);
                        play_fof_maxappss_displayDialog("Fingerprint Permission", "Fingerprint authentication permission not enabled");
                        return false;
                    } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                        Log.d(TAG, "To use fingerprint lock, please open phone Settings, add fingerprint first.");
                        maxapps_ashes_binding.fingerprintSwitch.setChecked(false);
                        play_fof_maxappss_displayFingerDialog("Register Fingerprint", "To use fingerprint lock, please open phone Settings, add fingerprint first.");
                        return false;
                    } else if (!keyguardManager.isKeyguardSecure()) {
                        Log.d(TAG, "Lock screen security not enabled in Settings");
                        maxapps_ashes_binding.fingerprintSwitch.setChecked(false);
                        play_fof_maxappss_displayFingerDialog("Lock Screen", "Lock screen security not enabled in Settings");
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    maxapps_ashes_binding.fingerprintSwitch.setChecked(false);
                    Log.d(TAG, "Your Device does not have a Fingerprint Sensor");
                    play_fof_maxappss_displayDialog("Device Sensor", "Your Device does not have a Fingerprint Sensor");

                    return false;

                }
            } else {
                maxapps_ashes_binding.fingerprintSwitch.setChecked(false);
                Log.d(TAG, "Your Device does not have a Fingerprint Sensor");
                play_fof_maxappss_displayDialog("Device Sensor", "Your Device does not have a Fingerprint Sensor");
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }





    }

    private void play_fof_maxappss_displayFingerDialog(String title, String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(requireContext());
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

    private void play_fof_maxappss_displayDialog(String title, String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(requireContext());
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
        play_fof_maxappss_defaultState();



    }

    private void play_fof_maxappss_defaultState() {
       /* if (GameAshes2_FingerprintHandler.maxappsAshes_getFingerprint(requireContext())){
            maxapps_ashes_binding.switchOn.setVisibility(View.VISIBLE);
            maxapps_ashes_binding.switchOff.setVisibility(View.GONE);
        }else{
            maxapps_ashes_binding.switchOn.setVisibility(View.GONE);
            maxapps_ashes_binding.switchOff.setVisibility(View.VISIBLE);
        }*/



    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        maxapps_ashes_binding = null;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }
}