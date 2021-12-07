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
    public static final String STORE_NAME = "com.example.cscb_project.STORENAME";
    private String username;
    private String storeID;
    private String storeName;

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
                String id = snapshot.child("storeID").getValue(String.class);
                storeID = id;
                searchStoreName();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        searchStoreName();
    }

    public void searchStoreName() {
        DatabaseReference storesRef = FirebaseDatabase.getInstance().getReference(getString(R.string.stores_path));

        storesRef.child(storeID).child("storeName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String name = snapshot.getValue(String.class);
                    storeName = name;
                    displayWelcome(username, name);
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

    public void goCreateProduct(View view) {
        Intent intent = new Intent(this, CreateProductPage.class);
        intent.putExtra(LoginPage.EXTRA_MESSAGE, username);
        intent.putExtra(StoreList.CURRENT_STORE, storeID);
        startActivity(intent);
    }

    public void goOwnerProductPage(View view) {
        Intent intent = new Intent(this, OwnerProductPage.class);
        intent.putExtra(LoginPage.EXTRA_MESSAGE, username);
        intent.putExtra(StoreList.CURRENT_STORE, storeID);
        startActivity(intent);
    }

    public void goOwnerOrdersPage(View view) {
        Intent intent = new Intent(this, OwnerOrdersPage.class);
        intent.putExtra(LoginPage.EXTRA_MESSAGE, username);
        intent.putExtra(StoreList.CURRENT_STORE, storeID);
        startActivity(intent);
    }

    public void goEditStore(View view) {
        Intent intent = new Intent(this, EditStoreNamePage.class);
        intent.putExtra(LoginPage.EXTRA_MESSAGE, username);
        intent.putExtra(StoreList.CURRENT_STORE, storeID);
        intent.putExtra(StoreOwnerPage.STORE_NAME, storeName);
        startActivity(intent);
    }

    public void signOut(View view) {
        finish();
    }

}