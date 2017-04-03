package com.angelkjoseski.live_results.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.angelkjoseski.live_results.SportResultsApplication;
import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.model.FixtureList;
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
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Service to fetch live-results in the background.
 */
public class LiveResultsFetcherService extends IntentService {
    private static final String TAG = "LiveResultsFetcherServi";
    private static final int DELAY_SECONDS = 10;

    @Inject
    FavouriteService favouriteService;

    @Inject
    ApiService apiService;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public LiveResultsFetcherService(String name) {
        super(name);
        SportResultsApplication.getInstance().getApplicationComponent().inject(this);
    }

    public LiveResultsFetcherService() {
        this(null);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (favouriteService.hasFavourites()) {
            fetchLiveDataForFavourites();
        } else {
            stopSelf();
        }
    }

    private void fetchLiveDataForFavourites() {
        apiService.getAllFixtures()
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
                        if (fixtures.size() == 0) {
                            throw new Exception("No live fixtures right now.");
                        } else {
                            return false;
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<List<Fixture>>() {
                    @Override
                    public void onNext(List<Fixture> value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
