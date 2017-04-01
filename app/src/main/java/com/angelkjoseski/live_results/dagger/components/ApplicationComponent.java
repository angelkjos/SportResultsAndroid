package com.angelkjoseski.live_results.dagger.components;

import com.angelkjoseski.live_results.SportResultsApplication;
import com.angelkjoseski.live_results.dagger.modules.ImageLoadingModule;
import com.angelkjoseski.live_results.dagger.modules.NetworkingModule;
import com.angelkjoseski.live_results.dagger.modules.TeamsModule;

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
     * used to inject {@link com.angelkjoseski.live_results.features.teams.AllTeamsFragment} dependencies.
     *
     * @param teamsModule module that provides fragment dependencies
     * @return new component with My Team dependencies added
     * @see TeamsComponent
     * @see TeamsModule
     * @see com.angelkjoseski.live_results.mvp.MyTeams
     * @see com.angelkjoseski.live_results.features.teams.AllTeamsFragment
     */
    TeamsComponent plus(TeamsModule teamsModule);

}