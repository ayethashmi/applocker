package com.maxprods.applocker.ui.themes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;


import  com.maxprods.applocker.R;

import com.maxprods.applocker.databinding.MaxappsActivityThemesBinding;
import com.maxprods.applocker.services.MaxApps_MyBlockService;
import com.maxprods.applocker.ui.util.MaxApps_MyConstant;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MaxApps_ThemesActivity extends AppCompatActivity {
    private MaxappsActivityThemesBinding play_fof_maxappss_binding;
    private WeakReference<Activity> weakReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        play_fof_maxappss_binding = DataBindingUtil.setContentView(this , R.layout.maxapps_activity_themes);
        maxappsashes_setupRecyclerView();
        maxappsashes_loadThemes();
        weakReference=new WeakReference<>(MaxApps_ThemesActivity.this);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        play_fof_maxappss_binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
      //  AdManager.theme_mInterstitialAd=null;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void maxappsashes_setupRecyclerView(){
        play_fof_maxappss_binding.recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext() , 3));
        play_fof_maxappss_binding.recyclerView.setHasFixedSize(true);
    }

    private void maxappsashes_loadThemes(){
        ArrayList<MaxApps_MyThemes> myList = new ArrayList<>();
        String [] list;
        try {
            list = getAssets().list("themes");
            if (list.length > 0) {
                for (String file : list) {
                    String completePath = "file:///android_asset/themes/" + file;
                    myList.add(new MaxApps_MyThemes(file,completePath));
                    Log.d("ADIL" , completePath);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        play_fof_maxappss_binding.recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext() , 3));
        play_fof_maxappss_binding.recyclerView.setHasFixedSize(true);
        MaxApps_MyThemesAdapter adapter = new MaxApps_MyThemesAdapter( myList, MaxApps_ThemesActivity.this);
        play_fof_maxappss_binding.recyclerView.setAdapter(adapter);
    }

    private String getLockType(int position){
        if (position < 5){
            return MaxApps_ThemeUtil.PATTERN;
        }else{
            return MaxApps_ThemeUtil.PIN;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        MaxApps_MyBlockService.ScreenNAme="main";
        MaxApps_MyConstant.firsttime=true;

    }


}