package com.angelkjoseski.live_results.model;

import java.util.List;

/**
 * Model for json element which wraps all fixtures.
 */
public class FixtureList {

    private List<Fixture> fixtures;

    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }
}
