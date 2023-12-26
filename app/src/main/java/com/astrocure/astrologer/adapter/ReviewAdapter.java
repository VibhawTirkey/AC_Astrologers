package com.astrocure.astrologer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.databinding.ItemReviewLayoutBinding;
import com.astrocure.astrologer.models.responseModels.ReviewListResponseModel;
import com.astrocure.astrologer.utils.AppConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private final Context context;
    private onItemClickListener onItemClickListener;
    private final List<ReviewListResponseModel.Datum> reviewList;

    public ReviewAdapter(Context context, List<ReviewListResponseModel.Datum> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemReviewLayoutBinding binding = ItemReviewLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ReviewViewHolder(binding);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        ReviewListResponseModel.Datum review = reviewList.get(position);
        holder.binding.name.setText(review.getCreatedBy().getUserName());
        holder.binding.review.setText(review.getReview());
        holder.binding.rating.setRating(review.getRating());
        try {
            Date date = new SimpleDateFormat(AppConstants.SERVER_TIME_FORMAT).parse(review.getCreatedAt());
            assert date != null;
            holder.binding.date.setText(new SimpleDateFormat("dd MMM yy").format(date));
            holder.binding.time.setText(new SimpleDateFormat("hh:mm a").format(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        holder.binding.reply.setOnClickListener(v -> {
            onItemClickListener.onReplyItemClick(position, review.getCreatedBy().getUserName(), review.getId());
        });
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public void setOnItemClick(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener {
        void onItemClick(int position, String username);
        void onReplyItemClick(int position,String username,String reviewId);
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        ItemReviewLayoutBinding binding;

        public ReviewViewHolder(ItemReviewLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
