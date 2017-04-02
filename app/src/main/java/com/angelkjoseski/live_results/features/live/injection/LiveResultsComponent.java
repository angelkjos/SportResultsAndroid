package com.angelkjoseski.live_results.features.live.injection;

import com.angelkjoseski.live_results.features.live.LiveResults;
import com.angelkjoseski.live_results.features.live.view.LiveResultsFragment;

import dagger.Subcomponent;

/**
 * Dagger component for {@link LiveResults} screen.
 */
@Subcomponent(modules = LiveResultsModule.class)
public interface LiveResultsComponent {

    void inject(LiveResultsFragment liveResultsFragment);
}
