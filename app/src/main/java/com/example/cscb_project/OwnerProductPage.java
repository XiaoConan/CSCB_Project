package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OwnerProductPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_product_page);
        Intent intent = getIntent();
        /** Reads the firebase for the items in the store, and display the name of the products
             in the spinner(and the first choice is "Add Product").
           Should the user choose an item of the spinner, display the information accordingly
             by getting the info of the product from firebase.
           Leave empty if the spinner chooses "Add Product"
         */
//        Intent intent = getIntent();
//        OwnerAccount owner = (OwnerAccount)intent.getSerializableExtra(LoginPage.MY_ACCOUNT);
//        Spinner spinner = findViewById(R.id.spinner);
//        String spinnerVal = String.valueOf(spinner.getSelectedItem());

        // read from firebase for the list of products.
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        // ** the following code is for a different program, requires a lot of change.
        //    and how to change it depends on how accounts are stored.
        ref.child("areas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                final List<String> areas = new ArrayList<String>();

                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    String areaName = areaSnapshot.child("areaName").getValue(String.class);
                    areas.add(areaName);
                }

                //Spinner areaSpinner = (Spinner) findViewById(R.id.spinner);
                //ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(UAdminActivity.this, android.R.layout.simple_spinner_item, areas);
                //areasAdapter.add("Add Product"); //first choice.
                //areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //spinner.setAdapter(areasAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // code ends. spinner should be populated by now.

        // I need a loop to keep checking new products if the user switches the spinner.
        //**and remember to change this condition.**
        /*while(false){
            // if the spinner is not on "Add Product", display the info of the product
            Product product = ref.child("products").child(spinnerVal).getValue();
            // ok, assuming I have retrieved productN
            EditText productN = (EditText) findViewById(R.id.editTextTextProductName);
            EditText productPrice = (EditText) findViewById(R.id.editTextTextProductName);
            EditText productBrand = (EditText) findViewById(R.id.editTextTextProductName);

            if (! spinnerVal.equals("Add Product")){
                productN.setText(product.getName());
                productPrice.setText(String.valueOf(product.getPrice()));
                productBrand.setText(product.getBrand());
            }
            else{ // set the fields to empty(in case they're not)
                productN.setText("");
                productPrice.setText("");
                productBrand.setText("");
            }
        }*/
    }

    public void updateProduct(View view){
        /** Triggered by the button "Confirm".
           if the spinner is in "Add Product", add the specifications given as a product to the
             store on firebase.
           if the spinner is in another product, then pressing "Confirm" will update the
             information of the product.
         */

        // I need to get the owner info from previous page. Assume an Intent is passed with OwnerAccount.
//        Intent intent = getIntent();
//        OwnerAccount owner = (OwnerAccount)intent.getSerializableExtra(LoginPage.MY_ACCOUNT);

        Spinner spinner = findViewById(R.id.spinner);
        // read from firebase for the list of products.
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

//        // if spinner is on "Add Product", we add a new product to store.
//        String productName = String.valueOf(spinner.getSelectedItem());
//        if (productName.equals("Add Product")){ // new product
//            EditText productN = (EditText) findViewById(R.id.editTextTextProductName);
//            String name = productN.getText().toString();
//            EditText productPrice = (EditText) findViewById(R.id.editTextTextProductPrice);
//            double price = Double.parseDouble(productPrice.getText().toString());
//            EditText productBrand = (EditText) findViewById(R.id.editTextTextProductBrand);
//            String brand = productBrand.getText().toString();
//            Product newProduct = new Product(name, price, brand, owner.getStore());
//            // add to firebase.
//            ref.child("products").child(productName).setValue(newProduct);
//        }
        // make the changes and update the product in firebase.
//        else{
//            //TODO
//        }
    }
}