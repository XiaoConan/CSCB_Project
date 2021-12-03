package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CustomerPage extends AppCompatActivity {
    public static final String MY_ACCOUNT = "com.example.cscb_project.CURRENTACCOUNT";
    CustomerAccount myAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);
        Intent intent = getIntent();
        CustomerAccount customer = (CustomerAccount)intent.getSerializableExtra("customer");
    }

    /** Called when the user taps the Go shop button */
    public void goToShop(View view) {
        Intent intent = new Intent(this, ShoppingStore.class);
        // intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /** Called when the user taps the My Cart button */
    public void goToCart(View view) {
        Intent intent = new Intent(this, CartPage.class);
        // intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}