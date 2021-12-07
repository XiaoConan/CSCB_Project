package com.example.cscb_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.MyViewHolder> {

    ArrayList<String> ids;
    ArrayList<String> names;
    Context context;
    String myAccount;

    public StoreListAdapter(Context ct, ArrayList<String> s1, ArrayList<String> s2, String myAcc) {
        this.context = ct;
        this.names = s1;
        this.ids = s2;
        this.myAccount = myAcc;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.store_list_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.storeView.setText(names.get(position));

        holder.myLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShoppingStore.class);
                intent.putExtra(LoginPage.EXTRA_MESSAGE, myAccount);
                intent.putExtra(StoreList.CURRENT_STORE, ids.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView storeView;
        ConstraintLayout myLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            storeView = itemView.findViewById(R.id.listView);
            myLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

}
