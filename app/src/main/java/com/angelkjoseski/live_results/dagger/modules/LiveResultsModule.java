package com.angelkjoseski.live_results.dagger.modules;

import com.angelkjoseski.live_results.mvp.LiveResults;
import com.angelkjoseski.live_results.mvp.interactors.LiveResultsInteractor;
import com.angelkjoseski.live_results.mvp.presenters.LiveResultsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides dependencies for {@link com.angelkjoseski.live_results.mvp.LiveResults} screen.
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
