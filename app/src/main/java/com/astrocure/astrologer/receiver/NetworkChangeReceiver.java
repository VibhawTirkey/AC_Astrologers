package com.astrocure.astrologer.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (checkInternet(context)) {
//                Toast.makeText(context, "Internet Connection Revived.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    boolean checkInternet(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}
