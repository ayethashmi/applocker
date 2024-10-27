package com.maxprods.applocker.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.maxprods.applocker.databinding.MaxappsActivityUnlockSettingBinding;
import com.maxprods.applocker.services.MaxApps_MyBlockService;
import com.maxprods.applocker.ui.fragments.pattern.MaxApps_PatternSettingFragment;
import com.maxprods.applocker.ui.fragments.pin.MaxApps_PinSettingFragment;
import com.maxprods.applocker.ui.pattern.MaxApps_PatternPasscodeActivity;
import com.maxprods.applocker.ui.pattern.MaxApps_PatternUtil;
import com.maxprods.applocker.ui.pin.MaxApps_PinPasscodeActivity;
import com.maxprods.applocker.ui.setting.MaxApps_PasswordSetting;
import com.maxprods.applocker.ui.util.MaxApps_GlobalMethods;
import com.maxprods.applocker.ui.util.MaxApps_MyConstant;
import com.maxprods.applocker.R;

import java.lang.ref.WeakReference;


public class MaxApps_UnlockSettingActivity extends AppCompatActivity  {
    private MaxappsActivityUnlockSettingBinding play_fof_binding;
    private WeakReference<Activity> weakReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        play_fof_binding = DataBindingUtil.setContentView(this, R.layout.maxapps_activity_unlock_setting);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        weakReference=new WeakReference<>(MaxApps_UnlockSettingActivity.this);

        maxapps_ashes_eventHandling();
        if (!MaxApps_GlobalMethods.maxapps_ashes_getSubscribe(getApplicationContext())) {

           }





        }






    private void maxappsAshes_addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frag_container, fragment)
                .commit();
    }

    private void maxappsAshes_replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_container, fragment)
                .commit();
    }

    private void maxapps_ashes_eventHandling() {
        play_fof_binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        play_fof_binding.pattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MaxApps_PatternUtil.getPasswordMode(getApplicationContext()).equals(MaxApps_MyConstant.PATTERN)) {
                    play_fof_binding.imageVisiblePin.setImageResource(R.drawable.fofapps2_hide);
                    play_fof_binding.imageVisiblePattern.setImageResource(R.drawable.fofapps2_fofapps_show);

                    maxappsAshes_replaceFragment(new MaxApps_PatternSettingFragment());
                    Intent intent = new Intent(getApplicationContext(), MaxApps_PatternPasscodeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);


                    MaxApps_GlobalMethods.maxapps_ashessaveRequestMode(getApplicationContext() , MaxApps_MyConstant.PATTERN_REQUEST);

                }


            }
        });


        play_fof_binding.pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MaxApps_PatternUtil.getPasswordMode(getApplicationContext()).equals(MaxApps_MyConstant.PIN)) {
                    play_fof_binding.imageVisiblePin.setImageResource(R.drawable.fofapps2_fofapps_show);
                    play_fof_binding.imageVisiblePattern.setImageResource(R.drawable.fofapps2_hide);

                    maxappsAshes_replaceFragment(new MaxApps_PinSettingFragment());

                    Intent intent = new Intent(getApplicationContext(), MaxApps_PinPasscodeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);


                    MaxApps_GlobalMethods.maxapps_ashessaveRequestMode(getApplicationContext() , MaxApps_MyConstant.PIN_REQUEST);



                }




            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        MaxApps_MyBlockService.ScreenNAme="main";
        maxappsAshes_bindData();
        MaxApps_MyConstant.firsttime=true;

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void maxappsAshes_bindData() {
        if (MaxApps_PasswordSetting.getPasswordMode(getApplicationContext()).equals(MaxApps_MyConstant.PATTERN)) {
            maxappsAshes_addFragment(new MaxApps_PatternSettingFragment());
            play_fof_binding.imageVisiblePin.setImageResource(R.drawable.fofapps2_hide);
            play_fof_binding.imageVisiblePattern.setImageResource(R.drawable.fofapps2_fofapps_show);

        } else {
            maxappsAshes_addFragment(new MaxApps_PinSettingFragment());
            play_fof_binding.imageVisiblePin.setImageResource(R.drawable.fofapps2_fofapps_show);
            play_fof_binding.imageVisiblePattern.setImageResource(R.drawable.fofapps2_hide);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
}
}