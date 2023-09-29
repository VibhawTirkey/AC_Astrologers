package com.astrocure.astrologer.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.databinding.ActivityResetPasswordBinding;
import com.bumptech.glide.Glide;

public class ResetPasswordActivity extends AppCompatActivity {
    ActivityResetPasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.passwordToggle.setOnClickListener(v -> {
            if (binding.password.getTransformationMethod().getClass().getSimpleName().equals("PasswordTransformationMethod")) {
                binding.password.setTransformationMethod(new SingleLineTransformationMethod());
                Glide.with(getApplicationContext()).load(R.drawable.ic_eye_active_login).into(binding.passwordToggle);
            } else {
                binding.password.setTransformationMethod(new PasswordTransformationMethod());
                Glide.with(getApplicationContext()).load(R.drawable.ic_eye_inactive_login).into(binding.passwordToggle);
            }
            binding.password.setSelection(binding.password.getText().length());
        });

        binding.newPasswordToggle.setOnClickListener(v -> {
            if (binding.newPassword.getTransformationMethod().getClass().getSimpleName().equals("PasswordTransformationMethod")) {
                binding.newPassword.setTransformationMethod(new SingleLineTransformationMethod());
                Glide.with(getApplicationContext()).load(R.drawable.ic_eye_active_login).into(binding.newPasswordToggle);
            } else {
                binding.newPassword.setTransformationMethod(new PasswordTransformationMethod());
                Glide.with(getApplicationContext()).load(R.drawable.ic_eye_inactive_login).into(binding.newPasswordToggle);
            }
            binding.newPassword.setSelection(binding.newPassword.getText().length());
        });

        binding.nextDashboard.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), DashboardActivity.class)));
    }
}