package com.angelkjoseski.live_results.dagger.components;

import com.angelkjoseski.live_results.dagger.modules.UpcomingFixturesModule;
import com.angelkjoseski.live_results.features.upcomingfixtures.UpcomingFixturesFragment;

import dagger.Subcomponent;

/**
 * Dagger component for {@link com.angelkjoseski.live_results.mvp.UpcomingFixtures} screen.
 */
@Subcomponent(modules = UpcomingFixturesModule.class)
public interface UpcomingFixturesComponent {

    void inject(UpcomingFixturesFragment upcomingFixturesFragment);
}
