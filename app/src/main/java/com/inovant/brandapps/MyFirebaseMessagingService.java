package com.inovant.brandapps;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.firebase.messaging.CommonNotificationBuilder;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.nio.channels.Channel;

import androidx.core.app.NotificationCompat;

public class MyFirebaseMessagingService extends FirebaseMessagingService   {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("TAG", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("TAG", "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
               // scheduleJob();
            } else {
                // Handle message within 10 seconds
               // handleNow();
            }

        }if (remoteMessage.getNotification() != null) {
            Log.d("TAG", "Message Notification Body: " + remoteMessage.getNotification().getBody());
            Log.d("TAG", "Message Notification Title: " + remoteMessage.getNotification().getTitle());
            String notificationData=remoteMessage.getNotification().getBody();
            String notificationTitle=remoteMessage.getNotification().getTitle();
            notificationShow(notificationTitle,notificationData);
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    public void notificationShow(String notificationData,String notificationTitle){
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MyFirebaseMessagingService.this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(notificationTitle)
                .setContentText(notificationData)
                .setAutoCancel(true);
        android.app.NotificationManager notificationManager =
                (android.app.NotificationManager) MyFirebaseMessagingService.this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}
