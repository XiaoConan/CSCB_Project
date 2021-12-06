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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShoppingStore extends AppCompatActivity {
    String myAccount;
    String currentStore;
    ArrayList<Product> products;
    RecyclerView recyclerView;
    Context context = this;
    TextView displayMessageBox;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference productRef = database.getReference("Products");
 //   DatabaseReference storeRef = FirebaseDatabase.getInstance().getReference("Stores").child(currentStore).child("productList");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_store);

        //get user info and store info from intent
        Intent intent = getIntent();
        myAccount = intent.getStringExtra(LoginPage.EXTRA_MESSAGE);
        currentStore = intent.getStringExtra(StoreList.CURRENT_STORE);

        //get product belong to the firebase
        productRef.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()) {
                    //check the product list and get all the products that belong to currentStore
                    if(ds.child("storeID").getValue().toString().equals(currentStore))
                        products.add(ds.getValue(Product.class));

//                   searchProudctById(bufferId);
                }

                recyclerView = findViewById(R.id.storeListView);

                ProductListAdapter myAdapter = new ProductListAdapter(context, products, myAccount);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));

//                displayMessageBox = findViewById(R.id.setOrderInfo);
//                displayMessageBox.setText("");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("warning", "loadPost:onCancelled", error.toException());
            }
        });
        //TextView storeName = findViewById(R.id.currentStoreView);
        //storeName.setText(currentStore);


    }

//    public void searchProudctById(String id){
//
//        productRef.child(id).addListenerForSingleValueEvent( new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Product product;
//                product = (Product) snapshot.getValue(Product.class);
//                products.add(product);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.w("warning", "loadPost:onCancelled", error.toException());
//            }
//        });
//    }

}