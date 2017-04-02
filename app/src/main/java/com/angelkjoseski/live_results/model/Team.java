package com.angelkjoseski.live_results.model;

/**
 * Model for one sports team.
 */
public class Team {
    private long teamId;
    private String teamName;
    private String bannerUrl;
    private boolean isFavourite = false;

    public Team() {
    }

    public Team(long teamId) {
        this.teamId = teamId;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Team)) {
            return false;
        }

        Team other = (Team) obj;
        return this.teamId == other.teamId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
