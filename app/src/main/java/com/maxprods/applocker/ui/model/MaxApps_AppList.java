package com.maxprods.applocker.ui.model;

import android.graphics.drawable.Drawable;

public class MaxApps_AppList {
    private String play_fof_maxappss_name;
    Drawable play_fof_maxappss_icon;
    private String play_fof_maxappss_packages;
    private boolean isLocked;

    public MaxApps_AppList(String name, Drawable icon, String packages) {
        this.play_fof_maxappss_name = name;
        this.play_fof_maxappss_icon = icon;
        this.play_fof_maxappss_packages = packages;
    }



    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public String getName() {
        return play_fof_maxappss_name;
    }

    public void setName(String name) {
        this.play_fof_maxappss_name = name;
    }

    public Drawable getIcon() {
        return play_fof_maxappss_icon;
    }

    public void setIcon(Drawable icon) {
        this.play_fof_maxappss_icon = icon;
    }

    public String getPackages() {
        return play_fof_maxappss_packages;
    }

    public void setPackages(String packages) {
        this.play_fof_maxappss_packages = packages;
    }
}
