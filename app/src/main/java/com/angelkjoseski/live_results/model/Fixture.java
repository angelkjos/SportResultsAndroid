package com.angelkjoseski.live_results.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Model class for a match fixture.
 */
public class Fixture {

    private long fixtureId = -1;
    private long teamIdHome;
    private long teamIdAway;
    private Date startTime;
    private Result result;

    private Team teamHome;
    private Team teamAway;

    public long getFixtureId() {
        return fixtureId;
    }

    public void setFixtureId(long fixtureId) {
        this.fixtureId = fixtureId;
    }

    public long getTeamIdHome() {
        return teamIdHome;
    }

    public void setTeamIdHome(long teamIdHome) {
        this.teamIdHome = teamIdHome;
    }

    public long getTeamIdAway() {
        return teamIdAway;
    }

    public void setTeamIdAway(long teamIdAway) {
        this.teamIdAway = teamIdAway;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Team getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(Team teamHome) {
        this.teamHome = teamHome;
    }

    public Team getTeamAway() {
        return teamAway;
    }

    public void setTeamAway(Team teamAway) {
        this.teamAway = teamAway;
    }

    public String getStartTimeString() {
        return SimpleDateFormat.getDateInstance().format(startTime);
    }

    public String getTeamsString() {
        String teamHomeName = teamHome != null ? teamHome.getTeamName() : "n/a";
        String teamAwayName = teamAway != null ? teamAway.getTeamName() : "n/a";
        return String.format(Locale.getDefault(), "%s - %s", teamHomeName, teamAwayName);
    }
}
