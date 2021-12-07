package com.example.cscb_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomerOrderAdapter extends RecyclerView.Adapter<CustomerOrderAdapter.MyViewHolder> {
    ArrayList<String> list; // of orderIDs
    Context context;

    public CustomerOrderAdapter(Context context, ArrayList<String> orderIDs) {
        this.context = context;
        this.list = orderIDs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerOrderAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String id = snapshot.getKey();
                String other = snapshot.child("Orders").child(list.get(position)).child("storeID").getValue(String.class);
                String storeName = snapshot.child("Stores").child(other).child("storeName").getValue(String.class);
                String status = snapshot.child("Orders").child(list.get(position)).child("complete").getValue(String.class);
                holder.idField.setText(id);
                holder.otherField.setText(storeName);
                holder.statusField.setText(status);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView idField, otherField, statusField;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idField = itemView.findViewById(R.id.orderField);
            otherField = itemView.findViewById(R.id.otherField);
            statusField = itemView.findViewById(R.id.status);
        }
    }
}
