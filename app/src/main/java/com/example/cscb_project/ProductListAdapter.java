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
    TextView textView;

    public ProductListAdapter(Context ct, ArrayList<String> s1, String myAccount){
        this.context = ct;
        this.list = s1;
        this.myAccount = myAccount;
        textView.findViewById(R.id.setOrderInfo);
    }

    @NonNull
    @Override
    public ProductListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new ProductListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.productView.setText(list.get(position));
        
        holder.myLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productView;
        ConstraintLayout myLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productView = itemView.findViewById(R.id.listView);
            myLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
