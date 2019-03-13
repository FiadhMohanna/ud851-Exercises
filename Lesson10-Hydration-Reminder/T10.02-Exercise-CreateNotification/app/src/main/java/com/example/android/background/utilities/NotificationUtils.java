package com.example.android.background.utilities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import com.example.android.background.MainActivity;
import com.example.android.background.R;

/**
 * Utility class for creating hydration notifications
 */
public class NotificationUtils {
    private final int intentID = 2030;
    // TODO (7) Create a method called remindUserBecauseCharging which takes a Context.
    // This method will create a notification for charging. It might be helpful
    // to take a look at this guide to see an example of what the code in this method will look like:
    // https://developer.android.com/training/notify-user/build-notification.html
    public static void remindUserBecauseCharging(Context context){
        // TODO (8) Get the NotificationManager using context.getSystemService
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // TODO (9) Create a notification channel for Android O devices
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    2018,
                    context.getString(R.string.main_notification_channel_name),
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }
        // TODO (10) In the remindUserBecauseCharging method use NotificationCompat.Builder to create a notification
        // that:
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context,WATER_REMINDER_NOTIFICATION_CHANNEL_ID)
        // - has a color of R.color.colorPrimary - use ContextCompat.getColor to get a compatible color
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
        // - has ic_drink_notification as the small icon
                .setSmallIcon(R.drawable.ic_drink_notification)
        // - uses icon returned by the largeIcon helper method as the large icon
                .setLargeIcon(largeIcon(context))
        // - sets the title to the charging_reminder_notification_title String resource
                .setContentTitle(context.getString(R.string.charging_reminder_notification_title))
        // - sets the text to the charging_reminder_notification_body String resource
                .setContentText(context.getString(R.string.charging_reminder_notification_body))
        // - sets the style to NotificationCompat.BigTextStyle().bigText(text)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                        context.getString(R.string.charging_reminder_notification_body)))
        // - sets the notification defaults to vibrate
                .setDefaults(Notification.DEFAULT_VIBRATE)
        // - uses the content intent returned by the contentIntent helper method for the contentIntent
                .setContentIntent(contentIntent(context))
        // - automatically cancels the notification when the notification is clicked
                .setAutoCancel(true);

        // TODO (11) If the build version is greater than or equal to JELLY_BEAN and less than OREO,
        // set the notification's priority to PRIORITY_HIGH.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        // TODO (12) Trigger the notification by calling notify on the NotificationManager.
        // Pass in a unique ID of your choosing for the notification and notificationBuilder.build()
        notificationManager.notify(2020, notificationBuilder.build());
    }



    // TODO (1) Create a helper method called contentIntent with a single parameter for a Context. It
    // should return a PendingIntent. This method will create the pending intent which will trigger when
    // the notification is pressed. This pending intent should open up the MainActivity.
    public PendingIntent contentIntent(Context context) {
        // TODO (2) Create an intent that opens up the MainActivity
        Intent mainIntent = new Intent(context, MainActivity.class);
        // TODO (3) Create a PendingIntent using getActivity that:
            // - Take the context passed in as a parameter
            // - Takes an unique integer ID for the pending intent (you can create a constant for
            //   this integer above
            // - Takes the intent to open the MainActivity you just created; this is what is triggered
            //   when the notification is triggered
            // - Has the flag FLAG_UPDATE_CURRENT, so that if the intent is created again, keep the
            // intent but update the data

        PendingIntent pendingIntent = PendingIntent.getActivity(context, intentID, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    // TODO (4) Create a helper method called largeIcon which takes in a Context as a parameter and
    // returns a Bitmap. This method is necessary to decode a bitmap needed for the notification.
    public Bitmap largeIcon(Context context){
        // TODO (5) Get a Resources object from the context.
        Resources resources = context.getResources();
        // TODO (6) Create and return a bitmap using BitmapFactory.decodeResource, passing in the
        // resources object and R.drawable.ic_local_drink_black_24px
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_local_drink_black_24px);
        return bitmap;
    }


}
