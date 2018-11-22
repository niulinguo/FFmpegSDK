package com.niles.ffmpeg;

import android.os.Environment;

import com.mabeijianxi.smallvideorecord2.DeviceUtils;
import com.mabeijianxi.smallvideorecord2.JianXiCamera;
import com.mabeijianxi.smallvideorecord2.LocalMediaCompress;
import com.mabeijianxi.smallvideorecord2.model.AutoVBRMode;
import com.mabeijianxi.smallvideorecord2.model.LocalMediaConfig;
import com.mabeijianxi.smallvideorecord2.model.OnlyCompressOverBean;

import java.io.File;

/**
 * Created by Niles
 * Date 2018/11/22 13:14
 * Email niulinguo@163.com
 */
public class FFmpegManager {

    public static void initSmallVideo(String dir) {
        // 设置拍摄视频缓存路径
        File dcim = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        if (DeviceUtils.isZte()) {
            if (dcim.exists()) {
                JianXiCamera.setVideoCachePath(dcim + "/" + dir + "/");
            } else {
                JianXiCamera.setVideoCachePath(dcim.getPath().replace("/sdcard/", "/sdcard-ext/") + "/" + dir + "/");
            }
        } else {
            JianXiCamera.setVideoCachePath(dcim + "/" + dir + "/");
        }
        // 初始化拍摄，遇到问题可选择开启此标记，以方便生成日志
        JianXiCamera.initialize(BuildConfig.DEBUG, new File(Environment.getExternalStorageDirectory(), "FFmpeg/log").getAbsolutePath());
    }

    public static String compress(String path, int frame) {
        // 选择本地视频压缩
        LocalMediaConfig.Buidler builder = new LocalMediaConfig.Buidler();
        final LocalMediaConfig config = builder
                .setVideoPath(path)
                .captureThumbnailsTime(1)
                .doH264Compress(new AutoVBRMode())
                .setFramerate(frame)
                .setScale(1.0f)
                .build();
        OnlyCompressOverBean onlyCompressOverBean = new LocalMediaCompress(config).startCompress();
        if (onlyCompressOverBean.isSucceed()) {
            return onlyCompressOverBean.getVideoPath();
        }

        return null;
    }
}
