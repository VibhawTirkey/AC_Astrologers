package com.astrocure.astrologer.repository;

import static com.astrocure.astrologer.utils.AppConstants.SERVER_ERR_MSG;

import androidx.annotation.NonNull;

import com.astrocure.astrologer.models.requestModels.AddDeviceIdRequestModel;
import com.astrocure.astrologer.models.requestModels.AuthRequestModel;
import com.astrocure.astrologer.models.requestModels.ForgotPassRequestModel;
import com.astrocure.astrologer.models.requestModels.LoginRequestModel;
import com.astrocure.astrologer.models.requestModels.ResetPasswordRequestModel;
import com.astrocure.astrologer.models.requestModels.UpdateTokenRequestModel;
import com.astrocure.astrologer.models.responseModels.AddDeviceIdResponseModel;
import com.astrocure.astrologer.models.responseModels.AstrologerResponseModel;
import com.astrocure.astrologer.models.responseModels.DeviceIdResponseModel;
import com.astrocure.astrologer.models.responseModels.ForgotPassResponseModel;
import com.astrocure.astrologer.models.responseModels.LoginResponseModel;
import com.astrocure.astrologer.models.responseModels.ResetPasswordResponseModel;
import com.astrocure.astrologer.models.responseModels.SessionLogResponseModel;
import com.astrocure.astrologer.models.responseModels.UpdateTokenResponseModel;
import com.astrocure.astrologer.models.responseModels.VerifyOtpResponseModel;
import com.astrocure.astrologer.network.RetrofitClient;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    public void astrologerDetailApi(String astrologerId, IAstrologerDetailResponse astrologerDetailResponse) {
        RetrofitClient.getAppClient().getAstrologerDetail(astrologerId).enqueue(new Callback<AstrologerResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<AstrologerResponseModel> call, @NonNull Response<AstrologerResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        astrologerDetailResponse.onResponse(response.body());
                    } else {
                        astrologerDetailResponse.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : SERVER_ERR_MSG).getString("alert"));
                    }
                } catch (Exception e) {
                    astrologerDetailResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<AstrologerResponseModel> call, @NonNull Throwable t) {
                astrologerDetailResponse.onFailure(t.getMessage());
            }
        });
    }

    public void callLoginApi(LoginRequestModel requestModel, ILoginResponse loginResponse) {
        RetrofitClient.getAppClient().loginAstrologer(requestModel).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        loginResponse.onResponse(response.body());
                    } else {
                        loginResponse.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : "null").getString("alert"));
                    }
                } catch (Exception e) {
                    loginResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponseModel> call, @NonNull Throwable t) {
                loginResponse.onFailure("Something went wrong, Please try again");
            }
        });
    }

    public void forgotPasswordApiCall(ForgotPassRequestModel requestModel, IForgotPasswordResponse forgotPasswordResponse) {
        RetrofitClient.getAppClient().forgotPassword(requestModel).enqueue(new Callback<ForgotPassResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ForgotPassResponseModel> call, @NonNull Response<ForgotPassResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        forgotPasswordResponse.onResponse(response.body());
                    } else {
                        forgotPasswordResponse.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : SERVER_ERR_MSG).getString("alert"));
                    }
                } catch (Exception e) {
                    forgotPasswordResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ForgotPassResponseModel> call, @NonNull Throwable t) {
                forgotPasswordResponse.onFailure(t.getMessage());
            }
        });
    }

    public void verifyOtpApi(AuthRequestModel authRequestModel, IVerifyOtpResponse verifyOtpResponse) {
        RetrofitClient.getAppClient().verifyOtp(authRequestModel).enqueue(new Callback<VerifyOtpResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<VerifyOtpResponseModel> call, @NonNull Response<VerifyOtpResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        verifyOtpResponse.onResponse(response.body());
                    } else {
                        verifyOtpResponse.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : SERVER_ERR_MSG).getString("alert"));
                    }
                } catch (Exception e) {
                    verifyOtpResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<VerifyOtpResponseModel> call, @NonNull Throwable t) {
                verifyOtpResponse.onFailure(t.getMessage());
            }
        });
    }

    public void resetPasswordApi(ResetPasswordRequestModel requestModel, IResetPasswordResponse resetPasswordResponse) {
        RetrofitClient.getAppClient().resetPassword(requestModel).enqueue(new Callback<ResetPasswordResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResetPasswordResponseModel> call, @NonNull Response<ResetPasswordResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        resetPasswordResponse.onResponse(response.body());
                    } else {
                        resetPasswordResponse.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : SERVER_ERR_MSG).getString("alert"));
                    }
                } catch (Exception e) {
                    resetPasswordResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResetPasswordResponseModel> call, @NonNull Throwable t) {
                resetPasswordResponse.onFailure(t.getMessage());
            }
        });
    }

    public void updateFirebaseToken(UpdateTokenRequestModel updateTokenRequestModel, IUpdateFirebaseToken firebaseTokenResponse) {
        RetrofitClient.getAppClient().updateFirebaseToken(updateTokenRequestModel).enqueue(new Callback<UpdateTokenResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<UpdateTokenResponseModel> call, @NonNull Response<UpdateTokenResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        firebaseTokenResponse.onSuccess(response.body());
                    } else {
                        firebaseTokenResponse.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : "null").getString("alert"));
                    }
                } catch (Exception e) {
                    firebaseTokenResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<UpdateTokenResponseModel> call, @NonNull Throwable t) {
                firebaseTokenResponse.onFailure(t.getMessage());
            }
        });
    }

    public void getDeviceId(String astrologerId, String deviceId, IGetDeviceId iGetDeviceId) {
        RetrofitClient.getAppClient().getDeviceId(astrologerId, deviceId).enqueue(new Callback<DeviceIdResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<DeviceIdResponseModel> call, @NonNull Response<DeviceIdResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        iGetDeviceId.onSuccess(response.body());
                    } else {
                        iGetDeviceId.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : SERVER_ERR_MSG).getString("alert"));
                    }
                } catch (Exception e) {
                    iGetDeviceId.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<DeviceIdResponseModel> call, @NonNull Throwable t) {
                iGetDeviceId.onFailure(t.getMessage());
            }
        });
    }

    public void saveDeviceId(String astrologerId, String deviceId, ISaveDeviceId iSaveDeviceId) {
        RetrofitClient.getAppClient().saveDeviceId(new AddDeviceIdRequestModel(astrologerId, deviceId)).enqueue(new Callback<AddDeviceIdResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<AddDeviceIdResponseModel> call, @NonNull Response<AddDeviceIdResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        iSaveDeviceId.onSuccess(response.body());
                    } else {
                        iSaveDeviceId.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : SERVER_ERR_MSG).getString("alert"));
                    }
                } catch (Exception e) {
                    iSaveDeviceId.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<AddDeviceIdResponseModel> call, @NonNull Throwable t) {
                iSaveDeviceId.onFailure(t.getMessage());
            }
        });
    }

    public void getSessionLog(String astrologerId, ISessionLog iSessionLog) {
        RetrofitClient.getAppClient().getSessionLog(astrologerId).enqueue(new Callback<SessionLogResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<SessionLogResponseModel> call, @NonNull Response<SessionLogResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        iSessionLog.onSuccess(response.body());
                    } else {
                        iSessionLog.onFailure(new JSONObject(response.errorBody() != null ? response.errorBody().string() : SERVER_ERR_MSG).getString("alert"));
                    }
                } catch (Exception e) {
                    iSessionLog.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<SessionLogResponseModel> call, @NonNull Throwable t) {
                iSessionLog.onFailure(t.getMessage());
            }
        });
    }

    public interface ISessionLog {
        void onSuccess(SessionLogResponseModel logResponseModel);

        void onFailure(String throwable);
    }

    public interface ISaveDeviceId {
        void onSuccess(AddDeviceIdResponseModel responseModel);

        void onFailure(String throwable);
    }

    public interface IGetDeviceId {
        void onSuccess(DeviceIdResponseModel responseModel);

        void onFailure(String throwable);
    }

    public interface IAstrologerDetailResponse {
        void onResponse(AstrologerResponseModel astrologerResponseModel);

        void onFailure(String t);
    }

    public interface ILoginResponse {
        void onResponse(LoginResponseModel loginResponseModel);

        void onFailure(String t);
    }

    public interface IForgotPasswordResponse {
        void onResponse(ForgotPassResponseModel forgotPassResponseModel);

        void onFailure(String t);
    }

    public interface IVerifyOtpResponse {
        void onResponse(VerifyOtpResponseModel verifyOtpResponseModel);

        void onFailure(String t);
    }

    public interface IResetPasswordResponse {
        void onResponse(ResetPasswordResponseModel responseModel);

        void onFailure(String t);
    }

    public interface IUpdateFirebaseToken {
        void onSuccess(UpdateTokenResponseModel updateTokenResponseModel);

        void onFailure(String throwable);
    }
}
