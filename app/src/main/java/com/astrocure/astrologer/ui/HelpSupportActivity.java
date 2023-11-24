package com.astrocure.astrologer.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.astrologer.adapter.HelpQueryAdapter;
import com.astrocure.astrologer.databinding.ActivityHelpSupportBinding;
import com.astrocure.astrologer.models.HelpQuestionModel;

import java.util.ArrayList;
import java.util.List;

public class HelpSupportActivity extends AppCompatActivity {
    ActivityHelpSupportBinding binding;
    List<HelpQuestionModel> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHelpSupportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        getQuestionData();
        HelpQueryAdapter adapter = new HelpQueryAdapter(getApplicationContext(), questionList);
        binding.questionList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.questionList.setAdapter(adapter);

        binding.viewTickets.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ViewTicketActivity.class));
        });

        binding.chatAssist.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ChatAssistantActivity.class));
        });
    }

    private void getQuestionData() {
        questionList = new ArrayList<>();
        questionList.add(new HelpQuestionModel("Have an issue with a transaction?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        questionList.add(new HelpQuestionModel("UnderStanding Payments BY call and chat", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        questionList.add(new HelpQuestionModel("Having problem with chat history", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
    }
}