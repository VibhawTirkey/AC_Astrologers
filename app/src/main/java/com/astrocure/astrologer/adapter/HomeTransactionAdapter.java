package com.astrocure.astrologer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.databinding.ItemEarningHomeBinding;

public class HomeTransactionAdapter extends RecyclerView.Adapter<HomeTransactionAdapter.TransactionViewHolder> {
    Context context;

    public HomeTransactionAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEarningHomeBinding binding = ItemEarningHomeBinding.inflate(LayoutInflater.from(context), parent, false);
        return new TransactionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {
        ItemEarningHomeBinding binding;

        public TransactionViewHolder(ItemEarningHomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
