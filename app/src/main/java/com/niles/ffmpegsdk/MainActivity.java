package com.niles.ffmpegsdk;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.niles.ffmpeg.FFmpegManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(MainActivity.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File(Environment.getExternalStorageDirectory(), "hobot/media/adas/video/ADAS_RAW_Nebula_20181120-093347_399.mp4");
                String compress = FFmpegManager.compress(file.getAbsolutePath(), 10);
                Message message = Message.obtain();
                message.obj = compress;
                mHandler.sendMessage(message);
            }
        }).start();
    }
}
