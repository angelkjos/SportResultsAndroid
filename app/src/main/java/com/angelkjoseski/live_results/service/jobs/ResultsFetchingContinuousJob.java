package com.angelkjoseski.live_results.service.jobs;

import android.app.job.JobParameters;
import android.app.job.JobService;

import com.angelkjoseski.live_results.SportResultsApplication;
import com.angelkjoseski.live_results.service.BackgroundResultsFetcher;

import javax.inject.Inject;

/**
 * Background job which is started by the Android OS and will
 * start results fetching on a given period.
 */
public class ResultsFetchingContinuousJob extends JobService {

    @Inject
    BackgroundResultsFetcher backgroundResultsFetcher;

    public ResultsFetchingContinuousJob() {
        SportResultsApplication.getInstance().getApplicationComponent().inject(this);
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        backgroundResultsFetcher.startLiveResultsFetching(true).subscribe();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
}
