package com.astrocure.astrologer.repository;

import androidx.annotation.NonNull;

import com.astrocure.astrologer.models.responseModels.BindByIdResponseModel;
import com.astrocure.astrologer.models.responseModels.BindResponseModel;
import com.astrocure.astrologer.models.responseModels.ChangeRequestTypeResponseModel;
import com.astrocure.astrologer.models.responseModels.RequestStatusTypeResponseModel;
import com.astrocure.astrologer.network.RetrofitClient;
import com.astrocure.astrologer.utils.AppConstants;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BindDataRepository {
    public void languageById(String languageId, IBindByIdResponse bindResponse) {
        RetrofitClient.getAppClient().getLanguageById(languageId).enqueue(new Callback<BindByIdResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<BindByIdResponseModel> call, @NonNull Response<BindByIdResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        bindResponse.onSuccess(response.body());
                    } else {
                        bindResponse.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : "null").getString("alert"));
                    }
                } catch (Exception e) {
                    bindResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<BindByIdResponseModel> call, @NonNull Throwable t) {
                bindResponse.onFailure(t.getMessage());
            }
        });
    }

    public void expertiseById(String expertiseId, IBindByIdResponse bindResponse) {
        RetrofitClient.getAppClient().getExpertiseById(expertiseId).enqueue(new Callback<BindByIdResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<BindByIdResponseModel> call, @NonNull Response<BindByIdResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        bindResponse.onSuccess(response.body());
                    } else {
                        bindResponse.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : "null").getString("alert"));
                    }
                } catch (Exception e) {
                    bindResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<BindByIdResponseModel> call, @NonNull Throwable t) {
                bindResponse.onFailure(t.getMessage());
            }
        });
    }

    public void experienceById(String experienceId, IBindByIdResponse bindResponse) {
        RetrofitClient.getAppClient().getExperienceById(experienceId).enqueue(new Callback<BindByIdResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<BindByIdResponseModel> call, @NonNull Response<BindByIdResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        bindResponse.onSuccess(response.body());
                    } else {
                        bindResponse.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : "null").getString("alert"));
                    }
                } catch (Exception e) {
                    bindResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<BindByIdResponseModel> call, @NonNull Throwable t) {
                bindResponse.onFailure(t.getMessage());
            }
        });
    }

    public void getChangeDetailRequestType(IChangeRequestType iChangeRequestType) {
        RetrofitClient.getAppClient().getChangeRequestType().enqueue(new Callback<ChangeRequestTypeResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ChangeRequestTypeResponseModel> call, @NonNull Response<ChangeRequestTypeResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        iChangeRequestType.onSuccess(response.body());
                    } else {
                        iChangeRequestType.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : AppConstants.SERVER_ERR_MSG).getString("alert"));
                    }
                } catch (Exception e) {
                    iChangeRequestType.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ChangeRequestTypeResponseModel> call, @NonNull Throwable t) {
                iChangeRequestType.onFailure(t.getMessage());
            }
        });
    }

    public void getRequestStatusType(IChangeRequestStatusType requestStatusType) {
        RetrofitClient.getAppClient().getRequestStatusType().enqueue(new Callback<RequestStatusTypeResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<RequestStatusTypeResponseModel> call, @NonNull Response<RequestStatusTypeResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        requestStatusType.onSuccess(response.body());
                    } else {
                        requestStatusType.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : AppConstants.SERVER_ERR_MSG).getString("alert"));
                    }
                } catch (Exception e) {
                    requestStatusType.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RequestStatusTypeResponseModel> call, @NonNull Throwable t) {
                requestStatusType.onFailure(t.getMessage());
            }
        });
    }

    public void languageBind(IBindResponse bindResponse) {
        RetrofitClient.getAppClient().getLanguages().enqueue(new Callback<BindResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<BindResponseModel> call, @NonNull Response<BindResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        bindResponse.onSuccess(response.body());
                    } else {
                        bindResponse.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : "null").getString("alert"));
                    }
                } catch (Exception e) {
                    bindResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<BindResponseModel> call, @NonNull Throwable t) {
                bindResponse.onFailure(t.getMessage());
            }
        });
    }

    public void genderBind(IBindResponse bindResponse) {
        RetrofitClient.getAppClient().getGenders().enqueue(new Callback<BindResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<BindResponseModel> call, @NonNull Response<BindResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        bindResponse.onSuccess(response.body());
                    } else {
                        bindResponse.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : "null").getString("alert"));
                    }
                } catch (Exception e) {
                    bindResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<BindResponseModel> call, @NonNull Throwable t) {
                bindResponse.onFailure(t.getMessage());
            }
        });
    }

    public void expertiseBind(IBindResponse bindResponse) {
        RetrofitClient.getAppClient().getExpertise().enqueue(new Callback<BindResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<BindResponseModel> call, @NonNull Response<BindResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        bindResponse.onSuccess(response.body());
                    } else {
                        bindResponse.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : "null").getString("alert"));
                    }
                } catch (Exception e) {
                    bindResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<BindResponseModel> call, @NonNull Throwable t) {
                bindResponse.onFailure(t.getMessage());
            }
        });
    }

    public interface IBindResponse {
        void onSuccess(BindResponseModel responseModel);

        void onFailure(String throwable);
    }

    public interface IBindByIdResponse {
        void onSuccess(BindByIdResponseModel responseModel);

        void onFailure(String throwable);
    }

    public interface IChangeRequestType {
        void onSuccess(ChangeRequestTypeResponseModel responseModel);

        void onFailure(String throwable);
    }

    public interface IChangeRequestStatusType {
        void onSuccess(RequestStatusTypeResponseModel responseModel);

        void onFailure(String throwable);
    }

}
