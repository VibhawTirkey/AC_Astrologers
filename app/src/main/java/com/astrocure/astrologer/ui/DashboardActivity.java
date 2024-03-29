package com.astrocure.astrologer.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.callback.SideNavigationCallback;
import com.astrocure.astrologer.databinding.ActivityDashboardBinding;
import com.astrocure.astrologer.databinding.DialogQueryDetailBinding;
import com.astrocure.astrologer.ui.fragment.CallChatLogFragment;
import com.astrocure.astrologer.ui.fragment.EarningFragment;
import com.astrocure.astrologer.ui.fragment.HomeFragment;
import com.astrocure.astrologer.ui.fragment.ProfileFragment;
import com.astrocure.astrologer.utils.AppConstants;
import com.astrocure.astrologer.utils.SPrefClient;
import com.astrocure.astrologer.viewModel.DashboardViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SideNavigationCallback {
    private ActivityDashboardBinding binding;
    private DashboardViewModel viewModel;
    boolean doubleBackToExitPressedOnce = false;
    HomeFragment homeFragment;
    EarningFragment walletFragment;
    CallChatLogFragment logFragment;
    ProfileFragment profileFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @SuppressLint({"HardwareIds", "SimpleDateFormat", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        homeFragment = new HomeFragment();
        walletFragment = new EarningFragment();
        logFragment = new CallChatLogFragment();
        profileFragment = new ProfileFragment();
        setFragment(homeFragment);

        binding.bottomNav.setItemIconTintList(null);
        binding.bottomNav.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
        binding.sideNav.setNavigationItemSelectedListener(this);

        viewModel.matchDeviceId(SPrefClient.getAstrologerDetail(getApplicationContext()).getId(), Settings.Secure.getString(getApplication().getContentResolver(), Settings.Secure.ANDROID_ID));

        viewModel.getTokenLiveData().observe(this, data -> {
        });

        getFirebaseToken();

        subscribeTopic();

        viewModel.getKidsKundliRequest(SPrefClient.getAstrologerDetail(getApplicationContext()).getId());

        Intent kidIntent = new Intent(getApplicationContext(), KidsKundliActivity.class);
        viewModel.getKundliRequestListLiveData().observe(this, data -> {
            binding.kidBadgeCount.setText(data.size() + "");
            binding.kidBadgeCount.setVisibility(View.VISIBLE);
        });
        viewModel.getKundliRequestLiveData().observe(this, data -> {
            kidIntent.putExtra("KID_DETAIL", data);
            binding.gotoKidKundli.setVisibility(View.VISIBLE);
            DialogQueryDetailBinding requestBinding = DialogQueryDetailBinding.inflate(getLayoutInflater());
            Dialog dialog = new Dialog(DashboardActivity.this);
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.setCancelable(false);
            dialog.setContentView(requestBinding.getRoot());
            requestBinding.name.setText(data.getFullName());
            requestBinding.fName.setText(data.getFatherName());
            requestBinding.mName.setText(data.getMotherName());
            try {
                Date birthTime = new SimpleDateFormat("kk:mm:ss").parse(data.getTob());
                Date birthDate = new SimpleDateFormat(AppConstants.SERVER_TIME_FORMAT).parse(data.getDob());
                assert birthTime != null;
                requestBinding.tob.setText(new SimpleDateFormat("hh:mm a").format(birthTime));
                assert birthDate != null;
                requestBinding.dob.setText(new SimpleDateFormat("dd/MM/yyyy").format(birthDate));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            requestBinding.pob.setText(data.getLocation().getCityName());
            requestBinding.later.setOnClickListener(v -> dialog.dismiss());
            requestBinding.confirm.setOnClickListener(v -> {
                Intent intent = new Intent(getApplicationContext(), KidsKundliActivity.class);
                intent.putExtra("KID_DETAIL", data);
                startActivity(intent);
            });
            dialog.show();
        });
        binding.gotoKidKundli.setOnClickListener(v -> {
            startActivity(kidIntent);
        });
    }


    private void setFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(binding.mainContainer.getId(), fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.bottom_nav_home) {
            setFragment(homeFragment);
            return true;
        } else if (item.getItemId() == R.id.bottom_nav_wallet) {
            setFragment(walletFragment);
            return true;
        } else if (item.getItemId() == R.id.bottom_nav_history) {
            setFragment(logFragment);
            return true;
        } else if (item.getItemId() == R.id.bottom_nav_profile) {
            setFragment(profileFragment);
            return true;
        } else if (item.getItemId() == R.id.side_nav_help) {
            startActivity(new Intent(getApplicationContext(), HelpSupportActivity.class));
        } else if (item.getItemId() == R.id.side_nav_documentation) {
            startActivity(new Intent(getApplicationContext(), DocumentationActivity.class));
        } else if (item.getItemId() == R.id.side_nav_setting) {
            startActivity(new Intent(getApplicationContext(), SettingActivity.class));
        } else if (item.getItemId() == R.id.side_nav_profile) {
            binding.bottomNav.setSelectedItemId(R.id.bottom_nav_profile);
        } else if (item.getItemId() == R.id.side_nav_reviews) {
            startActivity(new Intent(getApplicationContext(), ReviewsActivity.class));
        } /*else if (item.getItemId() == R.id.side_nav_logout) {
            SPrefClient.deleteAstrologerDetail(getApplicationContext());
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }*/ else if (item.getItemId() == R.id.side_nav_session_log) {
            startActivity(new Intent(getApplicationContext(), SessionLogActivity.class));
        }
        binding.drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.findFragmentById(binding.mainContainer.getId()) instanceof HomeFragment) {
            if (binding.drawer.isDrawerOpen(GravityCompat.END)) {
                binding.drawer.closeDrawer(GravityCompat.END);
            } else {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed();
                    finishAffinity();
                    finish();
                    return;
                }
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_LONG).show();

                new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
            }
        } else {
            binding.bottomNav.setSelectedItemId(R.id.bottom_nav_home);
        }
    }

    @Override
    public void callBackAction(String action) {
        if (action.equals(OPEN_DRAWER)) {
            binding.drawer.openDrawer(GravityCompat.START);
        }
    }

    private void subscribeTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic("testing").addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.i("TAG", "onComplete: Subscribed");
            } else {
                Log.i("TAG", "onComplete: Subscribe Failed");
            }
        });
    }

    private void getFirebaseToken() {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                viewModel.updateFirebaseToken(SPrefClient.getAstrologerDetail(getApplicationContext()).getId(), task.getResult());
            } else {
                Log.i("TAG", "onComplete: Unsuccessful");
            }
        });
    }
}
