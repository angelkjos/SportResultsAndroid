package com.angelkjoseski.live_results.mvp.interactors;

import com.angelkjoseski.live_results.networking.ApiService;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Template for Interactor implementations.
 * <p>
 * Template is constructed to wrap a single API call which response type should
 * be passed as type parameter. If more API calls will be used in the interactor,
 * simply add additional Retrofit {@code Call} fields and use {@link #cancel(Call)}
 * method for cancelling the scheduling calls.
 * Additionally, you should override {@link #cancel()} method to cancel all of the API calls.
 *
 * @param <T> Type of the API call response.
 */
public class InteractorTemplate<T> {

    /**
     * Reference to the RESTful API service.
     */
    protected ApiService apiService;

    /**
     * Reference to singleton Retrofit instance.
     */
    protected Retrofit retrofit;

    /**
     * Current API call.
     */
    protected Call<T> call;

    /**
     * Constructor for injecting REST API service and Retrofit instance.
     *
     * @param apiService implementation of REST API service
     * @param retrofit singleton Retrofit instance
     */
    public InteractorTemplate(ApiService apiService, Retrofit retrofit) {
        this.apiService = apiService;
        this.retrofit = retrofit;
    }

    /**
     * Cancels the given Retrofit Call if it is not {@code null}.
     *
     * @param call Retrofit Call object
     */
    protected void cancel(Call<?> call) {
        if (call != null) {
            call.cancel();
        }
    }

    /**
     * This method should cancel all ongoing RESTful API requests.
     */
    public void cancel() {
        cancel(call);
    }
}
