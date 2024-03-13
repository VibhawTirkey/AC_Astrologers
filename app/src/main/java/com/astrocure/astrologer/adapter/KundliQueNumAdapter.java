package com.astrocure.astrologer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.databinding.ItemQuestionCountBinding;

public class KundliQueNumAdapter extends RecyclerView.Adapter<KundliQueNumAdapter.NumberViewHolder> {
    Context context;

    public KundliQueNumAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemQuestionCountBinding binding = ItemQuestionCountBinding.inflate(LayoutInflater.from(context), parent, false);
        return new NumberViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        holder.binding.number.setText(String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public static class NumberViewHolder extends RecyclerView.ViewHolder {
        ItemQuestionCountBinding binding;

        public NumberViewHolder(ItemQuestionCountBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
