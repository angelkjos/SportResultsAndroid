package com.angelkjoseski.live_results.features.teams;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.angelkjoseski.live_results.R;
import com.angelkjoseski.live_results.SportResultsApplication;
import com.angelkjoseski.live_results.dagger.modules.TeamsModule;
import com.angelkjoseski.live_results.features.common.SportResultsFragment;
import com.angelkjoseski.live_results.model.Team;
import com.angelkjoseski.live_results.mvp.Teams;
import com.angelkjoseski.live_results.networking.images.ImageLoadingService;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment for showing all teams, including the users favourites.
 */
public class AllTeamsFragment extends SportResultsFragment implements Teams.View {

    @BindView(R.id.textView)
    TextView textView;

    @Inject
    Teams.Presenter presenter;
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
        View view = inflater.inflate(R.layout.teams_fragment, container, false);
        ButterKnife.bind(this, view);

        presenter.onCreated();
        return view;
    }

    @Override
    public void showAllTeams(List<Team> teams) {
        textView.setText(teams.toString());
    }

    private void injectDependencies() {
        SportResultsApplication.getInstance()
                .getApplicationComponent()
                .plus(new TeamsModule(this))
                .inject(this);
    }
}
