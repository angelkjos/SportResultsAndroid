package com.angelkjoseski.live_results.features.teams.injection;

import com.angelkjoseski.live_results.features.teams.Teams;
import com.angelkjoseski.live_results.features.teams.interactor.TeamsInteractor;
import com.angelkjoseski.live_results.features.teams.presenter.TeamsPresenter;

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
