package com.nolamarel.mindmingle.bottomnav.profile;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.nolamarel.mindmingle.LoginActivity;
import com.nolamarel.mindmingle.R;
import com.nolamarel.mindmingle.databinding.FragmentProfileBinding;

import java.io.IOException;
import java.util.Locale;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private Uri filePath;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        loadUserInfo();

        binding.profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });

        Button changeLocaleButton = binding.changeLocaleButton;
        changeLocaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentLang = Locale.getDefault().getLanguage();
                switch (currentLang) {
                    case "en":
                        changeLocale("ru");
                        break;
                    case "ru":
                        changeLocale("en");
                        break;
                    default:
                        changeLocale("ru");
                        break;
                }
            }
        });

        return binding.getRoot();
    }

    public void changeLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        Activity activity = getActivity();
        if (activity != null) {
            activity.getResources().updateConfiguration(config, activity.getResources().getDisplayMetrics());
        }
    }

    ActivityResultLauncher<Intent> pickImageActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK && result.getData() != null && result.getData().getData()!=null){
                        filePath = result.getData().getData();

                        try {
                            Bitmap bitmap = MediaStore.Images.Media
                                    .getBitmap(
                                            requireContext().getContentResolver(),
                                            filePath
                                    );
                            binding.profileImageView.setImageBitmap(bitmap);
                        } catch (IOException e){
                            e.printStackTrace();
                        }

                        uploadImage();
                    }
                }
            });

    /**
     * The method for loading the profile page
     */
    private void loadUserInfo(){
        FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String userName = snapshot.child("username").getValue().toString();
                        String profileImage = snapshot.child("profileImage").getValue().toString();

                        binding.userNameTv.setText(userName);
                        if(!profileImage.isEmpty()){
                            Glide.with(getContext()).load(profileImage).into(binding.profileImageView);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }


    /**
     * The method for selecting an image from the gallery
     */
    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        pickImageActivityResultLauncher.launch(intent);
    }

    /**
     * Method for uploading an image to the Database
     */
    private void uploadImage(){
        if (filePath!=null){
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            FirebaseStorage.getInstance().getReference().child("images/" + uid)
                    .putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getContext(), "Photo upload complete", Toast.LENGTH_SHORT).show();

                            FirebaseStorage.getInstance().getReference().child("images/"+uid).getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .child("profileImage").setValue(uri.toString());
                                        }
                                    });
                        }
                    });
        }
    }

}
