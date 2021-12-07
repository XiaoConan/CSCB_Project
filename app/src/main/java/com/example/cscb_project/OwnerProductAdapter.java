package com.example.cscb_project;

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

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OwnerProductAdapter extends RecyclerView.Adapter<OwnerProductAdapter.MyViewHolder> {
    ArrayList<String> list; // of productIDs
    Context context;
    DecimalFormat df = new DecimalFormat("#.00");

    public OwnerProductAdapter(Context ct, ArrayList<String> s1) {
        this.context = ct;
        this.list = s1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_list_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DatabaseReference productRef = FirebaseDatabase.getInstance().getReference("Products").child(list.get(position));

        productRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue(String.class);
                holder.productName.setText(name);
                String brand = snapshot.child("brand").getValue(String.class);
                holder.productPrice.setText(brand);
                Double d = snapshot.child("price").getValue(Double.class);
                String price = "$" +  df.format(d);
                holder.productBrand.setText(price);
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

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView productName, productBrand, productPrice;
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
