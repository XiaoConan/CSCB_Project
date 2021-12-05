package com.example.cscb_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShoppingStore extends AppCompatActivity {
    CustomerAccount  myAccount;
    Store currentStore;
    ArrayList<Product> products = new ArrayList<Product>();
    Product product;
    String storeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_store);
        Intent intent = getIntent();
        //myAccount = (CustomerAccount) intent.getSerializableExtra(LoginPage.MY_ACCOUNT);
        currentStore = (Store) intent.getSerializableExtra(StoreList.CURRENT_STORE);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(currentStore.getOwner().getUsername()).child("products");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()) {
                    Product product = ds.getValue(Product.class);
                    products.add(product);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("warning", "loadPost:onCancelled", error.toException());
            }
        };

        //Display product List
    }

    public void addToCart(){

    }
}