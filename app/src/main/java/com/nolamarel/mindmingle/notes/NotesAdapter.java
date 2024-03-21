package com.nolamarel.mindmingle.notes;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nolamarel.mindmingle.ChatActivity;
import com.nolamarel.mindmingle.NoteActivity;
import com.nolamarel.mindmingle.R;
import com.nolamarel.mindmingle.message.Message;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{

    private List<Note> notes;

    public NotesAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item_rv, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = notes.get(position);

        holder.noteDate.setText(note.getDate());
        holder.noteText.setText(note.getText());
        holder.noteText.setEllipsize(TextUtils.TruncateAt.END);

        holder.itemView.setOnClickListener(v ->{
            Intent intent = new Intent(holder.itemView.getContext(), NoteActivity.class);
            intent.putExtra("noteId", note.getId()); // Передача идентификатора записи
            intent.putExtra("noteText", note.getText()); // Передача текста записи для отображения
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder{
        TextView noteDate, noteText;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            noteDate = itemView.findViewById(R.id.note_date);
            noteText = itemView.findViewById(R.id.note_text);

        }
    }
}
