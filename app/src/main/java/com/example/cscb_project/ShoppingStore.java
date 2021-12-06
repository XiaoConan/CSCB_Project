package com.example.cscb_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShoppingStore extends AppCompatActivity {
    String myAccount;
    String storeID;
    ArrayList<String> productIDs;
    ArrayList<Order> cart;
    RecyclerView recyclerView;
    Context context = this;
    TextView displayMessageBox;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference productRef = database.getReference("Products");
 //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_store);

        //get user info and store info from intent
        Intent intent = getIntent();
        myAccount = intent.getStringExtra(LoginPage.EXTRA_MESSAGE);
        storeID = intent.getStringExtra(StoreList.CURRENT_STORE);

        DatabaseReference storeRef = FirebaseDatabase.getInstance().getReference("Stores").child(storeID).child("productList");

        storeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productIDs = new ArrayList<>();
                cart = new ArrayList<Order>();

                for(DataSnapshot ds: snapshot.getChildren()) {
                    String productID = ds.getKey();
                    productIDs.add(productID);
                }

                recyclerView = findViewById(R.id.productView);

                ProductListAdapter myAdapter = new ProductListAdapter(context, productIDs, myAccount, cart);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("warning", "loadPost:onCancelled", error.toException());
            }
        });

//        //TextView storeName = findViewById(R.id.currentStoreView);
//        //storeName.setText(storeID);
//

    }



}