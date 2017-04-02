package com.angelkjoseski.live_results;

import com.angelkjoseski.live_results.dagger.components.TestApplicationComponent;

import javax.inject.Inject;

import okhttp3.mockwebserver.MockWebServer;

/**
 * Test application class used to inject testing dependencies in the app.
 */
public class TestSportResultsApplication extends SportResultsApplication {

    @Inject
    MockWebServer mockWebServer;

    protected TestApplicationComponent testApplicationComponent;

    @Override
    protected void injectDependencies() {
        //testApplicationComponent = DaggerTestApplicationComponent.builder().build();
        applicationComponent = testApplicationComponent;

        testApplicationComponent.inject(this);
    }

    public static MockWebServer getMockWebServer() {
        return ((TestSportResultsApplication) getInstance()).mockWebServer;
    }

}
