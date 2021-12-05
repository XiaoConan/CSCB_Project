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
        Intent intent = getIntent();
        //myAccount = (CustomerAccount) intent.getSerializableExtra(LoginPage.MY_ACCOUNT);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Stores");
        ref.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()) {
                    String bufferString = ds.getValue(String.class);
                    stores.add(bufferString);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("warning", "loadPost:onCancelled", error.toException());
            }
        });

        String storeList[] = {"test"};
        recyclerView = findViewById(R.id.storeListView);

        StoreListAdapter myAdapter = new StoreListAdapter(this, storeList);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void goToStore(View view){
        Intent intent = new Intent(this, ShoppingStore.class);
        Button clickedButton = (Button) findViewById(R.id.store_button);
        String choosingStore = clickedButton.getText().toString();
        intent.putExtra(CURRENT_STORE, choosingStore);
        intent.putExtra(LoginPage.EXTRA_MESSAGE, myAccount);
        startActivity(intent);
    }
}