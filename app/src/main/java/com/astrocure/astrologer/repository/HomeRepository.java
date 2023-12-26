package com.astrocure.astrologer.repository;

import androidx.annotation.NonNull;

import com.astrocure.astrologer.models.requestModels.ManageCounsellingRequestModel;
import com.astrocure.astrologer.models.requestModels.NextAvailableRequestModel;
import com.astrocure.astrologer.models.responseModels.CounsellingDetailResponseModel;
import com.astrocure.astrologer.models.responseModels.ManageCounsellingResponseModel;
import com.astrocure.astrologer.models.responseModels.NextAvailableResponseModel;
import com.astrocure.astrologer.network.RetrofitClient;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {

    public HomeRepository() {

    }

    public void getCounsellingDetail(String astrologerId, ICounsellingDetailResponse counsellingDetailResponse) {
        RetrofitClient.getAppClient().counsellingDetail(astrologerId).enqueue(new Callback<CounsellingDetailResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<CounsellingDetailResponseModel> call, @NonNull Response<CounsellingDetailResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        counsellingDetailResponse.onSuccess(response.body());
                    } else {
                        counsellingDetailResponse.onFailure(new JSONObject(response.errorBody().string()).getString("alert"));
                    }
                } catch (Exception e) {
                    counsellingDetailResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<CounsellingDetailResponseModel> call, @NonNull Throwable t) {
                counsellingDetailResponse.onFailure(t.getMessage());
            }
        });
    }

    public void getNextAvailability(NextAvailableRequestModel requestModel, INextAvailableResponse nextAvailableResponse) {
        RetrofitClient.getAppClient().setNextAvailability(requestModel).enqueue(new Callback<NextAvailableResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<NextAvailableResponseModel> call, @NonNull Response<NextAvailableResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        nextAvailableResponse.onSuccess(response.body());
                    } else {
                        nextAvailableResponse.onFailure(new JSONObject(response.errorBody().string()).getString("alert"));
                    }
                } catch (Exception e) {
                    nextAvailableResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<NextAvailableResponseModel> call, @NonNull Throwable t) {

            }
        });
    }

    public void manageSecondaryCounselling(ManageCounsellingRequestModel requestModel, ISecondaryCounsellingResponse counsellingResponse) {
        RetrofitClient.getAppClient().manageSecondaryCounsellingType(requestModel).enqueue(new Callback<ManageCounsellingResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ManageCounsellingResponseModel> call, @NonNull Response<ManageCounsellingResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        counsellingResponse.onSuccess(response.body());
                    } else {
                        counsellingResponse.onFailure(new JSONObject(response.errorBody().string()).getString("alert"));
                    }
                } catch (Exception e) {
                    counsellingResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ManageCounsellingResponseModel> call, @NonNull Throwable t) {
                counsellingResponse.onFailure(t.getMessage());
            }
        });
    }

    public interface ICounsellingDetailResponse {
        void onSuccess(CounsellingDetailResponseModel responseModel);

        void onFailure(String throwable);
    }

    public interface INextAvailableResponse {
        void onSuccess(NextAvailableResponseModel responseModel);

        void onFailure(String throwable);
    }

    public interface ISecondaryCounsellingResponse {
        void onSuccess(ManageCounsellingResponseModel responseModel);

        void onFailure(String throwable);
    }
}
