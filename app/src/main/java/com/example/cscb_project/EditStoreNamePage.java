package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditStoreNamePage extends AppCompatActivity {
    public static final String initial_message = "Change your store's name here.";
    public static final String empty_name_error = "Your store name cannot be blank...";
    public static final String same_name_error = "That's the same name!";
    public static final String success_message = "Store name successfully changed!";

    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    private String storeID;
    private String username;
    private String storeName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_store_name_page);

        display(initial_message);

        Intent intent = getIntent();
        username = intent.getStringExtra(LoginPage.EXTRA_MESSAGE);
        storeID = intent.getStringExtra(StoreList.CURRENT_STORE);
        storeName = intent.getStringExtra(StoreOwnerPage.STORE_NAME);

        TextView textView = findViewById(R.id.oldField);
        textView.setText(storeName);
    }

    public void display(String s) {
        TextView textView = findViewById(R.id.messageView2);
        textView.setText(s);
    }

    public void changeName(View view) {
        EditText editText1 = findViewById(R.id.newField);
        String name = editText1.getText().toString();
        TextView textView = findViewById(R.id.oldField);

        if (name.isEmpty()) {
            display(empty_name_error);
        } else if (name.equals(storeName)) {
            display(same_name_error);
        } else {
            DatabaseReference path = ref.child(getString(R.string.stores_path)).child(storeID).child("storeName");
            path.setValue(name);

            storeName = name;
            textView.setText(name);
            editText1.setText("");
            display(success_message);
        }

    }


}