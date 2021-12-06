package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {
    public static final String invalid_username_error = "Username not found";
    public static final String invalid_password_error = "Your password is incorrect";
    public static final String unexpected_error = "Uh oh - unexpected error";
    public static final String EXTRA_MESSAGE = "com.example.cscb_project.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }

    public void display(String s) {
        TextView textView = (TextView) findViewById(R.id.loginMessage);
        textView.setText(s);
    }

    public Intent createIntent(Class<?> c, String extra) {
        Intent intent = new Intent(this, c);
        intent.putExtra(EXTRA_MESSAGE, extra);
        return intent;
    }

    public void login(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference(getString(R.string.users_path));

        EditText usernameEditText = (EditText) findViewById(R.id.loginUsernameField);
        EditText passwordEditText = (EditText) findViewById(R.id.loginPasswordField);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.isEmpty()) {
            display(SignUpPage.empty_username_error);
        } else if (password.isEmpty()) {
            display(SignUpPage.empty_password_error);
        } else {
            // Check if username exists
            usersRef.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (!snapshot.exists()) {
                        display(invalid_username_error);
                    } else {
                        String expectedPassword = snapshot.child("password").getValue(String.class);
                        if (password.equals(expectedPassword)) {
                            // If info matches: get user type, create intent, clear fields, next page
                            // IMPORTANT: THIS ASSUMES THE DATA FORMAT IN FIREBASE IS CORRECT
                            String type = snapshot.child("type").getValue(String.class);

                            if (type.equals(getString(R.string.label_owner))) {
                                usernameEditText.setText("");
                                passwordEditText.setText("");
                                startActivity(createIntent(StoreOwnerPage.class, username));
                                finish();
                            } else if (type.equals(getString(R.string.label_customer))) {
                                usernameEditText.setText("");
                                passwordEditText.setText("");
                                startActivity(createIntent(CustomerPage.class, username));
                                finish();
                            } else {
                                // This is only if for some reason we read a type that is not owner/customer
                                display(unexpected_error);
                            }
                        } else {
                            display(invalid_password_error);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });
        }

    }

}