package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StoreManagingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_managing_page);
        Intent intent = getIntent();
    }

    public void add_product(View view){
        EditText editText1 = (EditText) findViewById(R.id.editTextTextPersonName);
        String product_name = editText1.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.editTextTextPersonName3);
        String product_price = editText1.getText().toString();
        EditText editText3 = (EditText) findViewById(R.id.editTextTextPersonName2);
        String product_brand = editText1.getText().toString();
    }
}