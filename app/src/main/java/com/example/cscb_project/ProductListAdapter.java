package com.example.cscb_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {
    ArrayList<String> list;
    Context context;
    String myAccount;
    ArrayList<Order> cart;
    RecyclerView recyclerView2;
    //TextView textView;

    public ProductListAdapter(Context ct, ArrayList<String> s1, String myAccount, ArrayList<Order> cart){
        this.context = ct;
        this.list = s1;
        this.myAccount = myAccount;
        this.cart = cart;
     //   textView.findViewById(R.id.setOrderInfo);
        //textView.findViewById(R.id.addToCartMessage);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_list_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        DatabaseReference productRef = FirebaseDatabase.getInstance().getReference("Products").child(list.get(position));

        productRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue(String.class);
                holder.productName.setText(name);
                String brand = snapshot.child("brand").getValue(String.class);
                holder.productPrice.setText(brand);
                Double d = snapshot.child("price").getValue(Double.class);
                String price = Double.toString(d);
                holder.productBrand.setText(price);
                holder.myLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Order order = new Order(name, 1, d);
                        if(cart.contains(order)){
                            cart.get(cart.indexOf(order)).setQuantity(cart.get(cart.indexOf(order)).getQuantity() + 1);
                            cart.get(cart.indexOf(order)).setSubtotal(cart.get(cart.indexOf(order)).getSubtotal() + order.getSubtotal());
                        }
                        else{
                            cart.add(order);
                        }
                        recyclerView2 = view.findViewById(R.id.cartView);

                        CartViewAdapter cartAdapter = new CartViewAdapter(cart, context, myAccount);
                        recyclerView2.setAdapter(cartAdapter);
                        recyclerView2.setLayoutManager(new LinearLayoutManager(context));

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("warning", "loadPost:onCancelled", error.toException());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productBrand;
        TextView productPrice;
        ConstraintLayout myLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productBrand = itemView.findViewById(R.id.productBrand);
            productPrice = itemView.findViewById(R.id.productPrice);
            myLayout = itemView.findViewById(R.id.productListView);
        }
    }
}
