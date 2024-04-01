package com.minhui.networkcapture;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;

import androidx.annotation.NonNull;

import com.hypertrack.hyperlog.DeviceLogModel;
import com.hypertrack.hyperlog.HLCallback;
import com.hypertrack.hyperlog.HyperLog;
import com.hypertrack.hyperlog.error.HLErrorResponse;
import com.hypertrack.hyperlog.utils.Utils;

import java.io.File;
import java.util.List;


/**
 * @author minhui.zhu
 *         Created by minhui.zhu on 2018/4/30.
 *         Copyright © 2017年 Oceanwing. All rights reserved.
 */

public class MyApplication extends Application
{
    public static final String BUGLY_ID="6c905fa4a7";
    private static  Context context;
    @Override
    public void onCreate()
    {
        super.onCreate();
    }
    public static Context getContext(){
        return context;
    }
}
