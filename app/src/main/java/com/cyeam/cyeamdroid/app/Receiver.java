package com.cyeam.cyeamdroid.app;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.cyeam.cyeamdroid.wallpaper.Bing;

public class Receiver extends BroadcastReceiver {
    public static final int WALLPAPER_NOTIFICATION = 0;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public static void onText(int type, String notification_title, String title, String text) {
        NotificationManager mNotificationMgr = (NotificationManager) CyeamApplication
                .getInstance().getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = new Notification(R.drawable.ic_launcher, notification_title,
                System.currentTimeMillis());
        notification.defaults = Notification.DEFAULT_ALL;

        Intent i = new Intent(CyeamApplication.getInstance(),
                MainActivity.class);

        PendingIntent contentIntent = PendingIntent.getActivity(
                CyeamApplication.getInstance(), R.string.app_name, i, 0);


        notification.setLatestEventInfo(CyeamApplication.getInstance(), title,
                text, contentIntent);
        mNotificationMgr.notify(type, notification);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bing.SetWallpaper();
    }
}
