package com.example.myapplication;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.example.myapplication.databinding.FragmentHomeBinding;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.ListenerRegistration;
//
//import java.util.zip.Inflater;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the  factory method to
// * create an instance of this fragment.
// */
//public class home extends Fragment {
//
//    private FirebaseAuth mAuth;
//    private FirebaseFirestore db;
//    private FirebaseUser currentUser;
//    private TextView usernameTextView;
//    private ListenerRegistration userListener;
//    String username;
//    public home() {
//        // Required empty public constructor
//    }
//
////
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        usernameTextView.setText("nayan");
//        // Initialize Firebase components
//        mAuth = FirebaseAuth.getInstance();
//        db = FirebaseFirestore.getInstance();
//
//        // Get the current user
//        currentUser = mAuth.getCurrentUser();
//
//        // Initialize the Firestore listener for user data
//        userListener = db.collection("user")
//                .document(currentUser.getUid())
//                .addSnapshotListener((documentSnapshot, e) -> {
//                    if (e != null) {
//                        // Handle errors
//                        return;
//                    }
//
//                    if (documentSnapshot != null && documentSnapshot.exists()) {
//                        // Retrieve the username from Firestore and update the UI
//                        username = documentSnapshot.getString("name");
////                        usernameTextView.setText(username);
//                    }
//                });
//    }
//    TextView t;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this frag
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
//         t = view.findViewById(R.id.textView2);
//         t.setText("hello," + username);
////        return inflater.inflate(R.layout.fragment_home, container, false);
//        return view;
//        }
//
//    /*   return inflater.inflate(R.layout.fragment_home, container, false);
//
//        }*/
//
//
//
//
//    private void replaceFragment(Fragment fragment){
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        @SuppressLint("CommitTransaction") FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
////        fragmentTransaction.replace(R.id.homeFrameLayout,fragment);
//        fragmentTransaction.replace(R.id.frame_layout, fragment);
//
//        fragmentTransaction.commit();
//    }
//
//
//
//}
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

public class home extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseUser currentUser;
    private TextView usernameTextView;
    private ListenerRegistration userListener;
    private String username;

    public home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase components
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Get the current user
        currentUser = mAuth.getCurrentUser();

        // Initialize the Firestore listener for user data
        userListener = db.collection("users")
                .document(currentUser.getUid())
                .addSnapshotListener((documentSnapshot, e) -> {
                    if (e != null) {
                        // Handle errors
                        return;
                    }

                    if (documentSnapshot != null && documentSnapshot.exists()) {
                        // Retrieve the username from Firestore and update the UI
                        username = documentSnapshot.getString("name"); // Change "Name" to your actual field name
                        updateUsername();
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        usernameTextView = view.findViewById(R.id.textView2);
        updateUsername();
        return view;
    }

    private void updateUsername() {
        if (username != null && !username.isEmpty()) {
            usernameTextView.setText("Hello, " + username);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Remove the Firestore listener when the view is destroyed
        if (userListener != null) {
            userListener.remove();
        }
    }
}
