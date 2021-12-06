package com.example.cscb_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartViewAdapter extends RecyclerView.Adapter<CartViewAdapter.MyViewHolder> {
    ArrayList<Order> products;
    Context context;
    String myAccount;

    public CartViewAdapter(ArrayList<Order> products, Context context, String myAccount) {
        this.products = products;
        this.context = context;
        this.myAccount = myAccount;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_cart_view, parent, false);
        return new CartViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String name = products.get(position).getName();
        holder.productName.setText(name);
        int amount = products.get(position).getQuantity();
        String amountS = String.valueOf(amount);
        holder.productAmount.setText(amountS);
        Double subtotal = products.get(position).getSubtotal();
        String subtotalS = String.valueOf(subtotal);
        holder.subtotal.setText(subtotalS);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView productName;
        TextView productAmount;
        TextView subtotal;
        ConstraintLayout myLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.cartProductName);
            productAmount = itemView.findViewById(R.id.cartProductAmount);
            subtotal = itemView.findViewById(R.id.cartProductPrice);
            myLayout = itemView.findViewById(R.id.myCartView);
        }
    }
}
