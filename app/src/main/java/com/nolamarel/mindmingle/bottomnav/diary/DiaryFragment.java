package com.nolamarel.mindmingle.bottomnav.diary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nolamarel.mindmingle.NoteActivity;
import com.nolamarel.mindmingle.R;
import com.nolamarel.mindmingle.databinding.FragmentDiaryBinding;
import com.nolamarel.mindmingle.notes.Note;
import com.nolamarel.mindmingle.notes.NotesAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DiaryFragment extends Fragment {
    private String textNote;
    private ArrayList<Note> notes = new ArrayList<>();
    private FragmentDiaryBinding binding;
//    private ActivityResultLauncher<Intent> noteActivityResultLauncher;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // Регистрация ActivityResultLauncher
//        noteActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//                result -> {
//                    // Обработка результата от NoteActivity
//                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
//                        // Обновление данных, если необходимо
//                        loadNotes();
//                    }
//                });
//}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDiaryBinding.inflate(inflater, container, false);

        binding.addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), NoteActivity.class);
//                noteActivityResultLauncher.launch(intent);
                startActivity(intent);
            }
        });
        loadNotes();
        return binding.getRoot();
    }
    public void onResume() {

        super.onResume();
        loadNotes();
    }

    /**
     * A method for downloading all Notes from the Database
     */
    private void loadNotes(){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("notes").orderByChild("date").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) return;
                notes.clear();
                Log.d("data", String.valueOf(snapshot));
                for (DataSnapshot notesSnapshot: snapshot.getChildren()){
                    String notesId = notesSnapshot.getKey();
                    Log.d("data", notesSnapshot.getKey());
                    String text = notesSnapshot.child("text").getValue().toString();
                    Log.d("data", text);
                    String date = notesSnapshot.child("date").getValue().toString();

                    notes.add(0, new Note(notesId, userId, text, date));
                }
                binding.notesRv.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.notesRv.setAdapter(new NotesAdapter(notes));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Failed to get user notes", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
