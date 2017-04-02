package com.angelkjoseski.live_results.model;

/**
 * Model for one sports team.
 */
public class Team {
    private long teamId;
    private String teamName;
    private String bannerUrl;

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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Team)) {
            return false;
        }

        Team other = (Team) obj;
        return this.teamId == other.teamId;
    }
}
