package com.nolamarel.mindmingle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nolamarel.mindmingle.bottomnav.chats.ChatsFragment;
import com.nolamarel.mindmingle.bottomnav.new_chat.NewChatFragment;
import com.nolamarel.mindmingle.bottomnav.profile.ProfileFragment;
import com.nolamarel.mindmingle.databinding.ActivityMainBinding;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        //ДЛЯ УДАЛЕНИЯ ПОЛЬЗОВАТЕЛЯ

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//        if (user != null) {
//            user.delete()
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//                                Log.d("User", "УДАЛИЛИ");
//                            } else {
//                                Log.d("User", "НЕ УДАЛИЛИ");
//
//                            }
//                        }
//                    });
//        }

        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                Log.d("User", String.valueOf(user));
                if (user == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    //finish(); // Опционально, для предотвращения возврата на MainActivity
                }
            }
        });
        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContsiner.getId(), new ChatsFragment()).commit();
        binding.bottomNav.setSelectedItemId(R.id.chats);
        Map<Integer, Fragment> fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.chats, new ChatsFragment());
        fragmentMap.put(R.id.new_chat, new NewChatFragment());
        fragmentMap.put(R.id.profile, new ProfileFragment());

        binding.bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment = fragmentMap.get(item.getItemId());

            getSupportFragmentManager().beginTransaction().replace(binding.fragmentContsiner.getId(), fragment).commit();

            return true;

        });

    }
}