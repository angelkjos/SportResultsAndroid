package com.angelkjoseski.live_results.features.live.interactor;

import com.angelkjoseski.live_results.features.common.interactor.InteractorTemplate;
import com.angelkjoseski.live_results.features.live.LiveResults;
import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.model.FixtureList;
import com.angelkjoseski.live_results.service.BackgroundResultsFetcher;
import com.angelkjoseski.live_results.service.networking.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Interactor with business-logic for 'Live Results' screen.
 */
public class LiveResultsInteractor extends InteractorTemplate<FixtureList> implements LiveResults.Interactor {

    private BackgroundResultsFetcher backgroundResultsFetcher;

    /**
     * Constructor for injecting REST API service and Retrofit instance.
     *
     * @param apiService implementation of REST API service
     * @param retrofit   singleton Retrofit instance
     */
    @Inject
    public LiveResultsInteractor(ApiService apiService, Retrofit retrofit, BackgroundResultsFetcher
            backgroundResultsFetcher) {
        super(apiService, retrofit);
        this.backgroundResultsFetcher = backgroundResultsFetcher;
    }

    @Override
    public Observable<List<Fixture>> getAllCurrentlyRunningFixturesForFavouriteTeams() {
        return backgroundResultsFetcher.startLiveResultsFetching(true);
    }
}
