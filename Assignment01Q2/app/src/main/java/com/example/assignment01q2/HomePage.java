package com.example.assignment01q2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Button btn = findViewById(R.id.Button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenProductActivity();
            }
        });
    }
    public void OpenProductActivity(){
        Intent intent = new Intent(HomePage.this, proudct.class);
        startActivity(intent);
    }
}