package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.ActivityChatBinding;
import com.example.myapplication.databinding.FragmentChatingBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link chating#newInstance} factory method to
 * create an instance of this fragment.
 */
public class chating extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public chating() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment chating.
     */
    // TODO: Rename and change types and number of parameters
    public static chating newInstance(String param1, String param2) {
        chating fragment = new chating();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    DatabaseReference databaseReference;
    userAdaptor userAdaptor;
     FragmentChatingBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
//        binding=FragmentChatingBinding.inflate(getLayoutInflater());
//        //setContentView(binding.getRoot());
//        userAdaptor = new userAdaptor(getActivity());
////        binding.recycler.setAdapter(userAdaptor);
////        binding.recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        databaseReference= FirebaseDatabase.getInstance().getReference("users");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                userAdaptor.clear();
//                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
//                    String uid;
//                    uid = dataSnapshot.getKey();
//                    if (!uid.equals(FirebaseAuth.getInstance().getUid())) {
//                        userModel user = dataSnapshot.getValue(userModel.class);
//                        userAdaptor.add(user);
//                    }
//                }

//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        return inflater.inflate(R.layout.fragment_chating, container, false);
    }
}