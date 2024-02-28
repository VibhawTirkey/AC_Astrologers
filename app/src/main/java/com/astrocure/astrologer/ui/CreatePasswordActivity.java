package com.astrocure.astrologer.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.databinding.ActivityCreatePasswordBinding;
import com.astrocure.astrologer.databinding.DialogAutoLogoutBinding;
import com.astrocure.astrologer.databinding.DialogPasswordCreatedBinding;
import com.astrocure.astrologer.viewModel.CreatePasswordViewModel;
import com.bumptech.glide.Glide;

import java.util.Objects;

public class CreatePasswordActivity extends AppCompatActivity {
    ActivityCreatePasswordBinding binding;
    private CreatePasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreatePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(CreatePasswordViewModel.class);
        String astrologerId = getIntent().getStringExtra("astroId");

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

        binding.passwordConToggle.setOnClickListener(v -> {
            if (binding.conPassword.getTransformationMethod().getClass().getSimpleName().equals("PasswordTransformationMethod")) {
                binding.conPassword.setTransformationMethod(new SingleLineTransformationMethod());
                Glide.with(getApplicationContext()).load(R.drawable.ic_eye_active_login).into(binding.passwordConToggle);
            } else {
                binding.conPassword.setTransformationMethod(new PasswordTransformationMethod());
                Glide.with(getApplicationContext()).load(R.drawable.ic_eye_inactive_login).into(binding.passwordConToggle);
            }
            binding.conPassword.setSelection(binding.conPassword.getText().length());
        });

        binding.confirm.setOnClickListener(v -> {
            String password = binding.password.getText().toString();
            String conPassword = binding.conPassword.getText().toString();
            if (password.length() < 8) {
                binding.password.setError("min 8 chars");
                return;
            }
            if (conPassword.length() < 8) {
                binding.conPassword.setError("min 8 chars");
                return;
            }
            if (!password.matches(conPassword)) {
                binding.conPassword.setError("Check the Password");
                return;
            }
            viewModel.resetPassword(astrologerId, password);
        });

        viewModel.getResetPasswordLiveData().observe(this, resetPasswordResponseModel -> {
            DialogPasswordCreatedBinding createdBinding = DialogPasswordCreatedBinding.inflate(getLayoutInflater());
            Dialog dialog = new Dialog(getApplicationContext());
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.setCancelable(false);
            dialog.setContentView(createdBinding.getRoot());
            createdBinding.gotoLogin.setOnClickListener(v -> {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                dialog.dismiss();
                finish();
            });
            dialog.show();
        });
    }
}