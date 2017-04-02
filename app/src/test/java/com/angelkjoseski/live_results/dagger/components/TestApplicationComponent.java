package com.angelkjoseski.live_results.dagger.components;

import com.angelkjoseski.live_results.TestSportResultsApplication;
import com.angelkjoseski.live_results.dagger.modules.ImageLoadingModule;
import com.angelkjoseski.live_results.dagger.modules.MockNetworkingModule;
import com.angelkjoseski.live_results.mvp.interactors.LiveResultsInteractorTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger 2 component for injecting test Application level dependencies.
 *
 * @see ApplicationComponent
 * @see com.angelkjoseski.live_results.SportResultsApplication
 */
@Singleton
@Component(modules = {
        MockNetworkingModule.class,
        ImageLoadingModule.class
})
public interface TestApplicationComponent extends ApplicationComponent {

    /**
     * Injects test application dependencies.
     *
     * @param testApplication
     */
    void inject(TestSportResultsApplication testApplication);

    // Injecting tests
    void inject(LiveResultsInteractorTest liveResultsInteractorTest);
}
