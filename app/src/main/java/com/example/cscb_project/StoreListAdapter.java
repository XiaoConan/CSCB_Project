package com.example.cscb_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.MyViewHolder> {

    String list[];
    Context context;

    public StoreListAdapter(Context ct, String s1[]){
        this.context = ct;
        this.list = s1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myButton.setText(list[position]);
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        Button myButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myButton = itemView.findViewById(R.id.store_button);
        }
    }

}
