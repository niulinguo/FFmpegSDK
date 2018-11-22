package com.niles.ffmpegsdk;

import android.app.Application;

import com.niles.ffmpeg.FFmpegManager;

/**
 * Created by Niles
 * Date 2018/11/22 13:15
 * Email niulinguo@163.com
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FFmpegManager.initSmallVideo("useiov_adas");
    }
}
