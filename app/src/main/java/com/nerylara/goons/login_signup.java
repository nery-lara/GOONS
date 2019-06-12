package com.nerylara.goons;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.mbms.StreamingServiceInfo;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class login_signup extends AppCompatActivity{

    private Button loginbutton;
    private Button signupbutton;
    //private static int SPLASH_TIME_OUT = 2000;
    TextView login_username1;
    TextView login_password1;
    TextView sign_username1;
    TextView sign_password1;
    Button availablebutton;
    private signUp signUp_info;
//    public final signUp signUp_info = new signUp(getApplicationContext());



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_signup);
        getSupportActionBar().hide();
        login_username1 = findViewById(R.id.login_username);
        login_password1 = findViewById(R.id.login_password);
        sign_username1 = findViewById(R.id.signup_username);
        sign_password1 = findViewById(R.id.signup_password);

       final signUp signUp_info = new signUp(getApplicationContext());
//

        @SuppressLint("WrongViewCast") final EditText username_signup = (EditText) findViewById(R.id.signup_username);
        username_signup.addTextChangedListener(signup_username_edit);
        loginbutton = findViewById(R.id.submit_login);
        signupbutton = findViewById(R.id.submit_signup);
        availablebutton = findViewById(R.id.check_name);

        // TODO: Create onClick method for checking availability. Implement function to search db
        //   for availability

        // TODO:

        availablebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp_info.setProposedId(sign_username1.getText().toString());
                signUp_info.sendProposedIDetails();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(signUp_info.getVerify()) {
                            signupbutton.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), "Username is VALID",
                                    Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Username is TAKEN",
                                    Toast.LENGTH_LONG).show();
                            signupbutton.setVisibility(View.INVISIBLE);
                        }
                    }
                }, 5000);
            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final login login_info = new login(getApplicationContext());
                login_info.setUserId(login_username1.getText().toString());
                System.out.println("SET USER ID");
                login_info.setPassword(login_password1.getText().toString());
                System.out.println("SET USER PASSWORD");
                login_info.sendLoginDetails();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("STALLING");
                        System.out.println(login_info.getVerify());
                        if(login_info.getVerify()) {
                            openMainActivity();
                        }else{
                            Toast.makeText(getApplicationContext(), "Invalid Username OR Password!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, 5000);
            }
        });

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sign_password1.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter password",
                            Toast.LENGTH_LONG).show();
                } else{
                    //signUp_info.setId(sign_username1.getText().toString());
                    //signUp_info.setPassword(sign_password1.getText().toString());
                    //signUp_info.sendSignUpDetails(getApplicationContext());
                    // Context myAppContext = getApplicationContext();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(getApplicationContext(), createProfile.class);
                            String username_transfer = sign_username1.getText().toString();
                            String password_transfer = sign_password1.getText().toString();
                            Bundle extra = new Bundle();
                            extra.putString("ExtraUsername",username_transfer);
                            extra.putString("ExtraPassword",password_transfer);
                            intent.putExtras(extra);
                            startActivity(intent);
                        }}, 5000);
                }
            }
        });
    check_button_visible();
    }

//    public class Singleton {
//        private static Singleton instance = null;
//        protected Singleton() {
//            // Exists only to defeat instantiation.
//        }
//        public static Singleton getInstance() {
//            if(instance == null) {
//                instance = new Singleton();
//            }
//            return instance;
//        }
//    }

    public void check_button_visible(){
        if (availablebutton.getVisibility() == View.INVISIBLE) {
            signupbutton.setVisibility(View.INVISIBLE);
        }
    }

    public void openMainActivity(){
        Intent intent_log = new Intent(this, MainActivity.class);
        intent_log.putExtra("Username_transfer",login_username1.getText().toString());
        startActivity(intent_log);
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
                check_button_visible();
            }
        }
        @Override
        public void afterTextChanged(Editable s) {
        }
    };



}
