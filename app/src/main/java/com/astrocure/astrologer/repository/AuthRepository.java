package com.astrocure.astrologer.repository;

import androidx.annotation.NonNull;

import com.astrocure.astrologer.models.requestModels.AuthRequestModel;
import com.astrocure.astrologer.models.requestModels.ForgotPassRequestModel;
import com.astrocure.astrologer.models.requestModels.LoginRequestModel;
import com.astrocure.astrologer.models.requestModels.ResetPasswordRequestModel;
import com.astrocure.astrologer.models.responseModels.AstrologerResponseModel;
import com.astrocure.astrologer.models.responseModels.ForgotPassResponseModel;
import com.astrocure.astrologer.models.responseModels.LoginResponseModel;
import com.astrocure.astrologer.models.responseModels.ResetPasswordResponseModel;
import com.astrocure.astrologer.models.responseModels.VerifyOtpResponseModel;
import com.astrocure.astrologer.network.RetrofitClient;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    public void astrologerDetailApi(String astrologerId,IAstrologerDetailResponse astrologerDetailResponse){
        RetrofitClient.getAppClient().getAstrologerDetail(astrologerId).enqueue(new Callback<AstrologerResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<AstrologerResponseModel> call, @NonNull Response<AstrologerResponseModel> response) {
                try {
                    if (response.isSuccessful()){
                        astrologerDetailResponse.onResponse(response.body());
                    }else {
                        astrologerDetailResponse.onFailure(new JSONObject(response.errorBody().string()).getString("alert"));
                    }
                }catch (Exception e){
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
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        loginResponse.onResponse(response.body());
                    } else if (response.code() == 400) {
                        loginResponse.onFailure(new JSONObject(response.errorBody().string()).getString("alert"));
                    }
                } catch (Exception e) {
                    loginResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                loginResponse.onFailure("Check your Network Connection");
            }
        });
    }

    public void forgotPasswordApiCall(ForgotPassRequestModel requestModel, IForgotPasswordResponse forgotPasswordResponse) {
        RetrofitClient.getAppClient().forgotPassword(requestModel).enqueue(new Callback<ForgotPassResponseModel>() {
            @Override
            public void onResponse(Call<ForgotPassResponseModel> call, Response<ForgotPassResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        forgotPasswordResponse.onResponse(response.body());
                    } else {
                        forgotPasswordResponse.onFailure(new JSONObject(response.errorBody().string()).getString("alert"));
                    }
                } catch (Exception e) {
                    forgotPasswordResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ForgotPassResponseModel> call, Throwable t) {
                forgotPasswordResponse.onFailure(t.getMessage());
            }
        });
    }

    public void verifyOtpApi(AuthRequestModel authRequestModel, IVerifyOtpResponse verifyOtpResponse) {
        RetrofitClient.getAppClient().verifyOtp(authRequestModel).enqueue(new Callback<VerifyOtpResponseModel>() {
            @Override
            public void onResponse(Call<VerifyOtpResponseModel> call, Response<VerifyOtpResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        verifyOtpResponse.onResponse(response.body());
                    } else {
                        verifyOtpResponse.onFailure(new JSONObject(response.errorBody().string()).getString("alert"));
                    }
                } catch (Exception e) {
                    verifyOtpResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<VerifyOtpResponseModel> call, Throwable t) {
                verifyOtpResponse.onFailure(t.getMessage());
            }
        });
    }

    public void resetPasswordApi(ResetPasswordRequestModel requestModel, IResetPasswordResponse resetPasswordResponse) {
        RetrofitClient.getAppClient().resetPassword(requestModel).enqueue(new Callback<ResetPasswordResponseModel>() {
            @Override
            public void onResponse(Call<ResetPasswordResponseModel> call, Response<ResetPasswordResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        resetPasswordResponse.onResponse(response.body());
                    } else {
                        resetPasswordResponse.onFailure(new JSONObject(response.errorBody().string()).getString("alert"));
                    }
                } catch (Exception e) {
                    resetPasswordResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResetPasswordResponseModel> call, Throwable t) {
                resetPasswordResponse.onFailure(t.getMessage());
            }
        });
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
}
