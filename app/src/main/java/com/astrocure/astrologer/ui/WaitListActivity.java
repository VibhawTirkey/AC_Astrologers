package com.astrocure.astrologer.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.astrologer.adapter.WaitListAdapter;
import com.astrocure.astrologer.databinding.ActivityWaitListBinding;

public class WaitListActivity extends AppCompatActivity {
    ActivityWaitListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWaitListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
        WaitListAdapter adapter = new WaitListAdapter(getApplicationContext());
        binding.waitList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.waitList.setAdapter(adapter);
    }
}