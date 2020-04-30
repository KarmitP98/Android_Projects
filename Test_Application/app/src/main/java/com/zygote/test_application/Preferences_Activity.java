package com.zygote.test_application;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Preferences_Activity extends AppCompatActivity {

    private static final String TAG = "Preferences_Activity";
    private String name = "Test";
    private int counter = 0;
    private Button saveBtn, resetBtn;
    private EditText inputEdit;
    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences_);

        saveBtn = findViewById(R.id.save_btn);
        resetBtn = findViewById(R.id.pref_btn);
        inputEdit = findViewById(R.id.input_txt);
        display = findViewById(R.id.display);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(name, counter++);
                if (editor.commit())
                    Log.i(TAG, "onClick: Name: " + name + " Counter:" + counter);

                Log.i(TAG, "onClick: Key present?" + preferences.contains(name));
                Log.i(TAG, "onClick: Key:" + name + " Value: " + preferences.getInt(name, 0));

            }
        });

    }
}
