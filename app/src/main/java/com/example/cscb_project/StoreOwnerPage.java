package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StoreOwnerPage extends AppCompatActivity {
    OwnerAccount myAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_page);
        // receive info from intent??
        Intent intent = getIntent();
        myAccount = (OwnerAccount)intent.getSerializableExtra(LoginPage.MY_ACCOUNT);
        TextView textView = findViewById(R.id.textView);
        textView.setText(myAccount.getUsername());
    }

    /** Called when the user taps the View Products button */
    public void goToProducts(View view) {
        Intent intent = new Intent(this, OwnerProductPage.class);
        // send to owner product page
        intent.putExtra(LoginPage.MY_ACCOUNT, myAccount);
        startActivity(intent);
    }

    /** Called when the user taps the View Orders button */
    public void goToOrders(View view) {
        Intent intent = new Intent(this, OwnerOrdersPage.class);
        // send the owner to owner order
        intent.putExtra(LoginPage.MY_ACCOUNT, myAccount);
        startActivity(intent);
    }


}