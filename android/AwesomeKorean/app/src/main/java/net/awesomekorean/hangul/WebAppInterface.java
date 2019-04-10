package net.awesomekorean.hangul;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.webkit.JavascriptInterface;

import java.util.Calendar;

public class WebAppInterface {
    Context mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    @JavascriptInterface
    public void showNotification(String notificationTitle, String notificationContent) {

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);

        // Create the actual notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext, MainActivity.CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_menu_compass)
                .setContentTitle(notificationTitle)
                .setContentText(notificationContent)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        // notificationId is a unique int for each notification that you must define
        Integer notificationId = 0;
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
        notificationManager.notify(notificationId, mBuilder.build());
    }

    @JavascriptInterface
    public void scheduleNotification(int hour, int minute) {

        // Example of setting an alarm 1 minute from now
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTimeInMillis(System.currentTimeMillis());
        // this a 24 hour clock
        // 2 in afternoon == 14
        //calendar.set(Calendar.HOUR, hour);
        //calendar.set(Calendar.MINUTE, minute);

        NotificationAlarm notificationAlarm = new NotificationAlarm();
        Notification notification = notificationAlarm.createNotification(mContext,
                "Awesome Korean Time",
                "Don't forget to study Korean!");

        notificationAlarm.scheduleNotification(mContext, notification, hour, minute );

    }

}
