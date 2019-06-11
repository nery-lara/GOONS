package com.nerylara.goons;

import android.app.Activity;
import android.content.Intent;
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
        switch (v.getId()){
            case R.id.icon0:
                int path1;
                path1 = R.drawable.icon1t;
                intent1.putExtra("path", path1);
                startActivity(intent1);
                break;
            case R.id.icon1:
                int path2;
                path2 = R.drawable.icon2t;
                intent1.putExtra("path", path2);
                startActivity(intent1);
                break;
            case R.id.icon2:
                int path3;
                path3 = R.drawable.icon3t;
                intent1.putExtra("path", path3);
                startActivity(intent1);
                break;
            case R.id.icon3:
                int path4;
                path4 = R.drawable.icon4t;
                intent1.putExtra("path", path4);
                startActivity(intent1);
                break;
            case R.id.icon4:
                int path5;
                path5 = R.drawable.icon5t;
                intent1.putExtra("path", path5);
                startActivity(intent1);
                break;
            case R.id.icon5:
                int path6;
                path6 = R.drawable.icon6t;
                intent1.putExtra("path", path6);
                startActivity(intent1);
                break;
            case R.id.icon6:
                int path7;
                path7 = R.drawable.icon7t;
                intent1.putExtra("path", path7);
                startActivity(intent1);
                break;
            case R.id.icon7:
                int path8;
                path8 = R.drawable.icon8t;
                intent1.putExtra("path", path8);
                startActivity(intent1);
                break;
            case R.id.icon8:
                int path9;
                path9 = R.drawable.icon9t;
                intent1.putExtra("path", path9);
                startActivity(intent1);
                break;

        }
    }

}
