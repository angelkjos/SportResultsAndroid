package com.angelkjoseski.live_results.features.live;

import com.angelkjoseski.live_results.features.common.SportResults;
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

        /**
         * Will fetch the fixtures in which the given team is playing and which are currently running.
         * @return An Observable emitting a list of all fixtures which correspond to the conditions.
         */
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
