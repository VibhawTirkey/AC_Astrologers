package com.astrocure.astrologer.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.databinding.ActivityLoginBinding;
import com.astrocure.astrologer.models.requestModels.LoginRequestModel;
import com.astrocure.astrologer.models.responseModels.AddDeviceIdResponseModel;
import com.astrocure.astrologer.models.responseModels.LoginResponseModel;
import com.astrocure.astrologer.utils.SPrefClient;
import com.astrocure.astrologer.viewModel.LoginViewModel;
import com.bumptech.glide.Glide;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        if (SPrefClient.getUserCredential(getApplicationContext()) != null) {
            binding.rememberMe.setChecked(true);
            binding.emailId.setText(SPrefClient.getUserCredential(getApplicationContext()).getEmail());
            binding.password.setText(SPrefClient.getUserCredential(getApplicationContext()).getPassword());
            binding.rememberMe.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox_active_login, 0, 0, 0);
        } else {
            binding.rememberMe.setChecked(false);
            binding.rememberMe.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox_inactive_login, 0, 0, 0);
        }

        viewModel.getSuccessLiveData().observe(this, aBoolean -> {
            if (aBoolean) {
                if (binding.rememberMe.isChecked()) {
                    SPrefClient.setUserCredential(getApplicationContext(), new LoginRequestModel(
                            binding.emailId.getText().toString(), binding.password.getText().toString()
                    ));
                } else {
                    SPrefClient.deleteUserCredential(getApplicationContext());
                }
            }
        });

        viewModel.getLoginLiveData().observe(this, data -> {
            SPrefClient.setAstrologerDetail(getApplicationContext(), data);
            viewModel.saveDeviceId(SPrefClient.getAstrologerDetail(getApplicationContext()).getId(), Settings.Secure.getString(getApplication().getContentResolver(), Settings.Secure.ANDROID_ID));
        });

        viewModel.getAddDeviceIdLiveData().observe(this, data -> {
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            finish();
        });

        viewModel.getAstrologerLiveData().observe(this, astrologerResponseModel -> {

        });

        viewModel.getLoginStatus().observe(this, s -> {
            binding.error.setText(s);
        });

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

        binding.forgetPassword.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ForgotPasswordActivity.class)));

        binding.login.setOnClickListener(v -> {
            if (binding.emailId.getText().toString().isEmpty()) {
                binding.emailId.setError("Empty");
                return;
            } else if (binding.password.getText().toString().isEmpty()) {
                binding.password.setError("Empty");
                return;
            }
            viewModel.login(binding.emailId.getText().toString(), binding.password.getText().toString());
        });

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