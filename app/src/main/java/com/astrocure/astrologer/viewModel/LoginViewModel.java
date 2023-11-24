package com.astrocure.astrologer.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.astrocure.astrologer.models.requestModels.LoginRequestModel;
import com.astrocure.astrologer.models.responseModels.LoginResponseModel;
import com.astrocure.astrologer.repository.AuthRepository;

public class LoginViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final MutableLiveData<String> loginResultLiveData = new MutableLiveData<>();

    public LoginViewModel() {
        authRepository = new AuthRepository();
        loginResultLiveData.postValue("");
    }

    public MutableLiveData<Boolean> login(String username, String password) {
        MutableLiveData<Boolean> successLiveData = new MutableLiveData<>();
        loginResultLiveData.postValue("");
        authRepository.callLoginApi(new LoginRequestModel(username, password), new AuthRepository.ILoginResponse() {
            @Override
            public void onResponse(LoginResponseModel loginResponseModel) {
                loginResultLiveData.postValue(loginResponseModel.getAlert());
                successLiveData.postValue(true);
            }

            @Override
            public void onFailure(String t) {
                loginResultLiveData.postValue(t);
                successLiveData.postValue(false);
            }
        });
        return successLiveData;
    }

    public LiveData<String> getLoginResult() {
        return loginResultLiveData;
    }
}
