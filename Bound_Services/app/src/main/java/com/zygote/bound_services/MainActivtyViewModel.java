package com.zygote.bound_services;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivtyViewModel extends ViewModel {

    private static final String TAG = "MainActivtyViewModel";

    private MutableLiveData<Boolean> mIsProgressUpdating = new MutableLiveData<>();
    private MutableLiveData<MyServices.MyBinder> mBinder = new MutableLiveData<>();

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: connected to service");
            MyServices.MyBinder binder = (MyServices.MyBinder) service;
            mBinder.postValue(binder);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBinder.postValue(null);
        }
    };

    public LiveData<Boolean> getmIsProgressUpdating() {
        return mIsProgressUpdating;
    }

    public LiveData<MyServices.MyBinder> getmBinder() {
        return mBinder;
    }
}
