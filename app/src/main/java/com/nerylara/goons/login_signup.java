package com.nerylara.goons;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login_signup extends AppCompatActivity {

    private Button loginbutton;
    private Button signupbutton;
    //private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_signup);


        @SuppressLint("WrongViewCast") EditText username_signup = (EditText) findViewById(R.id.signup_username);
        username_signup.addTextChangedListener(signup_username_edit);
        loginbutton = findViewById(R.id.submit_login);
        signupbutton = findViewById(R.id.submit_signup);

        // TODO: Create onClick method for checking availability. Implement function to search db
        //   for availability

        // TODO:

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: Implement function that check if username and password are in database
                openMainActivity();
            }
        });

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: Implement function that will update db with new username and password
                openCreateProfile();
            }
        });

    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openCreateProfile(){
        Intent intent = new Intent(this, createProfile.class);
        startActivity(intent);
    }



    private TextWatcher signup_username_edit = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Button check_availability =  findViewById(R.id.check_name);
            check_availability.setVisibility(View.VISIBLE);
            if(s.length() == 0){
                check_availability.setVisibility(View.INVISIBLE);
            }
        }
        @Override
        public void afterTextChanged(Editable s) {
        }
    };



}
