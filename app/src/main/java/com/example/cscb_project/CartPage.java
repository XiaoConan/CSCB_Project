package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CartPage extends AppCompatActivity {
    CustomerAccount myAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);

    }

    public void sentOrder(View view) {
//        myAccount.getCart().get(0).getProduct().getStore().receiveOrder(new OrderList(myAccount.getCart()));
//        myAccount.sendOrder();
    }
}