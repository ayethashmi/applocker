package com.maxprods.applocker.ui.home;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.maxprods.applocker.activities.MaxApps_AppSettingActivity;
import com.maxprods.applocker.activities.MaxApps_LoadAppsActivity;
import  com.maxprods.applocker.R;
import com.maxprods.applocker.databinding.MaxappsFragmentHomeBinding;
import com.maxprods.applocker.ui.themes.MaxApps_ThemesActivity;
import com.maxprods.applocker.ui.util.MaxApps_GlobalMethods;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import java.io.File;
import java.util.List;

public class MaxApps_HomeFragment extends Fragment {

    private MaxappsFragmentHomeBinding maxapps_ashes_binding;
    private Dialog dialog;
    private Activity activity;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        maxapps_ashes_binding = DataBindingUtil.inflate(inflater , R.layout.maxapps_fragment_home, container, false);






        return maxapps_ashes_binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        maxappsasheseventHandling();

    }
    private void maxappsasheseventHandling(){

        maxapps_ashes_binding.appLock.setOnClickListener(v->{
          //  AdManager.AppLock_loadInterstitial(activity);
            if (activity!=null){
                if (maxapps_ashes_binding!=null){
                    if (  !MaxApps_GlobalMethods.hasUsageStatsPermission(activity) || !MaxApps_GlobalMethods.canDrawOverlays(activity)) {
                        MaxApps_GlobalMethods.isDialogOpen=false;

                        MaxApps_GlobalMethods.maxapps_ashes_bottomShieldDialogue(activity);


                    }else {
                        Intent intent=new Intent(activity, MaxApps_LoadAppsActivity.class);
                        startActivity(intent);
                      //  replaceFragment(new GameAshes2_BlockAppsFragment());

                    }

                }
            }

        });
        maxapps_ashes_binding.myThemeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  AdManager.theme_loadInterstitial(activity);
                     if (activity!=null){
                         if (maxapps_ashes_binding!=null){
                             Intent intent = new Intent(activity , MaxApps_ThemesActivity.class);
                             intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                             startActivity(intent);

                         }
                     }





            }
        });
        maxapps_ashes_binding.settingVault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // AdManager.AppSettings_loadInterstitial(activity);
                if (activity!=null){
                    if (maxapps_ashes_binding!=null) {
                        Intent intent = new Intent(activity, MaxApps_AppSettingActivity.class);
                        startActivity(intent);


                    }}


            }
        });
        maxapps_ashes_binding.statussever.setOnClickListener(v->{
            apps_trend_Statussever();
        });

        maxapps_ashes_binding.photoVault.setOnClickListener(v->{
                if (SDK_INT >= Build.VERSION_CODES.R) {
                  //  AdManager.photo_loadInterstitial(activity);

                    if (!Environment.isExternalStorageManager()) {
                        // perform action when allow permission success
                     maxapps_ashesmanageFileDialog();
                    } else{
                        if (activity!=null){
                            if (maxapps_ashes_binding!=null) {
                                makeFolder();
//                                enterListScreen(MaxApps_MyConstant.VAULT, MaxApps_MyConstant.PHOTO, new Intent(activity, Game_LockedActivity.class));
                            }}
                    }
                }else{
                    PermissionX.init(this)
                            .permissions(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                            .request(new RequestCallback() {
                                @Override
                                public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                                  //  AdManager.photo_loadInterstitial(activity);

                                    makeFolder();
                                    if (activity!=null){
                                        if (maxapps_ashes_binding!=null){
//                                    enterListScreen(MaxApps_MyConstant.VAULT , MaxApps_MyConstant.PHOTO , new Intent(activity, Game_LockedActivity.class));
                                }}}
                            });
                }



        });

        maxapps_ashes_binding.videoVault.setOnClickListener(v->{
                if (SDK_INT >= Build.VERSION_CODES.R) {
                  //  AdManager.Video_loadInterstitial(activity);

                    if (!Environment.isExternalStorageManager()) {
                        // perform action when allow permission success
                       maxapps_ashesmanageFileDialog();
                    } else{
                        if (activity!=null){
                            if (maxapps_ashes_binding!=null){
//                        enterListScreen(MaxApps_MyConstant.VAULT , MaxApps_MyConstant.VIDEO , new Intent(activity , Game_LockedActivity.class));

                    }}}
                }else{
                    PermissionX.init(MaxApps_HomeFragment.this)
                            .permissions(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                            .request(new RequestCallback() {
                                @Override
                                public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                                   // AdManager.Video_loadInterstitial(activity);
                                    if (activity!=null){
                                        if (maxapps_ashes_binding!=null){
//                                    enterListScreen(MaxApps_MyConstant.VAULT , MaxApps_MyConstant.VIDEO , new Intent(activity , Game_LockedActivity.class));
                                }}}
                            });
                }


        });



    }
    public  void maxapps_ashesmanageFileDialog(){

         dialog = new Dialog(activity);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.maxapps_ashes_filemanage_permission);


        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.allow);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                            intent.addCategory("android.intent.category.DEFAULT");
                            intent.setData(Uri.parse(String.format("package:%s",activity.getPackageName())));
                            activity.startActivity(intent);
                        } catch (Exception e) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                            activity.startActivity(intent);
                        }                    }
                },600);

                dialog.dismiss();
            }
        });

        dialog.show();


    }

    private void enterListScreen(String key , String fileType , Intent intent){
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(key , fileType);
        startActivity(intent);
    }

    public  void apps_trend_Statussever(){
        try{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.status.saver.wadownloader.status.downloader.savestatus")));
        }
        catch (ActivityNotFoundException e){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.status.saver.wadownloader.status.downloader.savestatus")));
        }
    }


    @Override
    public void onDestroyView() {
        maxapps_ashes_binding=null;
        activity=null;
        if (dialog!=null){
            dialog=null;
        }
        super.onDestroyView();

    }

    private void makeFolder(){
        File file=new File(Environment.getExternalStorageDirectory()+"/Pictures/AppLockSelfies");
        boolean isSucess=file.exists();
        if(!isSucess){
            file.mkdirs();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity=(Activity) context;
    }
}