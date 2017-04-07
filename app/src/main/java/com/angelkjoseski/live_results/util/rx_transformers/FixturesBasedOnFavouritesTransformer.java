package com.angelkjoseski.live_results.util.rx_transformers;

import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.model.Team;
import com.angelkjoseski.live_results.service.FavouriteService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

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
                .flatMap(fixtures -> Observable.fromIterable(fixtures))
                .flatMap(fixture -> filterFixtureWithNoFavourite(fixture));
    }

    private ObservableSource<Fixture> filterFixtureWithNoFavourite(Fixture fixture) {
        return favouriteService.getFavouriteTeams()
          .flatMap(favouriteTeams -> {
              Team tempTeamAway = new Team(fixture.getTeamIdAway());
              Team tempTeamHome = new Team(fixture.getTeamIdHome());
              if (favouriteTeams.contains(tempTeamAway) || favouriteTeams.contains(tempTeamHome)) {
                  return Observable.just(fixture);
              } else {
                  return Observable.just(new Fixture());
              }
          })
          .filter(filteredFixture -> filteredFixture.getFixtureId() > 0);
    }
}
