package com.angelkjoseski.live_results.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * GSON related utility methods.
 */
public class GsonUtils {

    /**
     * Globally used GSON object.
     */
    public static final Gson GSON = new GsonBuilder()
            .setDateFormat(Constants.DATE_FORMAT)
            .create();

    /**
     * This is an utility class so constructor is private.
     */
    private GsonUtils() {
    }
}
