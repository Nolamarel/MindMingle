package com.nolamarel.mindmingle.users;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nolamarel.mindmingle.R;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersViewHolder> {
    private ArrayList<User> users = new ArrayList<>();

    public UsersAdapter(ArrayList<User> users){
        this.users = users;
    }
    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item_rv, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        User user = users.get(position);

        holder.username_tv.setText(user.username);

        if(!user.profileImage.isEmpty()){
            Glide.with(holder.itemView.getContext()).load(user.profileImage).into(holder.profileImage_iv);
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
