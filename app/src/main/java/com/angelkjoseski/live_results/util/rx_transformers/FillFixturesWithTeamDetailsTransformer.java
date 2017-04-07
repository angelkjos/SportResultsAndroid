package com.angelkjoseski.live_results.util.rx_transformers;

import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.model.Team;
import com.angelkjoseski.live_results.service.networking.ApiService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

/**
 * RxJava stream transformer to filer a list of @{@link Fixture} based on which teams are favourites.
 */
public class FillFixturesWithTeamDetailsTransformer implements ObservableTransformer<List<Fixture>, List<Fixture>> {

    private ApiService apiService;

    public FillFixturesWithTeamDetailsTransformer(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public ObservableSource<List<Fixture>> apply(Observable<List<Fixture>> upstream) {
        return upstream.flatMap(this::addTeamDetailsToFixtures);
    }

    private ObservableSource<List<Fixture>> addTeamDetailsToFixtures(List<Fixture> fixtures) {
        return apiService.getAllTeams()
                .map(teamList -> {
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
                });
    }
}
