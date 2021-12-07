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
    ArrayList<String> orderIDs;
    RecyclerView recyclerView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_page);

        Intent intent = getIntent();
        myAccount = intent.getStringExtra(LoginPage.EXTRA_MESSAGE);

        recyclerView = findViewById(R.id.cOrdersDisplay);
        context = this;

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference(getString(R.string.users_path));
        DatabaseReference orderListRef =  usersRef.child(myAccount).child(getString(R.string.order_list));

        orderListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderIDs = new ArrayList<>();

                for(DataSnapshot ds: snapshot.getChildren()) {
                    String orderID = ds.getKey();
                    orderIDs.add(orderID);
                }

                CustomerOrderAdapter myAdapter = new CustomerOrderAdapter(context, orderIDs);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("warning", "loadPost:onCancelled", error.toException());
            }
        });


    }
}