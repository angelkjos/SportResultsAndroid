package com.angelkjoseski.live_results.model;

import java.util.List;

/**
 * Wrapper model for list of all teams.
 */
public class TeamList {
    private List<Team> teams;

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
