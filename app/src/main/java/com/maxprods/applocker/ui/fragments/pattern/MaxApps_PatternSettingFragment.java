package com.maxprods.applocker.ui.fragments.pattern;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.maxprods.applocker.databinding.MaxappsFragmentPatternSettingBinding;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;
import  com.maxprods.applocker.R;
import com.maxprods.applocker.ui.pattern.MaxApps_PatternPasscodeActivity;
import com.maxprods.applocker.ui.pattern.MaxApps_PatternUtil;

import java.util.List;


public class MaxApps_PatternSettingFragment extends Fragment {
    private MaxappsFragmentPatternSettingBinding maxapps_ashes_binding;
    private Activity activity;
    public MaxApps_PatternSettingFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        maxapps_ashes_binding = DataBindingUtil.inflate(inflater , R.layout.maxapps_fragment_pattern_setting, container, false);


        maxapps_ashes_eventHandling();

        return maxapps_ashes_binding.getRoot();
    }
    private void maxapps_ashes_eventHandling(){
        maxapps_ashes_binding.changePatternLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity!=null){
                    if (maxapps_ashes_binding!=null){
                        maxappsashes_goAnotherScreen(new Intent(maxapps_ashes_binding.getRoot().getContext() , MaxApps_PatternPasscodeActivity.class));

                    }
                }
            }
        });
        maxapps_ashes_binding.makePatternVisible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (activity!=null){
                    if (maxapps_ashes_binding!=null){
                    MaxApps_PatternUtil.play_fof_makePasswordVisible(maxapps_ashes_binding.getRoot().getContext(), b);


            }}}
        });

        maxapps_ashes_binding.touchVibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (activity!=null){
                    if (maxapps_ashes_binding!=null){
                   MaxApps_PatternHelper.maxappsashes_touchVibrate(maxapps_ashes_binding.getRoot().getContext(), b);


            }}}
        });

        maxapps_ashes_binding.hideKeyBoard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (activity!=null){
                    if (maxapps_ashes_binding!=null){

                   MaxApps_PatternHelper.maxappsashes_hideKeyboard(maxapps_ashes_binding.getRoot().getContext() , b);


            }}}
        });

        maxapps_ashes_binding.switchOn.setOnClickListener(v->{
            maxapps_ashes_binding.switchOn.setVisibility(View.GONE);
            maxapps_ashes_binding.switchOff.setVisibility(View.VISIBLE);
            if (activity!=null){
                if (maxapps_ashes_binding!=null) {


                    MaxApps_PatternUtil.setIntruder(maxapps_ashes_binding.getRoot().getContext(), false);

                }}
        });
        maxapps_ashes_binding.switchOff.setOnClickListener(v->{
            PermissionX.init(MaxApps_PatternSettingFragment.this)
                    .permissions(Manifest.permission.CAMERA)
                    .request(new RequestCallback() {
                        @Override
                        public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                            if (activity!=null){
                                if (maxapps_ashes_binding!=null){

                            MaxApps_PatternUtil.setIntruder(maxapps_ashes_binding.getRoot().getContext() , true);

                            maxapps_ashes_binding.switchOn.setVisibility(View.VISIBLE);
                            maxapps_ashes_binding.switchOff.setVisibility(View.GONE);
                        }}}
                    });
        });

        maxapps_ashes_binding.intruderSelfie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (activity!=null){
                    if (maxapps_ashes_binding!=null) {


                        MaxApps_PatternUtil.setIntruder(maxapps_ashes_binding.getRoot().getContext(), isChecked);
                        maxapps_ashes_binding.intruderSelfie.setChecked(isChecked);
                    }}

            }
        });
    }


    private void maxappsashes_hideKeyboard(){
        if (activity!=null) {
            if (maxapps_ashes_binding != null) {
                maxapps_ashes_binding.hideKeyBoard.setChecked(MaxApps_PatternHelper.maxappsashes_getHideKeyBoard(maxapps_ashes_binding.getRoot().getContext()));


            }

        }}

    private void maxappsashes_makePatternVisible(){
        if (activity!=null){
            if (maxapps_ashes_binding!=null) {
                maxapps_ashes_binding.makePatternVisible.setChecked(MaxApps_PatternHelper.maxappsashes_getPasswordVisible(maxapps_ashes_binding.getRoot().getContext()));

            }}
    }

    private void maxappsashes_touchVibrate(){
        if (activity!=null){
            if (maxapps_ashes_binding!=null){
            maxapps_ashes_binding.touchVibrate.setChecked(MaxApps_PatternHelper.maxappsashes_getTouchVibrate(maxapps_ashes_binding.getRoot().getContext()));


    }}}

    private void maxappsashes_defaultSetting(){
       maxappsashes_makePatternVisible();
        maxappsashes_hideKeyboard();
        maxappsashes_touchVibrate();
        maxappsashes_selfieIntruder();
    }
    private void maxappsashes_selfieIntruder(){
        if (activity!=null){
            if (maxapps_ashes_binding!=null) {
                maxapps_ashes_binding.intruderSelfie.setChecked(MaxApps_PatternUtil.getIntruder(maxapps_ashes_binding.getRoot().getContext()));

            }}
    }
    private void maxappsashes_goAnotherScreen(Intent intent){
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        if (activity!=null){
            if (maxapps_ashes_binding!=null) {
                MaxApps_PatternUtil.savePatternPassword(maxapps_ashes_binding.getRoot().getContext(), null);
             //   GameAshes2_FingerprintHandler.maxappsAshes_maxappss_saveFingerprint(maxapps_ashes_binding.getRoot().getContext(), false);

            }}
    }

    @Override
    public void onResume() {
        super.onResume();
        maxappsashes_defaultSetting();
    }

    @Override
    public void onDestroyView() {
        maxapps_ashes_binding=null;

        super.onDestroyView();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity=(Activity) context;
    }
}