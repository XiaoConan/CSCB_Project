package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CustomerPage extends AppCompatActivity {
    CustomerAccount myAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);
        Intent intent = getIntent();
        myAccount = (CustomerAccount)intent.getSerializableExtra(LoginPage.MY_ACCOUNT);
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
        // send customer to cart page
        intent.putExtra(LoginPage.MY_ACCOUNT, myAccount);
        startActivity(intent);
    }
}