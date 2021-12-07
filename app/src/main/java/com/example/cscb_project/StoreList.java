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

public class StoreList extends AppCompatActivity {
    public static final String CURRENT_STORE = "com.example.cscb_project.CURRENTSTORE";
    String myAccount;
    ArrayList<String> stores;
    ArrayList<String> ids;
    RecyclerView recyclerView;
    Context con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);

        recyclerView = findViewById(R.id.allStores);
        con = this;

        //get myAccount info from intent
        Intent intent = getIntent();
        myAccount = intent.getStringExtra(LoginPage.EXTRA_MESSAGE);

        //read store list from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Stores");
        ref.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                stores = new ArrayList<>();
                ids = new ArrayList<>();

                for(DataSnapshot ds: snapshot.getChildren()) {
                    String name = ds.child("storeName").getValue(String.class);
                    String id = ds.getKey();
                    stores.add(name);
                    ids.add(id);
                }

                StoreListAdapter productAdapter = new StoreListAdapter(con, stores, ids, myAccount);
                recyclerView.setAdapter(productAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(con));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}