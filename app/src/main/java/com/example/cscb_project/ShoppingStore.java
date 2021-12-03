package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ShoppingStore extends AppCompatActivity {
    CustomerAccount  myAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_store);
        Intent intent = getIntent();
        myAccount = (CustomerAccount)intent.getSerializableExtra(LoginPage.MY_ACCOUNT);
    }
}