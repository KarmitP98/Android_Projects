package com.zygote.todaysacitivty;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class Developer_Activity extends AppCompatActivity {

    private Button resetBtn, backBtn;
    private Intent incomingIntent;
    private String fileName;
    private Class nextClass = Showcase_activity.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_);

        getSupportActionBar().hide();

        resetBtn = findViewById(R.id.clear_btn);
        backBtn = findViewById(R.id.back_btn);

        incomingIntent = getIntent();
        fileName = incomingIntent.getStringExtra("fileName");

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                clearFile();
                nextClass = MainActivity.class;
                backBtn.setText("Restart");
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(Developer_Activity.this, nextClass);
                outIntent.putExtra("fileName", fileName);
                startActivity(outIntent);
            }
        });
    }

    private void clearFile() {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write("");
            outputStreamWriter.close();
            Toast.makeText(this, "File has been cleared!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "File not found!", Toast.LENGTH_SHORT).show();
        }
    }
}
