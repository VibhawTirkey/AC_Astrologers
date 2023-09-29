package com.astrocure.astrologer.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.adapter.MonthEarningAdapter;
import com.astrocure.astrologer.databinding.ActivityMonthChartBinding;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

public class MonthChartActivity extends AppCompatActivity {
    ActivityMonthChartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMonthChartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        ValueLineSeries series = new ValueLineSeries();
        series.setColor(R.color.toolbar_bg_color);

        series.addPoint(new ValueLinePoint("", 200f));
        series.addPoint(new ValueLinePoint("Jan", 200f));
        series.addPoint(new ValueLinePoint("Feb", 450f));
        series.addPoint(new ValueLinePoint("Mar", 375f));
        series.addPoint(new ValueLinePoint("Apr", 550f));
        series.addPoint(new ValueLinePoint("May", 900f));
        series.addPoint(new ValueLinePoint("Jun", 2000f));
        series.addPoint(new ValueLinePoint("Jul", 475f));
        series.addPoint(new ValueLinePoint("Aug", 295.4f));
        series.addPoint(new ValueLinePoint("Sep", 259.4f));
        series.addPoint(new ValueLinePoint("Oct", 395.4f));
        series.addPoint(new ValueLinePoint("Nov", 995.4f));
        series.addPoint(new ValueLinePoint("Dec", 108.3f));
        series.addPoint(new ValueLinePoint("", 108.3f));

        binding.earningChart.setIndicatorTextColor(R.color.toolbar_bg_color);
        binding.earningChart.addSeries(series);
        binding.earningChart.startAnimation();

        MonthEarningAdapter adapter = new MonthEarningAdapter(getApplicationContext());
        binding.monthEarningList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        binding.monthEarningList.setAdapter(adapter);
    }

}