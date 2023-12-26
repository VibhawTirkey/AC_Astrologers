package com.astrocure.astrologer.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.astrologer.adapter.ReviewAdapter;
import com.astrocure.astrologer.databinding.ActivityReviewsBinding;
import com.astrocure.astrologer.databinding.DialogBottomReplyReviewBinding;
import com.astrocure.astrologer.utils.SPrefClient;
import com.astrocure.astrologer.viewModel.ReviewsViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

public class ReviewsActivity extends AppCompatActivity {
    private ActivityReviewsBinding binding;
    private ReviewsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        viewModel = new ViewModelProvider(this).get(ReviewsViewModel.class);

        viewModel.getReviewListLiveData().observe(this, data -> {
            ReviewAdapter adapter = new ReviewAdapter(getApplicationContext(), data);
            binding.reviewList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            binding.reviewList.setAdapter(adapter);
            adapter.setOnItemClick(new ReviewAdapter.onItemClickListener() {
                @Override
                public void onItemClick(int position, String username) {

                }

                @Override
                public void onReplyItemClick(int position, String username, String reviewId) {
                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ReviewsActivity.this);
                    DialogBottomReplyReviewBinding replyBinding = DialogBottomReplyReviewBinding.inflate(getLayoutInflater());
                    bottomSheetDialog.setContentView(replyBinding.getRoot());
                    replyBinding.username.setText("@" + username);
                    replyBinding.send.setOnClickListener(v -> viewModel.getReplyReviewLiveData(reviewId, replyBinding.message.getText().toString(), 0, SPrefClient.getAstrologerDetail(getApplicationContext()).getId()).observe(ReviewsActivity.this, data1 -> {
                        Toast.makeText(ReviewsActivity.this, "Successfully replied to" + username, Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }));
                    bottomSheetDialog.show();
                }
            });
        });

        binding.selectionTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 1:
                        viewModel.getReviewList("6555dacdd54022eb20f93a3a", 1, 5);
                        break;
                    case 2:
                        viewModel.getReviewList("6555dacdd54022eb20f93a3a", 1, 4);
                        break;
                    case 3:
                        viewModel.getReviewList("6555dacdd54022eb20f93a3a", 1, 3);
                        break;
                    case 4:
                        viewModel.getReviewList("6555dacdd54022eb20f93a3a", 1, 2);
                        break;
                    case 5:
                        viewModel.getReviewList("6555dacdd54022eb20f93a3a", 1, 1);
                        break;
                    default:
                        viewModel.getReviewList("6555dacdd54022eb20f93a3a", 0, 1);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}