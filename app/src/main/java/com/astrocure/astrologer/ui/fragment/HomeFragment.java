package com.astrocure.astrologer.ui.fragment;

import static com.astrocure.astrologer.callback.SideNavigationCallback.OPEN_DRAWER;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.adapter.HomeTransactionAdapter;
import com.astrocure.astrologer.callback.SideNavigationCallback;
import com.astrocure.astrologer.databinding.BottomDialogPredictionReplyBinding;
import com.astrocure.astrologer.databinding.DialogNextOnlineTimeBinding;
import com.astrocure.astrologer.databinding.FragmentHomeBinding;
import com.astrocure.astrologer.models.responseModels.CounsellingDetailResponseModel;
import com.astrocure.astrologer.ui.ChatActivity;
import com.astrocure.astrologer.ui.DayChartActivity;
import com.astrocure.astrologer.ui.MonthChartActivity;
import com.astrocure.astrologer.ui.NotificationActivity;
import com.astrocure.astrologer.ui.ProfileActivity;
import com.astrocure.astrologer.ui.ReviewsActivity;
import com.astrocure.astrologer.ui.WaitListActivity;
import com.astrocure.astrologer.utils.AppUtilMethods;
import com.astrocure.astrologer.utils.SPrefClient;
import com.astrocure.astrologer.viewModel.HomeViewModel;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class HomeFragment extends Fragment implements Toolbar.OnMenuItemClickListener {
    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    SideNavigationCallback callback;
    private boolean isChatPrimary, isCallPrimary;

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

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        viewModel.getCounsellingDetail(SPrefClient.getAstrologerDetail(requireContext()).getId());

        setCounsellingData();

        binding.toolbar.setOnMenuItemClickListener(this);
        Glide.with(requireContext()).load(SPrefClient.getAstrologerDetail(requireContext()).getProfileUrl()).into(binding.profileImage);
        viewModel.getGreetTextLiveData().observe(getViewLifecycleOwner(), s -> binding.greet.setText(s));

        binding.userName.setText(SPrefClient.getAstrologerDetail(requireContext()).getUserName());

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
            Objects.requireNonNull(bottomSheetDialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
            replyBinding.toolbar.setNavigationOnClickListener(v1 -> bottomSheetDialog.dismiss());
            bottomSheetDialog.show();
        });

        binding.callSwitch.setOnClickListener(v -> {
            if (binding.callServiceSwitch.isChecked()) {
                if (binding.callSwitch.isChecked()) {
                    binding.callStatus.setText("Online");
                    setMargins(binding.callTimeContainer, 0, 0, 0, 6);
                    setOnlineTime("Call", true);
                } else {
                    setOnlineTime("Call", false);
                    binding.callStatus.setText("Offline");
                    setMargins(binding.callTimeContainer, 0, 0, 0, (int) AppUtilMethods.pxFromDp(requireContext(), -25));
                }
                Toast.makeText(requireContext(), String.valueOf(binding.callSwitch.isChecked()), Toast.LENGTH_SHORT).show();
            } else {
                binding.callSwitch.setChecked(!binding.callSwitch.isChecked());
                Snackbar.make(binding.getRoot(), "Call Service is Inactive !", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });

        binding.chatSwitch.setOnClickListener(v -> {
            if (binding.chatServiceSwitch.isChecked()) {
                if (binding.chatSwitch.isChecked()) {
                    setOnlineTime("Chat", true);
                    binding.chatStatus.setText("Online");
                    setMargins(binding.chatTimeContainer, 0, 0, 0, 6);
                } else {
                    setOnlineTime("Chat", false);
                    binding.chatStatus.setText("Offline");
                    setMargins(binding.chatTimeContainer, 0, 0, 0, (int) AppUtilMethods.pxFromDp(requireContext(), -25));
                }
                Toast.makeText(requireContext(), String.valueOf(binding.chatSwitch.isChecked()), Toast.LENGTH_SHORT).show();
            } else {
                binding.chatSwitch.setChecked(!binding.chatSwitch.isChecked());
                Snackbar.make(binding.getRoot(), "Chat Service is Inactive !", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });

        binding.callServiceSwitch.setOnClickListener(v -> {
            if (!isCallPrimary) {
                if (binding.callServiceSwitch.isChecked()) {
                    binding.callServiceStatus.setText("Call On");
                    viewModel.setSecondaryCounselling(SPrefClient.getAstrologerDetail(requireContext()).getId(),
                            "CALL",
                            1);
                } else {
                    binding.callServiceStatus.setText("Call Off");
                    viewModel.setSecondaryCounselling(SPrefClient.getAstrologerDetail(requireContext()).getId(),
                            "CALL",
                            0);
                }
            } else {
                binding.callServiceSwitch.setChecked(true);
                Snackbar.make(binding.getRoot(), "Permission Denied: Unable to set OFFLINE Primary Service", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });

        binding.chatServiceSwitch.setOnClickListener(v -> {
            if (!isChatPrimary) {
                if (binding.chatServiceSwitch.isChecked()) {
                    binding.chatServiceSwitch.setText("Chat On");
                    viewModel.setSecondaryCounselling(SPrefClient.getAstrologerDetail(requireContext()).getId(),
                            "CHAT",
                            1);
                } else {
                    binding.chatServiceSwitch.setText("Chat Off");
                    viewModel.setSecondaryCounselling(SPrefClient.getAstrologerDetail(requireContext()).getId(),
                            "CHAT",
                            0);
                }
            } else {
                binding.chatServiceSwitch.setChecked(true);
                Snackbar.make(binding.getRoot(), "Permission Denied: Unable to OFFLINE Primary Service", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });

        binding.continueChat.setOnClickListener(v -> startActivity(new Intent(requireContext(), ChatActivity.class)));

        return binding.getRoot();
    }

    @SuppressLint({"SimpleDateFormat", "SetTextI18n"})
    private void setCounsellingData() {
        //retrieving status data
        viewModel.getCounsellingDetailLiveData().observe(getViewLifecycleOwner(), data -> {
            binding.callServiceSwitch.setChecked(data.getCurrentAvailabilityStatus().isCallAvailability());
            binding.chatServiceSwitch.setChecked(data.getCurrentAvailabilityStatus().isChatAvailability());
            isChatPrimary = data.getInitialAvailabilitySelection().getPrimaryCounselling().matches("CHAT");
            isCallPrimary = data.getInitialAvailabilitySelection().getPrimaryCounselling().matches("CALL");
            binding.callServiceStatus.setText(data.getCurrentAvailabilityStatus().isCallAvailability() ? "Call On" : "Call Off");
            binding.chatServiceStatus.setText(data.getCurrentAvailabilityStatus().isChatAvailability() ? "Chat On" : "Chat Off");
            for (CounsellingDetailResponseModel.Data.NextAvailability statusData : data.getNextAvailability()) {
                if (statusData.getCounsellingType().matches("call")) {
                    binding.callSwitch.setChecked(statusData.isOnline());
                    binding.callStatus.setText(statusData.isOnline() ? "Online" : "Offline");
                    setMargins(binding.callTimeContainer, 0, 0, 0, statusData.isOnline() ? 6 : (int) AppUtilMethods.pxFromDp(requireContext(), -25));
                    try {
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(statusData.getNextAvailableDate() == null ? "" : statusData.getNextAvailableDate());
                        assert date != null;
                        binding.callTime.setText(new SimpleDateFormat("dd MMM yyyy ").format(date) + statusData.getNextAvailableTime());
                    } catch (ParseException ignored) {
                    }
                } else {
                    binding.chatSwitch.setChecked(statusData.isOnline());
                    binding.chatStatus.setText(statusData.isOnline() ? "Online" : "Offline");
                    setMargins(binding.chatTimeContainer, 0, 0, 0, statusData.isOnline() ? 6 : (int) AppUtilMethods.pxFromDp(requireContext(), -25));
                    try {
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(statusData.getNextAvailableDate() == null ? "" : statusData.getNextAvailableDate());
                        assert date != null;
                        binding.chatTime.setText(new SimpleDateFormat("dd MMM yyyy ").format(date) + statusData.getNextAvailableTime());
                    } catch (ParseException ignored) {
                    }
                }
            }
        });

        //setting offline data
        viewModel.getNextAvailableLiveData().observe(getViewLifecycleOwner(), data -> {
//            Date nextTime = null;
//            try {
//                nextTime = new SimpleDateFormat("yyyy-MM-dd").parse(data.getNextAvailableDate() == null ? "" : data.getNextAvailableDate());
//                if (data.getCounsellingType().matches("call")) {
//                    binding.callTime.setText(new SimpleDateFormat("d MMM yyyy ").format(nextTime) + data.getNextAvailableTime());
//                } else {
//                    binding.chatTime.setText(new SimpleDateFormat("d MMM yyyy ").format(nextTime) + data.getNextAvailableTime());
//                }
//            } catch (ParseException e) {
//                Log.e("TAG", "setCounsellingData: ", e);
//            }

        });

        //setting online data
        viewModel.getUpdateOnlineLiveData().observe(getViewLifecycleOwner(), data -> {
            if (data.getCounsellingType().matches("chat")) {
                binding.chatSwitch.setChecked(true);
            } else {
                binding.callSwitch.setChecked(true);
            }
        });

    }

    @SuppressLint({"SimpleDateFormat", "SetTextI18n"})
    private void setOnlineTime(String type, boolean isOnline) {
        Dialog dialog = new Dialog(requireContext());
        DialogNextOnlineTimeBinding onlineTimeBinding = DialogNextOnlineTimeBinding.inflate(getLayoutInflater());
        dialog.setContentView(onlineTimeBinding.getRoot());
        dialog.setCancelable(false);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 0);
        onlineTimeBinding.datePicker.setMinDate(cal.getTimeInMillis());
        cal.add(Calendar.DAY_OF_YEAR, 7);
        onlineTimeBinding.textView19.setText("Your Next Available Time for " + type);
        onlineTimeBinding.datePicker.setMaxDate(cal.getTimeInMillis());
        onlineTimeBinding.select.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);
            Date date = null;
            try {
                date = new SimpleDateFormat("dd MM yyyy").parse(onlineTimeBinding.datePicker.getDayOfMonth() + " " +
                        (onlineTimeBinding.datePicker.getMonth() + 1) + " " +
                        onlineTimeBinding.datePicker.getYear());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Date finalDate = date;
            TimePickerDialog timePickerDialog = new TimePickerDialog(requireContext(), (view, hourOfDay, minute) -> {
                Calendar cal1 = Calendar.getInstance();
                cal1.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal1.set(Calendar.MINUTE, minute);
                assert finalDate != null;
                viewModel.setOnlineStatus(isOnline, SPrefClient.getAstrologerDetail(requireContext()).getId(),
                        type.matches("Chat") ? "chat" : "call",
                        new SimpleDateFormat("yyyy-MM-dd").format(finalDate),
                        new SimpleDateFormat("hh:mm a").format(cal1.getTime()));
                if (type.matches("Chat")) {
                    binding.chatTime.setText(new SimpleDateFormat("dd MMM yyyy ").format(finalDate) + new SimpleDateFormat("hh:mm a").format(cal1.getTime()));
                } else {
                    binding.callTime.setText(new SimpleDateFormat("dd MMM yyyy ").format(finalDate) + new SimpleDateFormat("hh:mm a").format(cal1.getTime()));
                }
                dialog.dismiss();
            }, mHour, mMinute, false);
            timePickerDialog.setCancelable(false);
            timePickerDialog.show();
        });
        dialog.show();
    }

    private void setMargins(View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
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