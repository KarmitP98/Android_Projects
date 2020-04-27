package com.zygote.todaysacitivty;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Other_Activity extends AppCompatActivity {

    private EditText pushupEdit, dumbellEdit;
    private TextView nextAct;
    private int pushups = 0, dumbells = 0;
    private int laps;
    private String TAG = "oth_TAG";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_);

        getSupportActionBar().hide();

        pushupEdit = findViewById(R.id.pushup_edit);
        dumbellEdit = findViewById(R.id.dumbell_edit);
        nextAct = findViewById(R.id.next_act_swipe);

        Intent intent = getIntent();

        this.laps = intent.getIntExtra("Laps", 0);

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
        startActivity(intent);
    }
}
