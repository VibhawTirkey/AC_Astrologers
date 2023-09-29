package com.astrocure.astrologer.ui;

import static com.astrocure.astrologer.utils.AppConstants.PROFILE_IMG;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.astrologer.databinding.ActivityProfileBinding;
import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.close.setOnClickListener(v -> onBackPressed());
        Glide.with(getApplicationContext()).load(PROFILE_IMG).into(binding.profileImage);
    }
}