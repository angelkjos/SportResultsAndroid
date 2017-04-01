package com.angelkjoseski.live_results.mvp;

import com.angelkjoseski.live_results.model.Team;

import java.util.List;

import io.reactivex.Observable;

/**
 * Model - View - Presenter interfaces for 'Teams' screen.
 */
public interface Teams {


    /**
     * Teams Interactor interface.
     */
    interface Interactor {
        Observable<List<Team>> getAllTeams();
        void cancel();
    }

    /**
     * Teams View interface.
     */
    interface View extends SportResults.View {
        void showAllTeams(List<Team> teams);
    }

    /**
     * Teams Presenter interface.
     */
    interface Presenter {
        void onCreated();
    }
}
