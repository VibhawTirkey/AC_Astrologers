package com.astrocure.astrologer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.databinding.ItemChatCallLayoutBinding;

public class CallLogAdapter extends RecyclerView.Adapter<CallLogAdapter.CallLogViewHolder> {
    Context context;

    public CallLogAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CallLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemChatCallLayoutBinding binding = ItemChatCallLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new CallLogViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CallLogViewHolder holder, int position) {
        holder.binding.duration.setText("Call Duration: 6 minutes (12:44 PM-12:50 PM)");
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class CallLogViewHolder extends RecyclerView.ViewHolder {
        ItemChatCallLayoutBinding binding;

        public CallLogViewHolder(ItemChatCallLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
