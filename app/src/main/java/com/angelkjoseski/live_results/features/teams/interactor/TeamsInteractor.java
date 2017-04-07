package com.angelkjoseski.live_results.features.teams.interactor;

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
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Interactor responsible for business-logic in loading all teams.
 */
public class TeamsInteractor extends InteractorTemplate<TeamList> implements Teams.Interactor {

    private FavouriteService favouriteService;

    /**
     * Constructor for injecting REST API service and Retrofit instance.
     *
     * @param apiService implementation of REST API service
     * @param retrofit   singleton Retrofit instance
     */
    @Inject
    public TeamsInteractor(ApiService apiService, Retrofit retrofit, FavouriteService favouriteService) {
        super(apiService, retrofit);
        this.favouriteService = favouriteService;
    }


    @Override
    public Observable<List<Team>> getAllTeams() {
        return apiService.getAllTeams()
                .subscribeOn(Schedulers.io())
                .flatMap(teamList -> Observable.fromIterable(teamList.getTeams()))
                .flatMap(team -> setFavouriteFlagToTeam(team))
                .toList()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread());
    }

  private ObservableSource<Team> setFavouriteFlagToTeam(final Team team) {
      return favouriteService.getFavouriteTeams()
                  .flatMap(favouriteTeams -> {
                          team.setFavourite(favouriteTeams.contains(team));
                          return Observable.just(team);
                      }
                  );
  }

  @Override
    public void addTeamToFavourites(Team team) {
        favouriteService.storeFavourite(team);
    }
}
