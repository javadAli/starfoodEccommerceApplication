package com.example.starfood;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import com.example.starfood.ui.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Check if the message contains a data payload
        if (remoteMessage.getData().size() > 0) {
            // Safely retrieve data payload
            String dataBody = remoteMessage.getData().get("data_body");
            String dataTitle = remoteMessage.getData().get("data_title");

            // Save the message data if it exists
            if (dataBody != null && dataTitle != null) {
                saveMessageData(dataBody, dataTitle);
            }
        }

        // Check if the message contains a notification payload
        if (remoteMessage.getNotification() != null) {
            String notificationBody = remoteMessage.getNotification().getBody();
            String notificationTitle = remoteMessage.getNotification().getTitle();

            // Safely send notifications only if the title and body are not null
            if (notificationBody != null && notificationTitle != null) {
                sendNotification(notificationBody);
                sendNotification(notificationTitle, notificationBody);
            }
        }
    }

    public void  sendNotification(String from,String body){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    private void saveMessageData(String messageData,String messageTitle) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (messageData != null) {
            editor.putString("message_data", messageData);
        }

        if (messageTitle != null) {
            editor.putString("message_title", messageTitle);
        }

        editor.apply();
    }

    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("body",messageBody);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
        String channelId = "fcm_default_channel";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.starfood)
                        .setContentTitle("استارفود")
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}