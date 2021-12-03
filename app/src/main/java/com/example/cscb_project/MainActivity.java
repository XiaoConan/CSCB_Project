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
    public static ArrayList<OwnerAccount> owners;
    public static ArrayList<CustomerAccount> customers;
    public DatabaseReference ownerData;
    public DatabaseReference customerData;
    public static final String EXTRA_MESSAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Read from Firebase
        ownerData = FirebaseDatabase.getInstance().getReference("OwnerAccounts");
        ValueEventListener listener1 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child:dataSnapshot.getChildren()) {
                    owners.add(child.getValue(OwnerAccount.class));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("warning", "loadPost:onCancelled",
                        databaseError.toException());
            }
        };
        ownerData.addValueEventListener(listener1);
        customerData = FirebaseDatabase.getInstance().getReference("CustomerAccounts");
        ValueEventListener listener2 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child:dataSnapshot.getChildren()) {
                    customers.add(child.getValue(CustomerAccount.class));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("warning", "loadPost:onCancelled",
                        databaseError.toException());
            }
        };
        customerData.addValueEventListener(listener2);
    }

    public void goToLogin() {
        Intent intent = new Intent(this, LoginPage.class);
        Spinner main_spinner = findViewById(R.id.spinner);
        String status = String.valueOf(main_spinner.getSelectedItem());
        intent.putExtra(EXTRA_MESSAGE, status);
        startActivity(intent);
    }
}
