package com.angelkjoseski.live_results.features.upcomingfixtures.injection;

import com.angelkjoseski.live_results.features.upcomingfixtures.UpcomingFixtures;
import com.angelkjoseski.live_results.features.upcomingfixtures.interactor.UpcomingFixturesInteractor;
import com.angelkjoseski.live_results.features.upcomingfixtures.presenter.UpcomingFixturesPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides dependencies for {@link UpcomingFixtures} screen.
 */
@Module
public class UpcomingFixturesModule {

    /**
     * Reference to UpcomingFixtures view implementation.
     */
    private UpcomingFixtures.View view;

    public UpcomingFixturesModule(UpcomingFixtures.View view) {
        this.view = view;
    }

    @Provides
    public UpcomingFixtures.Interactor provideInteractor(UpcomingFixturesInteractor interactor) {
        return interactor;
    }

    @Provides
    public UpcomingFixtures.View provideView() {
        return view;
    }

    @Provides
    public UpcomingFixtures.Presenter providePresenter(UpcomingFixturesPresenter presenter) {
        return presenter;
    }
}
