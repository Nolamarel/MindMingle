package com.nolamarel.mindmingle.bottomnav.new_chat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nolamarel.mindmingle.databinding.FragmentNewChatBinding;
import com.nolamarel.mindmingle.users.User;
import com.nolamarel.mindmingle.users.UsersAdapter;

import java.util.ArrayList;

public class NewChatFragment extends Fragment {
    private FragmentNewChatBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewChatBinding.inflate(inflater, container, false);

        loadUsers();

        return binding.getRoot();
    }

    private void loadUsers(){
        ArrayList<User> users = new ArrayList<User>();
        FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()){
                    if (userSnapshot.getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        continue;
                    }

//                    if (userSnapshot.child("username").exists() && userSnapshot.child("profileImage").exists()) {
//                        String usern = userSnapshot.child("username").getValue(String.class);
//                        String profileIm = userSnapshot.child("profileImage").getValue(String.class);
//
//                        // Проверка значений
//                        Log.d("UserData", "Username: " + usern);
//                        Log.d("UserData", "Profile Image: " + profileIm);
//                    } else {
//                        Log.e("UserData", "Не удалось прочитать username или profileImage из DataSnapshot");
//                    }



                    String username = userSnapshot.child("username").getValue().toString();
                    String profileImage = userSnapshot.child("profileImage").getValue().toString();

                    users.add(new User(username, profileImage));
                }
                Log.d("UserCount", "Количество пользователей: " + users.size());
                binding.usersRv.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.usersRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                binding.usersRv.setAdapter(new UsersAdapter(users));
                Log.d("RecyclerViewVisibility", "Количество видимых элементов: " + binding.usersRv.getLayoutManager().getChildCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
