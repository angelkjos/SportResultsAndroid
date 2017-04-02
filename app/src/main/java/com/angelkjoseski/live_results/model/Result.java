package com.angelkjoseski.live_results.model;

/**
 * Model class for a result.
 */
public class Result {

    private int scoreHome;
    private int scoreAway;
    private boolean finished;

    public int getScoreHome() {
        return scoreHome;
    }

    public void setScoreHome(int scoreHome) {
        this.scoreHome = scoreHome;
    }

    public int getScoreAway() {
        return scoreAway;
    }

    public void setScoreAway(int scoreAway) {
        this.scoreAway = scoreAway;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getScoreString() {
        return String.format("%d : %d", scoreHome, scoreAway);
    }
}
