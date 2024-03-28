package com.nolamarel.mindmingle.bottomnav.chats;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nolamarel.mindmingle.chats.Chat;

import com.nolamarel.mindmingle.chats.ChatsAdapter;
import com.nolamarel.mindmingle.databinding.FragmentChatsBinding;
import com.nolamarel.mindmingle.users.User;
import com.nolamarel.mindmingle.users.UsersAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class ChatsFragment extends Fragment {
    private ArrayList<Chat> chats = new ArrayList<>();
    private FragmentChatsBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChatsBinding.inflate(inflater, container, false);

        loadChats();

        binding.searchView.clearFocus();
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchUsers(newText);
                if (newText.isEmpty()) {
                    binding.searchView.clearFocus(); // Уводим фокус с SearchView
                }
                return true;
            }
        });

        //  TODO: MAKE THE SEARCH TAB CLOSE WHEN YOU CLICK ON THE USER

        return binding.getRoot();
    }

    /**
     * A method for searching for users
     * @param query
     */
    private void searchUsers(String query) {
        ArrayList<User> filteredUsers = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()){
                    Log.d("error", userSnapshot.child("username").toString().toLowerCase());
                    if (userSnapshot.child("username").toString().toLowerCase().contains(query.toLowerCase())){

                    String uid = userSnapshot.getKey();
                    String username = userSnapshot.child("username").getValue().toString();
                    String profileImage = userSnapshot.child("profileImage").getValue().toString();

                    filteredUsers.add(new User(uid, username, profileImage));}
            }
                binding.chatsRv.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.chatsRv.setAdapter(new UsersAdapter(filteredUsers));
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /**
     * Method for uploading chats
     */
    private void loadChats(){


        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            String uid = currentUser.getUid();

            FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.child("Users").child(uid).child("chats").getValue() != null) {
                    String chatsStr = Objects.requireNonNull(snapshot.child("Users").child(uid).child("chats").getValue()).toString();
                    String[] chatsIds = chatsStr.split(",");

                    for (String chatId : chatsIds) {
                        DataSnapshot chatSnapshot = snapshot.child("Chats").child(chatId);

                        if (chatSnapshot.child("user1").getValue() != null && chatSnapshot.child("user2").getValue() != null) {
                            String userId1 = Objects.requireNonNull(chatSnapshot.child("user1").getValue()).toString();
                            String userId2 = Objects.requireNonNull(chatSnapshot.child("user2").getValue()).toString();

                            if (!isCommonChat(userId1, userId2)) {
                                String chatUserId = (uid.equals(userId1)) ? userId2 : userId1;

                                if (snapshot.child("Users").child(chatUserId).child("username").getValue() != null) {
                                    String chatName = Objects.requireNonNull(snapshot.child("Users").child(chatUserId).child("username").getValue()).toString();

                                    Chat chat = new Chat(chatId, chatName, userId1, userId2);
                                    chats.add(chat);
                                }
                            }
                        }
                    }

                    binding.chatsRv.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.chatsRv.setAdapter(new ChatsAdapter(chats));
            }}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Failed to get user chats", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

    /**
     * Checking for a shared chat
     * @param userId1 first user
     * @param userId2 second user
     * @return
     */
    private boolean isCommonChat(String userId1, String userId2) {
        // Проверяем наличие общего чата между userId1 и userId2
        for (Chat chat : chats) {
            if ((chat.getUserId1().equals(userId1) && chat.getUserId2().equals(userId2)) ||
                    (chat.getUserId1().equals(userId2) && chat.getUserId2().equals(userId1))) {
                return true; // Общий чат уже существует
            }
        }
        return false; // Общего чата нет
    }

}
