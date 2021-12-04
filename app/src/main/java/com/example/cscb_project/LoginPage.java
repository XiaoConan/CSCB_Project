package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {
    public static final String MY_ACCOUNT = "com.example.cscb_project.CURRENTACCOUNT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

    }

    public void display(String s) {
        TextView textView = (TextView) findViewById(R.id.loginMessage);
        textView.setText(s);
    }

    public void login(View view) {
        Intent intent = getIntent();
        String status = intent.getStringExtra(MainActivity.USER_TYPE_MESSAGE);
        EditText un = (EditText) findViewById(R.id.loginUsernameField);
        String username = un.getText().toString();
        EditText pw = (EditText) findViewById(R.id.loginPasswordField);
        String password = pw.getText().toString();

//        if (username == "" || password == "") {
//            display("Username or Password cannot be empty");
//        }
//        else if (status == "Store Owner") {
//            OwnerAccount owner = new OwnerAccount(username, password);
//            if (MainActivity.owners.contains(owner) == false) { //CHECK if firebase contains the username
//                display("User not found");
//            }
//            else {
//                int index = MainActivity.owners.indexOf(owner);
//                OwnerAccount current = MainActivity.owners.get(index);
//                if (current.getPassword() != password) { //Check if the password matches the username
//                    display("Incorrect Password");
//                }
//                else { //If all conditions satisfied
//                    Intent intent2 = new Intent(this, StoreOwnerPage.class);
//                    //send owner to next page
//                    intent2.putExtra(MY_ACCOUNT, current);
//                    startActivity(intent2);
//                }
//            }
//        }
//        else {
//            CustomerAccount customer = new CustomerAccount(username, password);
//            if (MainActivity.customers.contains(username)) { //CHECK if firebase contains the username
//                display.setText("User not found");
//            }
//            else {
//                int index = MainActivity.customers.indexOf(customer);
//                CustomerAccount current = MainActivity.customers.get(index);
//                if (current.getPassword() != password) { //Check if the password matches the username
//                    display("Incorrect Password");
//                }
//                else { //If all conditions satisfied
//                    Intent intent2 = new Intent(this, CustomerPage.class);
//                    //send customer to next page
//                    intent2.putExtra(MY_ACCOUNT, current);
//                    startActivity(intent2);
//                }
//            }
//
//        }

    }

}