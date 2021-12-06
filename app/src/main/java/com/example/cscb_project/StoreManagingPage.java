package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StoreManagingPage extends AppCompatActivity {
    public static final String invalid_product_error = "You already have this product";
    public static final String empty_box = "You need to enter all information of a product";
    public static final String unexpected_error = "Uh oh - unexpected error";


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



        //Then write to the firebase
    }
}   