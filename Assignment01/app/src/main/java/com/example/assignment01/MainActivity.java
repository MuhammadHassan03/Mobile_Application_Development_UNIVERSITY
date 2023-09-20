package com.example.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button signupButton;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signupButton =findViewById(R.id.loginBtn);
        signUp = findViewById(R.id.signupButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenHomePageActivity();
            }
        });
    }
    public void openLoginActivity(){
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
    }
    public void OpenHomePageActivity(){
        Intent intent = new Intent(MainActivity.this, HomePage.class);
        startActivity(intent);
    }

}