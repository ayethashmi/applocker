package com.maxprods.applocker.ui.fragments.pin;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;


import com.maxprods.applocker.databinding.MaxappsFragmentPinSettingBinding;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;
import  com.maxprods.applocker.R;
import com.maxprods.applocker.ui.pin.MaxApps_PinPasscodeActivity;
import com.maxprods.applocker.ui.pin.MaxApps_PinUtil;

import java.util.List;


public class MaxApps_PinSettingFragment extends Fragment {
    private MaxappsFragmentPinSettingBinding play_fof_maxappss_binding;
    private Activity play_fof_maxappss_activity;

    public MaxApps_PinSettingFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        play_fof_maxappss_binding =  DataBindingUtil.inflate(inflater , R.layout.maxapps_fragment_pin_setting, container, false);


        maxapps_ashes_eventHandling();


        return play_fof_maxappss_binding.getRoot();
    }

    private void maxapps_ashes_eventHandling(){
        play_fof_maxappss_binding.changePinLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (play_fof_maxappss_activity != null){
                    if (play_fof_maxappss_binding!=null){
               maxappssashes_go_AnotherScreen(new Intent(play_fof_maxappss_binding.getRoot().getContext(), MaxApps_PinPasscodeActivity.class));

                    }

                }

            }
        });

        play_fof_maxappss_binding.touchVibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (play_fof_maxappss_activity != null){
                    if (play_fof_maxappss_binding!=null){
                    MaxApps_PinHelper.play_fof_maxappss_touchVibrate(play_fof_maxappss_binding.getRoot().getContext(), b);
                }}

            }
        });

        play_fof_maxappss_binding.hideKeyboard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (play_fof_maxappss_activity != null) {
                    if (play_fof_maxappss_binding != null) {
                        MaxApps_PinHelper.play_fof_maxappss_hideKeyboard(play_fof_maxappss_binding.getRoot().getContext(), b);
                    }
                }
            }
        });
        play_fof_maxappss_binding.shuffleKeypad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (play_fof_maxappss_activity != null){
                    if (play_fof_maxappss_binding!=null){
                    MaxApps_PinUtil.play_fof_maxappss_setShuffle(play_fof_maxappss_binding.getRoot().getContext() , isChecked);
                }}
            }
        });
        play_fof_maxappss_binding.intruderSelfie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (play_fof_maxappss_activity != null){
                    if (play_fof_maxappss_binding!=null){
                    MaxApps_PinUtil.setIntruder(play_fof_maxappss_binding.getRoot().getContext() , isChecked);
                }}
            }
        });

        play_fof_maxappss_binding.switchOn.setOnClickListener(v->{
            play_fof_maxappss_binding.switchOn.setVisibility(View.GONE);
            play_fof_maxappss_binding.switchOff.setVisibility(View.VISIBLE);
            if (play_fof_maxappss_activity!=null){
                if (play_fof_maxappss_binding!=null){
                    MaxApps_PinUtil.setIntruder(play_fof_maxappss_binding.getRoot().getContext() , false);

                }
            }


        });
        play_fof_maxappss_binding.switchOff.setOnClickListener(v->{
            if (play_fof_maxappss_activity!=null){
                if (play_fof_maxappss_binding!=null){
                    PermissionX.init(MaxApps_PinSettingFragment.this)
                            .permissions(Manifest.permission.CAMERA)
                            .request(new RequestCallback() {
                                @Override
                                public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {

                                    MaxApps_PinUtil.setIntruder(play_fof_maxappss_binding.getRoot().getContext() , true);
                                    play_fof_maxappss_binding.switchOn.setVisibility(View.VISIBLE);
                                    play_fof_maxappss_binding.switchOff.setVisibility(View.GONE);
                                }
                            });

                }
            }
        });

    }

    private void maxappssashes_go_AnotherScreen(Intent intent){
        startActivity(intent);
        if (play_fof_maxappss_activity != null){
            if (play_fof_maxappss_binding!=null){
                MaxApps_PinUtil.play_fof_maxappss_savePinPassword(play_fof_maxappss_binding.getRoot().getContext(), null);

            }
        }

    }

    private void maxappssashes_defaultSetting(){
        maxappssashes_printHint();
        maxappssashes_touchVibrate();
        maxappssashes_hideKeyboard();
        maxappssashes_shuffleKeypad();
        maxappssashes_selfieIntruder();
    }

    private void maxappssashes_touchVibrate(){
        if (play_fof_maxappss_activity != null){
            if (play_fof_maxappss_binding.getRoot().getContext()!=null){
            play_fof_maxappss_binding.touchVibrate.setChecked(MaxApps_PinHelper.play_fof_maxappss_getTouchVibrate(play_fof_maxappss_binding.getRoot().getContext()));
        }}

    }

    private void maxappssashes_hideKeyboard(){
        if (play_fof_maxappss_activity != null){
            if (play_fof_maxappss_binding!=null){
            play_fof_maxappss_binding.hideKeyboard.setChecked(MaxApps_PinHelper.play_fof_maxappss_getHideKeyBoard(play_fof_maxappss_binding.getRoot().getContext()));
        }}

    }
    private void maxappssashes_selfieIntruder(){
        if (play_fof_maxappss_activity != null) {
            if (play_fof_maxappss_binding != null) {
                play_fof_maxappss_binding.intruderSelfie.setChecked(MaxApps_PinUtil.getIntruder(play_fof_maxappss_binding.getRoot().getContext()));
            }
        }
    }
    private void maxappssashes_shuffleKeypad(){
        if (play_fof_maxappss_activity != null){
            if (play_fof_maxappss_binding!=null){
            play_fof_maxappss_binding.shuffleKeypad.setChecked(MaxApps_PinUtil.play_fof_maxappss_getShuffle(play_fof_maxappss_binding.getRoot().getContext()));
        }}

    }
    private void maxappssashes_printHint(){
        if (play_fof_maxappss_activity != null){
            if (play_fof_maxappss_binding!=null){
            String hint = play_fof_maxappss_binding.inputHint.getText().toString();
            if (!TextUtils.isEmpty(hint)){
                MaxApps_PinHelper.play_fof_maxappss_printHint(play_fof_maxappss_binding.getRoot().getContext(), hint);
            }

            play_fof_maxappss_binding.inputHint.setText(MaxApps_PinHelper.play_fof_maxappss_getPrintHint(play_fof_maxappss_binding.getRoot().getContext()));
        }}

    }

    @Override
    public void onResume() {
        super.onResume();
        maxappssashes_defaultSetting();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        play_fof_maxappss_activity=(Activity) context;
    }

    @Override
    public void onDestroyView() {
        play_fof_maxappss_binding=null;
        super.onDestroyView();
        maxappssashes_printHint();
    }
}