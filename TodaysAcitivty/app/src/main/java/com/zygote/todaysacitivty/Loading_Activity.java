package com.zygote.todaysacitivty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class Loading_Activity extends AppCompatActivity {

    private static final String TAG = "Loading_Activity";
    private CountDownTimer ctd;
    private Class nextClass = MainActivity.class;
    private boolean newUser = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_);

        getSupportActionBar().hide();
        getPreferences();
        startTimer();

    }

    // Get data from shared preferences and see if the preferences have been set or not.
    private void getPreferences() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        newUser = sharedPreferences.getBoolean("userKey", true);
        Log.i(TAG, "getData: New User: " + (newUser ? "YES" : "NO"));
    }

    // Start the timer for 5 seconds, enough to load the values from share preferences.
    private void startTimer() {
        ctd = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                nextActivity();
            }
        };
        ctd.start();
    }

    // Select which one is the next activity depending on whether the preferences have been set or not
    private void nextActivity() {
        if (newUser) {
            nextClass = Preference_Activity.class;
        }
        Intent outIntent = new Intent(Loading_Activity.this, nextClass);
        startActivity(outIntent);
    }
}
