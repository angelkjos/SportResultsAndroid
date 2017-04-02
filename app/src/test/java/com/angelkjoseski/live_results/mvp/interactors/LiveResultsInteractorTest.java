package com.angelkjoseski.live_results.mvp.interactors;

import com.angelkjoseski.live_results.TestSportResultsApplication;
import com.angelkjoseski.live_results.dagger.components.TestApplicationComponent;
import com.angelkjoseski.live_results.features.live.interactor.LiveResultsInteractor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;

import javax.inject.Inject;

/**
 * Test for the live results fetching in the 'LiveResultsInteractor'.
 */
@RunWith(RobolectricGradleTestRunner.class)
public class LiveResultsInteractorTest {

    @Inject
    LiveResultsInteractor liveResultsInteractor;

    @Before
    public void setup() {
        ((TestApplicationComponent) TestSportResultsApplication.getInstance().getApplicationComponent()).inject(this);
    }

    @Test
    public void getAllCurrentlyRunningFixturesForFavouriteTeams() throws Exception {

    }

}