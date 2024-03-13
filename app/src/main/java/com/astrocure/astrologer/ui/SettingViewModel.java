package com.astrocure.astrologer.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.astrocure.astrologer.models.requestModels.ChangeDataRequestModel;
import com.astrocure.astrologer.models.responseModels.ChangeDataResponseModel;
import com.astrocure.astrologer.models.responseModels.ChangeRequestTypeResponseModel;
import com.astrocure.astrologer.models.responseModels.RequestStatusTypeResponseModel;
import com.astrocure.astrologer.repository.AstrologerRepository;
import com.astrocure.astrologer.repository.BindDataRepository;

import java.util.List;

public class SettingViewModel extends ViewModel {
    private BindDataRepository bindDataRepository;
    private AstrologerRepository astrologerRepository;
    private MutableLiveData<List<ChangeRequestTypeResponseModel.Datum>> requestTypeLiveData = new MutableLiveData<>();
    private MutableLiveData<List<RequestStatusTypeResponseModel.Datum>> requestStatusTypeLiveData = new MutableLiveData<>();

    public SettingViewModel() {
        this.bindDataRepository = new BindDataRepository();
        this.astrologerRepository = new AstrologerRepository();
        getRequestType();
        getRequestStatusType();
    }

    public void getRequestType() {
        bindDataRepository.getChangeDetailRequestType(new BindDataRepository.IChangeRequestType() {
            @Override
            public void onSuccess(ChangeRequestTypeResponseModel responseModel) {
                requestTypeLiveData.postValue(responseModel.getData());
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
    }

    public void getRequestStatusType() {
        bindDataRepository.getRequestStatusType(new BindDataRepository.IChangeRequestStatusType() {
            @Override
            public void onSuccess(RequestStatusTypeResponseModel responseModel) {
                requestStatusTypeLiveData.postValue(responseModel.getData());
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
    }

    public LiveData<ChangeDataResponseModel> getRequestSentLiveData(String astrologerId, String requestId, String requestStatus) {
        MutableLiveData<ChangeDataResponseModel> requestSentLiveData = new MutableLiveData<>();
        astrologerRepository.detailChangeRequest(new ChangeDataRequestModel(astrologerId, requestId, requestStatus), new AstrologerRepository.IDetailChange() {
            @Override
            public void onSuccess(ChangeDataResponseModel responseModel) {
                requestSentLiveData.postValue(responseModel);
            }

            @Override
            public void onFailure(String throwable) {
                requestSentLiveData.postValue(null);
            }
        });
        return requestSentLiveData;
    }

    public LiveData<List<ChangeRequestTypeResponseModel.Datum>> getRequestTypeLiveData() {
        return requestTypeLiveData;
    }

    public LiveData<List<RequestStatusTypeResponseModel.Datum>> getRequestStatusTypeLiveData() {
        return requestStatusTypeLiveData;
    }

}
