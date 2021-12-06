package com.example.cscb_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);

        //get myAccount info from intent
        Intent intent = getIntent();
        myAccount = intent.getStringExtra(LoginPage.EXTRA_MESSAGE);

        //read store list from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Stores");
        ref.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()) {
                    String bufferString = ds.getKey();
                    stores.add(bufferString);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("warning", "loadPost:onCancelled", error.toException());
            }
        });

        TextView tx = findViewById(R.id.testText);
        tx.setText(stores.get(0));
        //use recyclerView to display store list
//        recyclerView = findViewById(R.id.allStores);
//
//        StoreListAdapter myAdapter = new StoreListAdapter(this, stores, myAccount);
//        recyclerView.setAdapter(myAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

//    public void goToStore(View view){
//        Intent intent = new Intent(this, ShoppingStore.class);
//        //TextView storeView = findViewById(R.id.store_button);
//        //String choosingStore = storeView.getText().toString();
//        //intent.putExtra(CURRENT_STORE, choosingStore);
//        intent.putExtra(LoginPage.EXTRA_MESSAGE, myAccount);
//        startActivity(intent);
//    }
}