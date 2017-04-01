package com.angelkjoseski.live_results.dagger.components;

import com.angelkjoseski.live_results.dagger.modules.LiveResultsModule;
import com.angelkjoseski.live_results.features.live.LiveResultsFragment;

import dagger.Subcomponent;

/**
 * Dagger component for {@link com.angelkjoseski.live_results.mvp.LiveResults} screen.
 */
@Subcomponent(modules = LiveResultsModule.class)
public interface LiveResultsComponent {

    void inject(LiveResultsFragment liveResultsFragment);
}
