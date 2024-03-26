package com.astrocure.astrologer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.databinding.ItemQuestionCountBinding;
import com.astrocure.astrologer.models.responseModels.KidKundliQuestionResponseModel;

import java.util.List;

public class KundliQueNumAdapter extends RecyclerView.Adapter<KundliQueNumAdapter.NumberViewHolder> {
    Context context;
    List<KidKundliQuestionResponseModel.Datum> questionList;
    OnItemActionListener onItemActionListener;
    int setSelected = 0;

    public KundliQueNumAdapter(Context context, List<KidKundliQuestionResponseModel.Datum> questionList) {
        this.context = context;
        this.questionList = questionList;
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
        holder.binding.number.setBackgroundColor(ContextCompat.getColor(context, position == setSelected ? R.color.toolbar_bg_color : R.color.white));
        holder.binding.number.setTextColor(ContextCompat.getColor(context, position == setSelected ? R.color.white : R.color.black));
//        onItemActionListener.setQuestion(questionList.get(setSelected).getId(),position,questionList.get(setSelected).getQuestionText());
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

    @SuppressLint("NotifyDataSetChanged")
    public void setItemSelected(int setSelected) {
        if (setSelected < questionList.size()) {
            this.setSelected = setSelected;
            notifyDataSetChanged();
        }
    }

    public void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }

    public interface OnItemActionListener {
        void setQuestion(String questionId, int questionNum, String question);
    }
}
