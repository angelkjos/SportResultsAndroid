package com.angelkjoseski.live_results.util.rx_transformers;

import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.model.Team;
import com.angelkjoseski.live_results.model.TeamList;
import com.angelkjoseski.live_results.service.networking.ApiService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

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
      return upstream
        .flatMap(new Function<List<Fixture>, ObservableSource<List<Fixture>>>() {
          @Override
          public ObservableSource<List<Fixture>> apply(final List<Fixture> fixtures) throws Exception {
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
        });
  }
}
