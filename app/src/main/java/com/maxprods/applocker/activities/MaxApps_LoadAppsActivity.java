package com.maxprods.applocker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.maxprods.applocker.databinding.MaxappsActivityLoadAppsBinding;
import com.maxprods.applocker.services.MaxApps_MyBlockService;
import com.maxprods.applocker.ui.adapters.MaxApps_InstalledAppsAdapter;
import com.maxprods.applocker.ui.util.MaxApps_GlobalMethods;
import com.maxprods.applocker.ui.util.MaxApps_MyConstant;
import com.maxprods.applocker.R;

import java.lang.ref.WeakReference;

public class MaxApps_LoadAppsActivity extends AppCompatActivity {
    private MaxappsActivityLoadAppsBinding binding;
    private WeakReference<Activity> weakReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.maxapps_activity_load_apps);
        setupRecyclerView();
        getInstalledApps();

        weakReference=new WeakReference<>(MaxApps_LoadAppsActivity.this);

        binding.back.setOnClickListener(v->{
            onBackPressed();
        });

    }

    private void setupRecyclerView(){
        binding.recyclerViewInstalled.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recyclerViewInstalled.setHasFixedSize(true);

    }
    private void getInstalledApps(){
        if (!MaxApps_MyConstant.appsList.isEmpty()){

            MaxApps_InstalledAppsAdapter adapter = new MaxApps_InstalledAppsAdapter( MaxApps_MyConstant.appsList);
            binding.recyclerViewInstalled.setAdapter(adapter);
            binding.progressBar.setVisibility(View.INVISIBLE);


        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (MaxApps_MyBlockService.FLAG1 || MaxApps_MyBlockService.FLAG2){
            if(!MaxApps_GlobalMethods.hasUsageStatsPermission(getApplicationContext()) || !MaxApps_GlobalMethods.canDrawOverlays(getApplicationContext())){

            }else {
                MaxApps_MyBlockService.FLAG1=false;
                MaxApps_MyBlockService.FLAG2=false;
            }


        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}