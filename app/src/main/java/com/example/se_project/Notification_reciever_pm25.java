package com.example.se_project;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class Notification_reciever_pm25 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("PM25 receiver", "On");
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default",
                    "YOUR_CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DISCRIPTION");
            mNotificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "default")
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle("Alert PM2.5") // title for notification
                .setContentText("PM2.5 Alert from Xiangangster")// message for notification
                 // set alarm sound for notification
                .setAutoCancel(true); // clear notification after click
        Intent intent2 = new Intent(context, Repeating_activity_pm25.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
    }
}