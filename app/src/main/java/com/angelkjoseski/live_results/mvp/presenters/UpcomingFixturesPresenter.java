package com.angelkjoseski.live_results.mvp.presenters;

import com.angelkjoseski.live_results.model.ErrorResponse;
import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.mvp.UpcomingFixtures;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Presenter responsible for UI logic of 'Upcoming Fixtures' screen.
 */
public class UpcomingFixturesPresenter extends PresenterTemplate<UpcomingFixtures.View, UpcomingFixtures.Interactor>
        implements UpcomingFixtures.Presenter {

    /**
     * Constructor for injecting View and Interactor.
     *
     * @param view       view reference
     * @param interactor interactor reference
     */
    @Inject
    public UpcomingFixturesPresenter(UpcomingFixtures.View view, UpcomingFixtures.Interactor interactor) {
        super(view, interactor);
    }

    @Override
    public void indicatorOnlyFavouredTeamsChecked(boolean checked) {
        loadFixtures(checked);
    }

    @Override
    public void onCreated() {
        loadFixtures(false);
    }

    private void loadFixtures(final boolean onlyFavouredTeams) {
        Observable<List<Fixture>> observable;
        if (onlyFavouredTeams) {
            observable = interactor.getUpcomingFixturesForFavouriteTeams();
        } else {
            observable = interactor.getAllUpcomingFixtures();
        }

        observable.subscribe(new Observer<List<Fixture>>() {
            @Override
            public void onSubscribe(Disposable d) {
                view.setOnlyFavouredTeamsIndicator(onlyFavouredTeams);
            }

            @Override
            public void onNext(List<Fixture> value) {
                view.showFixtures(value);
            }

            @Override
            public void onError(Throwable e) {
                UpcomingFixturesPresenter.this.onError(new ErrorResponse());
            }

            @Override
            public void onComplete() {
                // nothing
            }
        });

    }
}
