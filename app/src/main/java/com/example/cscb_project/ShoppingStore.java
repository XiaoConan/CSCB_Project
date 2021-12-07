package com.example.cscb_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingStore extends AppCompatActivity {
    public static final String empty_cart_error = "Your cart is empty!";
    public static final String success_message = "Order sent successfully!";
    String myAccount;
    String storeID;
    ArrayList<String> productIDs;
    RecyclerView productView;
    Context context;
    RecyclerView cartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_store);

        productView = findViewById(R.id.productView);
        cartView = findViewById(R.id.cartView);
        context = this;

        //get user info and store info from intent
        Intent intent = getIntent();
        myAccount = intent.getStringExtra(LoginPage.EXTRA_MESSAGE);
        storeID = intent.getStringExtra(StoreList.CURRENT_STORE);

        DatabaseReference storesRef = FirebaseDatabase.getInstance().getReference(getString(R.string.stores_path));
        DatabaseReference productListRef = storesRef.child(storeID).child(getString(R.string.product_list));

        productListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productIDs = new ArrayList<>();

                for(DataSnapshot ds: snapshot.getChildren()) {
                    String productID = ds.getKey();
                    productIDs.add(productID);
                }

                ProductListAdapter myAdapter = new ProductListAdapter(context, productIDs, cartView);
                productView.setAdapter(myAdapter);
                productView.setLayoutManager(new LinearLayoutManager(context));

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void display(String s) {
        TextView textView = findViewById(R.id.setOrderInfo);
        textView.setText(s);
    }

    public void submitOrder(View view) {
        CartViewAdapter adapter = (CartViewAdapter) cartView.getAdapter();
        ArrayList<Order> orders = adapter.getCart();

        if (orders.isEmpty()) {
            display(empty_cart_error);
        } else {
            Map<String, Integer> map = new HashMap<>();
            for (Order order : orders) {
                map.put(order.getProductID(), order.getQuantity());
            }

            OrderList orderList = new OrderList(myAccount, storeID, map);

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

            DatabaseReference newRef = ref.child((getString(R.string.orders_path))).push();
            String orderID = newRef.getKey();

            DatabaseReference storesRef = ref.child((getString(R.string.stores_path)));
            DatabaseReference usersRef = ref.child((getString(R.string.users_path)));

            // Firebase: create order, add orderID to customer, add orderID to store
            newRef.setValue(orderList);
            storesRef.child(storeID).child(getString(R.string.order_list)).child(orderID).setValue(true);
            usersRef.child(myAccount).child(getString(R.string.order_list)).child(orderID).setValue(true);

            // Success message + clear cart
            display(success_message);
            orders.clear();
            adapter.notifyDataSetChanged();

        }
    }

}