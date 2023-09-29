package com.astrocure.astrologer.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.databinding.ActivityOtpBinding;

public class OtpActivity extends AppCompatActivity {
    ActivityOtpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.nextReset.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ResetPasswordActivity.class)));
    }
}