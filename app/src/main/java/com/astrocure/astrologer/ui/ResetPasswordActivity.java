package com.astrocure.astrologer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.databinding.ActivityResetPasswordBinding;
import com.astrocure.astrologer.viewModel.ResetPasswordViewModel;
import com.bumptech.glide.Glide;

public class ResetPasswordActivity extends AppCompatActivity {
    ActivityResetPasswordBinding binding;
    ResetPasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String userId = getIntent().getStringExtra("user_id");
        viewModel = new ViewModelProvider(this).get(ResetPasswordViewModel.class);

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

        viewModel.getPassValidResult().observe(this, s -> binding.message.setText(s));

        binding.nextDashboard.setOnClickListener(v -> {
            if (!binding.password.getText().toString().matches(binding.newPassword.getText().toString())) {
                binding.message.setText("Password does not match.");
                return;
            } else if (binding.password.getText().toString().isEmpty() || binding.newPassword.getText().toString().isEmpty()) {
                binding.message.setText("Enter your Password");
                return;
            }
            viewModel.verifyOtp(userId, binding.password.getText().toString()).observe(this, responseModel -> {
                if (responseModel.getStatusCode() == 200) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            });
        });
    }
}