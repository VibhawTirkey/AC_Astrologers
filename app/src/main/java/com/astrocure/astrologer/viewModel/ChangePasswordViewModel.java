package com.astrocure.astrologer.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.astrocure.astrologer.models.requestModels.ResetPasswordRequestModel;
import com.astrocure.astrologer.models.responseModels.ResetPasswordResponseModel;
import com.astrocure.astrologer.repository.AuthRepository;

public class ChangePasswordViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final MutableLiveData<ResetPasswordResponseModel> resetPasswordLiveData = new MutableLiveData<>();

    public ChangePasswordViewModel() {
        authRepository = new AuthRepository();
    }

    public void resetPassword(String astrologerId, String password) {
        authRepository.resetPasswordApi(new ResetPasswordRequestModel(astrologerId, password), new AuthRepository.IResetPasswordResponse() {

            @Override
            public void onResponse(ResetPasswordResponseModel responseModel) {
                resetPasswordLiveData.postValue(responseModel);
            }

            @Override
            public void onFailure(String t) {

            }
        });
    }

    public LiveData<ResetPasswordResponseModel> getResetPasswordLiveData() {
        return resetPasswordLiveData;
    }
}
