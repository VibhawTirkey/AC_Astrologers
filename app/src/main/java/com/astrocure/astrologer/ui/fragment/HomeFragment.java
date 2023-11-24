package com.astrocure.astrologer.ui.fragment;

import static com.astrocure.astrologer.callback.SideNavigationCallback.OPEN_DRAWER;
import static com.astrocure.astrologer.utils.AppConstants.PROFILE_IMG;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.adapter.HomeTransactionAdapter;
import com.astrocure.astrologer.callback.SideNavigationCallback;
import com.astrocure.astrologer.databinding.BottomDialogPredictionReplyBinding;
import com.astrocure.astrologer.databinding.DialogNextOnlineTimeBinding;
import com.astrocure.astrologer.databinding.FragmentHomeBinding;
import com.astrocure.astrologer.ui.DayChartActivity;
import com.astrocure.astrologer.ui.MonthChartActivity;
import com.astrocure.astrologer.ui.NotificationActivity;
import com.astrocure.astrologer.ui.ProfileActivity;
import com.astrocure.astrologer.ui.ReviewsActivity;
import com.astrocure.astrologer.ui.WaitListActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Calendar;

public class HomeFragment extends Fragment implements Toolbar.OnMenuItemClickListener {
    FragmentHomeBinding binding;
    SideNavigationCallback callback;
    String callTime;

    public HomeFragment() {
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
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        binding.toolbar.setOnMenuItemClickListener(this::onMenuItemClick);
        Glide.with(requireContext()).load(PROFILE_IMG).into(binding.profileImage);

        HomeTransactionAdapter homeTransactionAdapter = new HomeTransactionAdapter(requireContext());
        binding.transactionList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        binding.transactionList.setAdapter(homeTransactionAdapter);

        binding.toolbar.setNavigationOnClickListener(v -> callback.callBackAction(OPEN_DRAWER));

        binding.profileImage.setOnClickListener(v -> startActivity(new Intent(getContext(), ProfileActivity.class)));

        binding.reviewsContainer.setOnClickListener(v -> startActivity(new Intent(requireContext(), ReviewsActivity.class)));
        binding.waitListContainer.setOnClickListener(v -> startActivity(new Intent(requireContext(), WaitListActivity.class)));
        binding.monthEarningContainer.setOnClickListener(v -> startActivity(new Intent(requireContext(), MonthChartActivity.class)));
        binding.dayEarningContainer.setOnClickListener(v -> startActivity(new Intent(requireContext(), DayChartActivity.class)));
        binding.viewAll.setOnClickListener(v -> startActivity(new Intent(requireContext(), DayChartActivity.class)));

        binding.predictionQuestion.reply.setOnClickListener(v -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
            BottomDialogPredictionReplyBinding replyBinding = BottomDialogPredictionReplyBinding.inflate(getLayoutInflater());
            bottomSheetDialog.setContentView(replyBinding.getRoot());
            bottomSheetDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
            replyBinding.toolbar.setNavigationOnClickListener(v1 -> bottomSheetDialog.dismiss());
            bottomSheetDialog.show();
        });

        binding.callSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            /*if (!isChecked) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);
                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        binding.callTime.setText(dayOfMonth+" "+(month+1)+" "+year+", ");
                        TimePickerDialog timePickerDialog = new TimePickerDialog(requireContext(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                binding.callTime.append(hourOfDay+" "+minute);
//                                        txtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                        timePickerDialog.show();
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }*/
            if (!isChecked) {
                setOnlineTime("Call");
            }
        });

        binding.chatSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!isChecked) {
                setOnlineTime("Chat");
            }
        });

        return binding.getRoot();
    }

    private void setOnlineTime(String type) {
        Dialog dialog = new Dialog(requireContext());
        DialogNextOnlineTimeBinding onlineTimeBinding = DialogNextOnlineTimeBinding.inflate(getLayoutInflater());
        dialog.setContentView(onlineTimeBinding.getRoot());
        dialog.setCancelable(false);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 0);
        onlineTimeBinding.datePicker.setMinDate(cal.getTimeInMillis());
        cal.add(Calendar.DAY_OF_YEAR, 7);
        onlineTimeBinding.textView19.setText("Set Next Online Time for " + type);
        onlineTimeBinding.datePicker.setMaxDate(cal.getTimeInMillis());
        onlineTimeBinding.select.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(requireContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay,
                                      int minute) {
//                    binding.callTime.append(hourOfDay + " " + minute);
                    dialog.dismiss();
                }
            }, mHour, mMinute, false);
            timePickerDialog.setCancelable(false);
            timePickerDialog.show();
        });
        dialog.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.top_notification) {
            startActivity(new Intent(requireContext(), NotificationActivity.class));
            return true;
        }
        return false;
    }
}