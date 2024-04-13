package com.example.myhotelapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myhotelapp.ui.HomeFragment;
import com.example.myhotelapp.ui.ReservationCheckFragment;
import com.example.myhotelapp.ui.RoomSearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home_menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();
                return true;

            case R.id.edit_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new RoomSearchFragment()).commit();
                return true;

            case R.id.more_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ReservationCheckFragment()).commit();
                return true;
        }
        return false;
    }
}