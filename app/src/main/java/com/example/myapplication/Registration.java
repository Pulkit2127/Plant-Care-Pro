package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
   Button res, log;
   EditText password,email,name;
    private FirebaseFirestore db;
   private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);
        res=findViewById(R.id.RegistrationBtn);
        log=findViewById(R.id.loginBtn);
        password=findViewById(R.id.password);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        auth=FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name=name.getText().toString();
                String Password=password.getText().toString();
                String Email=email.getText().toString();
                if(TextUtils.isEmpty(Email)|| TextUtils.isEmpty(Password)|| TextUtils.isEmpty(Name)){
                    Toast.makeText(Registration.this,"fill all fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    userRegistration(Name,Email,Password);
                }
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registration.this, Login.class));
            }
        });
    }
    private  void userRegistration(String Name,String Email, String Password){
        Task<AuthResult> authResultTask = auth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            Map<String, Object> data= new HashMap<>();
                            data.put("name", Name);
                            data.put("email", Email);
                            data.put("password",Password);
                            db.collection("users").add(data);
                            Toast.makeText(Registration.this, "Registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Registration.this, MainActivity.class));

                        } else {
                            // Handle registration failure
                            Toast.makeText(Registration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}