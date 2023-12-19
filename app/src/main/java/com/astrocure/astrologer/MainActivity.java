package com.astrocure.astrologer;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.astrocure.astrologer.receiver.NetworkChangeReceiver;
import com.astrocure.astrologer.ui.DashboardActivity;
import com.astrocure.astrologer.ui.LoginActivity;
import com.astrocure.astrologer.utils.SPrefClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);



        if (SPrefClient.getAstrologerDetail(getApplicationContext())!=null){
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            finish();
        }else {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}