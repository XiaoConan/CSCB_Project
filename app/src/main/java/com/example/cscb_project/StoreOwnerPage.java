package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StoreOwnerPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_page);
        // receive info from intent??
        Intent intent = getIntent();
        OwnerAccount owner = (OwnerAccount)intent.getSerializableExtra("owner");
    }

    /** Called when the user taps the View Products button */
    public void goToProducts(View view) {
        Intent intent = new Intent(this, OwnerProductPage.class);
        // intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /** Called when the user taps the View Orders button */
    public void goToOrders(View view) {
        Intent intent = new Intent(this, OwnerOrdersPage.class);
        // intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


}