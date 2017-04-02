package com.angelkjoseski.live_results.dagger.modules;

import com.angelkjoseski.live_results.service.networking.ApiService;
import com.angelkjoseski.live_results.util.GsonUtils;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger 2 module that provides mock networking dependencies.
 */
@Module
public class MockNetworkingModule {

    @Provides
    @Singleton
    public MockWebServer provideMockWebServer() {
        MockWebServer mockWebServer = new MockWebServer();

        try {
            mockWebServer.start();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return mockWebServer;
    }

    @Provides
    @Singleton
    public OkHttpClient provideClient() {
        return new OkHttpClient.Builder()
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(MockWebServer mockWebServer, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonUtils.GSON))
                .build();
    }

    /**
     * Provides implementation of RESTful API interface.
     *
     * @return
     */
    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

}
