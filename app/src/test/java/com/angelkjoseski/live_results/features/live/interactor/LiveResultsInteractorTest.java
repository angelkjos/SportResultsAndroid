package com.angelkjoseski.live_results.features.live.interactor;

import com.angelkjoseski.live_results.model.FixtureList;
import com.angelkjoseski.live_results.service.FavouriteService;
import com.angelkjoseski.live_results.service.networking.ApiService;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

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

  @Test
  public void getAllCurrentlyRunningFixturesForFavouriteTeams() throws Exception {
    when(apiService.getAllFixtures()).thenReturn(Observable.<FixtureList>empty());
    TestObserver<FixtureList> testSubscriber = apiService.getAllFixtures().test();
    testSubscriber.assertSubscribed();
  }

}