package com.astrocure.astrologer.repository;

import static com.astrocure.astrologer.utils.AppConstants.SERVER_ERR_MSG;

import androidx.annotation.NonNull;

import com.astrocure.astrologer.models.requestModels.DeleteRecordingRequestModel;
import com.astrocure.astrologer.models.requestModels.UploadAudioRequestModel;
import com.astrocure.astrologer.models.responseModels.DeleteRecordingResponseModel;
import com.astrocure.astrologer.models.responseModels.KidKundliQuestionResponseModel;
import com.astrocure.astrologer.models.responseModels.UploadAudioResponseModel;
import com.astrocure.astrologer.network.RetrofitClient;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KidsKundliRepository {

    public void uploadRecordedAudio(UploadAudioRequestModel uploadAudioRequestModel, IAudioUpload iAudioUpload) {
        RetrofitClient.getAppClient().uploadRecordedAudio(uploadAudioRequestModel).enqueue(new Callback<UploadAudioResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<UploadAudioResponseModel> call, @NonNull Response<UploadAudioResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        iAudioUpload.onSuccess(response.body());
                    } else {
                        iAudioUpload.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : SERVER_ERR_MSG).getString("alert"));
                    }
                } catch (Exception e) {
                    iAudioUpload.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<UploadAudioResponseModel> call, @NonNull Throwable t) {
                iAudioUpload.onFailure(t.getMessage());
            }
        });
    }

    public void getKidsKundliQuestion(String astrologerId, IKidQuestion iKidQuestion) {
        RetrofitClient.getAppClient().getKidsKundliQuestions(astrologerId).enqueue(new Callback<KidKundliQuestionResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<KidKundliQuestionResponseModel> call, @NonNull Response<KidKundliQuestionResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        iKidQuestion.onSuccess(response.body());
                    } else {
                        iKidQuestion.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : SERVER_ERR_MSG).getString("alert"));
                    }
                } catch (Exception e) {
                    iKidQuestion.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<KidKundliQuestionResponseModel> call, @NonNull Throwable t) {
                iKidQuestion.onFailure(t.getMessage());
            }
        });
    }

    public void deletePrevRecordings(DeleteRecordingRequestModel recordingRequestModel, IDeleteRecord iDeleteRecord) {
        RetrofitClient.getAppClient().deletePreviousRecording(recordingRequestModel).enqueue(new Callback<DeleteRecordingResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<DeleteRecordingResponseModel> call, @NonNull Response<DeleteRecordingResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        iDeleteRecord.onSuccess(response.body());
                    } else {
                        iDeleteRecord.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : SERVER_ERR_MSG).getString("alert"));
                    }
                } catch (Exception e) {
                    iDeleteRecord.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<DeleteRecordingResponseModel> call, @NonNull Throwable t) {
                iDeleteRecord.onFailure(t.getMessage());
            }
        });
    }

    public interface IDeleteRecord {
        void onSuccess(DeleteRecordingResponseModel deleteRecordingResponseModel);

        void onFailure(String throwable);
    }

    public interface IAudioUpload {
        void onSuccess(UploadAudioResponseModel uploadAudioResponseModel);

        void onFailure(String throwable);
    }

    public interface IKidQuestion {
        void onSuccess(KidKundliQuestionResponseModel kundliQuestionResponseModel);

        void onFailure(String throwable);
    }
}
