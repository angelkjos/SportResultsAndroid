package com.angelkjoseski.live_results.features.teams.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.angelkjoseski.live_results.R;
import com.angelkjoseski.live_results.model.Team;
import com.angelkjoseski.live_results.networking.images.ImageLoadingService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter for RecyclerView displaying teams.
 */
public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> {

    private List<Team> teams;
    private ImageLoadingService imageLoadingService;

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.teamNameTextView) TextView teamName;
        @BindView(R.id.teamBannerImageView) ImageView teamBanner;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public TeamsAdapter(List<Team> teams, ImageLoadingService imageLoadingService) {
        this.teams = teams;
        this.imageLoadingService = imageLoadingService;
    }

    @Override
    public TeamsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_list_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TeamsAdapter.ViewHolder holder, int position) {
        Team team = teams.get(position);
        holder.teamName.setText(team.getTeamName());
        imageLoadingService.loadInto(team.getBannerUrl(), R.mipmap.ic_launcher, holder.teamBanner);
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }
}
