package com.pulkit.shutterstock.presentation;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import com.pulkit.shutterstock.di.DaggerUnitTestComponent;
import com.pulkit.shutterstock.domain.ShutterSearcher;
import com.pulkit.shutterstock.presentation.commons.DataFetcher;
import com.pulkit.shutterstock.presentation.commons.SchedulerProvider;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ImageSearchViewModelTest {

  @Rule
  public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
  @Mock
  public ShutterSearcher fetcher;

  @Inject
  SchedulerProvider schedulerProvider;
  @Inject
  DataFetcher dataFetcher;

  @Before
  public void setup() {
    DaggerUnitTestComponent.builder().build().inject(this);
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void when_first_page_fetcher_success_no_error() {
    //when(fetcher.search(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
      //  .thenReturn(Single.just());
  }


}
