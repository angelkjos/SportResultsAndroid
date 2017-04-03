package com.angelkjoseski.live_results.util;

import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.model.Team;
import com.angelkjoseski.live_results.service.FavouriteService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * RxJava stream transformer to filer a list of @{@link Fixture} based on which teams are favourites.
 */
public class FixturesBasedOnFavouritesTransformer implements ObservableTransformer<List<Fixture>, Fixture> {

  private final FavouriteService favouriteService;

  public FixturesBasedOnFavouritesTransformer(FavouriteService favouriteService) {
    this.favouriteService = favouriteService;
  }

  @Override
  public ObservableSource<Fixture> apply(Observable<List<Fixture>> upstream) {
    return upstream
            .flatMap(new Function<List<Fixture>, ObservableSource<Fixture>>() {
              @Override
              public ObservableSource<Fixture> apply(List<Fixture> fixtures) throws Exception {
                return Observable.fromIterable(fixtures);
              }
            })
            .flatMap(new Function<Fixture, ObservableSource<Fixture>>() {
              @Override
              public ObservableSource<Fixture> apply(final Fixture fixture) throws Exception {
                return favouriteService.getFavouriteTeams().flatMap(new Function<List<Team>, ObservableSource<Fixture>>() {
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
            });
  }
}
