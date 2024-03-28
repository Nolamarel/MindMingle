package com.nolamarel.mindmingle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.nolamarel.mindmingle.databinding.ActivityNoteBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class NoteActivity extends AppCompatActivity {
    private ActivityNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String noteId = getIntent().getStringExtra("noteId");
        //Log.d("noteId", noteId);
        String noteText = getIntent().getStringExtra("noteText");
        binding.noteEt.setText(noteText);
        binding.addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

                String note = binding.noteEt.getText().toString();
                if(note.isEmpty()){
                    Toast.makeText(NoteActivity.this, "Notes field cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                String date = simpleDateFormat.format(new Date());


                //sendNote( userId, note, date);



                if (!TextUtils.isEmpty(noteId)) {
                    //String updatedNote = binding.noteEt.getText().toString();
                    updateNote(userId, noteId, note, date);
                } else {
                    sendNote(userId, note, date);
                }
//                Intent resultIntent = new Intent();
//                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    /**
     * Method for adding a new note
     * @param userId user identity
     * @param note The text of the note
     * @param date Date of dispatch
     */
    private void sendNote( String userId, String note, String date){
        if (userId == null) return;
        HashMap<String, String> notesInfo = new HashMap<>();
        notesInfo.put("text", note);
        notesInfo.put("date", date);
        FirebaseDatabase.getInstance().getReference().child("Users").child(userId)
                .child("notes").push().setValue(notesInfo);
    }

    /**
     * A method for editing a note
     * @param userId user identity
     * @param noteId Note ID
     * @param updatedNote Updated text of the note
     * @param date Date of editing
     */
    private void updateNote(String userId, String noteId, String updatedNote, String date) {
        // Обновление записи с указанным noteId в базе данных
        if (userId == null) return;
        FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("notes").child(noteId).child("text").setValue(updatedNote);
        FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("notes").child(noteId).child("date").setValue(date);

    }
}