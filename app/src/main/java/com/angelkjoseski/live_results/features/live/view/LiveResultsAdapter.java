package com.angelkjoseski.live_results.features.live.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.angelkjoseski.live_results.R;
import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.service.networking.images.ImageLoadingService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter for RecyclerView displaying live fixtures.
 */
public class LiveResultsAdapter extends RecyclerView.Adapter<LiveResultsAdapter.ViewHolder> {

    private List<Fixture> fixtures;
    private ImageLoadingService imageLoadingService;

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.teamHomeName)
        TextView teamNameHome;
        @BindView(R.id.teamAwayName)
        TextView teamNameAway;
        @BindView(R.id.result)
        TextView result;
        @BindView(R.id.minutes)
        TextView minutes;
        @BindView(R.id.teamHomeBanner)
        ImageView teamHomeBanner;
        @BindView(R.id.teamAwayBanner)
        ImageView teamAwayBanner;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public LiveResultsAdapter(List<Fixture> fixtures, ImageLoadingService imageLoadingService) {
        this.fixtures = fixtures;
        this.imageLoadingService = imageLoadingService;
    }

    @Override
    public LiveResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fixture_list_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LiveResultsAdapter.ViewHolder holder, int position) {
        Fixture fixture = fixtures.get(position);

        if (fixture.getTeamHome() != null) {
            holder.teamNameHome.setText(fixture.getTeamHome().getTeamName());
            imageLoadingService
                    .loadInto(
                            fixture.getTeamHome().getBannerUrl(),
                            R.mipmap.ic_launcher,
                            holder.teamHomeBanner
                    );
        }
        if (fixture.getTeamAway() != null) {
            holder.teamNameAway.setText(fixture.getTeamAway().getTeamName());
            imageLoadingService
                    .loadInto(
                            fixture.getTeamAway().getBannerUrl(),
                            R.mipmap.ic_launcher,
                            holder.teamAwayBanner
                    );
        }

        holder.result.setText(fixture.getResult().getScoreString());
    }

    @Override
    public int getItemCount() {
        if (fixtures == null) {
            return 0;
        }
        return fixtures.size();
    }
}
