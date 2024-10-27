package com.maxprods.applocker.ui.model;

import androidx.fragment.app.Fragment;

public class MaxApps_MyPager {
    private int play_fof_maxappss_drawable;
    private Fragment play_fof_maxappss_ragment;

    public MaxApps_MyPager(int drawable, Fragment fragment) {
        this.play_fof_maxappss_drawable = drawable;
        this.play_fof_maxappss_ragment = fragment;
    }

    public int getDrawable() {
        return play_fof_maxappss_drawable;
    }

    public void setDrawable(int drawable) {
        this.play_fof_maxappss_drawable = drawable;
    }

    public Fragment getFragment() {
        return play_fof_maxappss_ragment;
    }

    public void setFragment(Fragment fragment) {
        this.play_fof_maxappss_ragment = fragment;
    }
}
