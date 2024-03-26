package com.astrocure.astrologer.repository;

import static com.astrocure.astrologer.utils.AppConstants.SERVER_ERR_MSG;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.astrocure.astrologer.models.requestModels.ManageCounsellingRequestModel;
import com.astrocure.astrologer.models.requestModels.NextAvailableRequestModel;
import com.astrocure.astrologer.models.requestModels.UpdateOnlineRequestModel;
import com.astrocure.astrologer.models.responseModels.CounsellingDetailResponseModel;
import com.astrocure.astrologer.models.responseModels.FileUploadResponseModel;
import com.astrocure.astrologer.models.responseModels.ManageCounsellingResponseModel;
import com.astrocure.astrologer.models.responseModels.NextAvailableResponseModel;
import com.astrocure.astrologer.models.responseModels.UpdateOnlineResponseModel;
import com.astrocure.astrologer.network.RetrofitClient;

import org.json.JSONObject;

import java.io.File;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {
    public void getCounsellingDetail(String astrologerId, ICounsellingDetailResponse counsellingDetailResponse) {
        RetrofitClient.getAppClient().counsellingDetail(astrologerId).enqueue(new Callback<CounsellingDetailResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<CounsellingDetailResponseModel> call, @NonNull Response<CounsellingDetailResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        counsellingDetailResponse.onSuccess(response.body());
                    } else {
                        counsellingDetailResponse.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : SERVER_ERR_MSG).getString("alert"));
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
                        nextAvailableResponse.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : SERVER_ERR_MSG).getString("alert"));
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
                        counsellingResponse.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : SERVER_ERR_MSG).getString("alert"));
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

    public void updateOnlineStatus(UpdateOnlineRequestModel requestModel, IUpdateOnlineResponse onlineResponse) {
        RetrofitClient.getAppClient().updateOnlineStatus(requestModel).enqueue(new Callback<UpdateOnlineResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<UpdateOnlineResponseModel> call, @NonNull Response<UpdateOnlineResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        onlineResponse.onSuccess(response.body());
                    } else {
                        onlineResponse.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : SERVER_ERR_MSG).getString("alert"));
                    }
                } catch (Exception e) {
                    onlineResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<UpdateOnlineResponseModel> call, @NonNull Throwable t) {
                onlineResponse.onFailure(t.getMessage());
            }
        });
    }

    public void uploadFiles(Uri uri, IUploadImage iUploadImage) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        File file = new File(Objects.requireNonNull(uri.getPath()));
        builder.addFormDataPart("files", file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
        RetrofitClient.getAppClient().uploadFile(builder.build()).enqueue(new Callback<FileUploadResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<FileUploadResponseModel> call, @NonNull Response<FileUploadResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        iUploadImage.onSuccess(response.body());
                    } else {
                        iUploadImage.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : "null").getString("alert"));
                    }
                } catch (Exception e) {
                    iUploadImage.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<FileUploadResponseModel> call, @NonNull Throwable t) {
                iUploadImage.onFailure(t.getMessage());
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

    public interface IUpdateOnlineResponse {
        void onSuccess(UpdateOnlineResponseModel responseModel);

        void onFailure(String throwable);
    }

    public interface IUploadImage {
        void onSuccess(FileUploadResponseModel responseModel);

        void onFailure(String throwable);
    }
}
