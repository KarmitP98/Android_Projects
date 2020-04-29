package com.zygote.todaysacitivty;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Preference_Activity extends AppCompatActivity {

    private static final String TAG = "Preference_Activity";
    private Spinner durSpin, lapSpin, pushSpin, dumbSpin, kgSpin;
    private TextView nextAct;
    private Class nextClass = MainActivity.class;
    private String[] values = new String[500];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_);

        getSupportActionBar().hide();

        durSpin = findViewById(R.id.dur_spin);
        lapSpin = findViewById(R.id.lap_spin);
        pushSpin = findViewById(R.id.push_spin);
        dumbSpin = findViewById(R.id.dumb_spin);
        kgSpin = findViewById(R.id.weight_spin);

        nextAct = findViewById(R.id.next_btn);

        setValues();
        setEntries();
        setActions();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setActions() {
        nextAct.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("Time", (Integer) durSpin.getSelectedItem());
                editor.putInt("Laps", (Integer) lapSpin.getSelectedItem());
                editor.putInt("Pushups", (Integer) pushSpin.getSelectedItem());
                editor.putInt("Dumbells", (Integer) dumbSpin.getSelectedItem());
                editor.putInt("Weight", (Integer) kgSpin.getSelectedItem());
                editor.putBoolean("New User", true);
                editor.commit();
                Intent outIntent = new Intent(Preference_Activity.this, nextClass);
                startActivity(outIntent);
            }
        });
    }

    private void setEntries() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        durSpin.setAdapter(adapter);
        lapSpin.setAdapter(adapter);
        pushSpin.setAdapter(adapter);
        dumbSpin.setAdapter(adapter);
        kgSpin.setAdapter(adapter);
    }

    private void setValues() {
        for (int i = 1; i < 500; i++)
            values[i] = Integer.toString(i);
    }
}
