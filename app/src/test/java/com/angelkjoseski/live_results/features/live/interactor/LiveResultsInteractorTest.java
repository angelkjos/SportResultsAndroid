package com.angelkjoseski.live_results.features.live.interactor;

import com.angelkjoseski.live_results.helper.TestHelper;
import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.model.FixtureList;
import com.angelkjoseski.live_results.service.FavouriteService;
import com.angelkjoseski.live_results.service.networking.ApiService;
import com.angelkjoseski.live_results.util.GsonUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.when;

/**
 * Test for the live results fetching in the 'LiveResultsInteractor'.
 */
public class LiveResultsInteractorTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  ApiService apiService;

  @Mock
  FavouriteService favouriteService;

  @InjectMocks
  LiveResultsInteractor liveResultsInteractor;

  @BeforeClass
  public static void setupClass() {
    RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
      @Override
      public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
        return Schedulers.trampoline();
      }
    });
  }

  @Test
  public void getAllCurrentlyRunningFixturesForFavouriteTeams() throws Exception {
    FixtureList fixtureList = GsonUtils.GSON.fromJson(new TestHelper().getTextContentFromResourcesFile("fixtures.json"), FixtureList.class);
    when(apiService.getAllFixtures()).thenReturn(Observable.just(fixtureList));

    TestObserver<List<Fixture>> testSubscriber = liveResultsInteractor.getAllCurrentlyRunningFixturesForFavouriteTeams().test();
    testSubscriber.assertSubscribed();
    //testSubscriber.assertComplete();
    //testSubscriber.assertValueCount(1);
  }

}