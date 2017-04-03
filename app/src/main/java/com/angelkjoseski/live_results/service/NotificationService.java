package com.angelkjoseski.live_results.service;

import com.angelkjoseski.live_results.model.Fixture;

/**
 * Interface for handling all Notification related actions.
 * Should be implemented by any class which will handle all notification logic.
 */
public interface NotificationService {

    /**
     * Will show a notification for the result of the given fixture.
     * @param fixture The fixture whos result should be shown in a notification.
     */
    void showNotificationForResult(Fixture fixture);

}
