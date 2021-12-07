package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class OwnerProductPage extends AppCompatActivity {
    private String storeID;
    ArrayList<String> productIDs;
    RecyclerView recyclerView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_product_page);

        Intent intent = getIntent();
        storeID = intent.getStringExtra(StoreList.CURRENT_STORE);

        context = this;
        recyclerView = findViewById(R.id.my_products);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference storeRef = database.getReference(getString(R.string.stores_path));

        productIDs = new ArrayList<String>();

        DatabaseReference productListRef = storeRef.child(storeID).child(getString(R.string.product_list));
        productListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                productIDs = new ArrayList<>();

                for(DataSnapshot ds: snapshot.getChildren()) {
                    String productID = ds.getKey();
                    productIDs.add(productID);
                }

                if (productIDs.isEmpty()) {


                }

                OwnerProductAdapter myAdapter = new OwnerProductAdapter(context, productIDs);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            }
            @Override
            public void onCancelled(DatabaseError error) { }
        });
    }

}