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
        textView.findViewById(R.id.addToCartMessage);
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

                //read how many item (I don't know why here needs a array, if i just use int will be a error
                final int[] amount = new int[1];
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Carts").child(myAccount);
                ref.addListenerForSingleValueEvent( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //if item exist then add one, if not create the item and set it as 1
                        if(snapshot.exists())
                            amount[0] = (int)snapshot.getValue();
                        else{
                            amount[0] = 0;
                            ref.child(list.get(position)).setValue(amount[0]);
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("warning", "loadPost:onCancelled", error.toException());
                    }
                });
                ref.child(list.get(position)).setValue(amount[0] + 1);
                textView.setText("add one " + list.get(position) + " successfully" + "\n" + "now you have " + (amount[0] +1) +" of "+ list.get(position) + " in the Cart");
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
