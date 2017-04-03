package com.angelkjoseski.live_results.service;

import com.angelkjoseski.live_results.model.Fixture;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface for class which handles fetching of results in background.
 */
public interface BackgroundResultsFetcher {

    /**
     * Will start fetching of live results for fixtures where
     * a favourite team is playing. If there are currently live matches, notifications
     * for the results will be shown, and the fetching will be rescheduled.
     *
     * If there are no live matches for favourite teams, then a job will be scheduled to start
     * the fetching of live results when the first upcoming fixture is.
     *
     * @return an Observable emiting live results.
     */
    Observable<List<Fixture>> startLiveResultsFetching(boolean repeat);

    /**
     * Will schedule a background job which will run periodically and check for results.
     */
    void scheduleBackgroundJob();

}
