package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class result extends AppCompatActivity {
    TextView r1,r2,r3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().hide();
        r1 = findViewById(R.id.res1);
        r2 = findViewById(R.id.res2);
        r3 = findViewById(R.id.res3);

        Intent intent = getIntent();
        String res1= intent.getStringExtra("res1");
        String res2= intent.getStringExtra("res2");
        String res3= intent.getStringExtra("res3");

        // Retrieve the result text from the intent extra
        String resultText = getIntent().getStringExtra("res1");
        String resultText2 = getIntent().getStringExtra("res2");
        String resultText3 = getIntent().getStringExtra("res3");

        // Set the result text to the TextView
        r1.setText(resultText);
        r2.setText(resultText2);
        r3.setText(resultText3);
    }
}
