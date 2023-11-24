package com.astrocure.astrologer.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.astrocure.astrologer.models.requestModels.AuthRequestModel;
import com.astrocure.astrologer.models.responseModels.VerifyOtpResponseModel;
import com.astrocure.astrologer.repository.AuthRepository;

public class OtpViewModel extends ViewModel {
    private final AuthRepository repository;
    private final MutableLiveData<String> otpValidationLiveData = new MutableLiveData<>();

    public OtpViewModel() {
        repository = new AuthRepository();
        otpValidationLiveData.postValue("");
    }

    public MutableLiveData<VerifyOtpResponseModel.Data> verifyOtp(String otp, String otpId) {
        MutableLiveData<VerifyOtpResponseModel.Data> successLiveData = new MutableLiveData<>();
        otpValidationLiveData.postValue("");
        repository.verifyOtpApi(new AuthRequestModel("forgot_password", otpId, otp), new AuthRepository.IVerifyOtpResponse() {
            @Override
            public void onResponse(VerifyOtpResponseModel verifyOtpResponseModel) {
                otpValidationLiveData.postValue(verifyOtpResponseModel.getAlert());
                successLiveData.postValue(verifyOtpResponseModel.getData());
            }

            @Override
            public void onFailure(String t) {
                otpValidationLiveData.postValue(t);
                successLiveData.postValue(null);
            }
        });
        return successLiveData;
    }

    public LiveData<String> getOtpValidResult() {
        return otpValidationLiveData;
    }
}
