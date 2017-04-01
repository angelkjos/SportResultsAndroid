package com.angelkjoseski.live_results.mvp;

import com.angelkjoseski.live_results.model.Fixture;

import java.util.List;

import io.reactivex.Observable;

/**
 * Model - View - Presenter interfaces for 'Live Results' screen.
 */
public interface LiveResults {

    /**
     * 'Live Results' Interactor interface.
     */
    interface Interactor {
        Observable<List<Fixture>> getAllCurrentlyRunningFixturesForFavouriteTeams();
    }

    /**
     * 'Live Results' View interface.
     */
    interface View extends SportResults.View {
        void showLiveResults(List<Fixture> fixtures);
    }

    /**
     * 'Live Results' Presenter interface.
     */
    interface Presenter {
        void onCreated();
    }
}
