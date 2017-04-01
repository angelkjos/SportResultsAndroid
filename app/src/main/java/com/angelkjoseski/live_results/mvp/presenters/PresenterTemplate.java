package com.angelkjoseski.live_results.mvp.presenters;

import com.angelkjoseski.live_results.R;
import com.angelkjoseski.live_results.SportResultsApplication;
import com.angelkjoseski.live_results.model.ErrorResponse;
import com.angelkjoseski.live_results.mvp.SportResults;

/**
 * A template for a common Presenter in MVP model. Provides a
 * constructor that take View and Interactor instance.
 *
 * @param <V> view implementation that will be used with this presenter
 * @param <I> interactor implementation that will be used with this presenter
 */
public abstract class PresenterTemplate<V extends SportResults.View, I> {

    /**
     * View reference.
     */
    protected V view;

    /**
     * Interactor reference.
     */
    protected I interactor;

    /**
     * Constructor for injecting View and Interactor.
     *
     * @param view       view reference
     * @param interactor interactor reference
     */
    public PresenterTemplate(V view, I interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    /**
     * Default error handling implementation method.
     *
     * @param errorResponse object holding error data
     */
    public void onError(ErrorResponse errorResponse) {
        view.showLoading(false);
        view.showMessage(
                SportResultsApplication.getInstance().getString(R.string.error),
                SportResultsApplication.getInstance().getString(R.string.please_try_again)
        );
    }
}
