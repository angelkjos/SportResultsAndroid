package com.angelkjoseski.live_results.model;

import java.util.Date;

/**
 * Model class for a match fixture.
 */
public class Fixture {

    private long fixtureId;
    private long teamIdHome;
    private long teamIdAway;
    private Date startTime;
    private Result result;

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
}
