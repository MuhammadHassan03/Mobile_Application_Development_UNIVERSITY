package com.example.convertor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Homepage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        LinearLayout volumeSelector = findViewById(R.id.volumeSelector);
        LinearLayout weightSelector = findViewById(R.id.weightSelector);
        LinearLayout massSelector = findViewById(R.id.massSelector);
        LinearLayout friendShipSelector = findViewById(R.id.friendShipSelector);
        LinearLayout bmiSelector = findViewById(R.id.bmiSelector);
        LinearLayout lengthSelector = findViewById(R.id.lengthSelector);
        LinearLayout timeUnitsSelector = findViewById(R.id.timeUnitsSelector);
        LinearLayout ageSelector = findViewById(R.id.ageSelector);
        LinearLayout timeCountrySelector = findViewById(R.id.timeCountrySelector);


        volumeSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConversionLayout("Volume");
            }
        });

        weightSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConversionLayout("Weight");
            }
        });

        massSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConversionLayout("Mass");
            }
        });

        friendShipSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConversionLayout("FriendShip");
            }
        });

        bmiSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConversionLayout("BMI");
            }
        });

        lengthSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConversionLayout("Length");
            }
        });


        timeUnitsSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConversionLayout("Time Unit");
            }
        });

        ageSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConversionLayout("Age");
            }
        });

        timeCountrySelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConversionLayout("Time Country");
            }
        });
    }
    public void openConversionLayout(String adapterType){
        Intent intent = new Intent(Homepage.this, ConversionLayout.class);
        intent.putExtra("adapterType", adapterType);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        startActivity(intent);
    }
}