package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class OwnerProductPage extends AppCompatActivity {
    /**  This page displays the products available in this store.
        (This page looks similar to StoreManagingPage, which allows the owner to
        add/remove a product.)
     */

    /*String myAccount;
    String storeID;
    ArrayList<String> productIDs;
    RecyclerView recyclerView;
    Context context = this;
    TextView displayMessageBox;*/
    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference productRef = database.getReference("Products");//private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_product_page);

        /* Reads the firebase for the items in the store, and display the name of the products
         in the Recycler View.
         */
        // Assume I am getting the owner's name from Intent.
        Intent intent = getIntent();
        String username = intent.getStringExtra(LoginPage.EXTRA_MESSAGE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference(getString(R.string.users_path));
        DatabaseReference storeRef = database.getReference(getString(R.string.stores_path));
        DatabaseReference productsRef = database.getReference(getString(R.string.products_path));
        Context context = this;

        // for storing strings as them come in, the info will later be given to productsIDs.
        ArrayList<String> productIDArrList = new ArrayList<String>();

        // To display product information as Recycler View, we will get them as string arrays.
        //  To do this, we first go to store(same name as owner's name), and
        //    get all productIDs as ArrayList<String> then transformed to String[].
        //  Then, knowing the size of the following arrays(productIDs.length)we will populate
        //     brands, names, prices in the corresponding order.

        // users -> ourUser -> storeID(key) -> (value)
        String storeID = usersRef.child(username).child(getString(R.string.store_id_child)).push().getKey(); //added .push().

        // store -> storeID -> productList -> listen here(to items in productList).
        storeRef.child(storeID).child(getString(R.string.product_list)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot products : snapshot.getChildren()) {
                    //String prodID = (String) products.getKey(); // since the data is stored as productID:true.
                    final String prodID = products.getKey();
                    productIDArrList.add(prodID);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) { }
        });

        String[] productIDs, brands, names;
        double[] prices;

        if (productIDArrList.isEmpty()){
            // if empty, we manually given them a value which is the message we want to display.
            productIDs = null;
            brands = new String[]{"No brand"};
            names = new String[]{"No products yet, please return to the previous page to add a product."};
            prices = new double[]{0};

            // populate the Recycler view with the products.
            // get the reference of RecyclerView
            RecyclerView recyclerView = findViewById(R.id.OwnerProductRecyclerView);
            // passing the data.
            OwnerProductAdapter adapter = new OwnerProductAdapter(context, productIDs, names, brands, prices);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context)); // set LayoutManager to RecyclerView
        }
        else {
            // initialize all the data arrays we need to display.
            productIDs = (String[]) productIDArrList.toArray();
            brands = new String[productIDs.length];
            names = new String[productIDs.length];
            prices = new double[productIDs.length];

            for (int i = 0; i < productIDs.length; i++) {
                // we read each productID, and get the info
                int finalI = i;
                productsRef.child(productIDs[i]).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        Product product = snapshot.getValue(Product.class);
                        brands[finalI] = product.getBrand();
                        names[finalI] = product.getName();
                        prices[finalI] = product.getPrice();
                        // we don't need to get the store id, do we?
                        // we're displaying to the store owner afterall.

                        // populate the Recycler view with the products.
                        // get the reference of RecyclerView
                        RecyclerView recyclerView = findViewById(R.id.OwnerProductRecyclerView);
                        // passing the data.
                        OwnerProductAdapter adapter = new OwnerProductAdapter(context, productIDs, names, brands, prices);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context)); // set LayoutManager to RecyclerView
                    }

                    @Override
                    public void onCancelled(DatabaseError error) { }
                });
            }
        }

        // After getting the information of the products, we create the RecyclerView
/*
        // populate the Recycler view with the products.
        // get the reference of RecyclerView
        RecyclerView recyclerView = findViewById(R.id.OwnerProductRecyclerView);
        // passing the data.
        OwnerProductAdapter adapter = new OwnerProductAdapter(this, productIDs, names, brands, prices);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // set LayoutManager to RecyclerView
*/    }

}