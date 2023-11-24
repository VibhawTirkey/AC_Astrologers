package com.astrocure.astrologer.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.astrologer.adapter.ReviewAdapter;
import com.astrocure.astrologer.databinding.ActivityReviewsBinding;
import com.astrocure.astrologer.databinding.DialogBottomReplyReviewBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ReviewsActivity extends AppCompatActivity {
    ActivityReviewsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        ReviewAdapter adapter = new ReviewAdapter(getApplicationContext());
        binding.reviewList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.reviewList.setAdapter(adapter);
        adapter.setOnItemClick((position, username) -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ReviewsActivity.this);
            DialogBottomReplyReviewBinding replyBinding = DialogBottomReplyReviewBinding.inflate(getLayoutInflater());
            bottomSheetDialog.setContentView(replyBinding.getRoot());
            replyBinding.username.setText("@" + username);
            bottomSheetDialog.show();
        });


    }
}