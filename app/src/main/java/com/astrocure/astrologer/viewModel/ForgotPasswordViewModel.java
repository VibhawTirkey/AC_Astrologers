package com.astrocure.astrologer.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.astrocure.astrologer.models.requestModels.ForgotPassRequestModel;
import com.astrocure.astrologer.models.responseModels.ForgotPassResponseModel;
import com.astrocure.astrologer.repository.AuthRepository;

public class ForgotPasswordViewModel extends ViewModel {
    private final MutableLiveData<String> fPasswordResultLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> numFormatResultLiveData = new MutableLiveData<>();
    private AuthRepository authRepository;

    public ForgotPasswordViewModel() {
        authRepository = new AuthRepository();
        fPasswordResultLiveData.postValue("");
    }

    public MutableLiveData<ForgotPassResponseModel.Data> forgotPassword(String phoneNum) {
        MutableLiveData<ForgotPassResponseModel.Data> successLiveData = new MutableLiveData<>();
        fPasswordResultLiveData.postValue("");
        if (phoneNum.length() != 10) {
            numFormatResultLiveData.postValue("Invalid Phone Number");
        } else {
            authRepository.forgotPasswordApiCall(new ForgotPassRequestModel(Long.parseLong(phoneNum)), new AuthRepository.IForgotPasswordResponse() {
                @Override
                public void onResponse(ForgotPassResponseModel forgotPassResponseModel) {
                    fPasswordResultLiveData.postValue(forgotPassResponseModel.getAlert());
                    successLiveData.postValue(forgotPassResponseModel.getData());
                }

                @Override
                public void onFailure(String t) {
                    fPasswordResultLiveData.postValue(t);
                    successLiveData.postValue(null);
                }
            });
        }

        return successLiveData;
    }

    public LiveData<String> getForgotPasswordResult() {
        return fPasswordResultLiveData;
    }

    public LiveData<String> getPhoneNumValidation() {
        return numFormatResultLiveData;
    }
}