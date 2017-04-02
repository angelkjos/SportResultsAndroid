package com.angelkjoseski.live_results.service.networking.images.impl;

import android.widget.ImageView;

import com.angelkjoseski.live_results.service.networking.images.ImageLoadingService;
import com.bumptech.glide.Glide;

import java.io.Serializable;

import javax.inject.Inject;

/**
 * Image loading service implemented with Glide.
 */
public class GlideImageLoadingService implements ImageLoadingService, Serializable {

    @Inject
    public GlideImageLoadingService() {
    }

    @Override
    public void loadInto(String url, int placeholderResourceId, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(placeholderResourceId)
                .into(imageView);
    }

}