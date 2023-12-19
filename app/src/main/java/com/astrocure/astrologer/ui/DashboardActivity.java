package com.astrocure.astrologer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.callback.SideNavigationCallback;
import com.astrocure.astrologer.databinding.ActivityDashboardBinding;
import com.astrocure.astrologer.ui.fragment.CallChatLogFragment;
import com.astrocure.astrologer.ui.fragment.EarningFragment;
import com.astrocure.astrologer.ui.fragment.HomeFragment;
import com.astrocure.astrologer.ui.fragment.ProfileFragment;
import com.astrocure.astrologer.utils.SPrefClient;
import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SideNavigationCallback {
    ActivityDashboardBinding binding;
    boolean doubleBackToExitPressedOnce = false;
    HomeFragment homeFragment;
    EarningFragment walletFragment;
    CallChatLogFragment logFragment;
    ProfileFragment profileFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        homeFragment = new HomeFragment();
        walletFragment = new EarningFragment();
        logFragment = new CallChatLogFragment();
        profileFragment = new ProfileFragment();
        setFragment(homeFragment);

        binding.bottomNav.setItemIconTintList(null);
        binding.bottomNav.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
        binding.sideNav.setNavigationItemSelectedListener(this::onNavigationItemSelected);

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
        } else if (item.getItemId() == R.id.side_nav_logout) {
            SPrefClient.deleteAstrologerDetail(getApplicationContext());
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
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
}
