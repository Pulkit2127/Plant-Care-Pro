package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.myapplication.adapter.UserRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Test extends AppCompatActivity {
    UserRecyclerAdapter adapter;
    RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        recyclerView=findViewById(R.id.recycler);
        setupUserRecycleAdapter("");
    }
    void setupUserRecycleAdapter(String searchTerm){
        Query query= FirebaseFirestore.getInstance().collection("users").whereGreaterThanOrEqualTo("username",searchTerm);
        FirestoreRecyclerOptions<userModel> options=new FirestoreRecyclerOptions.Builder<userModel>().setQuery(query,userModel.class).build();
        adapter= new UserRecyclerAdapter(options,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
    @Override
    protected void onStart(){
        super.onStart();
        if(adapter!=null)
            adapter.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        if(adapter!=null)
            adapter.stopListening();
    }
    @Override
    protected void onResume(){
        super.onResume();
        if(adapter!=null){
            adapter.startListening();
        }
    }
}