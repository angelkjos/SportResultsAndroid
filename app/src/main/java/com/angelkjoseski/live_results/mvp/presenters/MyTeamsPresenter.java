package com.angelkjoseski.live_results.mvp.presenters;

import com.angelkjoseski.live_results.model.ErrorResponse;
import com.angelkjoseski.live_results.model.Team;
import com.angelkjoseski.live_results.mvp.MyTeams;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Presenter responsible for UI logic in 'MyTeams' view.
 */
public class MyTeamsPresenter extends PresenterTemplate<MyTeams.View, MyTeams.Interactor> implements MyTeams.Presenter {

    /**
     * Constructor for injecting View and Interactor.
     *
     * @param view       view reference
     * @param interactor interactor reference
     */
    @Inject
    public MyTeamsPresenter(MyTeams.View view, MyTeams.Interactor interactor) {
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
                MyTeamsPresenter.this.onError(new ErrorResponse());
            }

            @Override
            public void onComplete() {
                // nothing
            }
        });
    }
}
