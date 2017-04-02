package com.angelkjoseski.live_results.features.upcomingfixtures.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.angelkjoseski.live_results.R;
import com.angelkjoseski.live_results.SportResultsApplication;
import com.angelkjoseski.live_results.features.upcomingfixtures.injection.UpcomingFixturesModule;
import com.angelkjoseski.live_results.features.common.view.SportResultsFragment;
import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.features.upcomingfixtures.UpcomingFixtures;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Android fragment for displaying the upcoming fixtures.
 */
public class UpcomingFixturesFragment extends SportResultsFragment implements UpcomingFixtures.View {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.onlyFavouredCheckbox)
    CheckBox onlyFavouredCheckbox;

    @Inject
    UpcomingFixtures.Presenter presenter;

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
        presenter.onCreated();

        onlyFavouredCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.indicatorOnlyFavouredTeamsChecked(isChecked);
            }
        });
        return view;
    }

    private void injectDependencies() {
        SportResultsApplication.getInstance()
                .getApplicationComponent()
                .plus(new UpcomingFixturesModule(this))
                .inject(this);
    }


    @Override
    public void showFixtures(List<Fixture> fixtures) {
        textView.setText(fixtures.toString());
    }

    @Override
    public void setOnlyFavouredTeamsIndicator(boolean checked) {
        onlyFavouredCheckbox.setChecked(checked);
    }
}
