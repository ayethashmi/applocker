package com.maxprods.applocker.activities;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.maxprods.applocker.ui.util.MaxApps_GlobalMethods;

public class MaxApps_MyWork extends Worker {
    public MaxApps_MyWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        if (!MaxApps_GlobalMethods.maxapps_ashes_getRating(getApplicationContext())){
            MaxApps_GlobalMethods.maxapps_ashes_saveOneTimeRating(getApplicationContext(),false);
        }
          return Result.success();
    }
}
