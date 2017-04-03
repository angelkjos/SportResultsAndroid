package com.angelkjoseski.live_results.service.impl;

import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.model.FixtureList;
import com.angelkjoseski.live_results.service.BackgroundResultsFetcher;
import com.angelkjoseski.live_results.service.FavouriteService;
import com.angelkjoseski.live_results.service.NotificationService;
import com.angelkjoseski.live_results.service.networking.ApiService;
import com.angelkjoseski.live_results.util.rx_transformers.FillFixturesWithTeamDetailsTransformer;
import com.angelkjoseski.live_results.util.rx_transformers.FixturesBasedOnFavouritesTransformer;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Class which handles fetching of live results using RxJava.
 */
public class ReactiveBackgroundResultsFetcher implements BackgroundResultsFetcher {
    private static final int DELAY_SECONDS = 10;

    private ApiService apiService;
    private FavouriteService favouriteService;
    private NotificationService notificationService;


    @Inject
    public ReactiveBackgroundResultsFetcher(ApiService apiService,
                                            FavouriteService favouriteService,
                                            NotificationService notificationService) {
        this.apiService = apiService;
        this.favouriteService = favouriteService;
        this.notificationService = notificationService;
    }

    @Override
    public Observable<List<Fixture>> startLiveResultsFetching(final boolean repeat) {
        return apiService.getAllFixtures()
                .subscribeOn(Schedulers.io())
                .map(new Function<FixtureList, List<Fixture>>() {
                    @Override
                    public List<Fixture> apply(FixtureList fixtureList) throws Exception {
                        return fixtureList.getFixtures();
                    }
                })
                .compose(new FixturesBasedOnFavouritesTransformer(favouriteService))
                .filter(new Predicate<Fixture>() {
                    @Override
                    public boolean test(Fixture fixture) throws Exception {
                        return !fixture.getResult().isFinished();
                    }
                })
                .filter(new Predicate<Fixture>() {
                    @Override
                    public boolean test(Fixture fixture) throws Exception {
                        return new Date().after(fixture.getStartTime());
                    }
                })
                .toList()
                .toObservable()
                .compose(new FillFixturesWithTeamDetailsTransformer(apiService))
                .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                        return objectObservable.delay(DELAY_SECONDS, TimeUnit.SECONDS);
                    }
                })
                .takeUntil(new Predicate<List<Fixture>>() {
                    @Override
                    public boolean test(List<Fixture> fixtures) throws Exception {
                        if (!repeat) {
                            return true;
                        } else if (fixtures.size() == 0) {
                            throw new Exception("No live fixtures right now.");
                        } else {
                            return false;
                        }
                    }
                })
                .doOnNext(new Consumer<List<Fixture>>() {
                    @Override
                    public void accept(List<Fixture> fixtures) throws Exception {
                        for (Fixture fixture : fixtures) {
                            notificationService.showNotificationForResult(fixture);
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
