package com.angelkjoseski.live_results.dagger.modules;

import com.angelkjoseski.live_results.mvp.MyTeams;
import com.angelkjoseski.live_results.mvp.interactors.MyTeamsInteractor;
import com.angelkjoseski.live_results.mvp.presenters.MyTeamsPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides dependencies for {@link com.angelkjoseski.live_results.mvp.MyTeams} screen.
 */
@Module
public class TeamsModule {

    /**
     * Reference to MyTeams view implementation.
     */
    private MyTeams.View view;

    public TeamsModule(MyTeams.View view) {
        this.view = view;
    }

    @Provides
    @Singleton
    public MyTeams.Interactor provideInteractor(MyTeamsInteractor myTeamsInteractor) {
        return myTeamsInteractor;
    }

    @Provides
    @Singleton
    public MyTeams.View provideView() {
        return view;
    }

    @Provides
    @Singleton
    public MyTeams.Presenter providePresenter(MyTeamsPresenter myTeamPresenter) {
        return myTeamPresenter;
    }
}
