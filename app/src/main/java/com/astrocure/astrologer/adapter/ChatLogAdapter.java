package com.astrocure.astrologer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.databinding.ItemChatCallLayoutBinding;
import com.astrocure.astrologer.ui.ChatActivity;

public class ChatLogAdapter extends RecyclerView.Adapter<ChatLogAdapter.ChatLogViewHolder> {
    Context context;

    public ChatLogAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ChatLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemChatCallLayoutBinding binding = ItemChatCallLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ChatLogViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatLogViewHolder holder, int position) {
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ChatActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class ChatLogViewHolder extends RecyclerView.ViewHolder {
        ItemChatCallLayoutBinding binding;

        public ChatLogViewHolder(ItemChatCallLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
