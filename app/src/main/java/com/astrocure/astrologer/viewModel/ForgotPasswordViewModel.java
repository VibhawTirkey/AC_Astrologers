package com.astrocure.astrologer.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.astrocure.astrologer.models.requestModels.ForgotPassRequestModel;
import com.astrocure.astrologer.models.responseModels.ForgotPassResponseModel;
import com.astrocure.astrologer.repository.AuthRepository;

public class ForgotPasswordViewModel extends ViewModel {
    private final MutableLiveData<String> fPasswordResultLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    private final MutableLiveData<ForgotPassResponseModel.Data> successLiveData = new MutableLiveData<>();
    private AuthRepository authRepository;

    public ForgotPasswordViewModel() {
        authRepository = new AuthRepository();
        fPasswordResultLiveData.postValue("");
    }

    public void forgotPassword(String phoneNum) {

        fPasswordResultLiveData.postValue("");
        if (phoneNum.length() != 10) {
            errorLiveData.postValue("Invalid Phone Number");
        } else {
            authRepository.forgotPasswordApiCall(new ForgotPassRequestModel(Long.parseLong(phoneNum)), new AuthRepository.IForgotPasswordResponse() {
                @Override
                public void onResponse(ForgotPassResponseModel forgotPassResponseModel) {
                    fPasswordResultLiveData.postValue(forgotPassResponseModel.getAlert());
                    successLiveData.postValue(forgotPassResponseModel.getData());
                }

                @Override
                public void onFailure(String t) {
                    errorLiveData.postValue(t);
                }
            });
        }
    }

    public LiveData<ForgotPassResponseModel.Data> getSuccessLiveData() {
        return successLiveData;
    }

    public LiveData<String> getForgotPasswordResult() {
        return fPasswordResultLiveData;
    }

    public MutableLiveData<String> getErrorLiveData() {
        return errorLiveData;
    }
}