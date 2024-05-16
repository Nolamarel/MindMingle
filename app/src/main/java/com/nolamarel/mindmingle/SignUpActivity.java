package com.nolamarel.mindmingle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.nolamarel.mindmingle.databinding.ActivitySignUpBinding;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
private ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.nameEmail.getText().toString().isEmpty() || binding.namePassword.
                        getText().toString().isEmpty() || binding.userName.getText().toString().isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Fields can't be empty",
                            Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.nameEmail.getText().toString(), binding.namePassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull
                                                       Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        HashMap<String, String> userInfo = new HashMap<>();
                                        userInfo.put("chats", "");
                                        userInfo.put("notes", "");
                                        userInfo.put("email", binding.nameEmail.getText().toString());
                                        userInfo.put("username", binding.userName.getText().toString());
                                        userInfo.put("profileImage", "");
                                        FirebaseDatabase.getInstance().getReference().child
                                                        ("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(userInfo)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> databaseTask) {
                                                        if (databaseTask.isSuccessful()) {
                                                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                                        } else {
                                                            // Обрабатываем сбой сохранения данных
                                                            Toast.makeText(SignUpActivity.this,
                                                                    "Ошибка при сохранении данных пользователя", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    } else {
                                        // Обрабатываем неудачное создание пользователя
                                        Toast.makeText(SignUpActivity.this, "Регистрация пользователя не удалась", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });


    }
}