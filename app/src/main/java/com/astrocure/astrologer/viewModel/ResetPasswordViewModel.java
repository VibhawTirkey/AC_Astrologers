package com.astrocure.astrologer.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.astrocure.astrologer.models.requestModels.ResetPasswordRequestModel;
import com.astrocure.astrologer.models.responseModels.ResetPasswordResponseModel;
import com.astrocure.astrologer.repository.AuthRepository;

public class ResetPasswordViewModel extends ViewModel {
    private final MutableLiveData<String> passwordValidationLiveData = new MutableLiveData<>();
    private final AuthRepository authRepository;

    public ResetPasswordViewModel() {
        authRepository = new AuthRepository();
        passwordValidationLiveData.postValue("");
    }

    public MutableLiveData<ResetPasswordResponseModel> verifyOtp(String userId, String newPassword) {
        MutableLiveData<ResetPasswordResponseModel> successLiveData = new MutableLiveData<>();
        passwordValidationLiveData.postValue("");
        authRepository.resetPasswordApi(new ResetPasswordRequestModel(userId, newPassword), new AuthRepository.IResetPasswordResponse() {
            @Override
            public void onResponse(ResetPasswordResponseModel responseModel) {
                passwordValidationLiveData.postValue(responseModel.getAlert());
                successLiveData.postValue(responseModel);
            }

            @Override
            public void onFailure(String t) {
                passwordValidationLiveData.postValue(t);
                successLiveData.postValue(null);
            }
        });
        return successLiveData;
    }

    public LiveData<String> getPassValidResult() {
        return passwordValidationLiveData;
    }
}
