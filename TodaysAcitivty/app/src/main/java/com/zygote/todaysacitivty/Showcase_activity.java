package com.zygote.todaysacitivty;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Showcase_activity extends AppCompatActivity {

    private static final String TAG = "Showcase_activity";
    private TextView lapText, dumbText, pushText, datePicker;
    private int laps, dumbells, pushups, cday, cmonth, cyear, tLaps, tdumbells, tpushups, weight;
    private View view;
    private String fileName, date;
    private HashMap<String, Integer> lapData = new HashMap<>(), pushData = new HashMap<>(), dumbData = new HashMap<>(), weightData = new HashMap<>(), tLapData = new HashMap<>(), tDumbData = new HashMap<>(), tPushData = new HashMap<>();
    private List<String> dates = new ArrayList<>();
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private Calendar c = Calendar.getInstance();
    private ArrayList<String> months = new ArrayList<>(Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));
    private Button devBtn;
    private int time;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcase_activity);

        getSupportActionBar().hide();

        lapText = findViewById(R.id.lap_text);
        dumbText = findViewById(R.id.dumb_text);
        pushText = findViewById(R.id.push_text);
        datePicker = findViewById(R.id.date_picker);

        view = findViewById(R.id.view);

        devBtn = findViewById(R.id.dev_btn);

        // Goes to developer activity
        devBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Showcase_activity.this, Developer_Activity.class);
                intent.putExtra("fileName", fileName);
                startActivity(intent);
            }
        });

        getPreferenceData();

        // Sets today's date
        setCurrentDate();

        // Sets the fileName for file reading
        setIntentData();

        // Read data from the file
        readFile(fileName);

        // Update the TextViews
        updateDisplay();

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                finishAffinity();
                System.exit(0);
                return true;
            }
        });

        // Add click listener to date picker
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cyear = c.get(Calendar.YEAR);
                cmonth = c.get(Calendar.MONTH);
                cday = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Showcase_activity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener, cyear, cmonth, cday);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                setDate(dayOfMonth, month, year);
                if (!dates.get(dates.size() - 1).equals(date)) {
                    lapText.setText("Data not found for this day");
                    pushText.setText("");
                    dumbText.setText("");
                } else {
                    setData();
                    updateDisplay();
                }
            }
        };

    }

    private void getPreferenceData() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        this.tLaps = preferences.getInt("laps", 0);
        this.tpushups = preferences.getInt("pushups", 0);
        this.tdumbells = preferences.getInt("dumbells", 0);
        this.weight = preferences.getInt("weight", 0);
        this.time = preferences.getInt("time", 0);
    }

    @SuppressLint("SetTextI18n")
    private void readFile(String fileName) {
        String ret = "";

        try {
            InputStream inputStream = getApplicationContext().openFileInput(fileName);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((line = bufferedReader.readLine()) != null) {
                    String[] data = line.split("::");
                    lapData.put(data[0], Integer.parseInt(data[1]));
                    pushData.put(data[0], Integer.parseInt(data[2]));
                    dumbData.put(data[0], Integer.parseInt(data[2]));
                    dates.add(data[0]);
                    stringBuilder.append("\n").append(line);
                }

                inputStream.close();
                ret = stringBuilder.toString();

                if (!dates.get(dates.size() - 1).equals(date)) {
                    lapText.setText("Data not found for this day");
                    pushText.setText("");
                    dumbText.setText("");
                } else {
                    setData();
                    updateDisplay();
                }

                // WORK needs to be done

                Log.i(TAG, "readFile: Data" + ret);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "File cannot be read", Toast.LENGTH_SHORT).show();
        }
    }

    private void setIntentData() {
        Intent intent = getIntent();
        fileName = intent.getStringExtra("fileName");
    }

    @SuppressLint("SetTextI18n")
    private void updateDisplay() {
        lapText.setText(laps + " Laps in " + time + " mins @" + (laps / (time * 1.0)) + " laps/min");
        dumbText.setText(dumbells + " dumbells curves @ " + weight + " Kg");
        pushText.setText(pushups + " pushups");
    }

    private void setCurrentDate() {
        cyear = c.get(Calendar.YEAR);
        cmonth = c.get(Calendar.MONTH);
        cday = c.get(Calendar.DAY_OF_MONTH);

        setDate(cday, cmonth, cyear);
    }

    @SuppressLint("SetTextI18n")
    private void setDate(int day, int month, int year) {
        date = day + "/" + month + "/" + year;
        datePicker.setText(day + " " + months.get(month) + ", " + year);
    }

    private void setData() {
        this.laps = lapData.get(date);
        this.pushups = pushData.get(date);
        this.dumbells = dumbData.get(date);
    }

}
