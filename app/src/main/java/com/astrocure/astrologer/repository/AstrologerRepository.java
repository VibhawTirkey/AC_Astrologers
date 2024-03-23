package com.astrocure.astrologer.repository;

import androidx.annotation.NonNull;

import com.astrocure.astrologer.models.requestModels.ChangeDataRequestModel;
import com.astrocure.astrologer.models.requestModels.PredictionReplyRequestModel;
import com.astrocure.astrologer.models.responseModels.ChangeDataResponseModel;
import com.astrocure.astrologer.models.responseModels.KidsKundliInfoResponseModel;
import com.astrocure.astrologer.models.responseModels.PredictionQuestionResponseModel;
import com.astrocure.astrologer.models.responseModels.PredictionReplyResponseModel;
import com.astrocure.astrologer.network.RetrofitClient;
import com.astrocure.astrologer.utils.AppConstants;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AstrologerRepository {
    public void detailChangeRequest(ChangeDataRequestModel requestModel, IDetailChange iDetailChange) {
        RetrofitClient.getAppClient().changeDataRequest(requestModel).enqueue(new Callback<ChangeDataResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ChangeDataResponseModel> call, @NonNull Response<ChangeDataResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        iDetailChange.onSuccess(response.body());
                    } else {
                        iDetailChange.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : AppConstants.SERVER_ERR_MSG).getString("alert"));
                    }
                } catch (Exception e) {
                    iDetailChange.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ChangeDataResponseModel> call, @NonNull Throwable t) {
                iDetailChange.onFailure(t.getMessage());
            }
        });
    }

    public void getPredictionQuestion(String astrologerId, IPredictionQuestion predictionQuestion) {
        RetrofitClient.getAppClient().getPredictionQuestion(astrologerId).enqueue(new Callback<PredictionQuestionResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<PredictionQuestionResponseModel> call, @NonNull Response<PredictionQuestionResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        predictionQuestion.onSuccess(response.body());
                    } else {
                        predictionQuestion.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : AppConstants.SERVER_ERR_MSG).getString("alert"));
                    }
                } catch (Exception e) {
                    predictionQuestion.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PredictionQuestionResponseModel> call, @NonNull Throwable t) {
                predictionQuestion.onFailure(t.getMessage());
            }
        });
    }

    public void sendPredictionAnswer(PredictionReplyRequestModel replyRequestModel, IPredictionAnswer predictionAnswer) {
        RetrofitClient.getAppClient().sendPredictionAnswer(replyRequestModel).enqueue(new Callback<PredictionReplyResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<PredictionReplyResponseModel> call, @NonNull Response<PredictionReplyResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        predictionAnswer.onSuccess(response.body());
                    } else {
                        predictionAnswer.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : AppConstants.SERVER_ERR_MSG).getString("alert"));
                    }
                } catch (Exception e) {
                    predictionAnswer.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PredictionReplyResponseModel> call, @NonNull Throwable t) {
                predictionAnswer.onFailure(t.getMessage());
            }
        });
    }

    public void getKidsKundliRequest(String astrologerId, IKidsKundliRequest iKidsKundliRequest) {
        RetrofitClient.getAppClient().getKidsKundliRequest(astrologerId).enqueue(new Callback<KidsKundliInfoResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<KidsKundliInfoResponseModel> call, @NonNull Response<KidsKundliInfoResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        iKidsKundliRequest.onSuccess(response.body());
                    } else {
                        iKidsKundliRequest.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : AppConstants.SERVER_ERR_MSG).getString("alert"));
                    }
                } catch (Exception e) {
                    iKidsKundliRequest.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<KidsKundliInfoResponseModel> call, @NonNull Throwable t) {
                iKidsKundliRequest.onFailure(t.getMessage());
            }
        });
    }

    ;

    public interface IKidsKundliRequest {
        void onSuccess(KidsKundliInfoResponseModel kundliInfoResponseModel);

        void onFailure(String throwable);
    }
    public interface IDetailChange {
        void onSuccess(ChangeDataResponseModel responseModel);

        void onFailure(String throwable);
    }

    public interface IPredictionQuestion {
        void onSuccess(PredictionQuestionResponseModel predictionQuestionResponse);

        void onFailure(String throwable);
    }

    public interface IPredictionAnswer {
        void onSuccess(PredictionReplyResponseModel predictionReplyResponse);

        void onFailure(String throwable);
    }
}
