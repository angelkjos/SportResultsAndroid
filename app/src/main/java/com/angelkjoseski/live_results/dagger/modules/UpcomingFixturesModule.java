package com.angelkjoseski.live_results.dagger.modules;

import com.angelkjoseski.live_results.mvp.UpcomingFixtures;
import com.angelkjoseski.live_results.mvp.interactors.UpcomingFixturesInteractor;
import com.angelkjoseski.live_results.mvp.presenters.UpcomingFixturesPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides dependencies for {@link com.angelkjoseski.live_results.mvp.UpcomingFixtures} screen.
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
