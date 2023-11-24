package com.astrocure.astrologer.network;

import com.astrocure.astrologer.models.requestModels.AuthRequestModel;
import com.astrocure.astrologer.models.requestModels.ForgotPassRequestModel;
import com.astrocure.astrologer.models.requestModels.LoginRequestModel;
import com.astrocure.astrologer.models.requestModels.ResetPasswordRequestModel;
import com.astrocure.astrologer.models.responseModels.ForgotPassResponseModel;
import com.astrocure.astrologer.models.responseModels.LoginResponseModel;
import com.astrocure.astrologer.models.responseModels.ResetPasswordResponseModel;
import com.astrocure.astrologer.models.responseModels.VerifyOtpResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("api/v1/auth/login-astrologer")
    Call<LoginResponseModel> loginAstrologer(@Body LoginRequestModel loginRequestModel);

    @POST("api/v1/auth/forgot-password")
    Call<ForgotPassResponseModel> forgotPassword(@Body ForgotPassRequestModel forgotPassRequestModel);

    @POST("api/v1/auth/verify-otp")
    Call<VerifyOtpResponseModel> verifyOtp(@Body AuthRequestModel authRequestModel);

    @POST("api/v1/auth/reset-password")
    Call<ResetPasswordResponseModel> resetPassword(@Body ResetPasswordRequestModel resetPasswordRequestModel);
}
