package com.example.cscb_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OwnerOrdersAdapter extends RecyclerView.Adapter<OwnerOrdersAdapter.MyViewHolder> {
    ArrayList<String> list; // of orderIDs
    Context context;
    public static final String complete_message = "Complete";
    public static final String incomplete_message = "Incomplete";

    public OwnerOrdersAdapter (Context context, ArrayList<String> orderIDs) {
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference("Orders").child(list.get(position));
        orderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String id = snapshot.getKey();
                String displayID = "#" + id.substring(id.length() - 8);

                OrderList orderList = snapshot.getValue(OrderList.class);
                String other = orderList.getCustomerID();
                boolean status = orderList.isComplete();
                //String other = snapshot.child("customerID").getValue(String.class);
                //Boolean status = snapshot.child("complete").getValue(Boolean.class);
                holder.idField.setText(displayID);
                holder.otherField.setText(other);

                if (status) {
                    holder.statusField.setText(complete_message);
                } else {
                    holder.statusField.setText(incomplete_message);
                }
                holder.myLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        orderRef.child("complete").setValue(!status);
                    }
                });

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
        ConstraintLayout myLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idField = itemView.findViewById(R.id.orderField);
            otherField = itemView.findViewById(R.id.otherField);
            statusField = itemView.findViewById(R.id.status);
            myLayout = itemView.findViewById(R.id.customerOrderView);
        }
    }
}
