package com.example.myapplication.adapter;
import com.example.myapplication.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.example.myapplication.userModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class UserRecyclerAdapter extends FirestoreRecyclerAdapter<userModel,UserRecyclerAdapter.UserModelViewHolder> {
    Context context;
    public UserRecyclerAdapter(@NonNull FirestoreRecyclerOptions<userModel> options,Context context){
        super(options);
        this.context=context;
    }
    public void onBindViewHolder(@NonNull UserModelViewHolder holder, int position, @NonNull userModel model){
        holder.username.setText(model.getUsername());
        holder.userid.setText(model.getUserId());
    }
    @NonNull
    @Override
    public UserModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        View view= LayoutInflater.from(context).inflate(R.layout.user_row,parent,false);
        return new UserModelViewHolder(view);
    }
    class  UserModelViewHolder extends RecyclerView.ViewHolder{
        TextView username, userid;
        public UserModelViewHolder (@NonNull View itemView){
            super(itemView);
            username=itemView.findViewById(R.id.username);
            userid=itemView.findViewById(R.id.userid);
        }

    }
}
