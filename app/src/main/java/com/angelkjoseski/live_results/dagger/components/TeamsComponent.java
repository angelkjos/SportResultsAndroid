package com.angelkjoseski.live_results.dagger.components;

import com.angelkjoseski.live_results.dagger.modules.TeamsModule;
import com.angelkjoseski.live_results.features.teams.AllTeamsFragment;
import com.angelkjoseski.live_results.mvp.Teams;

import dagger.Subcomponent;

/**
 * Dagger component for {@link Teams} screen.
 */
@Subcomponent(modules = TeamsModule.class)
public interface TeamsComponent {

    void inject(AllTeamsFragment allTeamsFragment);
}
