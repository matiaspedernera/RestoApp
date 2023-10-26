package com.example.restoapp;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;



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
