package com.angelkjoseski.live_results.features.live.presenter;

import com.angelkjoseski.live_results.features.common.presenter.PresenterTemplate;
import com.angelkjoseski.live_results.model.ErrorResponse;
import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.features.live.LiveResults;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        setDateOnView();
    }

    private void setDateOnView() {
        Date dateNow = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        view.setDate(simpleDateFormat.format(dateNow));
    }

    private void loadLiveMatches() {
        interactor.getAllCurrentlyRunningFixturesForFavouriteTeams()
                .subscribe(new Observer<List<Fixture>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        view.showLoading(true);
                    }

                    @Override
                    public void onNext(List<Fixture> value) {
                        view.showLoading(false);
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
