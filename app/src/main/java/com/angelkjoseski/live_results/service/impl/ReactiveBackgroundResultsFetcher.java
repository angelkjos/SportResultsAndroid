package com.angelkjoseski.live_results.service.impl;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.service.BackgroundResultsFetcher;
import com.angelkjoseski.live_results.service.FavouriteService;
import com.angelkjoseski.live_results.service.NotificationService;
import com.angelkjoseski.live_results.service.jobs.ResultsFetchingContinuousJob;
import com.angelkjoseski.live_results.service.networking.ApiService;
import com.angelkjoseski.live_results.util.rx_transformers.FillFixturesWithTeamDetailsTransformer;
import com.angelkjoseski.live_results.util.rx_transformers.FixturesBasedOnFavouritesTransformer;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Class which handles fetching of live results using RxJava.
 */
public class ReactiveBackgroundResultsFetcher implements BackgroundResultsFetcher {
    private static final int DELAY_SECONDS = 10;

    private static final int SECOND_MILLIS = 1000;
    private static final int JOB_PERIODIC_DELAY = 20;

    private int jobId = 1;

    private Context context;
    private ApiService apiService;
    private FavouriteService favouriteService;
    private NotificationService notificationService;


    @Inject
    public ReactiveBackgroundResultsFetcher(Context context, ApiService apiService,
                                            FavouriteService favouriteService,
                                            NotificationService notificationService) {
        this.context = context;
        this.apiService = apiService;
        this.favouriteService = favouriteService;
        this.notificationService = notificationService;
    }

    @Override
    public Observable<List<Fixture>> startLiveResultsFetching(final boolean repeat) {
        return apiService.getAllFixtures()
                .subscribeOn(Schedulers.io())
                .map(fixtureList -> fixtureList.getFixtures())
                .compose(new FixturesBasedOnFavouritesTransformer(favouriteService))
                .filter(fixture -> !fixture.getResult().isFinished())
                .filter(fixture -> new Date().after(fixture.getStartTime()))
                .toList()
                .toObservable()
                .compose(new FillFixturesWithTeamDetailsTransformer(apiService))
                .repeatWhen(objectObservable -> objectObservable.delay(DELAY_SECONDS, TimeUnit.SECONDS))
                .takeUntil(fixtures -> {
                    if (!repeat) {
                        return true;
                    } else if (fixtures.size() == 0) {
                        throw new Exception("No live fixtures right now.");
                    } else {
                        return false;
                    }
                })
                .doOnNext(fixtures -> {
                    for (Fixture fixture : fixtures) {
                        notificationService.showNotificationForResult(fixture);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void scheduleBackgroundJob() {
        ComponentName serviceComponent = new ComponentName(context, ResultsFetchingContinuousJob.class);
        JobInfo.Builder builder = new JobInfo.Builder(jobId++ , serviceComponent);
        builder.setPeriodic(JOB_PERIODIC_DELAY * SECOND_MILLIS);
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
    }
}
