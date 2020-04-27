package com.zygote.todaysacitivty;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Showcase_activity extends AppCompatActivity {

    private TextView lapText, dumbText, pushText;
    private int laps, dumbells, pushups;
    private View view;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcase_activity);

        getSupportActionBar().hide();

        lapText = findViewById(R.id.lap_text);
        dumbText = findViewById(R.id.dumb_text);
        pushText = findViewById(R.id.push_text);

        view = findViewById(R.id.view);

        Intent intent = getIntent();
        this.laps = intent.getIntExtra("Laps", 0);
        this.pushups = intent.getIntExtra("Pushups", 0);
        this.dumbells = intent.getIntExtra("Dumbells", 0);

        lapText.setText(laps + " Laps in 20 mins @" + (laps / 20.0) + " laps/min");
        dumbText.setText(dumbells + " dumbells curves @ 7 Kg");
        pushText.setText(pushups + " pushups");

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                finishAffinity();
                System.exit(0);
                return true;
            }
        });

    }

}
