package com.example.assignment04;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;


public class UserActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private ArrayList<LocationNote> notesList;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText editTextLocationName, editTextLocationDescription;
    private ImageView imageViewLocationPicture;
    private Uri imageUri;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("LocationNotes");

        editTextLocationName = findViewById(R.id.editTextLocationName);
        editTextLocationDescription = findViewById(R.id.editTextLocationDescription);
        imageViewLocationPicture = findViewById(R.id.imageViewLocationPicture);
        Button buttonAddPicture = findViewById(R.id.buttonAddPicture);
        Button buttonSaveLocationNote = findViewById(R.id.buttonSaveLocationNote);

        buttonAddPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }

            }
        });

        buttonSaveLocationNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLocationNote();
            }
        });

        recyclerView = findViewById(R.id.recyclerViewNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesList = new ArrayList<>();
        noteAdapter = new NoteAdapter(this, notesList);
        recyclerView.setAdapter(noteAdapter);
    }

    private void saveLocationNote() {
        String locationName = editTextLocationName.getText().toString();
        String locationDescription = editTextLocationDescription.getText().toString();

        if (TextUtils.isEmpty(locationName) || TextUtils.isEmpty(locationDescription)) {
            Toast.makeText(UserActivity.this, "Please fill in all details", Toast.LENGTH_SHORT).show();
            return;
        }

        // Generate a unique key for the location note
        String noteId = databaseReference.push().getKey();

        // Upload the image to Firebase Storage if an image is selected
        if (imageUri != null) {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("location_images/" + noteId);
            storageReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get the image URL from the taskSnapshot and save it in the database
                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageUrl = uri.toString();
                                    LocationNote locationNote = new LocationNote(noteId, locationName, locationDescription, imageUrl);
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    databaseReference.child(user.getUid()).child(noteId).setValue(locationNote);
                                    Toast.makeText(UserActivity.this, "Saved Success", Toast.LENGTH_SHORT).show();
                                    clearFields();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UserActivity.this, "Failed to upload image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            // If no image is selected, save the location note without an image URL
            LocationNote locationNote = new LocationNote(noteId, locationName, locationDescription, null);
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            databaseReference.child(user.getUid()).child(noteId).setValue(locationNote);
            clearFields();
        }
    }

    private void clearFields() {
        editTextLocationName.setText("");
        editTextLocationDescription.setText("");
        imageViewLocationPicture.setImageURI(null);
        imageUri = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Set the image URI and display the selected picture
            imageUri = data.getData();
            imageViewLocationPicture.setImageURI(imageUri);
        }
    }


}
