package com.subtitle.vtt2srt;

import android.app.Application;

import com.subtitle.vtt2srt.crash.CrashActivity;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashActivity.handleCrash(this);
    }
}
