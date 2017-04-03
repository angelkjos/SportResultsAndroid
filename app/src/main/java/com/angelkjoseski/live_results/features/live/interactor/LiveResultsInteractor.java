package com.angelkjoseski.live_results.features.live.interactor;

import com.angelkjoseski.live_results.features.common.interactor.InteractorTemplate;
import com.angelkjoseski.live_results.features.live.LiveResults;
import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.model.FixtureList;
import com.angelkjoseski.live_results.model.Team;
import com.angelkjoseski.live_results.model.TeamList;
import com.angelkjoseski.live_results.service.FavouriteService;
import com.angelkjoseski.live_results.service.networking.ApiService;
import com.angelkjoseski.live_results.util.FixturesBasedOnFavouritesTransformer;

import java.util.Date;
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

        return apiService.getAllFixtures()
                .subscribeOn(Schedulers.io())
                .map(new Function<FixtureList, List<Fixture>>() {
                    @Override
                    public List<Fixture> apply(FixtureList fixtureList) throws Exception {
                        return fixtureList.getFixtures();
                    }
                })
                .compose(new FixturesBasedOnFavouritesTransformer(favouriteService))
                .filter(new Predicate<Fixture>() {
                    @Override
                    public boolean test(Fixture fixture) throws Exception {
                        return !fixture.getResult().isFinished();
                    }
                })
                .filter(new Predicate<Fixture>() {
                    @Override
                    public boolean test(Fixture fixture) throws Exception {
                        return new Date().after(fixture.getStartTime());
                    }
                })
                .toList()
                .flatMapObservable(new Function<List<Fixture>, ObservableSource<? extends List<Fixture>>>() {
                    @Override
                    public ObservableSource<? extends List<Fixture>> apply(final List<Fixture> fixtures) throws
                            Exception {
                        return apiService.getAllTeams()
                                .map(new Function<TeamList, List<Fixture>>() {
                                    @Override
                                    public List<Fixture> apply(TeamList teamList) throws Exception {
                                        for (Fixture fixture : fixtures) {
                                            for (Team team : teamList.getTeams()) {
                                                if (team.getTeamId() == fixture.getTeamIdHome()) {
                                                    fixture.setTeamHome(team);
                                                } else if (team.getTeamId() == fixture.getTeamIdAway()) {
                                                    fixture.setTeamAway(team);
                                                }
                                            }
                                        }
                                        return fixtures;
                                    }
                                });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
