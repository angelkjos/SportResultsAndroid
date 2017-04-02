package com.angelkjoseski.live_results.mvp.presenters;

import com.angelkjoseski.live_results.model.ErrorResponse;
import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.mvp.LiveResults;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Presenter responsible for UI logic of 'Live Results' screen.
 */

public class LiveResultsPresenter extends PresenterTemplate<LiveResults.View, LiveResults.Interactor> implements
        LiveResults.Presenter {

    /**
     * Constructor for injecting View and Interactor.
     *
     * @param view       view reference
     * @param interactor interactor reference
     */
    @Inject
    public LiveResultsPresenter(LiveResults.View view, LiveResults.Interactor interactor) {
        super(view, interactor);
    }

    @Override
    public void onCreated() {
        loadLiveMatches();
    }

    private void loadLiveMatches() {
        interactor.getAllCurrentlyRunningFixturesForFavouriteTeams()
                .subscribe(new Observer<List<Fixture>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<Fixture> value) {
                        view.showLiveResults(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LiveResultsPresenter.this.onError(new ErrorResponse());
                    }

                    @Override
                    public void onComplete() {
                        // nothing here
                    }
                });
    }
}
