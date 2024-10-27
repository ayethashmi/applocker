package com.maxprods.applocker.activities;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maxprods.applocker.databinding.MaxappsActivityMainBinding;
import com.maxprods.applocker.services.MaxApps_MyBlockService;
import com.maxprods.applocker.ui.home.MaxApps_SecuredAppsActivity;
import com.maxprods.applocker.ui.themes.MaxApps_ThemesActivity;
import com.maxprods.applocker.ui.util.MaxApps_GlobalMethods;
import com.maxprods.applocker.ui.util.MaxApps_MyConstant;
import com.maxprods.applocker.R;
import com.google.android.material.navigation.NavigationView;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import java.io.File;
import java.util.List;

public class MaxApps_MainActivity extends AppCompatActivity {
    private MaxappsActivityMainBinding binding;
    private Dialog dialog;
    private  int maxapps_ashes_navIndex = 0;
    int drawer_navIndex=0;
    private String tag = null;
    private Dialog ratingDialog, exitDialog;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        binding= MaxappsActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prefs = this.getSharedPreferences(
                "rate", Context.MODE_PRIVATE);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        maxapps_ashes_setupToolbar();

        tag = getIntent().getStringExtra("tag");
        maxappsasheseventHandling();




    }

    private void maxappsasheseventHandling(){

        binding.appLock.setOnClickListener(v->{
            //  AdManager.AppLock_loadInterstitial(activity);
            if (  !MaxApps_GlobalMethods.hasUsageStatsPermission(getApplicationContext()) || !MaxApps_GlobalMethods.canDrawOverlays(getApplicationContext())) {
                MaxApps_GlobalMethods.isDialogOpen=false;

                MaxApps_GlobalMethods.maxapps_ashes_bottomShieldDialogue(MaxApps_MainActivity.this);


            }else {
                Intent intent=new Intent(getApplicationContext(), MaxApps_LoadAppsActivity.class);
                startActivity(intent);
                //  replaceFragment(new GameAshes2_BlockAppsFragment());

            }
        });
        binding.myThemeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  AdManager.theme_loadInterstitial(activity);
                Intent intent = new Intent(getApplicationContext() , MaxApps_ThemesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);





            }
        });
        binding.settingVault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AdManager.AppSettings_loadInterstitial(activity);
                Intent intent = new Intent(getApplicationContext(), MaxApps_AppSettingActivity.class);
                startActivity(intent);


            }
        });
        binding.statussever.setOnClickListener(v->{
            Statussever();
        });
        binding.vault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SDK_INT >= Build.VERSION_CODES.R) {
                    //  AdManager.photo_loadInterstitial(activity);

                    if (!Environment.isExternalStorageManager()) {
                        // perform action when allow permission success
                        maxapps_ashesmanageFileDialog();
                    } else{
                        makeFolder();
//                        Intent intent = new Intent(getApplicationContext(), maxapps_VaultActivity.class);
//                        startActivity(intent);
                    }
                }else{
                    PermissionX.init(MaxApps_MainActivity.this)
                            .permissions(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                            .request(new RequestCallback() {
                                @Override
                                public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                                    //  AdManager.photo_loadInterstitial(activity);

                                    makeFolder();
//                                    Intent intent = new Intent(getApplicationContext(), maxapps_VaultActivity.class);
//                                    startActivity(intent);
                                    }
                            });
                }


            }
        });

        binding.photoVault.setOnClickListener(v->{
            if (SDK_INT >= Build.VERSION_CODES.R) {
                //  AdManager.photo_loadInterstitial(activity);

                if (!Environment.isExternalStorageManager()) {
                    // perform action when allow permission success
                    maxapps_ashesmanageFileDialog();
                } else{
                    makeFolder();
//                    enterListScreen(MaxApps_MyConstant.VAULT, MaxApps_MyConstant.PHOTO, new Intent(getApplicationContext(), maxapps_LockedActivity.class));
                }
            }else{
                PermissionX.init(this)
                        .permissions(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                        .request(new RequestCallback() {
                            @Override
                            public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                                //  AdManager.photo_loadInterstitial(activity);

                                makeFolder();
//                                enterListScreen(MaxApps_MyConstant.VAULT , MaxApps_MyConstant.PHOTO , new Intent(getApplicationContext(), maxapps_LockedActivity.class));
}
                        });
            }



        });

        binding.videoVault.setOnClickListener(v->{
            if (SDK_INT >= Build.VERSION_CODES.R) {
                //  AdManager.Video_loadInterstitial(activity);

                if (!Environment.isExternalStorageManager()) {
                    // perform action when allow permission success
                    maxapps_ashesmanageFileDialog();
                } else{
//                    enterListScreen(MaxApps_MyConstant.VAULT , MaxApps_MyConstant.VIDEO , new Intent(getApplicationContext() , maxapps_LockedActivity.class));
}
            }else{
                PermissionX.init(MaxApps_MainActivity.this)
                        .permissions(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                        .request(new RequestCallback() {
                            @Override
                            public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                                // AdManager.Video_loadInterstitial(activity);
//                                enterListScreen(MaxApps_MyConstant.VAULT , MaxApps_MyConstant.VIDEO , new Intent(getApplicationContext() , maxapps_LockedActivity.class));
}
                        });
            }


        });

/*
        binding.fingerprints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent=new Intent(getApplicationContext(), maxapps_FingerprintsActivity.class);
                startActivity(intent);

            }
        });
*/
        binding.intruder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
//                intent=new Intent(getApplicationContext(), maxapps_IntruderSelfieActivity.class);
//                startActivity(intent);

            }
        });

    }
    private void makeFolder(){
        File file=new File(Environment.getExternalStorageDirectory()+"/Pictures/AppLockSelfies");
        boolean isSucess=file.exists();
        if(!isSucess){
            file.mkdirs();
        }
    }

    public  void Statussever(){
        try{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.status.saver.wadownloader.status.downloader.savestatus")));
        }
        catch (ActivityNotFoundException e){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.status.saver.wadownloader.status.downloader.savestatus")));
        }
    }
    public  void maxapps_ashesmanageFileDialog(){

        dialog = new Dialog(MaxApps_MainActivity.this);
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
                            intent.setData(Uri.parse(String.format("package:%s",getPackageName())));
                            startActivity(intent);
                        } catch (Exception e) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                            startActivity(intent);
                        }                    }
                },600);

                dialog.dismiss();
            }
        });

        dialog.show();


    }




    private void maxapps_ashes_setupToolbar() {
        setSupportActionBar(binding.toolbar);
        setupDrawerToggle(binding.drawerLayout, binding.toolbar).setDrawerIndicatorEnabled(true);
        binding.drawerLayout.addDrawerListener(setupDrawerToggle(binding.drawerLayout, binding.toolbar));
        setupDrawerToggle(binding.drawerLayout, binding.toolbar).syncState();
        setupNavigationView();

    }


    private ActionBarDrawerToggle setupDrawerToggle(DrawerLayout mDrawer, Toolbar toolbar) {
        return new ActionBarDrawerToggle(MaxApps_MainActivity.this, mDrawer, toolbar, R.string.openDrawer, R.string.closeDrawer);
    }

    public  void maxapps_ashes_RateUs(){
        try{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
        }
        catch (ActivityNotFoundException e){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
        }
    }

    private void setupNavigationView() {

        binding.navigationView.getMenu().getItem(maxapps_ashes_navIndex).setChecked(true);
        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {



                    case R.id.nav_setting:
                        try {
                            drawer_navIndex = 1;
                            binding.navigationView.getMenu().getItem(maxapps_ashes_navIndex).setChecked(true);
                            binding.drawerLayout.closeDrawers();
                          //  binding.tabLayout.getTabAt(1).select();
                            //  FofApps2AdManager.showInterstitialWithoutIntent(FofApps2MainActivity.this);
                            menuItem.setChecked(false);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return true;
                    case R.id.nav_support:
                        try {
                            drawer_navIndex = 2;
                            binding.navigationView.getMenu().getItem(maxapps_ashes_navIndex).setChecked(true);
                            binding.drawerLayout.closeDrawers();


                            maxapps_ashes_RateUs();
                            menuItem.setChecked(false);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return true;
                    case R.id.nav_share:
                        try {
                            drawer_navIndex = 3;
                            binding.navigationView.getMenu().getItem(maxapps_ashes_navIndex).setChecked(true);
                            binding.drawerLayout.closeDrawers();
                            shareApp();
                            menuItem.setChecked(false);

                            return true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    case R.id.nav_theme:
                        try {
                            drawer_navIndex = 4;
                            binding.navigationView.getMenu().getItem(maxapps_ashes_navIndex).setChecked(true);
                            binding.drawerLayout.closeDrawers();
                            Intent intent = new Intent(getApplicationContext(), MaxApps_ThemesActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            //AdManager.theme_showInterstitial(GameAshes2_MainActivity.this, intent);
                            menuItem.setChecked(false);

                            //  startActivity(intent);
                            // FofApps2AdManager.showInterstitial(FofApps2MainActivity.this, intent);
                            return true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    case R.id.security:
                        binding.navigationView.getMenu().getItem(maxapps_ashes_navIndex).setChecked(true);
                        binding.drawerLayout.closeDrawers();
                        Intent intent = new Intent(getApplicationContext(), MaxApps_SecuritySettingActivity.class);
                        startActivity(intent);
                        return true;

                 /*   case R.id.nav_selfies:
                        play_fof_navIndex=5;
                        play_fof_binding.navigationView.getMenu().getItem(play_fof_navIndex).setChecked(true);
                        play_fof_binding.drawerLayout.closeDrawers();
                        Intent i = new Intent(GameAshes2_MainActivity.this, SelfiesActivity.class);
                        startActivity(i);
                        makeFolder();*/

                    case R.id.mSecure:
                        maxapps_ashes_navIndex = 6;
                        binding.navigationView.getMenu().getItem(maxapps_ashes_navIndex).setChecked(true);
                        binding.drawerLayout.closeDrawers();
                        enterListScreen(MaxApps_MyConstant.APP_SECURITY, MaxApps_MyConstant.SECURED, new Intent(getApplicationContext(), MaxApps_SecuredAppsActivity.class));
                        return true;

                    case R.id.mUnsecure:
                        maxapps_ashes_navIndex = 7;
                        binding.navigationView.getMenu().getItem(maxapps_ashes_navIndex).setChecked(true);
                        binding.drawerLayout.closeDrawers();
                        enterListScreen(MaxApps_MyConstant.APP_SECURITY, MaxApps_MyConstant.UNSECURED, new Intent(getApplicationContext(), MaxApps_SecuredAppsActivity.class));
                        return true;
                }
                return true;
            }
        });
    }
    private void shareApp() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            String shareMessage = "\nLet me recommend you use this app blocker to secure your apps.\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + getPackageName() + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }

  
    private void enterListScreen(String key , String fileType , Intent intent){
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(key , fileType);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {


        if (!MaxApps_GlobalMethods.maxapps_ashes_getRating(getApplicationContext()) && !MaxApps_GlobalMethods.maxapps_ashes_getOneTimeRating(getApplicationContext())) {

            displayRatingDialog();

        }else{
            displayExitDialog();


            MaxApps_MyBlockService.ScreenNAme = "NOAd";

        }

    }


    private void displayExitDialog() {
        exitDialog = new Dialog(MaxApps_MainActivity.this);
        exitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
exitDialog.setContentView(R.layout.maxapps_exit_dialog);
        Window window = exitDialog.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        exitDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        ImageView yes = exitDialog.findViewById(R.id.buttonyes);
        ImageView no = exitDialog.findViewById(R.id.buttonNo);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitDialog.dismiss();
               finish();


            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitDialog.dismiss();

            }
        });

        exitDialog.setCancelable(false);
        exitDialog.show();


    }

    private void displayRatingDialog() {
        ratingDialog = new Dialog(MaxApps_MainActivity.this);
        ratingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ratingDialog.setContentView(R.layout.maxapps_ashes_rating_dialog);
        Window window = ratingDialog.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        ratingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        RatingBar mRatingBar = ratingDialog.findViewById(R.id.ratingBar);
        ImageView feedback = ratingDialog.findViewById(R.id.feedback);
        // TextView textExit=ratingDialog.findViewById(R.id.exitdialog);
        TextView exit = ratingDialog.findViewById(R.id.EXIT);
        TextView later = ratingDialog.findViewById(R.id.LATER);
        ImageView rateNow = ratingDialog.findViewById(R.id.imageViewRateNow);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ratingDialog.dismiss();
                finish();

            }
        });
        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaxApps_GlobalMethods.maxapps_ashes_saveOneTimeRating(getApplicationContext(), true);

                ratingDialog.dismiss();
            }
        });
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                float rate = mRatingBar.getRating();

                /*if(rate==3 && rate != 4)*/
                if (rate <= 3) {

                    rateNow.setVisibility(View.GONE);
                    feedback.setVisibility(View.VISIBLE);

                 /*   textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "your_email"));
                                intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
                                intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                                startActivity(intent);
                            } catch (ActivityNotFoundException e){
                                //TODO smth
                            }

                        }
                    });*/
                    MaxApps_GlobalMethods.maxapps_ashes_saveRating(getApplicationContext(), true);


                } else if (rate > 3) {
                    rateNow.setVisibility(View.VISIBLE);
                    feedback.setVisibility(View.GONE);

                    MaxApps_GlobalMethods.maxapps_ashes_saveRating(getApplicationContext(), true);

                }

                SharedPreferences.Editor editor2 = prefs.edit();
                editor2.putBoolean("rating", true);
                editor2.apply();

            }
        });


        feedback.setOnClickListener(v -> {
            rateNow.setVisibility(View.GONE);
            feedback();
            ratingDialog.dismiss();
            SharedPreferences.Editor editor2 = prefs.edit();
            editor2.putBoolean("rating", true);
            editor2.apply();

            ratingDialog.dismiss();
        });


        rateNow.setOnClickListener(v -> {

            rateUs();
            ratingDialog.dismiss();


            SharedPreferences.Editor editor2 = prefs.edit();
            editor2.putBoolean("rating", true);
            editor2.apply();

            ratingDialog.dismiss();
        });

        ratingDialog.setCancelable(false);


        ratingDialog.show();


    }

    private void feedback() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "your_email"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
        intent.putExtra(Intent.EXTRA_TEXT, "your_text");
        startActivity(intent);
    }

    private void rateUs() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id" + getPackageName())));

        }
    }

    @Override
    protected void onDestroy() {
        setSupportActionBar(null);
        if (ratingDialog!=null && exitDialog!=null){
            ratingDialog=null;
            exitDialog=null;

        }
        if (ratingDialog != null && ratingDialog.isShowing()) {
            ratingDialog.dismiss();
        }
        if (exitDialog != null && exitDialog.isShowing()) {
            exitDialog.dismiss();
        }
        super.onDestroy();
        MaxApps_MyBlockService.ScreenNAme = "NOAd";


    }

    @Override
    protected void onStop() {
        super.onStop();
        MaxApps_MyBlockService.ScreenNAme="NOAd";

    }

/*
  */


    @Override
    protected void onResume() {
        super.onResume();
        MaxApps_MyBlockService.ScreenNAme = "main";


    }
}
