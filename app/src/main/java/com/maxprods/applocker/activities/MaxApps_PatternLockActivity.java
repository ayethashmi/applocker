package com.maxprods.applocker.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;


import com.maxprods.applocker.databinding.MaxappsActivityLockPatternBinding;
import com.maxprods.applocker.services.MaxApps_MyBlockService;
import com.maxprods.applocker.ui.fragments.pattern.MaxApps_PatternHelper;
import com.maxprods.applocker.ui.pattern.MaxApps_PatternUtil;
import com.maxprods.applocker.ui.util.MaxApps_GlobalMethods;
import com.maxprods.applocker.R;
import com.reginald.patternlockview.PatternLockView;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class MaxApps_PatternLockActivity extends AppCompatActivity {
    private static final String KEY_NAME = "androidHive";
    private static final String TAG = MaxApps_PatternLockActivity.class.getName();
    private MaxappsActivityLockPatternBinding play_fof_binding;
    private KeyStore play_fof_keyStore;
    private Cipher play_fof_cipher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        play_fof_binding = DataBindingUtil.setContentView(this, R.layout.maxapps_activity_lock_pattern);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        maxappsAshes_eventHandling();

        maxappsAshes_applyBackgroundTheme();
    }

    private void maxappsAshes_applyBackgroundTheme(){
        if (MaxApps_GlobalMethods.maxapps_ashes_getTheme(getApplicationContext()) != 0){
            play_fof_binding.parentView.setBackgroundResource(MaxApps_GlobalMethods.maxapps_ashes_getTheme(getApplicationContext()));
        }
    }







    private void maxappsAshes_eventHandling() {

        play_fof_binding.lockView.setCallBack(new PatternLockView.CallBack() {
            @Override
            public int onFinish(PatternLockView.Password password) {
                try {
                    if (MaxApps_PatternUtil.getPatternPassword(getApplicationContext()).equals(password.string)) {
                        Log.d(TAG, "Correct password");
                        MaxApps_GlobalMethods.maxapps_ashes_saveStatusLastUnLockedApp(getApplicationContext(), true);
                        finishAffinity();
                    } else {
                        Log.d(TAG, "Wrong password");
                    }

                    if (password.string.equals(MaxApps_PatternUtil.getPatternPassword(getApplicationContext()))) {
                        return PatternLockView.CODE_PASSWORD_CORRECT;
                    } else {
                        play_fof_displayDialog();
                        return PatternLockView.CODE_PASSWORD_ERROR;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    if (password.string.equals(MaxApps_PatternUtil.getPatternPassword(getApplicationContext()))) {
                        return PatternLockView.CODE_PASSWORD_CORRECT;
                    } else {
                        play_fof_displayDialog();
                        return PatternLockView.CODE_PASSWORD_ERROR;
                    }
                }


                // return 0;
            }
        });

        play_fof_binding.lockView.setOnNodeTouchListener(new PatternLockView.OnNodeTouchListener() {
            @Override
            public void onNodeTouched(int NodeId) {
                Log.d(TAG, "node " + NodeId + " is touched!");
            }


        });
    }

    private void maxappsAshes_defaultSetting() {
        play_fof_binding.lockView.setPatternVisible(MaxApps_PatternHelper.maxappsashes_getPasswordVisible(getApplicationContext()));


    }

    @Override
    protected void onResume() {
        super.onResume();
        maxappsAshes_defaultSetting();
        MaxApps_MyBlockService.ScreenNAme="main";

    }

    //-------------------------------------------- Fingerprint ------------------------------//

    @TargetApi(Build.VERSION_CODES.M)
    protected void aa_generateKey() {
        try {
            play_fof_keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }

        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException("Failed to get KeyGenerator instance", e);
        }

        try {
            play_fof_keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException |
                InvalidAlgorithmParameterException
                | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean cipherInit() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                play_fof_cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }

        try {
            play_fof_keyStore.load(null);
            SecretKey key = (SecretKey) play_fof_keyStore.getKey(KEY_NAME,
                    null);
            play_fof_cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    class FingerprintHandler extends FingerprintManager.AuthenticationCallback{


        public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
            CancellationSignal cancellationSignal = new CancellationSignal();
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
        }

        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            Log.d("AUTHENTCATE" , "onAuthenticationError");
        }

        @Override
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {

        }

        @Override
        public void onAuthenticationFailed() {
            Log.d("AUTHENTCATE" , "onAuth Failed");
            play_fof_displayDialog();
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {

            MaxApps_GlobalMethods.maxapps_ashes_saveStatusLastUnLockedApp(getApplicationContext(), true);
            finishAffinity();
        }

    }

    private void play_fof_displayDialog() {
        //Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_text);
        play_fof_binding.displayError.setVisibility(View.VISIBLE);
        //binding.displayError.startAnimation(shake);
        play_fof_vibrate();

    }

    private void play_fof_vibrate(){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(500);
        }


    }

    @Override
    public void onBackPressed() {
        // return null
    }
}
