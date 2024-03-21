package com.nolamarel.mindmingle.chats;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nolamarel.mindmingle.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatViewHolder extends RecyclerView.ViewHolder {

CircleImageView chat_iv;
TextView chat_name_tv;

    public ChatViewHolder(@NonNull View itemView) {
        super(itemView);
        chat_iv = itemView.findViewById(R.id.profile_rv);
        chat_name_tv = itemView.findViewById(R.id.username_tv);
    }
}
