package com.angelkjoseski.live_results.service.impl;

import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.angelkjoseski.live_results.SportResultsApplication;
import com.angelkjoseski.live_results.model.Team;
import com.angelkjoseski.live_results.service.FavouriteService;
import com.angelkjoseski.live_results.util.GsonUtils;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * An implementation of the @{@link FavouriteService} which saves the favourites locally
 * in the SharedPreferences.
 */
public class StorageFavouriteService implements FavouriteService {
    private static final String FAVOURITES_KEY = "favourite_teams_shared_prefs";

    @Inject
    public StorageFavouriteService() {
    }

    @Override
    public Observable<List<Team>> getFavouriteTeams() {
        return Observable.just(getFavouriteTeamsAsList());
    }

    @Override
    public void storeFavourite(Team team) {
        List<Team> storedTeams = getFavouriteTeamsAsList();
        storedTeams.add(team);

        String json = GsonUtils.GSON.toJson(storedTeams);
        PreferenceManager.getDefaultSharedPreferences(SportResultsApplication.getInstance())
                .edit()
                .putString(FAVOURITES_KEY, json)
                .apply();
    }

    private List<Team> getFavouriteTeamsAsList() {
        String json = PreferenceManager
                .getDefaultSharedPreferences(SportResultsApplication.getInstance())
                .getString(FAVOURITES_KEY, "");
        if (TextUtils.isEmpty(json)) {
            return new ArrayList<>();
        }

        Type listType = new TypeToken<ArrayList<Team>>(){}.getType();
        List<Team> teamList = GsonUtils.GSON.fromJson(json, listType);

        return teamList;
    }

}
