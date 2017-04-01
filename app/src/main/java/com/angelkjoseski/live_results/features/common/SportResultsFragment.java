package com.angelkjoseski.live_results.features.common;

import android.support.v4.app.Fragment;

import com.angelkjoseski.live_results.mvp.SportResults;

/**
 * Base fragment for the application.
 * All fragments should inherit from this one, so that they have common functions.
 */
public class SportResultsFragment extends Fragment implements SportResults.View {

    protected SportResultsActivity getSportResultsActivity() {
        try {
            return (SportResultsActivity) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must extend SportResultsActivity.");
        }
    }

    @Override
    public void showLoading(boolean show) {
        //Delegate to hosting activity.
        if (getSportResultsActivity() != null) {
            getSportResultsActivity().showLoading(show);
        }
    }

    @Override
    public void showMessage(String message, String description) {
        //Delegate to hosting activity.
        if (getSportResultsActivity() != null) {
            getSportResultsActivity().showMessage(message, description);
        }
    }
}
