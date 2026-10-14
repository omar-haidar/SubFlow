package dev.omarhaidar.subflow;

import android.app.Application;

import dev.omarhaidar.subflow.crash.CrashActivity;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashActivity.handleCrash(this);
    }
}
