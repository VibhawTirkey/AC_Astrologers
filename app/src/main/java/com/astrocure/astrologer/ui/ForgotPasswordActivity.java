package com.astrocure.astrologer.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.databinding.ActivityForgotPasswordBinding;
import com.astrocure.astrologer.viewModel.ForgotPasswordViewModel;

import java.util.Objects;

public class ForgotPasswordActivity extends AppCompatActivity {
    private ActivityForgotPasswordBinding binding;
    private ForgotPasswordViewModel viewModel;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);

        dialog = new Dialog(ForgotPasswordActivity.this);
        dialog.setContentView(R.layout.dialog_loader_layout);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        viewModel.getForgotPasswordResult().observe(this, s -> {
            binding.message.setText(s);
            dialog.dismiss();
        });
        viewModel.getErrorLiveData().observe(this, s -> {
            binding.phoneNum.setError(s);
            dialog.dismiss();
        });
        viewModel.getSuccessLiveData().observe(this, data -> {
            Intent intent = new Intent(getApplicationContext(), OtpActivity.class);
            intent.putExtra("otp_id", data.getOtpId());
            intent.putExtra("otp", data.getOtp());
            intent.putExtra("phone_num", binding.phoneNum.getText().toString());
            startActivity(intent);
            binding.nextOtp.setEnabled(true);
        });

        binding.nextOtp.setOnClickListener(v -> {
            if (binding.phoneNum.getText().length() == 10) {
                dialog.show();
            }
            viewModel.forgotPassword(binding.phoneNum.getText().toString());
        });
    }
}