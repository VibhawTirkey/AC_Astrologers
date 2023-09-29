package com.astrocure.astrologer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.databinding.ItemWaitListLayoutBinding;

public class WaitListAdapter extends RecyclerView.Adapter<WaitListAdapter.WaitListViewHolder> {
    Context context;

    public WaitListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public WaitListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWaitListLayoutBinding binding = ItemWaitListLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new WaitListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WaitListViewHolder holder, int position) {
        holder.binding.title.setText(ordinal_suffix_of(position + 1) + " Call Waiting");
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    private String ordinal_suffix_of(int i) {
        int j = i % 10,
                k = i % 100;
        if (j == 1 && k != 11) {
            return i + "st";
        }
        if (j == 2 && k != 12) {
            return i + "nd";
        }
        if (j == 3 && k != 13) {
            return i + "rd";
        }
        return i + "th";
    }

    public class WaitListViewHolder extends RecyclerView.ViewHolder {
        ItemWaitListLayoutBinding binding;

        public WaitListViewHolder(ItemWaitListLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
