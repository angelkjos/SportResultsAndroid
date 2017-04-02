package com.angelkjoseski.live_results.features.upcomingfixtures.injection;

import com.angelkjoseski.live_results.features.upcomingfixtures.UpcomingFixtures;
import com.angelkjoseski.live_results.features.upcomingfixtures.view.UpcomingFixturesFragment;

import dagger.Subcomponent;

/**
 * Dagger component for {@link UpcomingFixtures} screen.
 */
@Subcomponent(modules = UpcomingFixturesModule.class)
public interface UpcomingFixturesComponent {

    void inject(UpcomingFixturesFragment upcomingFixturesFragment);
}
