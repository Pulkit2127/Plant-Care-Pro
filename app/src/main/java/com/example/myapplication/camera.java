package com.example.myapplication;

import static android.app.Activity.RESULT_OK;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class camera extends Fragment {

    private Button cameraButton, resultButton;
    private boolean isImageUploaded = false;
    private String r1, r2, r3;
    public String name;
    private String result;
    TextView textView, result1, result2, result3,res1,res2,res3;
    private Uri uri; // Use Uri class for URI
    private static final int IMAGE_REQUEST = 2;

    public camera() {
        // Required empty public constructor
    }

    public static camera newInstance() {
        return new camera();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        cameraButton = view.findViewById(R.id.photo_button);
        resultButton = view.findViewById(R.id.buttonresult);
        textView = view.findViewById(R.id.text);
        result1 = view.findViewById(R.id.result1);
        result2 = view.findViewById(R.id.result2);
        result3 = view.findViewById(R.id.result3);
        res1=view.findViewById(R.id.result1_sol);
        res2=view.findViewById(R.id.result2_sol);
        res3=view.findViewById(R.id.result3_sol);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Handle the result button click here
                // You can access the results in result1, result2, and result3 TextViews
            }
        });

        return view; // Return the inflated view
    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            uri = data.getData();
            uploadImage();

        } else {
            Toast.makeText(requireContext(), "Image not uploaded", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = requireContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    private void uploadImage() {
        final ProgressDialog pd = new ProgressDialog(requireContext());
        pd.setMessage("Image is uploading");
        pd.show();

        if (uri != null) {
            name = (System.currentTimeMillis() + "." + getFileExtension(uri)).toString();
            StorageReference fileRef = FirebaseStorage.getInstance().getReference()
                    .child("uploads")
                    .child(name);

            fileRef.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String downloadUrl = uri.toString();
                                Log.d("CameraFragment", "Image uploaded. Download URL: " + downloadUrl);
                                pd.dismiss();
//                                Toast.makeText(requireContext(), "Image uploaded", Toast.LENGTH_SHORT).show();
                                textView.setText("Photo Uploaded");

                                // Set the flag to true to indicate that image upload is complete
                                isImageUploaded = true;

                                // Call your network request function only after the image is uploaded
                                makeNetworkRequest();
                            }
                        });
                    } else {
                        pd.dismiss();
                        Toast.makeText(requireContext(), "Upload failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            // Handle the case where the URI is null (not selected or invalid)
            pd.dismiss();
//            Toast.makeText(requireContext(), "No image selected", Toast.LENGTH_SHORT).show();
        }
    }
    private void makeNetworkRequest() {
        if (isImageUploaded) {
            RequestQueue requestQueue;
            requestQueue = Volley.newRequestQueue(requireContext());
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", name);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String url = "https://soumesh.pythonanywhere.com/prediction";

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Handle the JSON response here
                            try {
                                // Parse and process the response
                                r1 = response.getString("pred1");
                                r2 = response.getString("pred2");
                                r3 = response.getString("pred3");
                                result1.setText(r1);
                                result2.setText(r2);
                                result3.setText(r3);
                                getResponse("My Plant has the this disease"+r1+".Now give 3 most effective methods to cure  this.Just give 3 points for methods ",1);
                                getResponse("My Plant has the this disease"+r2+".Now give 3 most effective methods to cure  this.Just give 3 points for methods ",2);
                                getResponse("My Plant has the this disease"+r3+".Now give 3 most effective methods to cure  this.Just give 3 points for methods ",3);

                            } catch (JSONException e) {
                                Toast.makeText(requireContext(), "Error parsing JSON", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Handle errors here
                            Log.e("CameraFragment", "Error: " + error.toString());
                        }
                    }
                    );
            requestQueue.add(jsonObjectRequest);
        } else {
            // Handle the case where image upload is not yet complete
//            Toast.makeText(requireContext(), "Image upload in progress", Toast.LENGTH_SHORT).show();
        }
    }
    private void getResponse(String query,int i) {
//        Toast.makeText(requireContext(), query, Toast.LENGTH_SHORT).show();
        textView.setText("");
         String url = "https://api.openai.com/v1/completions";
        @SuppressLint("RestrictedApi") RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("model", "text-davinci-003");
            jsonObject.put("prompt", query);
            jsonObject.put("temperature", 0);
            jsonObject.put("max_tokens", 100);
            jsonObject.put("top_p", 1);
            jsonObject.put("frequency_penalty", 0.0);
            jsonObject.put("presence_penalty", 0.0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("SuspiciousIndentation")
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the JSON response here
                        try {
                            // Parse and process the response
                            JSONArray choicesArray = response.getJSONArray("choices");
                            JSONObject choiceObject = choicesArray.getJSONObject(0);// Get the first choice
                            String resultt = choiceObject.getString("text");
                            //result
                            result=resultt;
                            if(i==1)
                            res1.setText(resultt);
                            if(i==2)
                            res2.setText(resultt);
                            if(i==3)
                            res3.setText(resultt);

//                            Toast.makeText(getContext(), resultt, Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            Log.d( "onError","no");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors here
                        Log.e("solution", "Error: " + error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer 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"); // Replace with your actual API key with "Bearer" prefix
                return headers;
            }
        };

        queue.add(jsonObjectRequest);
    }
}
