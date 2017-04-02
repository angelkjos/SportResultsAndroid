package com.angelkjoseski.live_results.service.networking;

import com.angelkjoseski.live_results.model.FixtureList;
import com.angelkjoseski.live_results.model.TeamList;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Interface that defines REST API service.
 * Based on RxJava and returnes @{@link Observable}.
 */
public interface ApiService {

    /**
     * Api call to fetch all available @{@link com.angelkjoseski.live_results.model.Team}.
     * @return An Observable which can be subscribed to. Emits the result of @{@link TeamList}.
     */
    @GET("teams")
    Observable<TeamList> getAllTeams();

    /**
     * Api call to fetch all available @{@link com.angelkjoseski.live_results.model.Fixture}.
     * @return An Observable which can be subscribed to. Emits the result of @{@link FixtureList}.
     */
    @GET("fixtures")
    Observable<FixtureList> getAllFixtures();

}
