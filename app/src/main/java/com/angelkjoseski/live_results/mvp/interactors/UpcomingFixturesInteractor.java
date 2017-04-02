package com.angelkjoseski.live_results.mvp.interactors;

import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.model.FixtureList;
import com.angelkjoseski.live_results.mvp.UpcomingFixtures;
import com.angelkjoseski.live_results.networking.ApiService;

import java.util.ArrayList;
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
 * Interactor with business-logic for 'All fixtures' screen.
 */
public class UpcomingFixturesInteractor extends InteractorTemplate<FixtureList> implements UpcomingFixtures.Interactor {

    private static final long CONSTANT_ID = 12;

    /**
     * Constructor for injecting REST API service and Retrofit instance.
     *
     * @param apiService implementation of REST API service
     * @param retrofit   singleton Retrofit instance
     */
    @Inject
    public UpcomingFixturesInteractor(ApiService apiService, Retrofit retrofit) {
        super(apiService, retrofit);
    }

    @Override
    public Observable<List<Fixture>> getAllUpcomingFixtures() {
        return apiService
                .getAllFixtures()
                .subscribeOn(Schedulers.newThread())
                .map(new Function<FixtureList, List<Fixture>>() {
                    @Override
                    public List<Fixture> apply(FixtureList fixtureList) throws Exception {
                        return fixtureList.getFixtures();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Fixture>> getUpcomingFixturesForFavouriteTeams() {

        // TODO: create 'Favourites Service' to load these IDs.
        final List<Long> favouredTeamIds = new ArrayList<>();
        favouredTeamIds.add(CONSTANT_ID);

        if (favouredTeamIds == null || favouredTeamIds.size() == 0) {
            // No favourite teams
            return Observable.just(new ArrayList<Fixture>())
                    .map(new Function<ArrayList<Fixture>, List<Fixture>>() {
                        @Override
                        public List<Fixture> apply(ArrayList<Fixture> fixtures) throws Exception {
                            throw new IllegalArgumentException("No favourites teams yet.");
                        }
                    });
        }

        return apiService
                .getAllFixtures()
                .subscribeOn(Schedulers.newThread())
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
                                        return (favouredTeamIds.contains(fixture.getTeamIdAway())
                                                || favouredTeamIds.contains(fixture.getTeamIdHome()));
                                    }
                                });
                    }
                })
                .toList()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread());
    }
}
