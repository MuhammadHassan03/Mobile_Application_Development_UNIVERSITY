package com.example.assignment04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        mAuth = FirebaseAuth.getInstance();

        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextAddress = findViewById(R.id.editTextAddress);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        Button signup_button = findViewById(R.id.buttonRegister);



        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = editTextName.getText().toString();
                String Email = editTextEmail.getText().toString();
                String Address = editTextAddress.getText().toString();
                String Password = editTextPassword.getText().toString();

                if(Name.isEmpty() || Address.isEmpty() || Password.isEmpty() || Email.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Fill All Details First", Toast.LENGTH_SHORT).show();
                }

                else{
                    mAuth.createUserWithEmailAndPassword(Email, Password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = mAuth.getCurrentUser(); // Get the current user
                            User newuser = new User(Name, Email, Password, Address);
                            databaseReference.child("Users").child(user.getUid()).setValue(newuser);
                            startActivity(new Intent(MainActivity.this, UserActivity.class));
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, "Reason" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

        Button switchToLoginButton = findViewById(R.id.buttonSwitchToLogin);

        switchToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Login activity
                startActivity(new Intent(MainActivity.this, ActivityLogin.class));
            }
        });
    }

}