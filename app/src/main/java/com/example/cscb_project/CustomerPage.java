package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CustomerPage extends AppCompatActivity {
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);

        Intent intent = getIntent();
        username = intent.getStringExtra(LoginPage.EXTRA_MESSAGE);

        TextView textView = findViewById(R.id.textView2);
        String message = "Welcome " + username;
        textView.setText(message);
    }

    public void browseStores(View view) {
        Intent intent = new Intent(this, StoreList.class);
        intent.putExtra(LoginPage.EXTRA_MESSAGE, username);
        startActivity(intent);
    }

    public void goMyOrders(View view){
        Intent intent = new Intent(this, CustomerOrderPage.class);
        startActivity(intent);
    }

    public void signOut(View view) {
        finish();
    }

}