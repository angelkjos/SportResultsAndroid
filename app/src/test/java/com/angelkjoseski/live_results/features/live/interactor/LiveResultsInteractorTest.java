package com.angelkjoseski.live_results.features.live.interactor;

import com.angelkjoseski.live_results.helper.TestHelper;
import com.angelkjoseski.live_results.model.Fixture;
import com.angelkjoseski.live_results.model.FixtureList;
import com.angelkjoseski.live_results.model.Team;
import com.angelkjoseski.live_results.model.TeamList;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
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

  private TestHelper testHelper = new TestHelper();

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
    // Mock fixtures
    final FixtureList fixtureList = GsonUtils.GSON.fromJson(testHelper.getTextContentFromResourcesFile("fixtures.json"), FixtureList.class);
    when(apiService.getAllFixtures()).thenReturn(Observable.just(fixtureList));

    // Mock favourite teams
    TeamList allTeamList = GsonUtils.GSON.fromJson(testHelper.getTextContentFromResourcesFile("all_teams.json"), TeamList.class);
    TeamList favouriteTeamList = GsonUtils.GSON.fromJson(testHelper.getTextContentFromResourcesFile("favourite_teams.json"), TeamList.class);
    when(favouriteService.getFavouriteTeams()).thenReturn(Observable.just(favouriteTeamList.getTeams()));
    when(apiService.getAllTeams()).thenReturn(Observable.just(allTeamList));

    // Test interactor
    TestObserver<List<Fixture>> testObserver = liveResultsInteractor.getAllCurrentlyRunningFixturesForFavouriteTeams()
      .test();
    testObserver.awaitTerminalEvent();
    testObserver.assertSubscribed();
    testObserver.assertComplete();
    testObserver.assertNoErrors();
    testObserver.assertValueCount(1);
    testObserver.assertValue(new Predicate<List<Fixture>>() {
      @Override
      public boolean test(List<Fixture> fixtures) throws Exception {
        return fixtures.size() == 1 && fixtures.get(0).getTeamIdAway() == 10L;
      }
    });
  }

}