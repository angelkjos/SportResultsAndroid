package com.angelkjoseski.live_results.dagger.modules;

import com.angelkjoseski.live_results.networking.images.ImageLoadingService;
import com.angelkjoseski.live_results.networking.images.impl.GlideImageLoadingService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Module that provides an implementation of the @{@link ImageLoadingService}.
 */
@Module
public class ImageLoadingModule {

    @Provides
    @Singleton
    public ImageLoadingService provideImageLoadingService(GlideImageLoadingService imageLoadingService) {
        return imageLoadingService;
    }
}