package com.angelkjoseski.live_results.features.live.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.angelkjoseski.live_results.R;
import com.angelkjoseski.live_results.SportResultsApplication;
import com.angelkjoseski.live_results.features.common.view.SportResultsFragment;
import com.angelkjoseski.live_results.features.live.LiveResults;
import com.angelkjoseski.live_results.features.live.injection.LiveResultsModule;
import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.service.networking.images.ImageLoadingService;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment for displaying the live results.
 */
public class LiveResultsFragment extends SportResultsFragment implements LiveResults.View {

    @BindView(R.id.dateTextView)
    TextView dateTextView;
    @BindView(R.id.liveResultsRecyclerView)
    RecyclerView recyclerView;
    private LiveResultsAdapter adapter;

    @Inject
    LiveResults.Presenter presenter;

    @Inject
    ImageLoadingService imageLoadingService;
    private List<Fixture> fixtures;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.live_results_fragment, container, false);
        ButterKnife.bind(this, view);

        setupRecyclerView();

        presenter.onCreated();
        return view;
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getSportResultsActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new LiveResultsAdapter(fixtures, imageLoadingService);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showLiveResults(List<Fixture> fixtures) {
        this.fixtures = fixtures;
        adapter = new LiveResultsAdapter(fixtures, imageLoadingService);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setDate(String date) {
        dateTextView.setText(date);
    }

    private void injectDependencies() {
        SportResultsApplication.getInstance()
                .getApplicationComponent()
                .plus(new LiveResultsModule(this))
                .inject(this);
    }
}
