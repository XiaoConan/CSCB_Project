package com.example.cscb_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomerOrderPage extends AppCompatActivity {
    String myAccount;
    ArrayList<String> productIDs;
    ArrayList<Integer> quantity;
    RecyclerView recyclerView;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_page);
        //get user info and store info from intent
        Intent intent = getIntent();
        myAccount = intent.getStringExtra(LoginPage.EXTRA_MESSAGE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference orderRef = database.getReference("Orders");

        orderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productIDs = new ArrayList<>();

                for(DataSnapshot ds: snapshot.getChildren()) {
                    if (ds.child("customerID").getKey().equals(myAccount)){
                        String productID = ds.getKey();
                        int num = (int) ds.getValue();
                        productIDs.add(productID);
                        quantity.add(num);
                    }

                }

                recyclerView = findViewById(R.id.cOrdersDisplay);

                CustomerOrderAdapter myAdapter = new CustomerOrderAdapter(context, productIDs, quantity, myAccount);
                //recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("warning", "loadPost:onCancelled", error.toException());
            }
        });

//        //TextView storeName = findViewById(R.id.currentStoreView);
//        //storeName.setText(storeID);
    }
}