package com.zygote.todaysacitivty;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView date_display, act_display;
    private Calendar c = Calendar.getInstance();
    private ArrayList<String> days = new ArrayList<>(Arrays.asList("SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY")), acts = new ArrayList<>(Arrays.asList("BREAK DAY", "ABS & QUADS", "ABS & TRICEPS", "GLUTES & BACK", "ABS & BICEP", "CHEST & BACK", "CHEST & BACK"));
    private String day, act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date_display = findViewById(R.id.date_display);
        act_display = findViewById(R.id.activity_display);

        day = days.get(c.get(Calendar.DAY_OF_WEEK) - 1);
        act = acts.get(c.get(Calendar.DAY_OF_WEEK) - 1);

        date_display.setText(day);
        act_display.setText(act);



    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
