package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class expert extends AppCompatActivity {

    private TextInputEditText name, email, password, number;
    private AutoCompleteTextView category;

    private Button register,login;
    private FirebaseFirestore firestore;
    String[] languages = {"Fungal Diseases",
            "Bacterial Diseases",
            "Viral Diseases",
            "Nematode Diseases",
            "Insect Pests",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert);
        getSupportActionBar().hide();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, languages);
        //Find TextView control
        AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.category);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView.setThreshold(1);
        //Set the adapter
        acTextView.setAdapter(adapter);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        number = findViewById(R.id.number);
        category = findViewById(R.id.category);
        register = findViewById(R.id.RegistrationBtn);
        login = findViewById(R.id.loginBtn);
        firestore = FirebaseFirestore.getInstance();



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String expertName = name.getText().toString();
                String expertEmail = email.getText().toString();
                String expertNumber = number.getText().toString();
                String expertPassword = password.getText().toString();
                String expertCategory = category.getText().toString();

                if (expertEmail.isEmpty() || expertName.isEmpty() || expertPassword.isEmpty() || expertNumber.isEmpty() || expertCategory.isEmpty()) {
                    Toast.makeText(expert.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                } else {
                    Map<String, Object> expertData = new HashMap<>();
                    expertData.put("Name", expertName);
                    expertData.put("Email", expertEmail);
                    expertData.put("Number", expertNumber);
                    expertData.put("Password", expertPassword);
                    expertData.put("Category",expertCategory);

                    firestore.collection("Experts")
                            .add(expertData)
                            .addOnSuccessListener(documentReference -> {
                                Toast.makeText(expert.this, "Registered", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(expert.this,Confirmation.class));
                                clearFields();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(expert.this, "Error registering", Toast.LENGTH_SHORT).show();
                            });
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(expert.this,Login.class));
            }
        });
    }

    private void clearFields() {
        name.setText("");
        email.setText("");
        password.setText("");
        number.setText("");
    }
}