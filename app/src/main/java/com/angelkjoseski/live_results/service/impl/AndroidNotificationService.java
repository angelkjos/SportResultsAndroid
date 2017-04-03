package com.angelkjoseski.live_results.service.impl;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.SparseBooleanArray;

import com.angelkjoseski.live_results.R;
import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.service.NotificationService;

import java.util.Locale;

import javax.inject.Inject;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Notification service for showing the standard Android notifications on the top.
 */
public class AndroidNotificationService implements NotificationService {

    private Context context;
    private SparseBooleanArray alreadyShownNotifications; // Im-Memory storage of shown notifications

    @Inject
    public AndroidNotificationService(Context context) {
        this.context = context;
        alreadyShownNotifications = new SparseBooleanArray();
    }

    @Override
    public void showNotificationForResult(Fixture fixture) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(fixture.getResult().getScoreString())
                        .setContentText(fixture.getTeamsString());
        // Sets an ID for the notification
        int mNotificationId = createNotificationId(fixture);
        if (alreadyShownNotifications.get(mNotificationId, false)) {
            return;
        }
        alreadyShownNotifications.put(mNotificationId, true);
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

    private int createNotificationId(Fixture fixture) {
        String fixtureString = String.format(Locale.getDefault(), "%d%s-%s",
                fixture.getFixtureId(), fixture.getTeamsString(), fixture.getResult().getScoreString());
        return fixtureString.hashCode();
    }
}
