package com.angelkjoseski.live_results.features.upcomingfixtures.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.angelkjoseski.live_results.R;
import com.angelkjoseski.live_results.SportResultsApplication;
import com.angelkjoseski.live_results.features.common.view.SportResultsFragment;
import com.angelkjoseski.live_results.features.upcomingfixtures.UpcomingFixtures;
import com.angelkjoseski.live_results.features.upcomingfixtures.injection.UpcomingFixturesModule;
import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.service.networking.images.ImageLoadingService;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Android fragment for displaying the upcoming fixtures.
 */
public class UpcomingFixturesFragment extends SportResultsFragment implements UpcomingFixtures.View {

    @BindView(R.id.onlyFavouredCheckbox)
    CheckBox onlyFavouredCheckbox;
    @BindView(R.id.upcomingFixturesRecyclerView)
    RecyclerView recyclerView;

    @Inject
    UpcomingFixtures.Presenter presenter;
    @Inject
    ImageLoadingService imageLoadingService;

    private List<Fixture> fixtures;
    private UpcomingFixturesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.upcoming_fixtures_fragment, container, false);
        ButterKnife.bind(this, view);

        setupRecyclerView();

        presenter.onCreated();

        onlyFavouredCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.indicatorOnlyFavouredTeamsChecked(isChecked);
            }
        });
        return view;
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getSportResultsActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new UpcomingFixturesAdapter(fixtures, imageLoadingService);
        recyclerView.setAdapter(adapter);
    }

    private void injectDependencies() {
        SportResultsApplication.getInstance()
                .getApplicationComponent()
                .plus(new UpcomingFixturesModule(this))
                .inject(this);
    }


    @Override
    public void showFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
        adapter = new UpcomingFixturesAdapter(fixtures, imageLoadingService);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setOnlyFavouredTeamsIndicator(boolean checked) {
        onlyFavouredCheckbox.setChecked(checked);
    }
}
