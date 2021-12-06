package com.example.cscb_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OwnerProductAdapter extends RecyclerView.Adapter<OwnerProductAdapter.MyViewHolder> {
    // to hold values. IDs are not really used.
    String[] IDs, names, brands;
    double[] price;
    Context context;

    public OwnerProductAdapter(Context context, String[] IDs, String[] names, String[] brands, double[] price){
        // initialize our values.
        this.context = context;
        this.IDs = IDs;
        this.names = names;
        this.brands = brands;
        this.price = price;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.owner_product_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(names[position]);
        holder.brand.setText(brands[position]);
        holder.price.setText(Double.toString(price[position]));
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, brand, price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.TextView_OwnerProductRowName);
            brand = itemView.findViewById(R.id.TextView_OwnerProductRowBrand);
            price = itemView.findViewById(R.id.TextView_OwnerProductRowPrice);
        }
    }
}
