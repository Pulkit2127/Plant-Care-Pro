package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;
    String password,email;

//    @Override
//    public void onStart() {
//        super.onStart();
//    if(FirebaseAuth.getInstance().getCurrentUser()!=null){
//        startActivity(new Intent(Login.this,MainActivity.class));
//        finish();
//    }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=binding.email.getText().toString();
                password=binding.password.getText().toString();
                login();
            }
        });

    }
    private  void login(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email.trim(),password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                startActivity(new Intent(Login.this,MainActivity.class));
                finish();
            }
        });
    }
}