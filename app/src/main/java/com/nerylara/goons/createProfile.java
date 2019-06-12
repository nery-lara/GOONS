package com.nerylara.goons;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import static android.support.constraint.Constraints.TAG;

public class createProfile extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);


        ImageView image0 = findViewById(R.id.icon0);
        ImageView image1 = findViewById(R.id.icon1);
        ImageView image2 = findViewById(R.id.icon2);
        ImageView image3 = findViewById(R.id.icon3);
        ImageView image4 = findViewById(R.id.icon4);
        ImageView image5 = findViewById(R.id.icon5);
        ImageView image6 = findViewById(R.id.icon6);
        ImageView image7 = findViewById(R.id.icon7);
        ImageView image8 = findViewById(R.id.icon8);

        image0.setOnClickListener(this);
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
        image6.setOnClickListener(this);
        image7.setOnClickListener(this);
        image8.setOnClickListener(this);

        // *** = DONE

        // TODO: Display the different squads ****
        // TODO: Display Icons that they can choose from ****
        // TODO: Send icon info to server
        // TODO: Go to choose_squad activity ****
    }

    public void onClick(View v){
        Log.i(TAG, "Enters onClick Method");
        Intent intent1 = new Intent(this, ChooseSquad.class);
        final signUp signUp_info = new signUp(getApplicationContext());
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String username_string = extras.getString("ExtraUsername");
        String password_string = extras.getString("ExtraPassword");

        switch (v.getId()){
            case R.id.icon0:
                signUp_info.setId(username_string);
                signUp_info.setPassword(password_string);
                signUp_info.setimageNum(0);
                signUp_info.sendSignUpDetails(getApplicationContext());
                intent1.putExtra("Username_Transfer",username_string);
                startActivity(intent1);
                break;
            case R.id.icon1:
                signUp_info.setId(username_string);
                signUp_info.setPassword(password_string);
                signUp_info.setimageNum(1);
                signUp_info.sendSignUpDetails(getApplicationContext());
                intent1.putExtra("Username_Transfer",username_string);
                startActivity(intent1);
                break;
            case R.id.icon2:
                signUp_info.setId(username_string);
                signUp_info.setPassword(password_string);
                signUp_info.setimageNum(2);
                signUp_info.sendSignUpDetails(getApplicationContext());
                intent1.putExtra("Username_Transfer",username_string);
                startActivity(intent1);
                break;
            case R.id.icon3:
                signUp_info.setId(username_string);
                signUp_info.setPassword(password_string);
                signUp_info.setimageNum(3);
                signUp_info.sendSignUpDetails(getApplicationContext());
                intent1.putExtra("Username_Transfer",username_string);
                startActivity(intent1);
                break;
            case R.id.icon4:
                signUp_info.setId(username_string);
                signUp_info.setPassword(password_string);
                signUp_info.setimageNum(4);
                signUp_info.sendSignUpDetails(getApplicationContext());
                intent1.putExtra("Username_Transfer",username_string);
                startActivity(intent1);
                break;
            case R.id.icon5:
                signUp_info.setId(username_string);
                signUp_info.setPassword(password_string);
                signUp_info.setimageNum(5);
                intent1.putExtra("Username_Transfer",username_string);
                signUp_info.sendSignUpDetails(getApplicationContext());
                startActivity(intent1);
                break;
            case R.id.icon6:
                signUp_info.setId(username_string);
                signUp_info.setPassword(password_string);
                signUp_info.setimageNum(6);
                signUp_info.sendSignUpDetails(getApplicationContext());
                intent1.putExtra("Username_Transfer",username_string);
                startActivity(intent1);
                break;
            case R.id.icon7:
                signUp_info.setId(username_string);
                signUp_info.setPassword(password_string);
                signUp_info.setimageNum(7);
                signUp_info.sendSignUpDetails(getApplicationContext());
                intent1.putExtra("Username_Transfer",username_string);
                startActivity(intent1);
                break;
            case R.id.icon8:
                signUp_info.setId(username_string);
                signUp_info.setPassword(password_string);
                signUp_info.setimageNum(8);
                signUp_info.sendSignUpDetails(getApplicationContext());
                intent1.putExtra("Username_Transfer",username_string);
                startActivity(intent1);
                break;
        }
    }

}
