package com.angelkjoseski.live_results.service.impl;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.angelkjoseski.live_results.R;
import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.service.NotificationService;

import javax.inject.Inject;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Notification service for showing the standard Android notifications on the top.
 */
public class AndroidNotificationService implements NotificationService {

    private Context context;

    @Inject
    public AndroidNotificationService(Context context) {
        this.context = context;
    }

    @Override
    public void showNotificationForResult(Fixture fixture) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(fixture.getResult().getScoreString())
                        .setContentText(fixture.getTeamsString());
        // Sets an ID for the notification
        int mNotificationId = (int) fixture.getFixtureId();
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
