package com.example.cscb_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<UserAccount> users;
    FirebaseDatabase data;
    public static final String EXTRA_MESSAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Write Random info to Firebase
//        FirebaseDatabase data = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = data.getReference("Users");
//        myRef.child("username").setValue("password");
//        myRef = data.getReference("Stores");
//        myRef.child("store").setValue("s");
//        myRef = data.getReference("Products");
//        myRef.child("product").setValue("p");
//        myRef = data.getReference("Orders");
//        myRef.child("order").setValue("o");
//        //asdasdad



        //Read from Firebase
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users/username");
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                String value = snapshot.getValue(String.class);
//            }
//            @Override
//            public void onCancelled(DatabaseError error) {
//                Log.w("warning", "loadPost:onCancelled", error.toException());
//            }
//        });
    }

    public void goToLogin() {
        Intent intent = new Intent(this, LoginPage.class);
        Spinner main_spinner = findViewById(R.id.spinner);
        String status = String.valueOf(main_spinner.getSelectedItem());
        intent.putExtra(EXTRA_MESSAGE, status);
        startActivity(intent);
    }
}
