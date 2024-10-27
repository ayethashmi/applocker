package com.maxprods.applocker.ui.adapters;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.PurchaseInfo;*/
import com.maxprods.applocker.services.MaxApps_MyBlockService;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import  com.maxprods.applocker.R;
import com.maxprods.applocker.ui.model.MaxApps_AppList;
import com.maxprods.applocker.ui.util.MaxApps_GlobalMethods;

import java.util.ArrayList;
import java.util.List;

public class MaxApps_InstalledAppsAdapter extends RecyclerView.Adapter<MaxApps_InstalledAppsAdapter.InstalledViewHolder>{
    private List<MaxApps_AppList> maxappsashes_maxappss_appLists;

    private boolean play_fof_maxappss_purchaseFlag = false;

    public MaxApps_InstalledAppsAdapter(List<MaxApps_AppList> appLists) {
        this.maxappsashes_maxappss_appLists = appLists;
       // play_fof_maxappss_setupBilling(context);


    }

    @NonNull
    @Override
    public InstalledViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstalledViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.maxapps_item_row_apps, parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstalledViewHolder holder, int position) {
        holder.play_fof_maxappss_bindIcon(maxappsashes_maxappss_appLists.get(position).getIcon());
        holder.play_fof_maxappss_bindName(maxappsashes_maxappss_appLists.get(position).getName());
        holder.play_fof_maxappss_bindPackageName(maxappsashes_maxappss_appLists.get(position).getPackages());
        holder.play_fof_maxappss_bindBlock();
        /*if (!play_fof_maxappss_purchaseFlag){

        }*/
       // holder.bindAd();
    }

    @Override
    public int getItemCount() {
        return maxappsashes_maxappss_appLists.size();
    }



    public class InstalledViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView mDisplayLock;
        ImageView mDisplayUnlocked;
        public InstalledViewHolder(@NonNull View itemView) {
            super(itemView);
            setIsRecyclable(false);
            itemView.setOnClickListener(this);
            mDisplayLock = itemView.findViewById(R.id.item_row_displayLocked);
            mDisplayUnlocked = itemView.findViewById(R.id.item_row_displayUnLock);
        }



        void play_fof_maxappss_bindName(String name){
            TextView mDisplayName = itemView.findViewById(R.id.item_row_displayName);
            mDisplayName.setText(name);
        }
        void play_fof_maxappss_bindIcon(Drawable icon){
            ImageView mIcon = itemView.findViewById(R.id.item_row_icon);
            Glide.with(itemView.getContext())
                    .load(icon)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate()
                    .skipMemoryCache(true)
                    .override(80 , 80)
                    .placeholder(R.drawable.fofapps2_placeholder)
                    .into(mIcon);
        }
        void play_fof_maxappss_bindPackageName(String packageName){
            TextView fof_play_mPackageName = itemView.findViewById(R.id.item_row_displayPackage);
            fof_play_mPackageName.setText(packageName);
        }
        void play_fof_maxappss_bindBlock(){

            final ArrayList<String> blockList = MaxApps_GlobalMethods.maxapps_ashes_getBlockList(itemView.getContext());
            if (!blockList.isEmpty()){

                for (int i = 0; i < maxappsashes_maxappss_appLists.size(); i++){
                    for (int j = 0; j < blockList.size(); j++){
                        if (maxappsashes_maxappss_appLists.get(getAbsoluteAdapterPosition()).getPackages().equals(blockList.get(j))){
                            mDisplayUnlocked.setVisibility(View.GONE);
                            mDisplayLock.setVisibility(View.VISIBLE);


                            break;
                        }else {
                            mDisplayUnlocked.setVisibility(View.VISIBLE);
                            mDisplayLock.setVisibility(View.GONE);
                        }
                    }
                }
            }



        }



        @Override
        public void onClick(View v) {
            Log.d("AYESHA",""+ MaxApps_GlobalMethods.isDialogOpen);
            MaxApps_MyBlockService.ENTERy="ENTER";



                    MaxApps_AppList app = maxappsashes_maxappss_appLists.get(getAbsoluteAdapterPosition());
                    if (maxappsashes_maxappss_appLists.get(getAbsoluteAdapterPosition()).isLocked()){
                        mDisplayLock.setVisibility(View.GONE);
                        mDisplayUnlocked.setVisibility(View.VISIBLE);
                        app.setLocked(false);
                        maxappsashes_maxappss_appLists.set(getAbsoluteAdapterPosition() , app);

                        MaxApps_GlobalMethods.maxapps_ashes_removeAppFromBlock(v.getContext(), maxappsashes_maxappss_appLists.get(getAdapterPosition()).getPackages());

                        // play_fof_maxappss_flag = false;

                    }else{
                        mDisplayLock.setVisibility(View.VISIBLE);
                        mDisplayUnlocked.setVisibility(View.GONE);
                        app.setLocked(true);
                        maxappsashes_maxappss_appLists.set(getAbsoluteAdapterPosition() , app);




                        MaxApps_GlobalMethods.maxapps_ashes_addAppToBlock(v.getContext(), maxappsashes_maxappss_appLists.get(getAdapterPosition()).getPackages());

                        //  play_fof_maxappss_flag = true;
                    }
            }







    }



}
