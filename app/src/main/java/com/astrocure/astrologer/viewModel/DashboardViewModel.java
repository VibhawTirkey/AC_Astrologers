package com.astrocure.astrologer.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.astrocure.astrologer.models.requestModels.UpdateTokenRequestModel;
import com.astrocure.astrologer.models.responseModels.UpdateTokenResponseModel;
import com.astrocure.astrologer.repository.AuthRepository;

public class DashboardViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final MutableLiveData<UpdateTokenResponseModel.Data> tokenLiveData = new MutableLiveData<>();

    public DashboardViewModel() {
        authRepository = new AuthRepository();
    }

    public void updateFirebaseToken(String userId, String firebaseToken) {
        authRepository.updateFirebaseToken(new UpdateTokenRequestModel(userId,firebaseToken), new AuthRepository.IUpdateFirebaseToken() {
            @Override
            public void onSuccess(UpdateTokenResponseModel updateTokenResponseModel) {
                tokenLiveData.postValue(updateTokenResponseModel.getData());
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
    }

    public MutableLiveData<UpdateTokenResponseModel.Data> getTokenLiveData() {
        return tokenLiveData;
    }
}
