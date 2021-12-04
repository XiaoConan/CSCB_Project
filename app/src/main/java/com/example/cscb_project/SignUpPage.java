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

import java.util.regex.Pattern;

public class SignUpPage extends AppCompatActivity {
    public static final String empty_username_error = "Please enter a username";
    public static final String empty_password_error = "Please enter a password";
    public static final String invalid_username_error = "Username cannot contain spaces or special characters";
    public static final String username_taken_error = "Username already in use";
    public static final String success_message = "Successfully registered!";
    public static final String validUsernameRegex = "\\w+";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
    }

    public void display(String s) {
        // display s in signupErrorField
        TextView textView = (TextView) findViewById(R.id.signupMessage);
        textView.setText(s);
    }

    public void signUp(View view) {
        Intent intent = getIntent();
        String userType = intent.getStringExtra(MainActivity.USER_TYPE_MESSAGE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference(getString(R.string.users_path));

        EditText usernameEditText = (EditText) findViewById(R.id.signupUsernameField);
        EditText passwordEditText = (EditText) findViewById(R.id.signupPasswordField);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Check username & password valid
        if (username.isEmpty()) {
            display(empty_username_error);
        } else if (password.isEmpty()) {
            display(empty_password_error);
        } else if (!Pattern.matches(validUsernameRegex, username)) {
            display(invalid_username_error);
        }

        // Check if username already exists
        ValueEventListener listener = usersRef.child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    display(username_taken_error);
                } else {
                    // Add info to firebase
                    usersRef.child(username).child("password").setValue(password);
                    usersRef.child(username).child("type").setValue(userType);

                    display(success_message);

                    // Clear fields
                    usernameEditText.setText("");
                    passwordEditText.setText("");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        // Close listener
        usersRef.removeEventListener(listener);
    }

}
