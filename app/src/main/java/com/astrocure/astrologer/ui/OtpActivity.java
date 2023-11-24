package com.astrocure.astrologer.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.astrocure.astrologer.databinding.ActivityOtpBinding;
import com.astrocure.astrologer.models.responseModels.VerifyOtpResponseModel;
import com.astrocure.astrologer.viewModel.OtpViewModel;

public class OtpActivity extends AppCompatActivity {
    ActivityOtpBinding binding;
    OtpViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(OtpViewModel.class);
        String otpId = getIntent().getStringExtra("otp_id");
        String otp = getIntent().getStringExtra("otp");
        String phone_num = getIntent().getStringExtra("phone_num");

        binding.textView10.setText("+91 " + phone_num);

//        binding.inputOne.setText(otp.charAt(0));
//        binding.inputTwo.setText(otp.charAt(1));
//        binding.inputThree.setText(otp.charAt(2));
//        binding.inputFour.setText(otp.charAt(3));
//        binding.inputFive.setText(otp.charAt(4));
//        binding.inputSix.setText(otp.charAt(5));
        viewModel.getOtpValidResult().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });

        binding.nextReset.setOnClickListener(v -> {
            viewModel.verifyOtp(otp, otpId).observe(this, new Observer<VerifyOtpResponseModel.Data>() {
                @Override
                public void onChanged(VerifyOtpResponseModel.Data data) {
                    if (data != null) {
                        Intent intent = new Intent(getApplicationContext(), ResetPasswordActivity.class);
                        intent.putExtra("user_id", data.getUserId());
                        startActivity(intent);
                    }
                }
            });
        });
    }
}