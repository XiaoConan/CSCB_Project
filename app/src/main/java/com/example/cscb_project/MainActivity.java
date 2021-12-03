package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<UserAccount> users;
    public DatabaseReference data;
    public static final String EXTRA_MESSAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Write Random info to Firebase
//        data = FirebaseDatabase.getInstance().getReference();
//        data.child("Users");
//        data.child("Products");
//        data.child("orders");
//        data.child("stores");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        UserAccount user = new OwnerAccount("sean", "abcd");
        ref.child("Users").child("user1").setValue(user);


        //Read from Firebase
        data = FirebaseDatabase.getInstance().getReference();
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child:dataSnapshot.getChildren()) {
                    users.add(child.getValue(UserAccount.class));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("warning", "loadPost:onCancelled",
                        databaseError.toException());
            }
        };
        data.addValueEventListener(listener);
    }

    public void goToLogin() {
        Intent intent = new Intent(this, LoginPage.class);
        Spinner main_spinner = findViewById(R.id.spinner);
        String status = String.valueOf(main_spinner.getSelectedItem());
        intent.putExtra(EXTRA_MESSAGE, status);
        startActivity(intent);
    }
}
