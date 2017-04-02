package com.angelkjoseski.live_results.features.teams.interactor;

import com.angelkjoseski.live_results.features.common.interactor.InteractorTemplate;
import com.angelkjoseski.live_results.model.Team;
import com.angelkjoseski.live_results.model.TeamList;
import com.angelkjoseski.live_results.features.teams.Teams;
import com.angelkjoseski.live_results.service.networking.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Interactor responsible for business-logic in loading all teams.
 */
public class TeamsInteractor extends InteractorTemplate<TeamList> implements Teams.Interactor {

    /**
     * Constructor for injecting REST API service and Retrofit instance.
     *
     * @param apiService implementation of REST API service
     * @param retrofit   singleton Retrofit instance
     */
    @Inject
    public TeamsInteractor(ApiService apiService, Retrofit retrofit) {
        super(apiService, retrofit);
    }


    @Override
    public Observable<List<Team>> getAllTeams() {
        return apiService.getAllTeams()
                .subscribeOn(Schedulers.newThread())
                .map(new Function<TeamList, List<Team>>() {
                    @Override
                    public List<Team> apply(TeamList teamList) throws Exception {
                        return teamList.getTeams();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
