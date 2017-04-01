package com.angelkjoseski.live_results.dagger.modules;

import com.angelkjoseski.live_results.BuildConfig;
import com.angelkjoseski.live_results.networking.ApiService;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger 2 module that provides networking dependencies.
 */
@Module
public class NetworkingModule {

    @Provides
    @Singleton
    public OkHttpClient provideClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            //Make sure this interceptor is added last to capture all modifications by
            //other interceptors.
            clientBuilder.addNetworkInterceptor(new StethoInterceptor());
        }

        return clientBuilder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
