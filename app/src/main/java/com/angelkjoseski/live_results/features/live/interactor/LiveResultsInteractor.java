package com.angelkjoseski.live_results.features.live.interactor;

import com.angelkjoseski.live_results.features.common.interactor.InteractorTemplate;
import com.angelkjoseski.live_results.features.live.LiveResults;
import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.model.FixtureList;
import com.angelkjoseski.live_results.model.Team;
import com.angelkjoseski.live_results.service.FavouriteService;
import com.angelkjoseski.live_results.service.networking.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Interactor with business-logic for 'Live Results' screen.
 */
public class LiveResultsInteractor extends InteractorTemplate<FixtureList> implements LiveResults.Interactor {

    private FavouriteService favouriteService;

    /**
     * Constructor for injecting REST API service and Retrofit instance.
     *
     * @param apiService implementation of REST API service
     * @param retrofit   singleton Retrofit instance
     */
    @Inject
    public LiveResultsInteractor(ApiService apiService, Retrofit retrofit, FavouriteService favouriteService) {
        super(apiService, retrofit);
        this.favouriteService = favouriteService;
    }

    @Override
    public Observable<List<Fixture>> getAllCurrentlyRunningFixturesForFavouriteTeams() {
        return favouriteService.getFavouriteTeams()
                .subscribeOn(Schedulers.newThread())
                .concatMap(new Function<List<Team>, ObservableSource<? extends List<Fixture>>>() {
                    @Override
                    public ObservableSource<? extends List<Fixture>> apply(final List<Team> teams) throws Exception {
                        return apiService.getAllFixtures()
                                .map(new Function<FixtureList, List<Fixture>>() {
                                    @Override
                                    public List<Fixture> apply(FixtureList fixtureList) throws Exception {
                                        return fixtureList.getFixtures();
                                    }
                                })
                                .flatMap(new Function<List<Fixture>, ObservableSource<Fixture>>() {
                                    @Override
                                    public ObservableSource<Fixture> apply(List<Fixture> fixtures) throws Exception {
                                        return Observable
                                                .fromIterable(fixtures)
                                                .filter(new Predicate<Fixture>() {
                                                    @Override
                                                    public boolean test(Fixture fixture) throws Exception {
                                                        Team tempTeamAway = new Team(fixture.getTeamIdAway());
                                                        Team tempTeamHome = new Team(fixture.getTeamIdHome());
                                                        return (teams.contains(tempTeamAway)
                                                                || teams.contains(tempTeamHome))
                                                                && !fixture.getResult().isFinished();
                                                    }
                                                });
                                    }
                                })
                                .toList()
                                .toObservable();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
