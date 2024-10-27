package com.maxprods.applocker.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.maxprods.applocker.databinding.MaxappsFragmentBlockAppsBinding;
import com.maxprods.applocker.services.MaxApps_MyBlockService;
import com.maxprods.applocker.ui.adapters.MaxApps_InstalledAppsAdapter;
import com.maxprods.applocker.ui.util.MaxApps_GlobalMethods;
import com.maxprods.applocker.ui.util.MaxApps_MyConstant;
import com.maxprods.applocker.R;

public class MaxApps_BlockAppsFragment extends Fragment {
    private MaxappsFragmentBlockAppsBinding mbinding;
    MaxApps_InstalledAppsAdapter adapter;
    private Activity activity;

    public MaxApps_BlockAppsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mbinding =  DataBindingUtil.inflate(inflater , R.layout.maxapps_fragment_block_apps, container, false);

        /***
         * methods
         */
        maxappsashesInstalledBgTask();

         if (mbinding !=null){

         }

        return mbinding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (activity!=null){
            if (mbinding!=null){
                MaxApps_MyConstant.firsttime=false;
                if (MaxApps_MyBlockService.FLAG1 || MaxApps_MyBlockService.FLAG2){
                    if(!MaxApps_GlobalMethods.hasUsageStatsPermission(mbinding.getRoot().getContext()) || !MaxApps_GlobalMethods.canDrawOverlays(mbinding.getRoot().getContext())){
                        MaxApps_GlobalMethods.maxapps_ashes_bottomShieldDialogue(mbinding.getRoot().getContext());
                    }else {
                        MaxApps_MyBlockService.FLAG1=false;
                        MaxApps_MyBlockService.FLAG2=false;
                    }
            }
        }





        }
    }

    private void maxappsashesInstalledBgTask(){
        if (activity!=null){
            if (mbinding!=null){
                mbinding.recyclerViewInstalled.setHasFixedSize(true);
                mbinding.recyclerViewInstalled.setLayoutManager(new LinearLayoutManager(mbinding.getRoot().getContext()));

//                appsViewModel = new ViewModelProvider(this).get(GameAshes2_AppsViewModel.class);
//                appsViewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<List<MaxApps_AppList>>() {
//                    @Override
//                    public void onChanged(List<MaxApps_AppList> fofAppsAppLists) {
//                        adapter  = new MaxApps_InstalledAppsAdapter( fofAppsAppLists);
//                        mbinding.recyclerViewInstalled.setAdapter(adapter);
//
//                    }
//                });
            }
        }

    }

    @Override
    public void onDestroyView() {
        activity=null;
        mbinding =null;
//       appsViewModel=null;
        super.onDestroyView();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity=(Activity) context;
    }
}