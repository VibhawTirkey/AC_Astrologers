package com.astrocure.astrologer.network;

import com.astrocure.astrologer.models.requestModels.AuthRequestModel;
import com.astrocure.astrologer.models.requestModels.ForgotPassRequestModel;
import com.astrocure.astrologer.models.requestModels.LoginRequestModel;
import com.astrocure.astrologer.models.requestModels.ResetPasswordRequestModel;
import com.astrocure.astrologer.models.responseModels.AstrologerResponseModel;
import com.astrocure.astrologer.models.responseModels.ForgotPassResponseModel;
import com.astrocure.astrologer.models.responseModels.BindResponseModel;
import com.astrocure.astrologer.models.responseModels.LoginResponseModel;
import com.astrocure.astrologer.models.responseModels.ResetPasswordResponseModel;
import com.astrocure.astrologer.models.responseModels.VerifyOtpResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @POST("api/v1/auth/login-astrologer")
    Call<LoginResponseModel> loginAstrologer(@Body LoginRequestModel loginRequestModel);

    @POST("api/v1/auth/forgot-password")
    Call<ForgotPassResponseModel> forgotPassword(@Body ForgotPassRequestModel forgotPassRequestModel);

    @POST("api/v1/auth/verify-otp")
    Call<VerifyOtpResponseModel> verifyOtp(@Body AuthRequestModel authRequestModel);

    @POST("api/v1/auth/reset-password")
    Call<ResetPasswordResponseModel> resetPassword(@Body ResetPasswordRequestModel resetPasswordRequestModel);

    @GET("api/v1/astrologer/{astrologerId}")
    Call<AstrologerResponseModel> getAstrologerDetail(@Path("astrologerId")String astrologerId);

    @GET("api/v1/bind-language")
    Call<BindResponseModel> getLanguages();

    @GET("api/v1/bind-gender")
    Call<BindResponseModel> getGenders();

    @GET("api/v1/bind-expertise")
    Call<BindResponseModel> getExpertise();


}
