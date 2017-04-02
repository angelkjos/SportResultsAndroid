package com.angelkjoseski.live_results.features.upcomingfixtures.interactor;

import com.angelkjoseski.live_results.features.common.interactor.InteractorTemplate;
import com.angelkjoseski.live_results.features.upcomingfixtures.UpcomingFixtures;
import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.model.FixtureList;
import com.angelkjoseski.live_results.model.Team;
import com.angelkjoseski.live_results.model.TeamList;
import com.angelkjoseski.live_results.service.FavouriteService;
import com.angelkjoseski.live_results.service.networking.ApiService;

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
 * Interactor with business-logic for 'All fixtures' screen.
 */
public class UpcomingFixturesInteractor extends InteractorTemplate<FixtureList> implements UpcomingFixtures.Interactor {

    private final FavouriteService favouriteService;

    /**
     * Constructor for injecting REST API service and Retrofit instance.
     *
     * @param apiService implementation of REST API service
     * @param retrofit   singleton Retrofit instance
     */
    @Inject
    public UpcomingFixturesInteractor(ApiService apiService, Retrofit retrofit, FavouriteService favouriteService) {
        super(apiService, retrofit);
        this.favouriteService = favouriteService;
    }

    @Override
    public Observable<List<Fixture>> getAllUpcomingFixtures() {
        return apiService.getAllFixtures()
                .subscribeOn(Schedulers.io())
                .map(new Function<FixtureList, List<Fixture>>() {
                    @Override
                    public List<Fixture> apply(FixtureList fixtureList) throws Exception {
                        return  fixtureList.getFixtures();
                    }
                })
                .flatMap(new Function<List<Fixture>, ObservableSource<Fixture>>() {
                    @Override
                    public ObservableSource<Fixture> apply(List<Fixture> fixtures) throws Exception {
                        return Observable.fromIterable(fixtures);
                    }
                })
                .filter(new Predicate<Fixture>() {
                    @Override
                    public boolean test(Fixture fixture) throws Exception {
                        return new Date().before(fixture.getStartTime());
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

    @Override
    public Observable<List<Fixture>> getUpcomingFixturesForFavouriteTeams() {
        return apiService.getAllFixtures()
                .subscribeOn(Schedulers.io())
                .map(new Function<FixtureList, List<Fixture>>() {
                    @Override
                    public List<Fixture> apply(FixtureList fixtureList) throws Exception {
                        return  fixtureList.getFixtures();
                    }
                })
                .flatMap(new Function<List<Fixture>, ObservableSource<Fixture>>() {
                    @Override
                    public ObservableSource<Fixture> apply(List<Fixture> fixtures) throws Exception {
                        return Observable.fromIterable(fixtures);
                    }
                })
                .flatMap(new Function<Fixture, ObservableSource<Fixture>>() {
                    @Override
                    public ObservableSource<Fixture> apply(final Fixture fixture) throws Exception {
                        return favouriteService.getFavouriteTeams()
                                .flatMap(new Function<List<Team>, ObservableSource<Fixture>>() {
                                    @Override
                                    public ObservableSource<Fixture> apply(List<Team> favouriteTeams) throws Exception {
                                        Team tempTeamAway = new Team(fixture.getTeamIdAway());
                                        Team tempTeamHome = new Team(fixture.getTeamIdHome());
                                        if (favouriteTeams.contains(tempTeamAway) || favouriteTeams.contains(tempTeamHome)) {
                                            return Observable.just(fixture);
                                        } else {
                                            return Observable.just(new Fixture());
                                        }
                                    }
                                });
                    }
                })
                .filter(new Predicate<Fixture>() {
                    @Override
                    public boolean test(Fixture fixture) throws Exception {
                        return fixture.getFixtureId() > 0;
                    }
                })
                .filter(new Predicate<Fixture>() {
                    @Override
                    public boolean test(Fixture fixture) throws Exception {
                        return new Date().before(fixture.getStartTime());
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
