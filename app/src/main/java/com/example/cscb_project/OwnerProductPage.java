package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OwnerProductPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_product_page);
    }

    public void displayItems(View view){
        /* Read firebase for the items in the store, and display the name of them in the spinner.
             the info of the item can be viewed by the store owner, or altered.
           The last item of the spinner should be "Add Product"
             and in this case, let the user enter the product name, price, brand.
         */

        // I need to get the owner info from previous page. Assume an Intent is passed with OwnerAccount.
        Intent intent = getIntent();
        OwnerAccount owner = (OwnerAccount)intent.getSerializableExtra(LoginPage.MY_ACCOUNT);//get owner form Intent.;

        Spinner spinner = findViewById(R.id.spinner);
        // read from firebase for the list of products.
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        // ** the following code is for a different program, requires a lot of change.
        //    and how to change it depends on how accounts are stored.
        /*ref.child("areas").addValueEventListener(new ValueEventListener() {
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
                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(UAdminActivity.this, android.R.layout.simple_spinner_item, areas);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                areasAdapter.add("Add Product");
                spinner.setAdapter(areasAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        // code ends. spinner should be populated by now.

        // now we fill in the fields.
        String product = String.valueOf(spinner.getSelectedItem());
        if (product.equals("Add Product")){ // new product
            EditText productName = (EditText) findViewById(R.id.editTextTextProductName);
            String name = productName.getText().toString();
            EditText productPrice = (EditText) findViewById(R.id.editTextTextProductPrice);
            double price = Double.parseDouble(productPrice.getText().toString());
            EditText productBrand = (EditText) findViewById(R.id.editTextTextProductBrand);
            String brand = productBrand.getText().toString();
            Product newProduct = new Product(name, price, brand, owner.getStore());
        }
        // Display the info of the product from firebase
        else{

        }
    }
}