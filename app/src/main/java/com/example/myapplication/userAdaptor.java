package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class userAdaptor extends RecyclerView.Adapter<userAdaptor.MyViewHolder>{
    private Context context;
    public List<userModel> userModelList;
    public userAdaptor(Context context){
        this.context = context;
        userModelList = new ArrayList<>();
    }
    public void add(userModel user){
        userModelList.add(user);

        notifyDataSetChanged();
    }
    public void clear(){
        userModelList.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public  MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public  void onBindViewHolder(@NonNull MyViewHolder holder,int position){
        userModel user = userModelList.get(position);
        holder.name.setText(user.getUsername());
        holder.email.setText(user.getUser_email());
    }
    @Override
    public  int getItemCount(){
        return userModelList.size();
    }
    public  class  MyViewHolder extends  RecyclerView.ViewHolder{
        private TextView name,email;

        public  MyViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.username);
            email = itemView.findViewById(R.id.userid);

        }
    }
}
