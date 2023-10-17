package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import com.example.myapplication.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
//    Button login,register;
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        replaceFragment(new home());

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttomNavigationView.setOnItemSelectedListener(item->{
            int Id=item.getItemId();
            if (Id==R.id.camera){
               replaceFragment(new camera());
            }
            else if (Id==R.id.chating){
               replaceFragment(new chating());
            }
            else if(Id==R.id.home){
                replaceFragment(new home());
            }
            return true;
        });
//        login=(Button) findViewById(R.id.Login);
//        register = (Button) findViewById(R.id.Register);
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this, Login.class);
//                startActivity(intent);
//                return ;
//            }
//        });
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this, Registration.class);
//                startActivity(intent);
//                return ;
//            }
//        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        @SuppressLint("CommitTransaction") FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}