package com.maxprods.applocker.ui.themes;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import  com.maxprods.applocker.R;


import java.util.ArrayList;

public class MaxApps_MyThemesAdapter extends RecyclerView.Adapter<MaxApps_MyThemesAdapter.WallpaperViewHolder>{
    private ArrayList<MaxApps_MyThemes> list;
    private int count = 0;
    private Activity activity;
    private CountDownTimer timer;

    static boolean isRunningTimer = false;
    public MaxApps_MyThemesAdapter(ArrayList<MaxApps_MyThemes> list, Activity activity) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WallpaperViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.maxapps_themes_item_row, parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperViewHolder holder, int position) {
        holder.bindImage(list.get(position).getPath());
       // holder.bindAd();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class WallpaperViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public WallpaperViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        void bindImage(String uri){
            ImageView mImage = itemView.findViewById(R.id.item_view_theme);
            Glide.with(itemView.getContext())
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate()
                    .skipMemoryCache(true)
                    .into(mImage);
        }

        @Override
        public void onClick(View v) {
            MaxApps_ThemeUtil.maxappsashes_saveThemeName(itemView.getContext() , list.get(getAbsoluteAdapterPosition()).getName());
            activity.finish();

        }
    }

    public void play_fof_displayDownloadThemeDialog(Activity context , Intent intent , String image){

        try{
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.maxapps_download_dialog);

            ImageView downloadNow = dialog.findViewById(R.id.buttonDownload);
            ImageView mCancel = dialog.findViewById(R.id.buttonCancel);
            RelativeLayout mProgressbarLayout = dialog.findViewById(R.id.progressbarLayout);
            ImageView mImage = dialog.findViewById(R.id.themeImage);
            Glide.with(context).load(image).into(mImage);
            ProgressBar mProgressbar = dialog.findViewById(R.id.progressbar);

       
            downloadNow.setOnClickListener(v->{
                downloadNow.setVisibility(View.GONE);
                mProgressbarLayout.setVisibility(View.VISIBLE);
                timer = new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        isRunningTimer = true;
                        count++;
                        mProgressbar.setProgress(count);

                    }

                    @Override
                    public void onFinish() {
                        dialog.dismiss();
                        count = 0;
                        /*FofApps2AdManager.showInterstitial(context , intent);*/
                        context.startActivity(intent);
                        isRunningTimer = false;
                    }
                };
                timer.start();
            });
            mCancel.setOnClickListener(v->{
                if (isRunningTimer){
                    timer.cancel();
                    count = 0;
                }
                dialog.dismiss();
            });

            dialog.setCancelable(false);
            dialog.show();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }


    }

}
