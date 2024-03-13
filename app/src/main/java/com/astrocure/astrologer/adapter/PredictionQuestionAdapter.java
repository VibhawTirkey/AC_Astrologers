package com.astrocure.astrologer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.databinding.ItemPredictionQuestionLayoutBinding;
import com.astrocure.astrologer.models.responseModels.PredictionQuestionResponseModel;
import com.astrocure.astrologer.utils.AppConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PredictionQuestionAdapter extends RecyclerView.Adapter<PredictionQuestionAdapter.QuestionViewHolder> {
    Context context;
    List<PredictionQuestionResponseModel.Datum> questionList;
    OnItemActionListener onItemActionListener;

    public PredictionQuestionAdapter(Context context, List<PredictionQuestionResponseModel.Datum> questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPredictionQuestionLayoutBinding binding = ItemPredictionQuestionLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new QuestionViewHolder(binding);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        PredictionQuestionResponseModel.Datum question = questionList.get(position);
        holder.binding.title.setText(question.getTitle());
        holder.binding.hashTag.setText(question.getHashTag());
        holder.binding.questionContent.setText(question.getQuestion());
        String predictionDateTime, newDate, newTime;
        try {
            Date date = new SimpleDateFormat(AppConstants.SERVER_TIME_FORMAT).parse(question.getPredicationDateTime());
            assert date != null;
            newDate = new SimpleDateFormat("dd MMM yyyy").format(date);
            newTime = new SimpleDateFormat("hh:mm a").format(date);
            holder.binding.date.setText(newDate);
            holder.binding.time.setText(newTime);
            predictionDateTime = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        holder.binding.reply.setOnClickListener(v -> {
            onItemActionListener.onReplyClick(question.getId(), question.getTitle(), question.getHashTag(), question.getQuestion(), predictionDateTime, newDate, newTime);
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }

    public interface OnItemActionListener {
        void onReplyClick(String predictionId, String title, String hashTag, String question, String predictionTime, String date, String time);
    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {
        ItemPredictionQuestionLayoutBinding binding;

        public QuestionViewHolder(ItemPredictionQuestionLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
