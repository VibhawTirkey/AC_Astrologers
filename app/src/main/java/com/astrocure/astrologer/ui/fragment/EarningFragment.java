package com.astrocure.astrologer.ui.fragment;

import static com.astrocure.astrologer.callback.SideNavigationCallback.OPEN_DRAWER;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.astrocure.astrologer.callback.SideNavigationCallback;
import com.astrocure.astrologer.databinding.FragmentEarningBinding;
import com.astrocure.astrologer.ui.DayChartActivity;
import com.astrocure.astrologer.ui.MonthChartActivity;

public class EarningFragment extends Fragment {
    FragmentEarningBinding binding;
    SideNavigationCallback callback;

    public EarningFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEarningBinding.inflate(getLayoutInflater());
        binding.toolbar.setNavigationOnClickListener(v -> callback.callBackAction(OPEN_DRAWER));

        binding.monthHistory.setOnClickListener(v -> startActivity(new Intent(requireContext(), MonthChartActivity.class)));
        binding.dayHistory.setOnClickListener(v -> startActivity(new Intent(requireContext(), DayChartActivity.class)));

        return binding.getRoot();
    }
}