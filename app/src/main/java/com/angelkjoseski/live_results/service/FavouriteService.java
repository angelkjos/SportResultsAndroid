package com.angelkjoseski.live_results.service;

import com.angelkjoseski.live_results.model.Team;

import java.util.List;

import io.reactivex.Observable;

/**
 * Service for handling favourite teams.
 * Storing and retrieving favorites for current user.
 */
public interface FavouriteService {

    /**
     * Fetch all favourite teams.
     * @return An Observable which emits a list of the users favourite teams.
     */
    Observable<List<Team>> getFavouriteTeams();

    /**
     * Will save the provided team as a favourite for the current user.
     * @param team
     */
    void storeFavourite(Team team);

}
