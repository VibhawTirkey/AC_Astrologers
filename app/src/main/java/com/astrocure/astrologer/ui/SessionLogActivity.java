package com.astrocure.astrologer.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.astrologer.adapter.SessionLogAdapter;
import com.astrocure.astrologer.databinding.ActivitySessionLogBinding;
import com.astrocure.astrologer.utils.SPrefClient;
import com.astrocure.astrologer.viewModel.SessionLogViewModel;
import com.google.android.material.tabs.TabLayout;

public class SessionLogActivity extends AppCompatActivity {
    ActivitySessionLogBinding binding;
    private SessionLogViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySessionLogBinding.inflate(getLayoutInflater());
//        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        viewModel = new ViewModelProvider(this).get(SessionLogViewModel.class);

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        viewModel.getSessionLogs(SPrefClient.getAstrologerDetail(getApplicationContext()).getId(), "call");

        binding.selectionTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    viewModel.getSessionLogs(SPrefClient.getAstrologerDetail(getApplicationContext()).getId(), "call");
                } else {
                    viewModel.getSessionLogs(SPrefClient.getAstrologerDetail(getApplicationContext()).getId(), "chat");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewModel.getSessionLogLiveData().observe(this, data -> {
            SessionLogAdapter adapter = new SessionLogAdapter(getApplicationContext(), data);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, true);
            linearLayoutManager.setStackFromEnd(true);
            binding.logList.setLayoutManager(linearLayoutManager);
            binding.logList.setAdapter(adapter);
        });

    }
}