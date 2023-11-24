package com.astrocure.astrologer.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.adapter.HomeTransactionAdapter;
import com.astrocure.astrologer.databinding.ActivityDayChartBinding;
import com.google.android.material.datepicker.MaterialDatePicker;

import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DayChartActivity extends AppCompatActivity {
    ActivityDayChartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDayChartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        ValueLineSeries series = new ValueLineSeries();
        series.setColor(R.color.toolbar_bg_color);

        series.addPoint(new ValueLinePoint("", 200f));
        series.addPoint(new ValueLinePoint("Mon", 200f));
        series.addPoint(new ValueLinePoint("Tue", 450f));
        series.addPoint(new ValueLinePoint("Wed", 375f));
        series.addPoint(new ValueLinePoint("Thu", 550f));
        series.addPoint(new ValueLinePoint("Fri", 900f));
        series.addPoint(new ValueLinePoint("Sat", 2000f));
        series.addPoint(new ValueLinePoint("Sun", 475f));
        series.addPoint(new ValueLinePoint("", 475f));

        binding.earningChart.setIndicatorTextColor(R.color.toolbar_bg_color);
        binding.earningChart.addSeries(series);
        binding.earningChart.startAnimation();

        HomeTransactionAdapter adapter = new HomeTransactionAdapter(getApplicationContext());
        binding.monthEarningList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.monthEarningList.setAdapter(adapter);

        binding.calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog();
            }
        });
    }

    private void datePickerDialog() {
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Select a date range");

        MaterialDatePicker<Pair<Long, Long>> datePicker = builder.build();
        datePicker.addOnPositiveButtonClickListener(selection -> {
            Long startDate = selection.first;
            Long endDate = selection.second;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String startDateString = sdf.format(new Date(startDate));
            String endDateString = sdf.format(new Date(endDate));

            String selectedDateRange = startDateString + " - " + endDateString;

//            selectedDate.setText(selectedDateRange);
        });
        datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
    }
}