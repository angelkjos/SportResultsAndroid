package com.angelkjoseski.live_results.dagger.modules;

import com.angelkjoseski.live_results.mvp.Teams;
import com.angelkjoseski.live_results.mvp.interactors.TeamsInteractor;
import com.angelkjoseski.live_results.mvp.presenters.TeamsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides dependencies for {@link Teams} screen.
 */
@Module
public class TeamsModule {

    /**
     * Reference to MyTeams view implementation.
     */
    private Teams.View view;

    public TeamsModule(Teams.View view) {
        this.view = view;
    }

    @Provides
    public Teams.Interactor provideInteractor(TeamsInteractor teamsInteractor) {
        return teamsInteractor;
    }

    @Provides
    public Teams.View provideView() {
        return view;
    }

    @Provides
    public Teams.Presenter providePresenter(TeamsPresenter myTeamPresenter) {
        return myTeamPresenter;
    }
}
