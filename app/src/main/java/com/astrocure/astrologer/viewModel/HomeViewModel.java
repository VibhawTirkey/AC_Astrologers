package com.astrocure.astrologer.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.astrocure.astrologer.models.requestModels.ManageCounsellingRequestModel;
import com.astrocure.astrologer.models.requestModels.NextAvailableRequestModel;
import com.astrocure.astrologer.models.requestModels.PredictionReplyRequestModel;
import com.astrocure.astrologer.models.requestModels.UpdateOnlineRequestModel;
import com.astrocure.astrologer.models.responseModels.CounsellingDetailResponseModel;
import com.astrocure.astrologer.models.responseModels.DeviceIdResponseModel;
import com.astrocure.astrologer.models.responseModels.ManageCounsellingResponseModel;
import com.astrocure.astrologer.models.responseModels.NextAvailableResponseModel;
import com.astrocure.astrologer.models.responseModels.PredictionQuestionResponseModel;
import com.astrocure.astrologer.models.responseModels.PredictionReplyResponseModel;
import com.astrocure.astrologer.models.responseModels.UpdateOnlineResponseModel;
import com.astrocure.astrologer.repository.AstrologerRepository;
import com.astrocure.astrologer.repository.AuthRepository;
import com.astrocure.astrologer.repository.HomeRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private final HomeRepository homeRepository;
    private final AuthRepository authRepository;
    private final AstrologerRepository astrologerRepository;
    private final MutableLiveData<String> greetTextLiveData = new MutableLiveData<>();
    private final MutableLiveData<CounsellingDetailResponseModel.Data> counsellingDetailLiveData = new MutableLiveData<>();
    private final MutableLiveData<NextAvailableResponseModel.Data> nextAvailableLiveData = new MutableLiveData<>();
    private final MutableLiveData<ManageCounsellingResponseModel> secondaryCounsellingLiveData = new MutableLiveData<>();
    private final MutableLiveData<UpdateOnlineResponseModel.Data> updateOnlineLiveData = new MutableLiveData<>();
    private final MutableLiveData<DeviceIdResponseModel.Data> deviceIdLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<PredictionQuestionResponseModel.Datum>> questionListLiveData = new MutableLiveData<>();
    public HomeViewModel() {
        homeRepository = new HomeRepository();
        authRepository = new AuthRepository();
        astrologerRepository = new AstrologerRepository();
        greetingText();
    }

    public void greetingText() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour >= 12 && hour < 17) {
            greetTextLiveData.postValue("Good Afternoon");
        } else if (hour >= 17 && hour < 21) {
            greetTextLiveData.postValue("Good Evening");
        } else if (hour >= 21 /*&& hour < 24*/) {
            greetTextLiveData.postValue("Good Night");
        } else {
            greetTextLiveData.postValue("Good Morning");
        }
    }

    public void getCounsellingDetail(String astrologerId) {
        homeRepository.getCounsellingDetail(astrologerId, new HomeRepository.ICounsellingDetailResponse() {
            @Override
            public void onSuccess(CounsellingDetailResponseModel responseModel) {
                counsellingDetailLiveData.postValue(responseModel.getData());
            }

            @Override
            public void onFailure(String throwable) {

            }
        });

    }

    public void setNextAvailable(String astrologerId, String counsellingType, String availableDate, String availableTime) {
        homeRepository.getNextAvailability(new NextAvailableRequestModel(astrologerId, counsellingType, availableDate, availableTime), new HomeRepository.INextAvailableResponse() {
            @Override
            public void onSuccess(NextAvailableResponseModel responseModel) {
                nextAvailableLiveData.postValue(responseModel.getData());
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
    }

    public void setSecondaryCounselling(String astrologerId, String counsellingType, int status) {
        homeRepository.manageSecondaryCounselling(new ManageCounsellingRequestModel(astrologerId, counsellingType, status), new HomeRepository.ISecondaryCounsellingResponse() {
            @Override
            public void onSuccess(ManageCounsellingResponseModel responseModel) {
                secondaryCounsellingLiveData.postValue(responseModel);
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
    }

    public void setOnlineStatus(boolean isOnline, String astrologerId, String counsellingType, String date, String time) {
        homeRepository.updateOnlineStatus(new UpdateOnlineRequestModel(isOnline, astrologerId, counsellingType, date, time), new HomeRepository.IUpdateOnlineResponse() {
            @Override
            public void onSuccess(UpdateOnlineResponseModel responseModel) {
                updateOnlineLiveData.postValue(responseModel.getData());
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

    public void getPredictionQuestions(String astrologerId) {
        astrologerRepository.getPredictionQuestion(astrologerId, new AstrologerRepository.IPredictionQuestion() {
            @Override
            public void onSuccess(PredictionQuestionResponseModel predictionQuestionResponse) {
                questionListLiveData.postValue(predictionQuestionResponse.getData());
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
    }

    public LiveData<PredictionReplyResponseModel.Data> sendPredictionAnswer(String astrologerId, String title, String hashTag, String question, String answer, String predictionTime, String answerTime, String questionId) {
        final MutableLiveData<PredictionReplyResponseModel.Data> predictionAnswerLiveData = new MutableLiveData<>();
        PredictionReplyRequestModel replyRequestModel = new PredictionReplyRequestModel();
        replyRequestModel.setTitle(title);
        replyRequestModel.setAstrologerId(astrologerId);
        replyRequestModel.setHashTag(hashTag);
        replyRequestModel.setQuestion(question);
        replyRequestModel.setAnswer(answer);
        replyRequestModel.setPredicationDateTime(predictionTime);
        replyRequestModel.setAnswerDateTime(answerTime);
        replyRequestModel.setId(questionId);
        astrologerRepository.sendPredictionAnswer(replyRequestModel, new AstrologerRepository.IPredictionAnswer() {
            @Override
            public void onSuccess(PredictionReplyResponseModel predictionReplyResponse) {
                getPredictionQuestions(astrologerId);
                predictionAnswerLiveData.postValue(predictionReplyResponse.getData());
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
        return predictionAnswerLiveData;
    }

    public LiveData<List<PredictionQuestionResponseModel.Datum>> getQuestionListLiveData() {
        return questionListLiveData;
    }

    public LiveData<String> getGreetTextLiveData() {
        return greetTextLiveData;
    }

    public LiveData<CounsellingDetailResponseModel.Data> getCounsellingDetailLiveData() {
        return counsellingDetailLiveData;
    }

    public LiveData<NextAvailableResponseModel.Data> getNextAvailableLiveData() {
        return nextAvailableLiveData;
    }

    public LiveData<ManageCounsellingResponseModel> getSecondaryCounsellingLiveData() {
        return secondaryCounsellingLiveData;
    }

    public LiveData<UpdateOnlineResponseModel.Data> getUpdateOnlineLiveData() {
        return updateOnlineLiveData;
    }

    public LiveData<DeviceIdResponseModel.Data> getDeviceIdLiveData() {
        return deviceIdLiveData;
    }
}
