package com.nerylara.goons;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MiniGame extends AppCompatActivity {

    int limit = 30;
    private int[] imagesIds = new int[3];
    int counter = 0;
    int im_counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame);

       imagesIds[0]= R.drawable.pow;
       imagesIds[1]= R.drawable.boom;
       imagesIds[2]= R.drawable.icon2t;


        // THIS IS ONLY FOR TESTING
        Intent intent = getIntent();
        String username = intent.getStringExtra("Username_transfer");

        // ^^^^^^^^^^^^ MAY BE DELETED

        final ImageView opponent = findViewById(R.id.Enemy);
        final ImageView stop = findViewById(R.id.stop);
        opponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    System.out.println("COUNTER "+counter);
                    im_counter++;
                    System.out.println("IMAGE COUNTER INCREMENTED "+im_counter);
                    counter ++;
                    if (im_counter == 3){
                        im_counter = 0;
                    }
                    opponent.setImageResource(imagesIds[im_counter]);
                    System.out.println("COUNTER INCREMENTED "+counter);
                        if (counter == limit ){
                        Toast.makeText(getApplicationContext(), "DONE",
                                Toast.LENGTH_LONG).show();
                        opponent.setVisibility(View.INVISIBLE);
                        stop.setVisibility(View.VISIBLE);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = getIntent();
                                String username = intent.getStringExtra("Username_transfer");
                                Intent back = new Intent(getApplicationContext(),MainActivity.class);
                                back.putExtra("Username_transfer",username);

                                startActivity(back);
                            }
                        }, 2000);

                    }
            }
        });

    }



}