package com.maxprods.applocker.ui.forgot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import  com.maxprods.applocker.R;
import com.maxprods.applocker.services.MaxApps_MyBlockService;

import java.security.KeyStore;

import javax.crypto.Cipher;

public class MaxApps_ForgotPasscodeActivity extends AppCompatActivity {
    private static final String fTAG = MaxApps_ForgotPasscodeActivity.class.getName();
    private static final String KEY_NAME = "androidHive";
    private KeyStore maxappsashes_keyStore;
    private Cipher maxappsashes_cipher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maxapps_activity_fogot_passcode);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MaxApps_MyBlockService.ScreenNAme="main";
    }
}