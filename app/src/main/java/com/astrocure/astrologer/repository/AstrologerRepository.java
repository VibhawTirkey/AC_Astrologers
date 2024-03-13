package com.astrocure.astrologer.repository;

import androidx.annotation.NonNull;

import com.astrocure.astrologer.models.requestModels.ChangeDataRequestModel;
import com.astrocure.astrologer.models.responseModels.ChangeDataResponseModel;
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

    public interface IDetailChange {
        void onSuccess(ChangeDataResponseModel responseModel);

        void onFailure(String throwable);
    }
}
