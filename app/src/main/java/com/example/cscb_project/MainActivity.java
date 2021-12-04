package com.example.cscb_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String USER_TYPE_MESSAGE = "USER_TYPE_MESSAGE";

    public void exampleFirebaseCode() {
        // EXAMPLE WRITE FROM FIREBASE
        // We need a reference object to work with
        // One line example: DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        // Two line example:
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        // Use the reference from parent directory + .child to get to the key you need
        ref.child("Users").child("username").setValue("password");
        // Or you can create a reference using a path String
        ref = database.getReference("Stores");
        ref.child("store").setValue("s");
        String path = "/asdf";
        ref = database.getReference("Products" + path + "/name");
        ref.child("product").setValue("p");
        ref = database.getReference("Orders");
        ref.child("order").setValue("o");


        // EXAMPLE READ FROM FIREBASE
        // Basic get value
        ref = database.getReference("Users/username");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // If no data at location, getValue() returns null??
                String value = snapshot.getValue(String.class);
                // do something with value
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // This happens if getting the value failed... leave empty?
                //Log.w("warning", "loadPost:onCancelled", error.toException());
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToLoginPage(View view) {
        Intent intent = new Intent(this, LoginPage.class);
        Spinner main_spinner = findViewById(R.id.spinner);
        String status = String.valueOf(main_spinner.getSelectedItem());
        intent.putExtra(USER_TYPE_MESSAGE, status);
        startActivity(intent);
    }

    public void goToSignUpPage(View view) {
        Intent intent = new Intent(this, SignUpPage.class);
        Spinner main_spinner = findViewById(R.id.spinner);
        String status = String.valueOf(main_spinner.getSelectedItem());
        intent.putExtra(USER_TYPE_MESSAGE, status);
        startActivity(intent);
    }
}
