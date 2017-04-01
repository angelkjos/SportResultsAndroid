package com.angelkjoseski.live_results.dagger.components;

import com.angelkjoseski.live_results.SportResultsApplication;
import com.angelkjoseski.live_results.dagger.modules.ImageLoadingModule;
import com.angelkjoseski.live_results.dagger.modules.NetworkingModule;

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

}