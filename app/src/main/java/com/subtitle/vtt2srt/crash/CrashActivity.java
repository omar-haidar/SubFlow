package com.subtitle.vtt2srt.crash;

import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class CrashActivity extends AppCompatActivity {
    public static final String EXTRA_CRASH_MESSAGE = "EXTRA_CRASH_MESSAGE";

    private String crashMessage = "No crash message!";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_CRASH_MESSAGE)){
            crashMessage = intent.getStringExtra(EXTRA_CRASH_MESSAGE);
        }
        new MaterialAlertDialogBuilder(this)
                .setTitle("An error occourred")
                .setMessage(crashMessage)
                .setCancelable(false)
                .setPositiveButton("Exit",(d,i)->finish())
                .setNegativeButton("Copy",(d,i)-> {
                    copyToClipboard(crashMessage);
                    finish();
                })
                .create()
                .show();

    }

    public static void handleCrash(Application app){
        Thread.setDefaultUncaughtExceptionHandler((thread,throwable)->{
            Intent intent = new Intent(app.getApplicationContext(), CrashActivity.class);
            intent.putExtra(EXTRA_CRASH_MESSAGE, Log.getStackTraceString(throwable));
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            app.startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        });
    }

    private void copyToClipboard(CharSequence text){
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setPrimaryClip(ClipData.newPlainText("CrashMessage",text));
        Toast.makeText(this, "Copied to clipboard.", Toast.LENGTH_SHORT).show();

    }
}
