package com.astrocure.astrologer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.databinding.ActivityLoginBinding;
import com.bumptech.glide.Glide;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rememberMe.setChecked(true);
        setRememberMe();
        binding.rememberMe.setOnClickListener(v -> {
            setRememberMe();
        });

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

        binding.forgetPassword.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),ForgotPasswordActivity.class)));
        binding.login.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), DashboardActivity.class)));
    }

    private void setRememberMe() {
        if (binding.rememberMe.isChecked()) {
            binding.rememberMe.setChecked(false);
            binding.rememberMe.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox_inactive_login, 0, 0, 0);
        } else {
            binding.rememberMe.setChecked(true);
            binding.rememberMe.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox_active_login, 0, 0, 0);
        }
    }

}