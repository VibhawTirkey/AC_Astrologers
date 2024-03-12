package com.astrocure.astrologer.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.astrocure.astrologer.models.responseModels.SessionLogResponseModel;
import com.astrocure.astrologer.repository.AuthRepository;

import java.util.ArrayList;
import java.util.List;

public class SessionLogViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final List<SessionLogResponseModel.Datum> logList = new ArrayList<>();
    private MutableLiveData<List<SessionLogResponseModel.Datum>> sessionLogLiveData = new MutableLiveData<>();

    public SessionLogViewModel() {
        this.authRepository = new AuthRepository();
    }

    public void getSessionLogs(String astrologerId, String type) {
        authRepository.getSessionLog(astrologerId, new AuthRepository.ISessionLog() {
            @Override
            public void onSuccess(SessionLogResponseModel logResponseModel) {
                logList.clear();
                logResponseModel.getData().forEach(datum -> {
                    if (datum.getCounsellingType().matches(type)) {
                        logList.add(datum);
                    }
                });
                sessionLogLiveData.postValue(logList);
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
    }

    public LiveData<List<SessionLogResponseModel.Datum>> getSessionLogLiveData() {
        return sessionLogLiveData;
    }
}
