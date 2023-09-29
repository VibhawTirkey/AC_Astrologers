package com.astrocure.astrologer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.databinding.ItemMonthEarningBinding;

public class MonthEarningAdapter extends RecyclerView.Adapter<MonthEarningAdapter.EarnViewHolder> {
    Context context;

    public MonthEarningAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public EarnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMonthEarningBinding binding = ItemMonthEarningBinding.inflate(LayoutInflater.from(context),parent,false);
        return new EarnViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EarnViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class EarnViewHolder extends RecyclerView.ViewHolder {
        ItemMonthEarningBinding binding;

        public EarnViewHolder(ItemMonthEarningBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
