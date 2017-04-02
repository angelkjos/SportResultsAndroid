package com.angelkjoseski.live_results.features.teams;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.angelkjoseski.live_results.R;
import com.angelkjoseski.live_results.SportResultsApplication;
import com.angelkjoseski.live_results.dagger.modules.TeamsModule;
import com.angelkjoseski.live_results.features.common.SportResultsFragment;
import com.angelkjoseski.live_results.model.Team;
import com.angelkjoseski.live_results.mvp.Teams;
import com.angelkjoseski.live_results.networking.images.ImageLoadingService;
import com.angelkjoseski.live_results.util.view.RecyclerTouchListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment for showing all teams, including the users favourites.
 */
public class AllTeamsFragment extends SportResultsFragment implements Teams.View {

    @BindView(R.id.teamsRecyclerView)
    RecyclerView recyclerView;
    private TeamsAdapter adapter;

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

        setupRecyclerView();

        presenter.onCreated();
        return view;
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getSportResultsActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(getSportResultsActivity(),
                        recyclerView,
                        new RecyclerTouchListener.ClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                Toast.makeText(getSportResultsActivity(), "Click", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLongClick(View view, int position) {
                                Toast.makeText(getSportResultsActivity(), "Long-Click", Toast.LENGTH_SHORT).show();
                            }
                        }));
    }

    @Override
    public void showAllTeams(List<Team> teams) {
        adapter = new TeamsAdapter(teams, imageLoadingService);
        recyclerView.setAdapter(adapter);
    }

    private void injectDependencies() {
        SportResultsApplication.getInstance()
                .getApplicationComponent()
                .plus(new TeamsModule(this))
                .inject(this);
    }
}
