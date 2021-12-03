package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {
    public static final String MY_ACCOUNT = "com.example.cscb_project.CURRENTACCOUNT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

    }
    public void login() {
        Intent intent = getIntent();
        String status = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        EditText un = (EditText) findViewById(R.id.editTextTextPersonName3);
        String username = un.getText().toString();
        EditText pw = (EditText) findViewById(R.id.editTextTextPassword);
        String password = pw.getText().toString();
        TextView check = findViewById(R.id.textView2);
        if (username == "" || password == "") {
            check.setText("Username or Password cannot be empty");
        }
        else if (status == "Store Owner") {
            OwnerAccount owner = new OwnerAccount(username, password);
            if (MainActivity.owners.contains(owner) == false) { //CHECK if firebase contains the username
                check.setText("User not found");
            }
            else {
                int index = MainActivity.owners.indexOf(owner);
                OwnerAccount current = MainActivity.owners.get(index);
                if (current.getPassword() != password) { //Check if the password matches the username
                    check.setText("Incorrect Password");
                }
                else { //If all conditions satisfied
                    Intent intent2 = new Intent(this, StoreOwnerPage.class);
                    //send owner to next page
                    intent2.putExtra("owner", owner);
                    startActivity(intent2);
                }
            }
        }
        else {
            CustomerAccount customer = new CustomerAccount(username, password);
            if (MainActivity.customers.contains(username)) { //CHECK if firebase contains the username
                check.setText("User not found");
            }
            else {
                int index = MainActivity.customers.indexOf(customer);
                CustomerAccount current = MainActivity.customers.get(index);
                if (current.getPassword() != password) { //Check if the password matches the username
                    check.setText("Incorrect Password");
                }
                else { //If all conditions satisfied
                    Intent intent2 = new Intent(this, CustomerPage.class);
                    //send customer to next page
                    intent2.putExtra("customer", customer);
                    startActivity(intent2);
                }
            }

        }

    }
}