package com.zygote.test_application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Test_TAG";
    private EditText inputEdit;
    private Button saveBtn, readBtn, resetBtn, nextBtn;
    private TextView outputText;
    private String value, fileName = "TestFile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEdit = findViewById(R.id.input_edit);
        saveBtn = findViewById(R.id.save_btn);
        readBtn = findViewById(R.id.read_btn);
        outputText = findViewById(R.id.output_text);
        resetBtn = findViewById(R.id.reset_file);
        nextBtn = findViewById(R.id.next_btn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = String.valueOf(inputEdit.getText());
                writeFile(fileName, value);
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String output = readFile(fileName);
                Log.i(TAG, "onClick: File content" + output);
                outputText.setText(output);
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFile(fileName);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity();
            }
        });
    }

    private void nextActivity() {
        Intent intent = new Intent(this, DisplayActivity.class);
        startActivity(intent);
    }

    private void resetFile(String fileName) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write("");
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFile(String fileName) {
        String ret = "";

        try {
            InputStream inputStream = getApplicationContext().openFileInput(fileName);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
                Toast.makeText(this, "File has been read", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "File cannot be read", Toast.LENGTH_SHORT).show();
        }

        return ret;
    }

    private void writeFile(String fileName, String value) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput(fileName, Context.MODE_APPEND));
            outputStreamWriter.write(value + "\n");
            outputStreamWriter.close();
            Toast.makeText(MainActivity.this, "Value: " + value + " has been saved!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "File not saved!", Toast.LENGTH_SHORT).show();
        }
    }
}