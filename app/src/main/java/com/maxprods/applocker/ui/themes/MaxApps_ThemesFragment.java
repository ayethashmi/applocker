package com.maxprods.applocker.ui.themes;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import  com.maxprods.applocker.R;
import com.maxprods.applocker.databinding.MaxappsFragmentThemesBinding;

import java.io.IOException;
import java.util.ArrayList;

public class MaxApps_ThemesFragment extends Fragment {
    private MaxappsFragmentThemesBinding binding;

    private Activity mContext;
    public MaxApps_ThemesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater , R.layout.maxapps_fragment_themes, container, false);


        maxapps_ashes_setupRecyclerView();
        maxapps_ashes_loadThemes();




        return binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }

    private void maxapps_ashes_setupRecyclerView(){
        binding.recyclerView.setLayoutManager(new GridLayoutManager(requireContext() , 1));
        binding.recyclerView.setHasFixedSize(true);
    }

    private void maxapps_ashes_loadThemes(){
        ArrayList<MaxApps_MyThemes> myList = new ArrayList<>();
        String [] list;
        try {
            list = requireActivity().getAssets().list("themes");
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

        binding.recyclerView.setLayoutManager(new GridLayoutManager(binding.getRoot().getContext(), 3));
        binding.recyclerView.setHasFixedSize(true);
        MaxApps_MyThemesAdapter adapter = new MaxApps_MyThemesAdapter( myList, requireActivity());
        binding.recyclerView.setAdapter(adapter);
    }

    private String getLockType(int position){
        if (position < 5){
            return MaxApps_ThemeUtil.PATTERN;
        }else{
            return MaxApps_ThemeUtil.PIN;
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}