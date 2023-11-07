package com.example.assignment04;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private ArrayList<LocationNote> notesList;
    private Context context;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();


    public NoteAdapter(Context context, ArrayList<LocationNote> notesList) {
        this.notesList = notesList;
        this.context = context;
        fetchLocationNotes();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        LocationNote note = notesList.get(position);
        holder.textViewNoteName.setText("Location Name: " + note.getLocationName());
        holder.textViewNoteDescription.setText("Description: " + note.getLocationDescription());


        holder.buttonEditNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditDialog(note);
            }
        });

        holder.buttonDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog(note);
            }
        });
    }


    private void fetchLocationNotes() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            DatabaseReference userNotesReference = databaseReference.child("LocationNotes").child(user.getUid());

            userNotesReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    notesList.clear();
                    for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                        LocationNote locationNote = noteSnapshot.getValue(LocationNote.class);
                        if (locationNote != null) {
                            notesList.add(locationNote);
                        }
                    }
                    notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(context, "Failed to fetch location notes: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



    private void openEditDialog(LocationNote note) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View editView = LayoutInflater.from(context).inflate(R.layout.edit_note_dialog, null);
        builder.setView(editView);
        EditText editTextLocationName = editView.findViewById(R.id.editTextLocationName);
        EditText editTextLocationDescription = editView.findViewById(R.id.editTextLocationDescription);
        ImageView imageViewLocationPicture = editView.findViewById(R.id.imageViewLocationPicture);

        editTextLocationName.setText(note.getLocationName());
        editTextLocationDescription.setText(note.getLocationDescription());

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle the save action
                String updatedName = editTextLocationName.getText().toString();
                String updatedDescription = editTextLocationDescription.getText().toString();
                if (!updatedName.isEmpty() && !updatedDescription.isEmpty()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();;
                    note.setLocationName(updatedName);
                    note.setLocationDescription(updatedDescription);
                    databaseReference.child("LocationNotes").child(user.getUid()).child(note.getNoteId()).setValue(note);
                    notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Name and Description cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void showDeleteConfirmationDialog(LocationNote note) {
        // Create a confirmation dialog for deleting the note
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm Delete");
        builder.setMessage("Are you sure you want to delete this note?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();;
                databaseReference.child("LocationNotes").child(user.getUid()).child(note.getNoteId()).removeValue();
                notesList.remove(note);
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }


    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewNoteName;
        public TextView textViewNoteDescription;
        public ImageView imageViewNotePicture;
        public Button buttonEditNote;
        public Button buttonDeleteNote;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNoteName = itemView.findViewById(R.id.textViewNoteName);
            textViewNoteDescription = itemView.findViewById(R.id.textViewNoteDescription);
            imageViewNotePicture = itemView.findViewById(R.id.imageViewNotePicture);
            buttonEditNote = itemView.findViewById(R.id.buttonEditNote);
            buttonDeleteNote = itemView.findViewById(R.id.buttonDeleteNote);
        }
    }
}

