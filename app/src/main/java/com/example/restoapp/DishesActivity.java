package com.example.restoapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ViewFlipper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class DishesActivity extends AppCompatActivity {
    ViewFlipper v_flipper;

    int[] images = {R.drawable.peruana,R.drawable.sushi,R.drawable.pollo};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);

        v_flipper = findViewById(R.id.v_flipper);

        for (int image : images) {
            flipperImages(image);
        }

        //Para el Menu inferior de navegacion
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_dishes) {
                    startActivity(new Intent(getApplicationContext(), DishesActivity.class));
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);

                    return true;
                } else if (item.getItemId() == R.id.navigation_reservas) {
                    startActivity(new Intent(getApplicationContext(), ReservationsActivity.class));
                    return true;
                } else if (item.getItemId() == R.id.navigation_profile) {
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    return true;
                } else {
                    return false;
                }

            }
        });


    }

    public void flipperImages(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(image);
        v_flipper.addView(imageView);
        v_flipper.setAutoStart(true);

        v_flipper.setFlipInterval(3000);
        v_flipper.setAutoStart(true);

        Animation slideInLeft = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slideOutRight = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        v_flipper.setInAnimation(slideInLeft);
        v_flipper.setOutAnimation(slideOutRight);
    }
}
