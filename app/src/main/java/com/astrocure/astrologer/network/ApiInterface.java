package com.astrocure.astrologer.network;

import com.astrocure.astrologer.models.requestModels.AddDeviceIdRequestModel;
import com.astrocure.astrologer.models.requestModels.AuthRequestModel;
import com.astrocure.astrologer.models.requestModels.ChangeDataRequestModel;
import com.astrocure.astrologer.models.requestModels.ForgotPassRequestModel;
import com.astrocure.astrologer.models.requestModels.LoginRequestModel;
import com.astrocure.astrologer.models.requestModels.ManageCounsellingRequestModel;
import com.astrocure.astrologer.models.requestModels.NextAvailableRequestModel;
import com.astrocure.astrologer.models.requestModels.PredictionReplyRequestModel;
import com.astrocure.astrologer.models.requestModels.ReplyReviewRequestModel;
import com.astrocure.astrologer.models.requestModels.ResetPasswordRequestModel;
import com.astrocure.astrologer.models.requestModels.ReviewListRequestModel;
import com.astrocure.astrologer.models.requestModels.UpdateOnlineRequestModel;
import com.astrocure.astrologer.models.requestModels.UpdateTokenRequestModel;
import com.astrocure.astrologer.models.responseModels.AddDeviceIdResponseModel;
import com.astrocure.astrologer.models.responseModels.AstrologerResponseModel;
import com.astrocure.astrologer.models.responseModels.BindByIdResponseModel;
import com.astrocure.astrologer.models.responseModels.BindResponseModel;
import com.astrocure.astrologer.models.responseModels.ChangeDataResponseModel;
import com.astrocure.astrologer.models.responseModels.ChangeRequestTypeResponseModel;
import com.astrocure.astrologer.models.responseModels.CounsellingDetailResponseModel;
import com.astrocure.astrologer.models.responseModels.DeviceIdResponseModel;
import com.astrocure.astrologer.models.responseModels.ForgotPassResponseModel;
import com.astrocure.astrologer.models.responseModels.KidsKundliInfoResponseModel;
import com.astrocure.astrologer.models.responseModels.LoginResponseModel;
import com.astrocure.astrologer.models.responseModels.ManageCounsellingResponseModel;
import com.astrocure.astrologer.models.responseModels.NextAvailableResponseModel;
import com.astrocure.astrologer.models.responseModels.PredictionQuestionResponseModel;
import com.astrocure.astrologer.models.responseModels.PredictionReplyResponseModel;
import com.astrocure.astrologer.models.responseModels.ReplyReviewResponseModel;
import com.astrocure.astrologer.models.responseModels.RequestStatusTypeResponseModel;
import com.astrocure.astrologer.models.responseModels.ResetPasswordResponseModel;
import com.astrocure.astrologer.models.responseModels.ReviewListResponseModel;
import com.astrocure.astrologer.models.responseModels.SessionLogResponseModel;
import com.astrocure.astrologer.models.responseModels.UpdateOnlineResponseModel;
import com.astrocure.astrologer.models.responseModels.UpdateTokenResponseModel;
import com.astrocure.astrologer.models.responseModels.VerifyOtpResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
    Call<AstrologerResponseModel> getAstrologerDetail(@Path("astrologerId") String astrologerId);

    @GET("api/v1/bind-language")
    Call<BindResponseModel> getLanguages();

    @GET("api/v1/bind-language/{languageId}")
    Call<BindByIdResponseModel> getLanguageById(@Path("languageId") String languageId);

    @GET("api/v1/bind-expertise/{expertiseId}")
    Call<BindByIdResponseModel> getExpertiseById(@Path("expertiseId") String expertiseId);

    @GET("api/v1/bind-experience/{experienceId}")
    Call<BindByIdResponseModel> getExperienceById(@Path("experienceId") String experienceId);

    @GET("api/v1/bind-gender")
    Call<BindResponseModel> getGenders();

    @GET("api/v1/bind-expertise")
    Call<BindResponseModel> getExpertise();

    @GET("api/v1/astrologer/home/get-counselling-details/{astrologerId}")
    Call<CounsellingDetailResponseModel> counsellingDetail(@Path("astrologerId") String astrologerId);

    @POST("api/v1/astrologer/home/set-next-availability")
    Call<NextAvailableResponseModel> setNextAvailability(@Body NextAvailableRequestModel nextAvailableRequestModel);

    @POST("api/v1/astrologer/home/manage-secondary-counselling-type")
    Call<ManageCounsellingResponseModel> manageSecondaryCounsellingType(@Body ManageCounsellingRequestModel requestModel);

    @PUT("api/v1/astrologer/home/update-online-status")
    Call<UpdateOnlineResponseModel> updateOnlineStatus(@Body UpdateOnlineRequestModel updateOnlineRequestModel);

    @POST("api/v1/token-update")
    Call<UpdateTokenResponseModel> updateFirebaseToken(@Body UpdateTokenRequestModel updateTokenRequestModel);

    @POST("api/v1/astrologer/astro-counselling/list-astrologer-reviews")
    Call<ReviewListResponseModel> reviewList(@Body ReviewListRequestModel reviewListRequestModel);

    @POST("api/v1/astrologer/astro-counselling/reply-to-review")
    Call<ReplyReviewResponseModel> replyToReview(@Body ReplyReviewRequestModel replyRequestModel);

    @POST("api/v1/device-check")
    Call<AddDeviceIdResponseModel> saveDeviceId(@Body AddDeviceIdRequestModel addDeviceIdRequestModel);

    @GET("api/v1/device-check/{userId}/{deviceId}")
    Call<DeviceIdResponseModel> getDeviceId(@Path("userId") String astrologerId, @Path("deviceId") String deviceId);

    @GET("api/v1/astrologer/home/get-session-logs/{astrologerId}")
    Call<SessionLogResponseModel> getSessionLog(@Path("astrologerId") String astrologerId);

    @GET("api/v1/astrologer/bind-request")
    Call<ChangeRequestTypeResponseModel> getChangeRequestType();

    @GET("api/v1/astrologer/bind-request-status")
    Call<RequestStatusTypeResponseModel> getRequestStatusType();

    @POST("api/v1/astrologer/request")
    Call<ChangeDataResponseModel> changeDataRequest(@Body ChangeDataRequestModel changeDataRequestModel);

    @GET("api/v1//astrologer/prediction/{astrologerId}")
    Call<PredictionQuestionResponseModel> getPredictionQuestion(@Path("astrologerId") String astrologerId);

    @PUT("api/v1/astrologer/prediction")
    Call<PredictionReplyResponseModel> sendPredictionAnswer(@Body PredictionReplyRequestModel requestModel);

    @GET("api/v1/kids-by-astrologerid/{astrologerId}")
    Call<KidsKundliInfoResponseModel> getKidsKundliRequest(@Path("astrologerId") String astrologerId);
}
