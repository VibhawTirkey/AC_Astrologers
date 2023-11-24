package com.astrocure.astrologer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.databinding.ItemReviewLayoutBinding;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    Context context;
    onItemClickListener onItemClickListener;

    public ReviewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemReviewLayoutBinding binding = ItemReviewLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ReviewViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        int index = position;
        holder.binding.reply.setOnClickListener(v -> onItemClickListener.onItemClick(index, holder.binding.name.getText().toString()));
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public void setOnItemClick(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener {
        void onItemClick(int position, String username);
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        ItemReviewLayoutBinding binding;

        public ReviewViewHolder(ItemReviewLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
