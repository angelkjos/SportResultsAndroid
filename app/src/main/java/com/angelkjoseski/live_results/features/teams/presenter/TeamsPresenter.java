package com.angelkjoseski.live_results.features.teams.presenter;

import com.angelkjoseski.live_results.features.common.presenter.PresenterTemplate;
import com.angelkjoseski.live_results.model.ErrorResponse;
import com.angelkjoseski.live_results.model.Team;
import com.angelkjoseski.live_results.features.teams.Teams;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Presenter responsible for UI logic in 'MyTeams' view.
 */
public class TeamsPresenter extends PresenterTemplate<Teams.View, Teams.Interactor> implements Teams.Presenter {

    /**
     * Constructor for injecting View and Interactor.
     *
     * @param view       view reference
     * @param interactor interactor reference
     */
    @Inject
    public TeamsPresenter(Teams.View view, Teams.Interactor interactor) {
        super(view, interactor);
    }

    @Override
    public void onCreated() {
        loadAllTeams();
        view.showMessage(null, "Long-Click a team to add to favourites.");
    }

    @Override
    public void onTeamFavoriteClicked(Team team) {
        interactor.addTeamToFavourites(team);
        view.showMessage("Favourite", "Team was added to favourites.");
        loadAllTeams();
    }

    private void loadAllTeams() {
        interactor.getAllTeams().subscribe(new Observer<List<Team>>() {
            @Override
            public void onSubscribe(Disposable d) {
                view.showLoading(true);
            }

            @Override
            public void onNext(List<Team> value) {
                view.showLoading(false);
                view.showAllTeams(value);
            }

            @Override
            public void onError(Throwable e) {
                TeamsPresenter.this.onError(new ErrorResponse());
            }

            @Override
            public void onComplete() {
                // nothing
            }
        });
    }
}
