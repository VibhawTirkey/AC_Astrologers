package com.astrocure.astrologer.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.astrocure.astrologer.models.responseModels.BindByIdResponseModel;
import com.astrocure.astrologer.repository.BindDataRepository;

public class ProfileViewModel extends ViewModel {
    private final BindDataRepository bindRepository;
    private final MutableLiveData<String> languageLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> expertiseLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> experienceLiveData = new MutableLiveData<>();

    public ProfileViewModel() {
        bindRepository = new BindDataRepository();
    }

    public void getLanguageById(String languageId) {
        bindRepository.languageById(languageId, new BindDataRepository.IBindByIdResponse() {
            @Override
            public void onSuccess(BindByIdResponseModel responseModel) {
                languageLiveData.postValue(responseModel.getData().getDataName());
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
    }

    public void getExpertiseById(String expertiseId) {
        bindRepository.expertiseById(expertiseId, new BindDataRepository.IBindByIdResponse() {
            @Override
            public void onSuccess(BindByIdResponseModel responseModel) {
                expertiseLiveData.postValue(responseModel.getData().getDataName());
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
    }
    public void getExperienceById(String experienceId) {
        bindRepository.experienceById(experienceId, new BindDataRepository.IBindByIdResponse() {
            @Override
            public void onSuccess(BindByIdResponseModel responseModel) {
                experienceLiveData.postValue(responseModel.getData().getDataName());
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
    }

    public LiveData<String> getLanguageLiveData() {
        return languageLiveData;
    }

    public LiveData<String> getExpertiseLiveData() {
        return expertiseLiveData;
    }

    public LiveData<String> getExperienceLiveData() {
        return experienceLiveData;
    }
}
