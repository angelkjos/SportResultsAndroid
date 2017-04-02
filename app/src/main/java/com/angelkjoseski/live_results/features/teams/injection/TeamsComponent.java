package com.angelkjoseski.live_results.features.teams.injection;

import com.angelkjoseski.live_results.features.teams.view.AllTeamsFragment;
import com.angelkjoseski.live_results.features.teams.Teams;

import dagger.Subcomponent;

/**
 * Dagger component for {@link Teams} screen.
 */
@Subcomponent(modules = TeamsModule.class)
public interface TeamsComponent {

    void inject(AllTeamsFragment allTeamsFragment);
}
