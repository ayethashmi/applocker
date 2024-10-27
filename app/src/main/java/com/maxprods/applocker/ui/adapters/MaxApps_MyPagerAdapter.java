package com.maxprods.applocker.ui.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.maxprods.applocker.ui.model.MaxApps_MyPager;

import java.util.ArrayList;

public class MaxApps_MyPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<MaxApps_MyPager> fof_play_maxappss_pagerList;

    public MaxApps_MyPagerAdapter(@NonNull FragmentManager fm, int behavior , ArrayList<MaxApps_MyPager> pagerList) {
        super(fm, behavior);
        this.fof_play_maxappss_pagerList = pagerList;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fof_play_maxappss_pagerList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return fof_play_maxappss_pagerList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Privacy";
            case 1:
                return "Setting";
            default:
                return super.getPageTitle(position);
        }
    }
}
