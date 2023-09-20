package com.example.bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView Logo = findViewById(R.id.logo);
        TextView DeveloperCredit = findViewById(R.id.DeveloperCredit);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splashscreen);
        Logo.startAnimation(animation);
        DeveloperCredit.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, HomePage.class));
                finish();
            }
        }, 3000);
    }
}