package com.astrocure.astrologer.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class GenericTextWatcher implements TextWatcher {
    private EditText view;
    private List<EditText> otpDigitViews;
    private OTPCompleteListener otpListener;
    private static int lastOtpLength;

    public GenericTextWatcher(EditText otpView, List<EditText> otpDigitViews, OTPCompleteListener listener) {
        view = otpView;
        this.otpDigitViews = otpDigitViews;
        this.otpListener = listener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String digit1 = otpDigitViews.get(0).getText().toString();
        String digit2 = otpDigitViews.get(1).getText().toString();
        String digit3 = otpDigitViews.get(2).getText().toString();
        String digit4 = otpDigitViews.get(3).getText().toString();
        String digit5 = otpDigitViews.get(4).getText().toString();
        String digit6 = otpDigitViews.get(5).getText().toString();
        String currentDigit = s.toString();
        final String inputValue = digit1 + digit2 + digit3 + digit4 + digit5 + digit6;

        if (inputValue.length() == 6) {
            otpListener.onOTPFilled(inputValue);
        } else {
            if (currentDigit.length() >= 1 && view != otpDigitViews.get(3)) {
                if (view != null) {
                    view.focusSearch(View.FOCUS_RIGHT).requestFocus();
                }
            } else {
                if (currentDigit.length() == 0 && view.getSelectionStart() <= 0) {
                    try {
                        view.focusSearch(View.FOCUS_LEFT).requestFocus();
                    } catch (NullPointerException e) {
                        Log.e("TAG", "afterTextChanged: There is no view left to current edit text");
                    }
                }
            }

            if (GenericTextWatcher.lastOtpLength == 6) {
                otpListener.onOTPIncomplete();
            }
        }
    }

    public interface OTPCompleteListener {
        void onOTPFilled(String otp);

        void onOTPIncomplete();
    }
}
