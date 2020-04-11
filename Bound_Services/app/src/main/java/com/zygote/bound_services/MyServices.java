package com.zygote.bound_services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyServices extends Service {

    public static final String TAG = "MyService";

    private IBinder mBinder = new Binder();
    private Handler handler;
    private int mProgress, mMaxValue;
    private Boolean mIsPaused;


    @Override
    public void onCreate() {
        super.onCreate();

        handler = new Handler();
        mProgress = 0;
        mMaxValue = 500;
        mIsPaused = true;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void startPretend() {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mProgress >= mMaxValue || mIsPaused) {
                    Log.d(TAG, "run: removing callbacks");
                    handler.removeCallbacks(this);
                    pausePretend();
                } else {
                    Log.d(TAG, "run: progress: " + mProgress);
                    mProgress += 100;
                    handler.postDelayed(this, 100);

                }
            }
        };
        handler.postDelayed(runnable, 100);
    }

    private void pausePretend() {
        mIsPaused = true;
    }

    private void unPausePretend() {
        mIsPaused = false;
        startPretend();
    }

    public boolean getIsPaused() {
        return mIsPaused;
    }

    public int getmProgress() {
        return mProgress;
    }

    public int getmMaxValue() {
        return mMaxValue;
    }

    public void resetTask() {
        mProgress = 0;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        stopSelf();
    }

    public class MyBinder extends Binder {

        MyServices getService() {
            return MyServices.this;
        }
    }
}
