package com.astrocure.astrologer.ui.fragment;

import static com.astrocure.astrologer.callback.SideNavigationCallback.OPEN_DRAWER;
import static com.astrocure.astrologer.utils.AppConstants.PROFILE_IMG;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment implements Toolbar.OnMenuItemClickListener {
    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    SideNavigationCallback callback;
    private String primaryCounsellingType;

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
        Glide.with(requireContext()).load(PROFILE_IMG).into(binding.profileImage);
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
            bottomSheetDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
            replyBinding.toolbar.setNavigationOnClickListener(v1 -> bottomSheetDialog.dismiss());
            bottomSheetDialog.show();
        });

        binding.callSwitch.setOnClickListener(v -> {
            if (!binding.callSwitch.isChecked()) {
                setOnlineTime("Call");
                binding.callStatus.setText("Offline");
                setMargins(binding.callTimeContainer, 0, 0, 0, (int) AppUtilMethods.pxFromDp(requireContext(), -25));
            } else {
                binding.callStatus.setText("Online");
                setMargins(binding.callTimeContainer, 0, 0, 0, 6);
                viewModel.setOnlineStatus(SPrefClient.getAstrologerDetail(requireContext()).getId(), "call");
            }
        });

        binding.chatSwitch.setOnClickListener(v -> {
            if (!binding.chatSwitch.isChecked()) {
                setOnlineTime("Chat");
                binding.chatStatus.setText("Offline");
                setMargins(binding.chatTimeContainer, 0, 0, 0, (int) AppUtilMethods.pxFromDp(requireContext(), -25));
            } else {
                binding.chatStatus.setText("Online");
                setMargins(binding.chatTimeContainer, 0, 0, 0, 6);
                viewModel.setOnlineStatus(SPrefClient.getAstrologerDetail(requireContext()).getId(), "chat");
            }
        });

        binding.callServiceSwitch.setOnClickListener(v -> {
            if (binding.callServiceSwitch.isChecked()) {
                binding.callServiceStatus.setText("Call On");
                viewModel.setSecondaryCounselling(SPrefClient.getAstrologerDetail(requireContext()).getId(),
                        "CALL",
                        1);
            } else {
                if (!primaryCounsellingType.matches("CALL")) {
                    binding.callServiceStatus.setText("Call Off");
                    viewModel.setSecondaryCounselling(SPrefClient.getAstrologerDetail(requireContext()).getId(),
                            "CALL",
                            0);
                } else {
                    binding.callServiceSwitch.setChecked(true);
                    Toast.makeText(requireContext(), "Permission Denied: Unable to OFFLINE Primary Service", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.chatServiceSwitch.setOnClickListener(v -> {
            if (binding.chatServiceSwitch.isChecked()) {
                binding.chatServiceStatus.setText("Chat On");
                viewModel.setSecondaryCounselling(SPrefClient.getAstrologerDetail(requireContext()).getId(),
                        "CHAT",
                        1);
            } else {
                if (!primaryCounsellingType.matches("CHAT")) {
                    binding.chatServiceStatus.setText("Chat Off");
                    viewModel.setSecondaryCounselling(SPrefClient.getAstrologerDetail(requireContext()).getId(),
                            "CHAT",
                            0);
                } else {
                    binding.chatServiceSwitch.setChecked(true);
                    Toast.makeText(requireContext(), "Permission Denied: Unable to OFFLINE Primary Service", Toast.LENGTH_SHORT).show();
                }
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
            primaryCounsellingType = data.getInitialAvailabilitySelection().getPrimaryCounselling();
            binding.callServiceStatus.setText(data.getCurrentAvailabilityStatus().isCallAvailability() ? "Call On" : "Call Off");
            binding.chatServiceStatus.setText(data.getCurrentAvailabilityStatus().isChatAvailability() ? "Chat On" : "Chat Off");
            binding.callSwitch.setChecked(data.getNextAvailability().get(0).isOnline());
            binding.chatSwitch.setChecked(data.getNextAvailability().get(1).isOnline());
            for (CounsellingDetailResponseModel.Data.NextAvailability nextData : data.getNextAvailability()) {
                if (nextData.getCounsellingType().matches("call")) {
                    if (nextData.isOnline()) {
                        binding.callStatus.setText("Online");
                        setMargins(binding.callTimeContainer, 0, 0, 0, 6);
                    } else {
                        binding.callStatus.setText("Offline");
                        setMargins(binding.callTimeContainer, 0, 0, 0, (int) AppUtilMethods.pxFromDp(requireContext(), -25));
                        try {
                            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(nextData.getNextAvailableDate() == null ? "" : nextData.getNextAvailableDate());
                            assert date != null;
                            binding.callTime.setText(new SimpleDateFormat("dd MMM yyyy ").format(date) + nextData.getNextAvailableTime());
                        } catch (ParseException e) {
                            Log.e("TAG", "setCounsellingData: ", e);
                        }
                    }
                } else {
                    if (nextData.isOnline()) {
                        binding.chatStatus.setText("Online");
                        setMargins(binding.chatTimeContainer, 0, 0, 0, 6);
                    } else {
                        binding.chatStatus.setText("Offline");
                        setMargins(binding.chatTimeContainer, 0, 0, 0, (int) AppUtilMethods.pxFromDp(requireContext(), -25));
                        try {
                            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(nextData.getNextAvailableDate() == null ? "" : nextData.getNextAvailableDate());
                            assert date != null;
                            binding.chatTime.setText(new SimpleDateFormat("dd MMM yyyy ").format(date) + nextData.getNextAvailableTime());
                        } catch (ParseException e) {
                            Log.e("TAG", "setCounsellingData: ", e);
                        }
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

            @SuppressLint("SimpleDateFormat") Date date = null;
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
                viewModel.setNextAvailable(SPrefClient.getAstrologerDetail(requireContext()).getId(),
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