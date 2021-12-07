package com.example.cscb_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OwnerOrdersPage extends AppCompatActivity {
    String storeID;
    RecyclerView recyclerView;
    Context context;
    ArrayList<String> orderIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_orders_page);

        Intent intent = getIntent();
        storeID = intent.getStringExtra(StoreList.CURRENT_STORE);

        recyclerView = findViewById(R.id.ownerAllOrders);
        context = this;

        DatabaseReference storesRef = FirebaseDatabase.getInstance().getReference(getString(R.string.stores_path));
        DatabaseReference orderListRef = storesRef.child(storeID).child(getString(R.string.order_list));

        orderListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderIDs = new ArrayList<>();

                for(DataSnapshot ds: snapshot.getChildren()) {
                    String orderID = ds.getKey();
                    orderIDs.add(orderID);
                }

                OwnerOrdersAdapter myAdapter = new OwnerOrdersAdapter(context, orderIDs);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}