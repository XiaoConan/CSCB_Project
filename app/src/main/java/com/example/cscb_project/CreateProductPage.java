package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CreateProductPage extends AppCompatActivity {
    public static final String invalid_product_error = "You already have this product";
    public static final String empty_box = "You need to enter all information of a product";
    public static final String unexpected_error = "Uh oh - unexpected error";
    public static final String initial_message = "Please enter your product's information ^_^";

    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private String storeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product_page);
        display(initial_message);

        Intent intent = getIntent();
        String username = intent.getStringExtra(LoginPage.EXTRA_MESSAGE);

        // Get storeID from user info
        ref.child(getString(R.string.users_path)).child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                storeID = snapshot.child("storeID").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        display(storeID);
    }

    public void display(String s) {
        TextView textView = findViewById(R.id.messageView);
        textView.setText(s);
    }

    public void addProduct(View view){
        EditText editText1 = findViewById(R.id.itemNameField);
        String name = editText1.getText().toString();
        EditText editText2 = findViewById(R.id.brandField);
        String brand = editText1.getText().toString();
        EditText editText3 = findViewById(R.id.priceField);
        double price = Double.parseDouble(editText3.getText().toString());

        // Error checks/messages~~

        // Add product to Products path
        Product product = new Product(name, brand, price, storeID);
        DatabaseReference newRef = ref.child(getString(R.string.products_path)).push();
        String productID = newRef.getKey();
        newRef.setValue(product);
        // Add product ID to its store product list
        ref.child(getString(R.string.stores_path)).child(storeID).child("productList").child(productID).setValue(true);


    }
}   