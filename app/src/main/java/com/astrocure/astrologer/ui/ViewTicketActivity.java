package com.astrocure.astrologer.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.adapter.TicketAdapter;
import com.astrocure.astrologer.databinding.ActivityViewTicketBinding;
import com.astrocure.astrologer.models.TicketModel;

import java.util.ArrayList;
import java.util.List;

public class ViewTicketActivity extends AppCompatActivity {
    ActivityViewTicketBinding binding;
    List<TicketModel> ticketModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewTicketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        getTicketList();
        TicketAdapter adapter = new TicketAdapter(getApplicationContext(),ticketModels);
        binding.ticketList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        binding.ticketList.setAdapter(adapter);
    }

    private void getTicketList() {
        ticketModels = new ArrayList<>();
        ticketModels.add(new TicketModel("Issues with Chat with riya Payment","#132442",false,"19 Aug 23","5:44 PM"));
        ticketModels.add(new TicketModel("Call Amount not in Wallet","#132442",true,"19 Aug 23","5:44 PM"));
        ticketModels.add(new TicketModel("Issue with Transaction ID :C34243423423423","#132442",true,"19 Aug 23","5:44 PM"));
    }
}