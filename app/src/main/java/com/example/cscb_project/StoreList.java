package com.example.cscb_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
    CustomerAccount myAccount;
    ArrayList<Store> stores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);
        Intent intent = getIntent();
        myAccount = (CustomerAccount) intent.getSerializableExtra(LoginPage.MY_ACCOUNT);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("stores");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()) {
                    Store store = ds.getValue(Store.class);
                    stores.add(store);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("warning", "loadPost:onCancelled", error.toException());
            }
        };
    }

    public void goToStore(View view){
        Intent intent = new Intent(this, ShoppingStore.class);
        //need to able choose a store
        Store choosingStore = new Store();
        intent.putExtra(CURRENT_STORE, choosingStore);
        intent.putExtra(LoginPage.MY_ACCOUNT, myAccount);
        startActivity(intent);
    }
}