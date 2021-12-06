package com.example.cscb_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateProductPage extends AppCompatActivity {
    public static final String empty_name_error = "Please enter an item name";
    public static final String empty_brand_error = "Please enter a brand";
    public static final String empty_price_error = "Please enter a price";
    public static final String unexpected_error = "Uh oh - unexpected error";
    public static final String initial_message = "Please enter your product's information ^_^";
    public static final String success_message = "Successfully added new product! ^_____________^";

    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private String storeID;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product_page);
        display(initial_message);

        Intent intent = getIntent();
        username = intent.getStringExtra(LoginPage.EXTRA_MESSAGE);
        storeID = intent.getStringExtra(StoreList.CURRENT_STORE);
    }

    public void display(String s) {
        TextView textView = findViewById(R.id.messageView);
        textView.setText(s);
    }

    public void addProduct(View view){
        EditText editText1 = findViewById(R.id.itemField);
        String name = editText1.getText().toString();
        EditText editText2 = findViewById(R.id.brandField);
        String brand = editText2.getText().toString();
        EditText editText3 = findViewById(R.id.priceField);
        String priceString = editText3.getText().toString();

        // Error checks/messages~~
        if (name.isEmpty()) {
            display(empty_name_error);
        } else if (brand.isEmpty()) {
            display(empty_brand_error);
        } else if (priceString.isEmpty()) {
            display(empty_price_error);
        } else {

            double price = Double.parseDouble(priceString);

            // Add product to Products path
            Product product = new Product(name, brand, price, storeID);
            DatabaseReference newRef = ref.child(getString(R.string.products_path)).push();
            String productID = newRef.getKey();
            newRef.setValue(product);
            // Add product ID to its store product list
            ref.child(getString(R.string.stores_path)).child(storeID).child("productList").child(productID).setValue(true);

            // Clear fields~
            editText1.setText("");
            editText2.setText("");
            editText3.setText("");
            display(success_message);
        }

    }

    @Override
    public void onPrepareSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
        super.onPrepareSupportNavigateUpTaskStack(builder);
    }
}