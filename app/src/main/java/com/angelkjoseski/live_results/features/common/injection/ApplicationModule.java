package com.angelkjoseski.live_results.features.common.injection;

import android.content.Context;

import com.angelkjoseski.live_results.SportResultsApplication;
import com.angelkjoseski.live_results.service.FavouriteService;
import com.angelkjoseski.live_results.service.impl.StorageFavouriteService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Module that provides an general application-wide dependencies.
 */
@Module
public class ApplicationModule {

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return SportResultsApplication.getInstance().getApplicationContext();
    }

    @Provides
    @Singleton
    public FavouriteService provideFavouriteService() {
        return new StorageFavouriteService();
    }

}