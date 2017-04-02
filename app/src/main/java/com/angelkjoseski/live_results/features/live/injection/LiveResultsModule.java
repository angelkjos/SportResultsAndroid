package com.angelkjoseski.live_results.features.live.injection;

import com.angelkjoseski.live_results.features.live.LiveResults;
import com.angelkjoseski.live_results.features.live.interactor.LiveResultsInteractor;
import com.angelkjoseski.live_results.features.live.presenter.LiveResultsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides dependencies for {@link LiveResults} screen.
 */
@Module
public class LiveResultsModule {

    /**
     * Reference to LiveResults view implementation.
     */
    private LiveResults.View view;

    public LiveResultsModule(LiveResults.View view) {
        this.view = view;
    }

    @Provides
    public LiveResults.Interactor provideInteractor(LiveResultsInteractor interactor) {
        return interactor;
    }

    @Provides
    public LiveResults.View provideView() {
        return view;
    }

    @Provides
    public LiveResults.Presenter providePresenter(LiveResultsPresenter presenter) {
        return presenter;
    }
}
