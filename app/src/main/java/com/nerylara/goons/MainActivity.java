package com.nerylara.goons;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(navListener);

        // Start Profile Screen
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,new ProfileFragment())
                .commit();

        Intent svc= new Intent(this, backgroundMusic.class);
        startService(svc);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent svc= new Intent(this, backgroundMusic.class);
        startService(svc);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent svc= new Intent(this, backgroundMusic.class);
        startService(svc);
    }


    @Override
    protected void onStop() {
        super.onStop();
        Intent svc= new Intent(this, backgroundMusic.class);
        stopService(svc);
    }

    private  BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item){
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.navProfile:
                            selectedFragment = new ProfileFragment();
                            break;
                        case R.id.navWorld:
                            selectedFragment = new WorldFragment();
                            break;
                        case R.id.navSquads:
                            selectedFragment = new SquadsFragment();
                            break;
                    }
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,selectedFragment)
                            .commit();
                    return true;
                }
            };
}
