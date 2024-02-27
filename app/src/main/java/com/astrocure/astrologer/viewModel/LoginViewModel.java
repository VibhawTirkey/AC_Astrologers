package com.astrocure.astrologer.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.astrocure.astrologer.models.requestModels.LoginRequestModel;
import com.astrocure.astrologer.models.responseModels.AddDeviceIdResponseModel;
import com.astrocure.astrologer.models.responseModels.AstrologerResponseModel;
import com.astrocure.astrologer.models.responseModels.LoginResponseModel;
import com.astrocure.astrologer.repository.AuthRepository;

public class LoginViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final MutableLiveData<String> loginStatusLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> successLiveData = new MutableLiveData<>();
    private final MutableLiveData<AstrologerResponseModel> astrologerLiveData = new MutableLiveData<>();
    private final MutableLiveData<LoginResponseModel.Data> loginLiveData = new MutableLiveData<>();
    private final MutableLiveData<AddDeviceIdResponseModel.Data> addDeviceIdLiveData = new MutableLiveData<>();

    public LoginViewModel() {
        authRepository = new AuthRepository();
        loginStatusLiveData.postValue("");
    }

    public void login(String username, String password) {

        loginStatusLiveData.postValue("");
        authRepository.callLoginApi(new LoginRequestModel(username, password), new AuthRepository.ILoginResponse() {
            @Override
            public void onResponse(LoginResponseModel loginResponseModel) {
                loginStatusLiveData.postValue(loginResponseModel.getAlert());
                loginLiveData.postValue(loginResponseModel.getData());
                successLiveData.postValue(true);
            }

            @Override
            public void onFailure(String t) {
                loginStatusLiveData.postValue(t);
                successLiveData.postValue(false);
            }
        });

    }

    public void getAstrologerDetail(String astrologerId) {
        authRepository.astrologerDetailApi(astrologerId, new AuthRepository.IAstrologerDetailResponse() {
            @Override
            public void onResponse(AstrologerResponseModel astrologerResponseModel) {
                astrologerLiveData.postValue(astrologerResponseModel);
            }

            @Override
            public void onFailure(String t) {

            }
        });
    }

    public void saveDeviceId(String astrologerId, String deviceId) {
        authRepository.saveDeviceId(astrologerId, deviceId, new AuthRepository.ISaveDeviceId() {
            @Override
            public void onSuccess(AddDeviceIdResponseModel responseModel) {
                addDeviceIdLiveData.postValue(responseModel.getData());
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
    }

    public LiveData<Boolean> getSuccessLiveData() {
        return successLiveData;
    }

    public LiveData<String> getLoginStatus() {
        return loginStatusLiveData;
    }

    public LiveData<LoginResponseModel.Data> getLoginLiveData() {
        return loginLiveData;
    }

    public LiveData<AstrologerResponseModel> getAstrologerLiveData() {
        return astrologerLiveData;
    }

    public LiveData<AddDeviceIdResponseModel.Data> getAddDeviceIdLiveData() {
        return addDeviceIdLiveData;
    }
}
