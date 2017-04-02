package com.angelkjoseski.live_results.features.teams;

import com.angelkjoseski.live_results.features.common.SportResults;
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

        /**
         * Will save a team to the list of the users favourite teams.
         *
         * @param team The team to save.
         */
        void addTeamToFavourites(Team team);
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

        /**
         * Should be called when the user has favoured a team by clicking on a button
         * or doing an action. Will save the team as the users favorite.
         *
         * @param team The team which was favoured.
         */
        void onTeamFavoriteClicked(Team team);
    }
}
