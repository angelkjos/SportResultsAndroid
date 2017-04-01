package com.angelkjoseski.live_results.networking;

import com.angelkjoseski.live_results.model.TeamList;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Interface that defines REST API service.
 * Based on RxJava and returnes @{@link Observable}.
 */
public interface ApiService {

    @GET("teams")
    Observable<TeamList> getAllTeams();

}
