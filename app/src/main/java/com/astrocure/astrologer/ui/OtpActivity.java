package com.astrocure.astrologer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.astrocure.astrologer.databinding.ActivityOtpBinding;
import com.astrocure.astrologer.viewModel.OtpViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class OtpActivity extends AppCompatActivity {
    ActivityOtpBinding binding;
    OtpViewModel viewModel;
    private int currentEditIndex;

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

        viewModel.getOtpValidResult().observe(this, s -> {

        });

        binding.nextReset.setOnClickListener(v -> {
            String otpText = binding.inputOne.getText().toString()+
                    binding.inputTwo.getText().toString()+
                    binding.inputThree.getText().toString()+
                    binding.inputFour.getText().toString()+
                    binding.inputFive.getText().toString()+
                    binding.inputSix.getText().toString();
            if (otpText.length()==6){
                viewModel.verifyOtp(otpText, otpId).observe(this, data -> {
                    if (data != null) {
                        Intent intent = new Intent(getApplicationContext(), ResetPasswordActivity.class);
                        intent.putExtra("user_id", data.getUserId());
                        startActivity(intent);
                    }
                });
            }else {
                Snackbar.make(binding.getRoot(),"Enter Valid OTP", BaseTransientBottomBar.LENGTH_SHORT).show();
            }

        });

        binding.inputOne.addTextChangedListener(new TextChangeWatcher(1));
        binding.inputTwo.addTextChangedListener(new TextChangeWatcher(2));
        binding.inputThree.addTextChangedListener(new TextChangeWatcher(3));
        binding.inputFour.addTextChangedListener(new TextChangeWatcher(4));
        binding.inputFive.addTextChangedListener(new TextChangeWatcher(5));
        binding.inputSix.addTextChangedListener(new TextChangeWatcher(6));

        binding.inputOne.setOnKeyListener(keyListener);
        binding.inputTwo.setOnKeyListener(keyListener);
        binding.inputThree.setOnKeyListener(keyListener);
        binding.inputFour.setOnKeyListener(keyListener);
        binding.inputFive.setOnKeyListener(keyListener);
        binding.inputSix.setOnKeyListener(keyListener);

    }

    private View.OnKeyListener keyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if ((((EditText) v).getText().toString() == null || ((EditText) v).getText().toString().isEmpty()) && keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {

                if (currentEditIndex == 6) currentEditIndex = 5;
                if (currentEditIndex > 0) {
                    EditText editText = getEditTextFromIndex(currentEditIndex);
                    editText.setText("");
                    editText.requestFocusFromTouch();
                    currentEditIndex--;
                }
            }
            return false;
        }
    };

    class TextChangeWatcher implements TextWatcher {
        private int index;

        public TextChangeWatcher(int index) {
            super();
            this.index = index;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s != null && s.length() == 1) {
                if (index < 7) {
                    if (index < 6) {
                        EditText editText = getEditTextFromIndex(index);
                        editText.clearFocus();
                        getEditTextFromIndex(index + 1).requestFocusFromTouch();
                    }
                    currentEditIndex = index;
                }
            }
        }
    }

    private EditText getEditTextFromIndex(int index) {
        switch (index) {
            case 1:
                return binding.inputOne;
            case 2:
                return binding.inputTwo;
            case 3:
                return binding.inputThree;
            case 4:
                return binding.inputFour;
            case 5:
                return binding.inputFive;
            case 6:
                return binding.inputSix;

            default:
                break;
        }
        return null;
    }
}
