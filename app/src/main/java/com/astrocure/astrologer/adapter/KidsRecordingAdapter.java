package com.astrocure.astrologer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.databinding.ItemAudioLayoutBinding;

public class KidsRecordingAdapter extends RecyclerView.Adapter<KidsRecordingAdapter.RecordingViewHolder> {
    Context context;

    public KidsRecordingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecordingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAudioLayoutBinding binding = ItemAudioLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new RecordingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public static class RecordingViewHolder extends RecyclerView.ViewHolder {
        ItemAudioLayoutBinding binding;

        public RecordingViewHolder(ItemAudioLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
