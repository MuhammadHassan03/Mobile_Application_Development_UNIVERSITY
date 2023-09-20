package com.example.sms_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText phone_number = findViewById(R.id.phone_number);
                EditText message = findViewById(R.id.message);

                String ph = phone_number.getText().toString();
                String sms = message.getText().toString();

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(ph, null, sms, null, null);

                Toast.makeText(getApplicationContext(), "Message Send Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
}