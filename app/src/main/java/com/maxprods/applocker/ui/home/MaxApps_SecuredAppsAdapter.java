package com.maxprods.applocker.ui.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import  com.maxprods.applocker.R;
import com.maxprods.applocker.ui.model.MaxApps_AppList;
import com.maxprods.applocker.ui.util.MaxApps_GlobalMethods;

import java.util.ArrayList;
import java.util.List;

public class MaxApps_SecuredAppsAdapter extends RecyclerView.Adapter<MaxApps_SecuredAppsAdapter.InstalledViewHolder> {
    private Context play_fof_maxappss_context;
    private List<MaxApps_AppList> play_fof_maxappss_appLists;
    // private boolean play_fof_maxappss_flag = false;
    private boolean Flag;

    private boolean play_fof_maxappss_purchaseFlag = false;


    public MaxApps_SecuredAppsAdapter(Context context, List<MaxApps_AppList> appLists, boolean Flag) {
        this.play_fof_maxappss_context = context;
        this.play_fof_maxappss_appLists = appLists;
        this.Flag=Flag;


    }

    @NonNull
    @Override
    public InstalledViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstalledViewHolder(LayoutInflater.from(play_fof_maxappss_context).inflate(R.layout.maxapps_item_row_apps, parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstalledViewHolder holder, int position) {
        holder.play_fof_bindIcon(play_fof_maxappss_appLists.get(position).getIcon());
        holder.play_fof_bindName(play_fof_maxappss_appLists.get(position).getName());
        holder.play_fof_bindPackageName(play_fof_maxappss_appLists.get(position).getPackages());
        holder.play_fof_bindBlock();
        if (!play_fof_maxappss_purchaseFlag){
           // holder.bindAd();
        }

    }

    @Override
    public int getItemCount() {
        return play_fof_maxappss_appLists.size();
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

        void play_fof_bindName(String name){
            TextView mDisplayName = itemView.findViewById(R.id.item_row_displayName);
            mDisplayName.setText(name);
        }
        void play_fof_bindIcon(Drawable icon){
            ImageView mIcon = itemView.findViewById(R.id.item_row_icon);
            Glide.with(play_fof_maxappss_context)
                    .load(icon)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate()
                    .skipMemoryCache(true)
                    .override(80 , 80)
                    .placeholder(R.drawable.fofapps2_placeholder)
                    .into(mIcon);
        }
        void play_fof_bindPackageName(String packageName){
            TextView fof_play_mPackageName = itemView.findViewById(R.id.item_row_displayPackage);
            fof_play_mPackageName.setText(packageName);
        }
        void play_fof_bindBlock(){

            final ArrayList<String> blockList = MaxApps_GlobalMethods.maxapps_ashes_getBlockList(play_fof_maxappss_context);
            if (!blockList.isEmpty()){

                for (int i = 0; i < play_fof_maxappss_appLists.size(); i++){
                    for (int j = 0; j < blockList.size(); j++){
                        if (play_fof_maxappss_appLists.get(getAbsoluteAdapterPosition()).getPackages().equals(blockList.get(j))){
                            mDisplayUnlocked.setVisibility(View.GONE);
                            mDisplayLock.setVisibility(View.VISIBLE);

                            MaxApps_AppList app = play_fof_maxappss_appLists.get(getAbsoluteAdapterPosition());
                            app.setLocked(true);
                            play_fof_maxappss_appLists.set(getAbsoluteAdapterPosition() , app);

                            break;
                        }else {
                            MaxApps_AppList app = play_fof_maxappss_appLists.get(getAbsoluteAdapterPosition());
                            app.setLocked(false);
                            play_fof_maxappss_appLists.set(getAbsoluteAdapterPosition() , app);

                            mDisplayUnlocked.setVisibility(View.VISIBLE);
                            mDisplayLock.setVisibility(View.GONE);
                        }
                    }
                }
            }

            /*fof_play_mBlockView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PlayFofGamesGlobalMethods.play_fof_addAppToBlock(play_fof_context, play_fof_maxappss_appLists.get(getAdapterPosition()).getPackages());

                    fof_play_mBlockView.setVisibility(View.GONE);
                    fof_play_mUnBlockView.setVisibility(View.VISIBLE);

                }
            });
            fof_play_mUnBlockView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PlayFofGamesGlobalMethods.play_fof_removeAppFromBlock(play_fof_context, play_fof_maxappss_appLists.get(getAdapterPosition()).getPackages());
                    fof_play_mBlockView.setVisibility(View.VISIBLE);
                    fof_play_mUnBlockView.setVisibility(View.GONE);


                }
            });*/


        }

        @Override
        public void onClick(View v) {

            MaxApps_AppList app = play_fof_maxappss_appLists.get(getAbsoluteAdapterPosition());
            if (play_fof_maxappss_appLists.get(getAbsoluteAdapterPosition()).isLocked()){
                mDisplayLock.setVisibility(View.GONE);
                mDisplayUnlocked.setVisibility(View.VISIBLE);

                // play_fof_maxappss_flag = false;

            }else{
                mDisplayLock.setVisibility(View.VISIBLE);
                mDisplayUnlocked.setVisibility(View.GONE);
                app.setLocked(true);
                play_fof_maxappss_appLists.set(getAbsoluteAdapterPosition() , app);




                MaxApps_GlobalMethods.maxapps_ashes_addAppToBlock(play_fof_maxappss_context, play_fof_maxappss_appLists.get(getAdapterPosition()).getPackages());

                //  play_fof_maxappss_flag = true;
            }

        }


    }


}
