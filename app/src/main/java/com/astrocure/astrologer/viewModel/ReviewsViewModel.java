package com.astrocure.astrologer.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.astrocure.astrologer.models.requestModels.ReplyReviewRequestModel;
import com.astrocure.astrologer.models.requestModels.ReviewListRequestModel;
import com.astrocure.astrologer.models.responseModels.ReplyReviewResponseModel;
import com.astrocure.astrologer.models.responseModels.ReviewListResponseModel;
import com.astrocure.astrologer.repository.ReviewsRepository;

import java.util.List;

public class ReviewsViewModel extends AndroidViewModel {
    private final ReviewsRepository reviewsRepository;
    private final MutableLiveData<List<ReviewListResponseModel.Datum>> reviewListLiveData = new MutableLiveData<>();
    private final MutableLiveData<ReplyReviewResponseModel.Data> replyReviewLiveData = new MutableLiveData<>();

    public ReviewsViewModel(@NonNull Application application) {
        super(application);
        reviewsRepository = new ReviewsRepository();
        getReviewList(/*SPrefClient.getAstrologerDetail(getApplication().getApplicationContext()).getId()*/"6555dacdd54022eb20f93a3a",
                0, 1);
    }

    public void getReviewList(String astrologerId, long filter, long filterBy) {
        reviewsRepository.reviewList(new ReviewListRequestModel(astrologerId, filter, filterBy), new ReviewsRepository.IReviewListResponse() {
            @Override
            public void onSuccess(ReviewListResponseModel responseModel) {
                reviewListLiveData.postValue(responseModel.getData());
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
    }

    public void replyToReview(String reviewId, String replyMessage, int isNew, String astrologerId) {

        reviewsRepository.replyReview(new ReplyReviewRequestModel(reviewId, replyMessage, isNew, astrologerId), new ReviewsRepository.IReplyResponse() {
            @Override
            public void onSuccess(ReplyReviewResponseModel responseModel) {
                replyReviewLiveData.postValue(responseModel.getData());
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
    }

    public LiveData<List<ReviewListResponseModel.Datum>> getReviewListLiveData() {
        return reviewListLiveData;
    }

    public MutableLiveData<ReplyReviewResponseModel.Data> getReplyReviewLiveData(String reviewId, String replyMessage, int isNew, String astrologerId) {
        reviewsRepository.replyReview(new ReplyReviewRequestModel(reviewId, replyMessage, isNew, astrologerId), new ReviewsRepository.IReplyResponse() {
            @Override
            public void onSuccess(ReplyReviewResponseModel responseModel) {
                replyReviewLiveData.postValue(responseModel.getData());
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
        return replyReviewLiveData;
    }
}
