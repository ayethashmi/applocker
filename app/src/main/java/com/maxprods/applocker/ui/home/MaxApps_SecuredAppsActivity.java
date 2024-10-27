package com.maxprods.applocker.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;


import com.maxprods.applocker.activities.MaxApps_MainActivity;
import  com.maxprods.applocker.R;

import com.maxprods.applocker.databinding.MaxappsActivitySecuredAppsBinding;
import com.maxprods.applocker.services.MaxApps_MyBlockService;
import com.maxprods.applocker.ui.model.MaxApps_AppList;
import com.maxprods.applocker.ui.util.MaxApps_GlobalMethods;
import com.maxprods.applocker.ui.util.MaxApps_MyConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MaxApps_SecuredAppsActivity extends AppCompatActivity {
    private MaxappsActivitySecuredAppsBinding binding;

    boolean Flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.maxapps_activity_secured_apps);


        maxappsashessetupRecyclerView();
        maxappsashessecureAppBtn();

    }
    private void maxappsashessetupRecyclerView(){
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void loadData(){
        String type = getIntent().getStringExtra(MaxApps_MyConstant.APP_SECURITY);
        switch (type){
            case MaxApps_MyConstant.SECURED:
               secureApps();
                // filterSecuredApps();
                //new FFBgTask().execute();
                binding.toolbar.setTitle("Secured Apps");
                break;
            default:
                binding.toolbar.setTitle("Unsecured Apps");
                unSecuredApps();
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        loadData();
        MaxApps_MyBlockService.ScreenNAme="main";

    }
    private void unSecuredApps(){
        binding.secureapp.setVisibility(View.GONE);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override


            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        binding.progressbar.setVisibility(View.VISIBLE);

                    }
                });

                List<MaxApps_AppList> blockApps = new ArrayList<>();
                List<MaxApps_AppList> installed = MaxApps_GlobalMethods.maxapps_ashes_getInstalledApps(getApplicationContext());
                List<MaxApps_AppList> system = MaxApps_GlobalMethods.zz_getSystemApps(getApplicationContext());
                installed.addAll(system);
                ArrayList<String> blockList =  MaxApps_GlobalMethods.maxapps_ashes_getBlockList(getApplicationContext());

                for (MaxApps_AppList apps : installed){
                    if (!blockList.contains(apps.getPackages())){
                        blockApps.add(apps);
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        MaxApps_SecuredAppsAdapter adapter = new MaxApps_SecuredAppsAdapter(getApplicationContext(), blockApps,Flag);
                        binding.recyclerView.setAdapter(adapter);
                        binding.progressbar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
    }

    private void secureApps(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        binding.progressbar.setVisibility(View.VISIBLE);

                    }
                });
                binding.secureapp.setVisibility(View.GONE);


                List<MaxApps_AppList> blockApps = new ArrayList<>();
                List<MaxApps_AppList> installed = MaxApps_GlobalMethods.maxapps_ashes_getInstalledApps(getApplicationContext());
                List<MaxApps_AppList> system = MaxApps_GlobalMethods.zz_getSystemApps(getApplicationContext());
                installed.addAll(system);
                ArrayList<String> blockList =  MaxApps_GlobalMethods.maxapps_ashes_getBlockList(getApplicationContext());
                for (String name : blockList){
                    for (MaxApps_AppList apps : installed){
                        if (name.equals(apps.getPackages())){
                            blockApps.add(apps);
                        }
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        MaxApps_SecuredAppsAdapter adapter = new MaxApps_SecuredAppsAdapter(getApplicationContext(), blockApps,Flag);
                        binding.recyclerView.setAdapter(adapter);
                        binding.progressbar.setVisibility(View.GONE);
                        if (blockApps.isEmpty())
                        {
                            binding.secureapp.setVisibility(View.VISIBLE);

                        }else {
                            binding.secureapp.setVisibility(View.GONE);

                        }
                    }
                });
            }
        });
    }
    private  void maxappsashessecureAppBtn(){
        binding.secureapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSecuredApps();
            }
        });
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext() , MaxApps_MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }


}