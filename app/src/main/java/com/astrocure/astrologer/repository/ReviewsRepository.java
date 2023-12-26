package com.astrocure.astrologer.repository;

import androidx.annotation.NonNull;

import com.astrocure.astrologer.models.requestModels.ReplyReviewRequestModel;
import com.astrocure.astrologer.models.requestModels.ReviewListRequestModel;
import com.astrocure.astrologer.models.responseModels.ReplyReviewResponseModel;
import com.astrocure.astrologer.models.responseModels.ReviewListResponseModel;
import com.astrocure.astrologer.network.RetrofitClient;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewsRepository {
    public void reviewList(ReviewListRequestModel reviewListRequestModel, IReviewListResponse reviewListResponse) {
        RetrofitClient.getAppClient().reviewList(reviewListRequestModel).enqueue(new Callback<ReviewListResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ReviewListResponseModel> call, @NonNull Response<ReviewListResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        reviewListResponse.onSuccess(response.body());
                    } else {
                        reviewListResponse.onFailure(new JSONObject(response.errorBody().string()).getString("alert"));
                    }
                } catch (Exception e) {
                    reviewListResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ReviewListResponseModel> call, @NonNull Throwable t) {
                reviewListResponse.onFailure(t.getMessage());
            }
        });
    }

    public void replyReview(ReplyReviewRequestModel replyRequestModel, IReplyResponse iReplyResponse) {
        RetrofitClient.getAppClient().replyToReview(replyRequestModel).enqueue(new Callback<ReplyReviewResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ReplyReviewResponseModel> call, @NonNull Response<ReplyReviewResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        iReplyResponse.onSuccess(response.body());
                    } else {
                        iReplyResponse.onFailure(new JSONObject(response.errorBody().string()).getString("alert"));
                    }
                } catch (Exception e) {
                    iReplyResponse.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ReplyReviewResponseModel> call, @NonNull Throwable t) {
                iReplyResponse.onFailure(t.getMessage());
            }
        });
    }

    public interface IReviewListResponse {
        void onSuccess(ReviewListResponseModel responseModel);

        void onFailure(String throwable);
    }

    public interface IReplyResponse {
        void onSuccess(ReplyReviewResponseModel responseModel);

        void onFailure(String throwable);
    }
}
