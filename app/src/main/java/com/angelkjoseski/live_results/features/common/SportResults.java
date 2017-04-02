package com.angelkjoseski.live_results.features.common;

/**
 * Common Model - View - Presenter interfaces are defined here.
 */
public interface SportResults {

    /**
     * View interface that all View interfaces must inherit.
     */
    interface View {

        /**
         * Indicates in the UI that networking or background operation is currently
         * being performed.
         *
         * @param show show loading indicator if {@code true} or hide otherwise
         */
        void showLoading(boolean show);

        /**
         * Shows the given message and more detailed description to the user.
         *
         * @param message     short message
         * @param description more detailed description
         */
        void showMessage(String message, String description);
    }

}
