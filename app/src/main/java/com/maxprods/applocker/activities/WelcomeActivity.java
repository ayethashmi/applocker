package com.maxprods.applocker.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.maxprods.applocker.databinding.MaxappsWelcomeScreenBinding;
import com.maxprods.applocker.ui.pattern.MaxApps_PatternPasscodeActivity;
import com.maxprods.applocker.ui.pattern.MaxApps_PatternUtil;
import com.maxprods.applocker.ui.pin.MaxApps_PinPasscodeActivity;
import com.maxprods.applocker.ui.pin.MaxApps_PinUtil;
import com.maxprods.applocker.ui.util.MaxApps_MyConstant;
import com.maxprods.applocker.R;

public class WelcomeActivity extends AppCompatActivity {
    private final String TAG = WelcomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MaxappsWelcomeScreenBinding binding = DataBindingUtil.setContentView(this, R.layout.maxapps_welcome_screen);

        findViewById(R.id.buttonContinue).setOnClickListener(v->{
            savePrivacy(getApplicationContext() , true);
//            goMain();
            Log.d("kkkkkkkk", "Welcome");
            Intent intent = new Intent(getApplicationContext() , MaxApps_SplashActivity.class);
            startActivity(intent);

        });

    }



    private void goMain(){
        if (MaxApps_PatternUtil.getPasswordMode(getApplicationContext()).equals(MaxApps_MyConstant.PATTERN)) {
            if (MaxApps_PatternUtil.getPatternPassword(getApplicationContext()) == null) {
                Intent intent = new Intent(getApplicationContext(), MaxApps_PatternPasscodeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);


            } else {
                Intent intent = new Intent(getApplicationContext(), MaxApps_PatternPasscodeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("tag", "splash");




            }
        } else {
            if (MaxApps_PinUtil.play_fof_maxappss_getPinPassword(getApplicationContext()) == null) {
                Intent intent = new Intent(getApplicationContext(), MaxApps_PinPasscodeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            } else {
                Intent intent = new Intent(getApplicationContext(), MaxApps_PinPasscodeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("tag", "splash");

            }

        }
    }

    public static void savePrivacy(Context context , boolean visible){
        context.getSharedPreferences(MaxApps_MyConstant.PREF , Context.MODE_PRIVATE)
                .edit()
                .putBoolean(MaxApps_MyConstant.PRIVACY , visible)
                .apply();
    }




    protected void onDestroy() {

        super.onDestroy();
    }
    @Override
    public void onBackPressed() {

        }
}
