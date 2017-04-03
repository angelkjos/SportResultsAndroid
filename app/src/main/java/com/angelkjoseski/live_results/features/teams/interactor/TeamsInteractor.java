package com.angelkjoseski.live_results.features.teams.interactor;

import android.content.Context;

import com.angelkjoseski.live_results.features.common.interactor.InteractorTemplate;
import com.angelkjoseski.live_results.features.teams.Teams;
import com.angelkjoseski.live_results.model.Team;
import com.angelkjoseski.live_results.model.TeamList;
import com.angelkjoseski.live_results.service.FavouriteService;
import com.angelkjoseski.live_results.service.networking.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Interactor responsible for business-logic in loading all teams.
 */
public class TeamsInteractor extends InteractorTemplate<TeamList> implements Teams.Interactor {

    private FavouriteService favouriteService;
    private Context context;

    /**
     * Constructor for injecting REST API service and Retrofit instance.
     *
     * @param apiService implementation of REST API service
     * @param retrofit   singleton Retrofit instance
     */
    @Inject
    public TeamsInteractor(ApiService apiService, Retrofit retrofit, FavouriteService favouriteService, Context
            context) {
        super(apiService, retrofit);
        this.favouriteService = favouriteService;
        this.context = context;
    }


    @Override
    public Observable<List<Team>> getAllTeams() {
        return apiService.getAllTeams()
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<TeamList, ObservableSource<Team>>() {
                    @Override
                    public ObservableSource<Team> apply(TeamList teamList) throws Exception {
                        return Observable.fromIterable(teamList.getTeams());
                    }
                })
                .flatMap(new Function<Team, ObservableSource<Team>>() {
                    @Override
                    public ObservableSource<Team> apply(final Team team) throws Exception {
                        return favouriteService.getFavouriteTeams()
                                .flatMap(new Function<List<Team>, ObservableSource<Team>>() {
                                    @Override
                                    public ObservableSource<Team> apply(List<Team> favouriteTeams) throws Exception {
                                        team.setFavourite(favouriteTeams.contains(team));
                                        return Observable.just(team);
                                    }
                                });
                    }
                })
                .toList()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void addTeamToFavourites(Team team) {
        favouriteService.storeFavourite(team);
    }
}
