package com.nerylara.goons;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import static android.support.constraint.Constraints.TAG;

public class ChooseSquad extends AppCompatActivity implements View.OnClickListener {

    Intent intent = new Intent(this, MainActivity.class);
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_squad);


        final ImageView image0 = findViewById(R.id.squad0);
        final ImageView image1 = findViewById(R.id.squad1);
        final ImageView image2 = findViewById(R.id.squad2);
        final ImageView image3 = findViewById(R.id.squad3);
        image0.setOnClickListener(this);
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final joinSquad joinSquad_info = new joinSquad();
        Intent intent = getIntent();
        final String username =  intent.getStringExtra("Username_Transfer");
        switch (v.getId()) {
            case R.id.squad0:
                Log.i(TAG, "Clicked Squad 0");
                AlertDialog builder = new AlertDialog.Builder(this).create();
                builder.setTitle("Confirm");
                builder.setMessage("Are you sure you want to represent North Squad?");
                builder.setButton("Initiate", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        joinSquad_info.setId(username);
                        joinSquad_info.setSquad("north");
                        joinSquad_info.sendSquaDetails(getApplicationContext());
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("Username_transfer",username);
                        startActivity(intent);
                    }

                });
                builder.show();

                break;
            case R.id.squad1:
                Log.i(TAG, "Clicked Squad 1");
                AlertDialog builder1 = new AlertDialog.Builder(this).create();
                builder1.setTitle("Confirm");
                builder1.setMessage("Are you sure you want to represent EAST Squad?");
                builder1.setButton("Initiate", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        joinSquad_info.setId(username);
                        joinSquad_info.setSquad("south");
                        joinSquad_info.sendSquaDetails(getApplicationContext());
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("Username_transfer",username);




                        startActivity(intent);
                    }
                });
                builder1.show();
                break;
            case R.id.squad2:
                Log.i(TAG, "Clicked squad2");
                AlertDialog builder2 = new AlertDialog.Builder(this).create();
                builder2.setTitle("Confirm");
                builder2.setMessage("Are you sure you want to represent SOUTH Squad?");
                builder2.setButton("Initiate", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        joinSquad_info.setId(username);
                        joinSquad_info.setSquad("east");
                        joinSquad_info.sendSquaDetails(getApplicationContext());
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("Username_transfer",username);

                        startActivity(intent);

                    }
                });
                builder2.show();
                break;
            case R.id.squad3:
                AlertDialog builder3 = new AlertDialog.Builder(this).create();
                builder3.setTitle("Confirm");
                builder3.setMessage("Are you sure you want to represent WEST Squad?");
                builder3.setButton("Initiate", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        joinSquad_info.setId(username);
                        joinSquad_info.setSquad("west");
                        joinSquad_info.sendSquaDetails(getApplicationContext());
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("Username_transfer",username);
                        startActivity(intent);
                    }
                });
                builder3.show();
                break;
        }
    }
}
