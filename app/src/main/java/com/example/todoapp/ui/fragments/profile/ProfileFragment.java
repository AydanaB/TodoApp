package com.example.todoapp.ui.fragments.profile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todoapp.App;
import com.example.todoapp.R;
import com.example.todoapp.databinding.FragmentProfileBinding;
import com.example.todoapp.utils.Prefs;

import java.io.IOException;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ActivityResultLauncher<Intent> resultLauncher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
    }

    private void initListener() {
        binding.imgView.setOnClickListener(v->{
            askForPermission();
        });

        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent intent = result.getData();
                    if (intent != null){
                        Uri returnUri = intent.getData();
                        binding.imgView.setImageURI(returnUri);
                    }
                });

        binding.btnProfile.setOnClickListener(v->{
            initProfile();
            binding.edName.setText("");
            binding.edSurname.setText("");
        });
    }

    private void initProfile() {
        saveInShare();
        binding.tvName.setText(App.prefs.getName());
        binding.tvSurname.setText(App.prefs.getSurname());
    }

    private void saveInShare() {
        String resultName = binding.edName.getText().toString().trim();
        String resultSurname = binding.edSurname.getText().toString().trim();
        App.prefs.saveName(resultName);
        App.prefs.saveSurName(resultSurname);
    }

    private void askForPermission() {
        if (ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE) !=
        PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2000);
        }else {
            startGallery();
        }
    }

    private void startGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        resultLauncher.launch(intent);
    }
}