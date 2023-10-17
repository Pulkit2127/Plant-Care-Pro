package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class firstPage extends AppCompatActivity {
    private Button user,expert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);


        user = (Button) findViewById(R.id.userBtn);
        expert = (Button) findViewById(R.id.expertBtn);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(firstPage.this,Registration.class));
            }
        });

        expert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(firstPage.this,expert.class));
            }
        });
    }
}