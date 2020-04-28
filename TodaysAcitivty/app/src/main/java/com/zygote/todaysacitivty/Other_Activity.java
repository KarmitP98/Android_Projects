package com.zygote.todaysacitivty;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class Other_Activity extends AppCompatActivity {

    private static final String TAG = "Other_Activity";
    private EditText pushupEdit, dumbellEdit;
    private TextView nextAct;
    private int pushups = 0, dumbells = 0;
    private int laps, day, month, year;
    private String date;
    private Calendar c = Calendar.getInstance();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_);

        getSupportActionBar().hide();

        pushupEdit = findViewById(R.id.pushup_edit);
        dumbellEdit = findViewById(R.id.dumbell_edit);
        nextAct = findViewById(R.id.next_act_swipe);


        setDate();

        Intent incomingIntent = getIntent();
        this.laps = incomingIntent.getIntExtra("Laps", 0);

        Log.i(TAG, "onCreate: Laps: " + this.laps);

        nextAct.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                nextActivity();
            }
        });

    }

    private void nextActivity() {

        this.dumbells = Integer.parseInt(String.valueOf(dumbellEdit.getText()));
        this.pushups = Integer.parseInt(String.valueOf(pushupEdit.getText()));

        Intent intent = new Intent(this, Showcase_activity.class);
        intent.putExtra("Pushups", this.pushups);
        intent.putExtra("Dumbells", this.dumbells);
        intent.putExtra("Laps", this.laps);
        intent.putExtra("fileName", "Activity.txt");
        saveFile();
        startActivity(intent);
    }

    private void saveFile() {
        String fileName = "Activity.txt";
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput(fileName, Context.MODE_APPEND));
            outputStreamWriter.write(date + "::" + laps + "::" + pushups + "::" + dumbells + "\n");
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setDate() {
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);
        date = day + "/" + month + "/" + year;
    }

}
