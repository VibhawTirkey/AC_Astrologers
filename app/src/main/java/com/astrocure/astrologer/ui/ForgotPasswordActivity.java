package com.astrocure.astrologer.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.astrocure.astrologer.databinding.ActivityForgotPasswordBinding;
import com.astrocure.astrologer.viewModel.ForgotPasswordViewModel;

public class ForgotPasswordActivity extends AppCompatActivity {
    ActivityForgotPasswordBinding binding;
    ForgotPasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);

        viewModel.getForgotPasswordResult().observe(this, s -> binding.message.setText(s));
        viewModel.getPhoneNumValidation().observe(this, s -> binding.phoneNum.setError(s));

        binding.nextOtp.setOnClickListener(v -> {
            viewModel.forgotPassword(binding.phoneNum.getText().toString()).observe(this, data -> {
                if (data != null) {
                    Intent intent = new Intent(getApplicationContext(), OtpActivity.class);
                    intent.putExtra("otp_id", data.getOtpId());
                    intent.putExtra("otp", data.getOtp());
                    intent.putExtra("phone_num", binding.phoneNum.getText().toString());
                    startActivity(intent);
                }
            });
        });


    }
}