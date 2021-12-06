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

        TextView textView = findViewById(R.id.welcomeTextview);
        String message = "Welcome " + username;
        textView.setText(message);
    }

    public void goManageStore(View view) {
        Intent intent = new Intent(this, CreateProductPage.class);
        intent.putExtra(LoginPage.EXTRA_MESSAGE, username);
        startActivity(intent);
    }

    public void goOwnerProductPage(View view) {
        Intent intent = new Intent(this, OwnerProductPage.class);
        intent.putExtra(LoginPage.EXTRA_MESSAGE, username);//
        startActivity(intent);
    }

    public void goOwnerOrdersPage(View view) {
        Intent intent = new Intent(this, OwnerOrdersPage.class);
        intent.putExtra(LoginPage.EXTRA_MESSAGE, username);//
        startActivity(intent);
    }

    public void signOut(View view) { finish();}

}