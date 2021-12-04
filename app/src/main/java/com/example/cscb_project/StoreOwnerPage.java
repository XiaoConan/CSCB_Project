package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StoreOwnerPage extends AppCompatActivity {
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_page);

        Intent intent = getIntent();
        username = intent.getStringExtra(LoginPage.EXTRA_MESSAGE);

        TextView textView = findViewById(R.id.textView3);
        String message = "Welcome " + username;
        textView.setText(message);
    }


    public void goToProducts(View view) {
        Intent intent = new Intent(this, OwnerProductPage.class);
        // send to owner product page
        //intent.putExtra(LoginPage.MY_ACCOUNT, myAccount);
        startActivity(intent);
    }

    public void goToOrders(View view) {
        Intent intent = new Intent(this, OwnerOrdersPage.class);
        // send the owner to owner order
        //intent.putExtra(LoginPage.MY_ACCOUNT, myAccount);
        startActivity(intent);
    }

    // move orders+products onto a store page

    public void manageStore(View view) {
        // ????????????????
    }

    public void signOut(View view) {

    }


}