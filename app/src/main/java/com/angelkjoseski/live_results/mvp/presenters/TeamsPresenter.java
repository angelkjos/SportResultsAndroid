package com.angelkjoseski.live_results.mvp.presenters;

import com.angelkjoseski.live_results.model.ErrorResponse;
import com.angelkjoseski.live_results.model.Team;
import com.angelkjoseski.live_results.mvp.Teams;

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
    }

    private void loadAllTeams() {
        view.showLoading(true);
        interactor.getAllTeams().subscribe(new Observer<List<Team>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Team> value) {
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
