package com.astrocure.astrologer.service;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.models.requestModels.UpdateTokenRequestModel;
import com.astrocure.astrologer.models.responseModels.UpdateTokenResponseModel;
import com.astrocure.astrologer.network.RetrofitClient;
import com.astrocure.astrologer.ui.ChatActivity;
import com.astrocure.astrologer.ui.ChatRequestActivity;
import com.astrocure.astrologer.utils.SPrefClient;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppFirebaseMessagingService extends FirebaseMessagingService {
    public static String CHANNEL_ID = "111";
    //    private static final String CHANNEL_ID = "my_channel";
    public static final String FULL_SCREEN_ACTION = "full_screen_action";
    public static final int NOTIFICATION_ID = 101;

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        sendRegistrationToServer(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

//        createNotification(Objects.requireNonNull(message.getNotification()).getTitle(), message.getNotification().getBody());
        createFullScreenNotification(getApplicationContext(), message.getNotification().getTitle(), message.getNotification().getBody());
    }

    private void createNotification(String title, String body) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "name", NotificationManager.IMPORTANCE_HIGH);

            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, PendingIntent.FLAG_IMMUTABLE);
            Notification notification = new Notification.Builder(getApplicationContext(), CHANNEL_ID)
                    .setContentText(body)
                    .setContentTitle(title)
                    .setContentIntent(pendingIntent)
                    .addAction(android.R.drawable.sym_action_chat, "Accept", pendingIntent)
                    .addAction(android.R.drawable.sym_action_chat, "Reject", PendingIntent.getActivity(getApplicationContext(), 1, new Intent(), PendingIntent.FLAG_IMMUTABLE))
                    .setChannelId(CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_app_logo)
                    .setFullScreenIntent(pendingIntent, true)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
            notificationManager.notify(1, notification);
        }


    }

    private void sendRegistrationToServer(String token) {
        RetrofitClient.getAppClient().updateFirebaseToken(new UpdateTokenRequestModel(SPrefClient.getAstrologerDetail(getApplicationContext()).getId(), token)).enqueue(new Callback<UpdateTokenResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<UpdateTokenResponseModel> call, @NonNull Response<UpdateTokenResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        Log.i("TAG", "onResponse: Firebase token updated successfully");
                    }
                } catch (Exception e) {
                    Log.e("TAG", "onResponse: ", e);
                }
            }

            @Override
            public void onFailure(@NonNull Call<UpdateTokenResponseModel> call, @NonNull Throwable t) {

            }
        });
    }

    private void createFullScreenNotification(Context context, String title, String body) {
        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(context,111,new Intent(context, ChatRequestActivity.class),PendingIntent.FLAG_UPDATE_CURRENT| PendingIntent.FLAG_IMMUTABLE);

        Intent intentAccept = new Intent(context, ChatActivity.class);
        PendingIntent aPendingIntent = PendingIntent.getActivity(context, 0, intentAccept, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        Intent intentReject = new Intent();
        PendingIntent rPendingIntent = PendingIntent.getActivity(context, 0, intentReject, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build();
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            NotificationChannel channel = new NotificationChannel("1", "Notification", importance);
//            channel.setDescription(body);
//            channel.setShowBadge(true);
//            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
//            channel.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.message_ringtone), audioAttributes);
//            Notification notification =new Notification.Builder(context)
//                    .setFullScreenIntent(fullScreenPendingIntent,true)
//                    .build();
//            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//            notificationManager.notify(1,notification);

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "name", NotificationManager.IMPORTANCE_HIGH);
            Notification notification = new Notification.Builder(getApplicationContext(), CHANNEL_ID)
                    .setContentText(body)
                    .setContentTitle(title)
                    .setContentIntent(fullScreenPendingIntent)
                    .addAction(android.R.drawable.sym_action_chat, "Accept", aPendingIntent)
                    .addAction(android.R.drawable.sym_action_chat, "Reject", rPendingIntent)
                    .setChannelId(CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_app_logo)
                    .setFullScreenIntent(fullScreenPendingIntent, true)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
            notificationManager.notify(1, notification);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), "1");
        builder.setSmallIcon(R.drawable.ic_app_logo);
        builder.setContentTitle(title);
        builder.setContentText(body);
        builder.setAutoCancel(true);
        builder.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.message_ringtone));
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setFullScreenIntent(fullScreenPendingIntent,true);
        builder.addAction(R.drawable.ic_app_logo, "Accept", aPendingIntent);
        builder.addAction(R.drawable.ic_app_logo, "Reject", rPendingIntent);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        managerCompat.notify(101, builder.build());
    }
}