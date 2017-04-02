package com.angelkjoseski.live_results.mvp;

import com.angelkjoseski.live_results.model.Fixture;

import java.util.List;

import io.reactivex.Observable;

/**
 * Model - View - Presenter interfaces for 'Upcoming Fixtures' screen.
 */
public interface UpcomingFixtures {

    /**
     * 'Upcoming Fixtures' Interactor interface.
     */
    interface Interactor {

        /**
         * Will fetch all upcoming fixtures.
         *
         * @return An Observable emitting a list of all fixtures which correspond to the conditions.
         */
        Observable<List<Fixture>> getAllUpcomingFixtures();

        /**
         * Will fetch all upcoming fixtures where a team is playing which is favoured.
         *
         * @return An Observable emitting a list of all fixtures which correspond to the conditions.
         */
        Observable<List<Fixture>> getUpcomingFixturesForFavouriteTeams();
    }

    /**
     * 'Upcoming Fixtures' View interface.
     */
    interface View extends SportResults.View {
        /**
         * Will show the given list of fixtures in a list-view.
         *
         * @param fixtures The list of fixtures to show.
         */
        void showFixtures(List<Fixture> fixtures);

        /**
         * Sets the indicator which shows if current fixtures are only for favoured teams or all.
         *
         * @param checked The indicator will be set to "Only favoured teams" if this is true.
         */
        void setOnlyFavouredTeamsIndicator(boolean checked);
    }

    /**
     * 'Upcoming Fixtures' Presenter interface.
     */
    interface Presenter {

        /**
         * Callback method to presenter when the checked state of the indicator for
         * 'showOnlyFavouredTeams Fixtures' is changed.
         *
         * @param checked The state of the indicator. True if only fixtures for favoured teams should be shown.
         */
        void indicatorOnlyFavouredTeamsChecked(boolean checked);

        void onCreated();
    }
}
