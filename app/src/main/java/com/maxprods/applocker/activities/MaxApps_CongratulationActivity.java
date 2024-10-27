package com.maxprods.applocker.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.maxprods.applocker.R;
import com.maxprods.applocker.databinding.MaxappsActivityCongratulationBinding;

public class MaxApps_CongratulationActivity extends AppCompatActivity  {

    private MaxappsActivityCongratulationBinding play_fof_binding;

    private final String TAG = MaxApps_CongratulationActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        play_fof_binding = DataBindingUtil.setContentView(this , R.layout.maxapps_activity_congratulation);
        play_fof_bindMessage();
        play_fof_eventHandling();

    }









    private void play_fof_eventHandling(){
        play_fof_binding.buttonProceed.setOnClickListener(v->{




            Intent intent = new Intent(getApplicationContext() , MaxApps_MainActivity.class);
           startActivity(intent);
        });
    }

    private void aa_goOtherScreen(){
        Intent intent = new Intent(getApplicationContext() , MaxApps_MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void play_fof_bindMessage(){
        String message = getIntent().getStringExtra("message");
        play_fof_binding.displayMessage.setText(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}