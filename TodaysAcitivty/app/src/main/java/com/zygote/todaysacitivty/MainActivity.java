package com.zygote.todaysacitivty;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private final int RATIO = 60000;
    private TextView date_display, timerDisp, lapDisp;          // act_display;
    private Calendar c = Calendar.getInstance();
    private ArrayList<String> days = new ArrayList<>(Arrays.asList("SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY")), acts = new ArrayList<>(Arrays.asList("BREAK DAY", "ABS & QUADS", "ABS & TRICEPS", "GLUTES & BACK", "ABS & BICEP", "CHEST & BACK", "CHEST & BACK"));
    private Button pauseBtn;
    private int laps = 0;
    private long time = 20 * RATIO;
    private MediaPlayer mp;
    private ConstraintLayout timerView;
    private LinearLayout controlsView;
    private boolean paused = true;
    private CountDownTimer ct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date_display = findViewById(R.id.date_display);
        timerDisp = findViewById(R.id.timer_display);
        lapDisp = findViewById(R.id.lap_display);
        timerView = findViewById(R.id.timer_view);
        controlsView = findViewById(R.id.controls_view);
        View view = findViewById(R.id.view);

        initialize();
    }

    /*
    Initialize the variables with default values and set actions on views
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initialize() {

        // Get Day & Activity
        String day = days.get(c.get(Calendar.DAY_OF_WEEK) - 1);
        String act = acts.get(c.get(Calendar.DAY_OF_WEEK) - 1);
        // Set day & display
        date_display.setText(day);

        // Media Player controls
        mp = MediaPlayer.create(this, R.raw.cling_1);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                mp = null;
            }
        });

        resetAll();

        // OnClick for the timer view to add laps when clicked
        timerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!paused)
                    addLap();
            }
        });

        // OnLongClick to start or stop the timer
        timerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                flipState();
                return true;
            }
        });

        // Swipe the bottom of the screen to change activity
        controlsView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                nextActivity();
            }
        });

//        timerView.setOnTouchListener(new OnSwipeTouchListener(this){
//            @Override
//            public void onSwipeLeft() {
//                super.onSwipeLeft();
//                nextActivity();
//            }
//        });

    }

    /*
    Start the timer.
     */
    private void start() {
        if (paused) {
            paused = false;
            updateBtns();
            startTimer(time);
        }
    }

    /*
    Update the buttons depending on the state (Paused or not)
     */
    private void updateBtns() {
//        pauseBtn.setText(paused ? "Resume" : "Pause");
    }

    /*
    Reset time, laps, timer and buttons
     */
    private void resetAll() {
        paused = true;
        resetLaps();
        resetTime();
        updateBtns();
    }

    /*
    Resets the laps to 0 and updates the textview
     */
    private void resetLaps() {
        laps = 0;
        updateLaps();
    }

    /*
    Resets the timer and the time to 20 mins
     */
    private void resetTime() {
        if (time > 0 && paused == false)
            ct.cancel();
        int min = 20;
        time = min * RATIO;
        updateTimer();
    }

    /*
    Flips the state of the timer from on to off and vice-versa
     */
    private void flipState() {
        if (paused) {
            paused = false;
            startTimer(time);
        } else {
            paused = true;
            pauseTimer();
        }
    }

    /*
    Pauses the timer
     */
    private void pauseTimer() {
        ct.cancel();
        updateBtns();
        updateTimer();
    }

    /*
    Starts the timer for @t time
     */
    private void startTimer(long t) {
        ct = new CountDownTimer(t, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                onPause();
                nextActivity();
            }

            public boolean isRunning() {
                return time > 0 ? true : false;
            }

        };
        ct.start();
    }

    /*
    Update the timer textview with current time
     */
    private void updateTimer() {
        int minL = (int) time / (RATIO);
        int secL = (int) time / 1000;
        secL = secL % 60;

        timerDisp.setText(minL + "." + secL);
    }

    /*
    Changes to next activity
     */
    private void nextActivity() {

        Intent intent = new Intent(this, Other_Activity.class);
        intent.putExtra("Laps", this.laps);
        startActivity(intent);
    }

    /*
    Adds a lap and plays sound and updates the lap
     */
    private void addLap() {
        ++laps;
        makeSound();
        updateLaps();
    }

    /*
    Plays the sound.
    Default sound of ting.
     */
    private void makeSound() {
        try {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(this, R.raw.cling_1);
            } else
                mp = MediaPlayer.create(this, R.raw.cling_1);

            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    Updates the lap textview with the current laps
     */
    @SuppressLint("SetTextI18n")
    private void updateLaps() {
        lapDisp.setText(Integer.toString(laps));
    }


}
