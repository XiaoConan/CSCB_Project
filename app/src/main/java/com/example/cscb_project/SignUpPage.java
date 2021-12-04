package com.example.cscb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SignUpPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
    }

    public void message(String s){
        // display the message s in textView
        TextView textView = findViewById(R.id.signupErrorText);
        textView.setText(s);
    }

    /*public void signUp(View view){
        // This correspond to the actions of the button
        // Checks if the username & password are valid
        // if valid: complete the action of signing up, then directed to the user's page
        // if invalid: pop up a text message.

        EditText userName = (EditText) findViewById(R.id.editTextTextPersonName10);
        String name = userName.getText().toString();
        if (name == ""){message("Username cannot be empty");}
        if (name.contains(" ")){message("Username cannot contain spaces");}

        //now we create the user, but without setting the password at the moment.
        UserAccount newUser;
        Intent intent = getIntent(); // gets the intent from Main, to me more specifically, the status of the spinner.
        String userIdentity = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        if (userIdentity.equals("Store Owner")){
            newUser = new OwnerAccount();
            newUser.setUsername(name);
            // check if username is valid.
            if (MainActivity.owners.contains(newUser)){ // **account name taken (needs alteration to make sure this only checks username)
                // pop message: Username taken
                message("Username is taken.");
            }

        }
        //else if (userIdentity.equals("Customer")){
        else{ // for fulfilling initialization of newUser on line 76.
            newUser = new CustomerAccount();
            newUser.setUsername(userName.getText().toString());
            // check if username is valid
            if (MainActivity.customers.contains(newUser)){ // **account name taken (needs alteration to make sure this only checks username)
                // pop message: Username taken
                message("Username is taken.");
            }
        }

        // if the username is not taken && is valid, we check the password.
        EditText password = (EditText) findViewById(R.id.editTextTextPassword2);
        String pass = password.getText().toString();
        // check password
        if (pass == ""){message("Password cannot be empty");}
        if (pass.contains(" ")){message("Password cannot contain spaces");}
        // and we set the password
        newUser.setPassword(pass);

        // now we write to database.
        // Writing to a realtime database
        // (**come back later? for firebase**)
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("users").child(name).setValue(newUser);   //creates a new child.

        // we now go to the user's page.
        Intent intent2;
        if (userIdentity.equals("Store Owner")){
            intent2 = new Intent(this, StoreOwnerPage.class);
        }
        else{
            intent2 = new Intent(this, CustomerPage.class);
        }
        startActivity(intent2);
    }*/

}