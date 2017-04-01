package com.angelkjoseski.live_results.features.teams;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.angelkjoseski.live_results.features.common.SportResultsFragment;
import com.angelkjoseski.live_results.model.Team;
import com.angelkjoseski.live_results.mvp.MyTeams;
import com.angelkjoseski.live_results.networking.images.ImageLoadingService;

import java.util.List;

import javax.inject.Inject;

/**
 * Fragment for showing all teams, including the users favourites.
 */
public class AllTeamsFragment extends SportResultsFragment implements MyTeams.View {

    @Inject
    MyTeams.Presenter presenter;

    @Inject
    ImageLoadingService imageLoadingService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        return view;
    }

    @Override
    public void showAllTeams(List<Team> teams) {

    }

    private void injectDependencies() {

    }
}
