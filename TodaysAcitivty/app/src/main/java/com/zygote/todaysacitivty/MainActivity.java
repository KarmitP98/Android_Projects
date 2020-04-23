package com.zygote.todaysacitivty;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private TextView date_display, act_display;
    private Calendar c = Calendar.getInstance();
    private ArrayList<String> days = new ArrayList<>(Arrays.asList("SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY")), acts = new ArrayList<>(Arrays.asList("BREAK DAY", "ABS & QUADS", "ABS & TRICEPS", "GLUTES & BACK", "ABS & BICEP", "CHEST & BACK", "CHEST & BACK"));
    private String day, act;
    private Button btn;
    private int laps = 0;
    private String lapS = "Laps: ";
    private MediaPlayer mp;
    private View view;
    private String test = "Test String";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date_display = findViewById(R.id.date_display);
        act_display = findViewById(R.id.activity_display);
        btn = findViewById(R.id.btn);
        view = findViewById(R.id.view);

        create();


    }

    @SuppressLint("ClickableViewAccessibility")
    private void create() {
        // Get Day & Activity
        day = days.get(c.get(Calendar.DAY_OF_WEEK) - 1);
        act = acts.get(c.get(Calendar.DAY_OF_WEEK) - 1);

        // Set day & display
        date_display.setText(day);
//        act_display.setText(act);
        act_display.setText("Next Activity");

        act_display.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                nextActivity();
            }
        });

        mp = MediaPlayer.create(this, R.raw.cling_1);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                mp = null;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLap();
            }
        });

        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                resetLap();
                return true;
            }
        });

        updateLaps();
    }

    private void nextActivity() {
        Intent intent = new Intent(this, ShowcaseAcitivity.class);
        startActivity(intent);
    }

    private void resetLap() {
        laps = 0;
        updateLaps();
    }

    private void addLap() {
        laps++;
        makeSound();
        updateLaps();
    }

    private void makeSound() {
        try {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(this, R.raw.cling_1);
            }
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateLaps() {
        btn.setText(lapS + laps);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return false;
    }
}
