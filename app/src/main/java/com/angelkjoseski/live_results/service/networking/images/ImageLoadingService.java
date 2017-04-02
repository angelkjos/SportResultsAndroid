package com.angelkjoseski.live_results.service.networking.images;

import android.widget.ImageView;

/**
 * Image loading service.
 */
public interface ImageLoadingService {

    /**
     * Load an image into specified target image-view.
     *
     * @param url                   The URL from where the image should be fetched.
     * @param placeholderResourceId Placeholder image to be shown while loading the required.
     * @param imageView             The target image-view into which the image will be shown.
     */
    void loadInto(String url, int placeholderResourceId, ImageView imageView);

}