package com.example.cscb_project;

import android.annotation.SuppressLint;
import android.content.Context;
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

import java.util.ArrayList;

public class CustomerOrderAdapter extends RecyclerView.Adapter<CustomerOrderAdapter.MyViewHolder> {
    ArrayList<String> products;
    ArrayList<Integer> quantity;
    Context context;
    String myAccount;

    public CustomerOrderAdapter(Context context, ArrayList<String> products, ArrayList<Integer> amounts, String myAcc) {
        this.context = context;
        this.products = products;
        this.quantity = amounts;
        this.myAccount = myAcc;
    }

    @NonNull
    @Override
    public CustomerOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_cart_view, parent, false);
        return new CustomerOrderAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerOrderAdapter.MyViewHolder holder, int position) {
        String name = products.get(position);
        holder.productName.setText(name);
        int amount= quantity.get(position);
        String amountS = String.valueOf(amount);
        holder.productAmount.setText(amountS);

        DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference("Orders");
        orderRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String status = snapshot.child("complete").getValue(String.class);
                holder.textStatus.setText(status);
                String brand = snapshot.child("brand").getValue(String.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("warning", "loadPost:onCancelled", error.toException());
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView productName;
        TextView productAmount;
        TextView textStatus;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.orderProductName);
            productAmount = itemView.findViewById(R.id.orderProductAmount);
            textStatus = itemView.findViewById(R.id.status);
        }
    }
}
