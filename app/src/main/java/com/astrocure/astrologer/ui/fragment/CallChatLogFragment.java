package com.astrocure.astrologer.ui.fragment;

import static com.astrocure.astrologer.callback.SideNavigationCallback.OPEN_DRAWER;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.astrologer.adapter.CallLogAdapter;
import com.astrocure.astrologer.adapter.ChatLogAdapter;
import com.astrocure.astrologer.callback.SideNavigationCallback;
import com.astrocure.astrologer.databinding.FragmentCallChatLogBinding;
import com.google.android.material.tabs.TabLayout;


public class CallChatLogFragment extends Fragment {
    FragmentCallChatLogBinding binding;
    SideNavigationCallback callback;

    public CallChatLogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callback = (SideNavigationCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + "implementation failed");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCallChatLogBinding.inflate(getLayoutInflater());

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.callBackAction(OPEN_DRAWER);
            }
        });

        ChatLogAdapter chatLogAdapter = new ChatLogAdapter(getContext());
        CallLogAdapter callLogAdapter = new CallLogAdapter(getContext());
        binding.logList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.logList.setAdapter(callLogAdapter);

        binding.selectionTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    binding.logList.setAdapter(callLogAdapter);
                } else {
                    binding.logList.setAdapter(chatLogAdapter);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return binding.getRoot();
    }
}