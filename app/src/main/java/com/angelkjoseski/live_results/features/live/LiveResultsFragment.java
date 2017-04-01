package com.angelkjoseski.live_results.features.live;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.angelkjoseski.live_results.R;
import com.angelkjoseski.live_results.SportResultsApplication;
import com.angelkjoseski.live_results.dagger.modules.LiveResultsModule;
import com.angelkjoseski.live_results.features.common.SportResultsFragment;
import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.mvp.LiveResults;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment for displaying the live results.
 */
public class LiveResultsFragment extends SportResultsFragment implements LiveResults.View {

    @BindView(R.id.textView)
    TextView textView;

    @Inject
    LiveResults.Presenter presenter;

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

        presenter.onCreated();
        return view;
    }

    @Override
    public void showLiveResults(List<Fixture> fixtures) {
        textView.setText(fixtures.toString());
    }

    private void injectDependencies() {
        SportResultsApplication.getInstance()
                .getApplicationComponent()
                .plus(new LiveResultsModule(this))
                .inject(this);
    }
}
