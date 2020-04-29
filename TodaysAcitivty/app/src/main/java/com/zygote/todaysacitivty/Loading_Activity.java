package com.zygote.todaysacitivty;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

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
        getData();
        startTimer();

    }

    private void getData() {

    }

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

    private void nextActivity() {
        if (newUser) {
            nextClass = Preference_Activity.class;
        }
        Intent outIntent = new Intent(Loading_Activity.this, nextClass);
        startActivity(outIntent);
    }
}
