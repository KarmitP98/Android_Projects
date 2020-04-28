package com.zygote.test_application;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    private TextView act1, act2, act3, day;
    private ProgressBar pb1, pb1r, pb2, pb2r, pb3, pb3r;
    private int p1 = 30, p2 = 40, p3 = 60, p4 = 90, req = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        getSupportActionBar().hide();

        pb1 = findViewById(R.id.pb1);
        pb1r = findViewById(R.id.pb1_req);
        pb2 = findViewById(R.id.pb2);
        pb2r = findViewById(R.id.pb2_req);
        pb3 = findViewById(R.id.pb3);
        pb3r = findViewById(R.id.pb3_req);

        act1 = findViewById(R.id.act1_text);
        act2 = findViewById(R.id.act2_text);
        act3 = findViewById(R.id.act3_text);
        day = findViewById(R.id.day_title);

        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                day.setText("TV1");
                pb1.setProgress(p1, true);
                pb1r.setProgress(req);
                pb2.setProgress(p1, true);
                pb2r.setProgress(req);
                pb3.setProgress(p1, true);
                pb3r.setProgress(req);
            }
        });
        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                day.setText("TV2");
                pb1.setProgress(p2, true);
                pb1r.setProgress(req);
                pb2.setProgress(p2, true);
                pb2r.setProgress(req);
                pb3.setProgress(p2, true);
                pb3r.setProgress(req);
            }
        });
        findViewById(R.id.tv3).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                day.setText("TV3");
                pb1.setProgress(p3, true);
                pb1r.setProgress(req);
                pb2.setProgress(p3, true);
                pb2r.setProgress(req);
                pb3.setProgress(p3, true);
                pb3r.setProgress(req);
            }
        });
        findViewById(R.id.tv4).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                day.setText("TV4");
                pb1.setProgress(p4, true);
                pb1r.setProgress(req);
                pb2.setProgress(p4, true);
                pb2r.setProgress(req);
                pb3.setProgress(p4, true);
                pb3r.setProgress(req);
            }
        });
    }
}
