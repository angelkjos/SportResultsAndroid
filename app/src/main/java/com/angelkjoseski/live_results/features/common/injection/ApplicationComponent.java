package com.angelkjoseski.live_results.features.common.injection;

import com.angelkjoseski.live_results.SportResultsApplication;
import com.angelkjoseski.live_results.features.live.LiveResults;
import com.angelkjoseski.live_results.features.live.injection.LiveResultsComponent;
import com.angelkjoseski.live_results.features.live.injection.LiveResultsModule;
import com.angelkjoseski.live_results.features.teams.injection.TeamsComponent;
import com.angelkjoseski.live_results.features.upcomingfixtures.UpcomingFixtures;
import com.angelkjoseski.live_results.features.upcomingfixtures.injection.UpcomingFixturesComponent;
import com.angelkjoseski.live_results.features.teams.injection.TeamsModule;
import com.angelkjoseski.live_results.features.upcomingfixtures.injection.UpcomingFixturesModule;
import com.angelkjoseski.live_results.features.live.view.LiveResultsFragment;
import com.angelkjoseski.live_results.features.teams.view.AllTeamsFragment;
import com.angelkjoseski.live_results.features.upcomingfixtures.view.UpcomingFixturesFragment;
import com.angelkjoseski.live_results.features.teams.Teams;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Application scope dependencies Dagger 2 component.
 * This is the top level component that passes global singleton dependencies to all other sub-components.
 * <p>
 * Modules that provide application level dependencies must annotate them
 * with {@link Singleton}.
 */
@Singleton
@Component(modules = {
        NetworkingModule.class,
        ImageLoadingModule.class,
})
public interface ApplicationComponent {

    /**
     * Injects application dependencies.
     *
     * @param sportResultsApplication instance of application
     */
    void inject(SportResultsApplication sportResultsApplication);

    /**
     * Returns a new Dagger 2 component that inherits all dependencies from this component and
     * adds dependencies provided by {@link TeamsModule}. Returned component is
     * used to inject {@link AllTeamsFragment} dependencies.
     *
     * @param teamsModule module that provides fragment dependencies
     * @return new component with My Team dependencies added
     * @see TeamsComponent
     * @see TeamsModule
     * @see Teams
     * @see AllTeamsFragment
     */
    TeamsComponent plus(TeamsModule teamsModule);

    /**
     * Returns a new Dagger 2 component that inherits all dependencies from this component and
     * adds dependencies provided by {@link LiveResultsModule}.
     * Returned component is used to inject {@link LiveResultsFragment}
     * dependencies.
     *
     * @param liveResultsModule module that provides fragment dependencies
     * @return new component with dependencies added
     * @see LiveResultsComponent
     * @see LiveResultsModule
     * @see LiveResults
     * @see LiveResultsFragment
     */
    LiveResultsComponent plus(LiveResultsModule liveResultsModule);

    /**
     * Returns a new Dagger 2 component that inherits all dependencies from this component and
     * adds dependencies provided by {@link UpcomingFixturesModule}.
     * Returned component is used to inject {@link UpcomingFixturesFragment}
     * dependencies.
     *
     * @param upcomingFixturesModule module that provides fragment dependencies
     * @return new component with dependencies added
     * @see UpcomingFixturesComponent
     * @see UpcomingFixturesModule
     * @see UpcomingFixtures
     * @see UpcomingFixturesFragment
     */
    UpcomingFixturesComponent plus(UpcomingFixturesModule upcomingFixturesModule);
}