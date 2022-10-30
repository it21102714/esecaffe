package com.example.cafeproject;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {

    public static final String TAG = "TAG";
    private DatabaseReference UserRef;

    private String type = "" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent data = getIntent();
        Bundle bundle = data.getExtras();
        if (bundle != null)
        {
            type = getIntent().getExtras().get("User").toString();
        }

        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");

        Log.d(TAG,"onCreate:" +UserRef);
    }
}