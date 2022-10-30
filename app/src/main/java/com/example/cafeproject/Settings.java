package com.example.cafeproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.HashMap;

public class Settings extends AppCompatActivity {

    Button applyChangesBtn;
    EditText UserName, UserPhone, UserPassword;

    String name,phone,password;

    String userID = "";
    private DatabaseReference userRef;


    String username,upassword,uphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");

        UserName = findViewById(R.id.user_name_maintain);
        UserPhone = findViewById(R.id.user_phone_maintain);
        UserPassword = findViewById(R.id.user_password_maintain);
        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(phone);
        UserName.setHint(name);
        UserPhone.setHint(phone);
        UserPassword.setHint("********");
    }



    public void applyChanges(View view){


        username = UserName.getText().toString();
        upassword = UserPassword.getText().toString();
        uphone = UserPhone.getText().toString();
        if((username=="")||(upassword=="")||(uphone=="")) {
            Toast.makeText(Settings.this, "Please fill empty fields !", Toast.LENGTH_SHORT).show();
        }
        else{
            HashMap<String, Object> ProductMap = new HashMap<>();
            ProductMap.put("name" , username);
            ProductMap.put("password" , upassword);
            ProductMap.put("phone" , uphone);


            userRef.updateChildren(ProductMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(Settings.this, "Change applied successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            });
        }

    }


}