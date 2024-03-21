package com.nolamarel.mindmingle.users;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nolamarel.mindmingle.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersViewHolder extends RecyclerView.ViewHolder {
    CircleImageView profileImage_iv;
    TextView username_tv;

    public UsersViewHolder(@NonNull View itemView) {
        super(itemView);
        profileImage_iv = itemView.findViewById(R.id.profile_rv);
        username_tv = itemView.findViewById(R.id.username_tv);

    }
}
