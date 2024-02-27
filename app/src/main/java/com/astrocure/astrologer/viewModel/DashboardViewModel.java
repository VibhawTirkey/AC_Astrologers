package com.astrocure.astrologer.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.astrocure.astrologer.models.requestModels.UpdateTokenRequestModel;
import com.astrocure.astrologer.models.responseModels.DeviceIdResponseModel;
import com.astrocure.astrologer.models.responseModels.UpdateTokenResponseModel;
import com.astrocure.astrologer.repository.AuthRepository;

public class DashboardViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final MutableLiveData<UpdateTokenResponseModel.Data> tokenLiveData = new MutableLiveData<>();
    private final MutableLiveData<DeviceIdResponseModel.Data> deviceIdLiveData = new MutableLiveData<>();

    public DashboardViewModel() {
        authRepository = new AuthRepository();
    }

    public void updateFirebaseToken(String userId, String firebaseToken) {
        authRepository.updateFirebaseToken(new UpdateTokenRequestModel(userId, firebaseToken), new AuthRepository.IUpdateFirebaseToken() {
            @Override
            public void onSuccess(UpdateTokenResponseModel updateTokenResponseModel) {
                tokenLiveData.postValue(updateTokenResponseModel.getData());
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
    }

    public void matchDeviceId(String astrologerId, String deviceId) {
        authRepository.getDeviceId(astrologerId, deviceId, new AuthRepository.IGetDeviceId() {
            @Override
            public void onSuccess(DeviceIdResponseModel responseModel) {
                deviceIdLiveData.postValue(responseModel.getData());
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
    }

    public LiveData<DeviceIdResponseModel.Data> getDeviceIdLiveData() {
        return deviceIdLiveData;
    }

    public LiveData<UpdateTokenResponseModel.Data> getTokenLiveData() {
        return tokenLiveData;
    }
}
