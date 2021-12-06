package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StoreOwnerPage extends AppCompatActivity {
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_page);

        Intent intent = getIntent();
        username = intent.getStringExtra(LoginPage.EXTRA_MESSAGE);

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference(getString(R.string.users_path));

        usersRef.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String storeID = snapshot.child("storeID").getValue(String.class);
                searchStoreName(storeID);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }

    public void searchStoreName(String storeID) {
        DatabaseReference storesRef = FirebaseDatabase.getInstance().getReference(getString(R.string.stores_path));

        storesRef.child(storeID).child("storeName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String storeName = snapshot.getValue(String.class);
                    displayWelcome(username, storeName);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    public void displayWelcome(String u, String s) {
        TextView textView = findViewById(R.id.welcomeTextview);
        String message = "Welcome " + u + "\n" + "Owner of " + s;
        textView.setText(message);
    }

    public void goManageStore(View view) {
        Intent intent = new Intent(this, CreateProductPage.class);
        intent.putExtra(LoginPage.EXTRA_MESSAGE, username);
        startActivity(intent);
    }

    public void goOwnerProductPage(View view) {
        Intent intent = new Intent(this, OwnerProductPage.class);
        intent.putExtra(LoginPage.EXTRA_MESSAGE, username);
        startActivity(intent);
    }

    public void goOwnerOrdersPage(View view) {
        Intent intent = new Intent(this, OwnerOrdersPage.class);
        intent.putExtra(LoginPage.EXTRA_MESSAGE, username);
        startActivity(intent);
    }

    public void signOut(View view) {
        finish();
    }

}