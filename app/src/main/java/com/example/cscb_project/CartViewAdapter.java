package com.example.cscb_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartViewAdapter extends RecyclerView.Adapter<CartViewAdapter.MyViewHolder> {
    ArrayList<Order> cart;
    Context context;
    DecimalFormat df = new DecimalFormat("#0.00");

    public CartViewAdapter(ArrayList<Order> products, Context context) {
        this.cart = products;
        this.context = context;
    }

    public ArrayList<Order> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Order> cart) {
        this.cart = cart;
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
        String name = cart.get(position).getName();
        holder.productName.setText(name);
        int amount = cart.get(position).getQuantity();
        String amountS = amount + "x";
        holder.productAmount.setText(amountS);
        Double subtotal = cart.get(position).getSubtotal();

        String subtotalS = "$" + df.format(subtotal);
        holder.subtotal.setText(subtotalS);
    }

    @Override
    public int getItemCount() {
        return cart.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView productName;
        TextView productAmount;
        TextView subtotal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.cartProductName);
            productAmount = itemView.findViewById(R.id.cartProductAmount);
            subtotal = itemView.findViewById(R.id.cartProductPrice);

        }
    }
}
