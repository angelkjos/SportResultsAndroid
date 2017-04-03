package com.angelkjoseski.live_results;

import android.app.Application;

import com.angelkjoseski.live_results.features.common.injection.ApplicationComponent;
import com.angelkjoseski.live_results.features.common.injection.DaggerApplicationComponent;
import com.angelkjoseski.live_results.service.BackgroundResultsFetcher;
import com.facebook.stetho.Stetho;

import javax.inject.Inject;


/**
 * Custom Android application class.
 * <p>
 * Injects global application dependencies and handles session/data management.
 */
public class SportResultsApplication extends Application {

    /**
     * Reference to the singleton instance of Application.
     */
    private static SportResultsApplication instance;

    /**
     * Instance of the root application component for injecting dependencies.
     */
    protected ApplicationComponent applicationComponent;

    @Inject
    BackgroundResultsFetcher backgroundResultsFetcher;

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);

        injectDependencies();
        backgroundResultsFetcher.scheduleBackgroundJob();
    }

    public static SportResultsApplication getInstance() {
        return instance;
    }

    private void setInstance(SportResultsApplication instance) {
        SportResultsApplication.instance = instance;
    }

    /**
     * Will create the @{@link ApplicationComponent} object and inject needed dependencies
     * in the application class.
     * Also, will initialise Stetho for debugging.
     */
    protected void injectDependencies() {
        if (BuildConfig.DEBUG) {
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                            .build());
        }

        applicationComponent = DaggerApplicationComponent.builder().build();
        applicationComponent.inject(this);

    }

    public ApplicationComponent getApplicationComponent() {
        return getInstance().applicationComponent;
    }

}
