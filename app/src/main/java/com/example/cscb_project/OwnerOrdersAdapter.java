package com.example.cscb_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OwnerOrdersAdapter extends RecyclerView.Adapter<OwnerOrdersAdapter.MyViewHolder> {
    ArrayList<String> list; // of orderIDs
    Context context;

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
