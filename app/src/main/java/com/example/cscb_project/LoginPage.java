package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

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
            else if () { //Check if the password matches the username

            }
            else { //If all conditions satisfied
                intent = new Intent(this, CustomerPage.class);
                startActivity(intent);
            }
        }
        else {
            if (MainActivity.customers.contains(username)) { //CHECK if firebase contains the username

            }
            else if () { //Check if the password matches the username

            }
            else { //If all conditions satisfied
                intent = new Intent(this, CustomerPage.class);
                startActivity(intent);
            }

        }

    }
    public void checkUsername() {

    }
}