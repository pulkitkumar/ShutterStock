package com.pulkit.shutterstock.presentation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import com.pulkit.shutterstock.di.DaggerUnitTestComponent;
import com.pulkit.shutterstock.domain.ShutterSearcher;
import com.pulkit.shutterstock.domain.entity.ShutterPage;
import com.pulkit.shutterstock.presentation.commons.FooterState;
import com.pulkit.shutterstock.presentation.commons.SampleDataReader;
import com.pulkit.shutterstock.presentation.commons.SchedulerProvider;
import com.pulkit.shutterstock.presentation.commons.TestObserver;
import com.pulkit.shutterstock.presentation.image.ImageSearchViewModel;
import com.pulkit.shutterstock.presentation.image.entity.Image;
import io.reactivex.Single;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
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
  SampleDataReader sampleData;

  private ShutterPage page1;

  @Before
  public void setup() throws IOException {
    DaggerUnitTestComponent.builder().build().inject(this);
    MockitoAnnotations.initMocks(this);
    page1 = sampleData.page1();
  }

  @Test
  public void when_first_page_fetcher_success_no_error() {
    when(fetcher.search(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
        .thenReturn(Single.just(page1));

    ImageSearchViewModel viewModel = new ImageSearchViewModel(fetcher, schedulerProvider);

    TestObserver<String> errorObserver = new TestObserver();
    TestObserver<List<Image>> imagesObserver = new TestObserver<>();
    TestObserver<Boolean> progressObserver = new TestObserver<>();
    TestObserver<FooterState> footerStateObserver = new TestObserver<>();

    viewModel.errorMessage.observeForever(errorObserver);
    viewModel.images.observeForever(imagesObserver);
    viewModel.loadProgress.observeForever(progressObserver);
    viewModel.footerState.observeForever(footerStateObserver);

    viewModel.search(UUID.randomUUID().toString());


    assertEquals(errorObserver.getObservedValues().size(), 0);
    assertEquals(imagesObserver.getLatest().size(), page1.getData().size());
    assertEquals(imagesObserver.getLatest().get(0), new Image(page1.getData().get(0)));
    assertEquals(progressObserver.getObservedValues(), Arrays.asList(false, false, true, false)); // initial false, hide progress, show, hide
    assertEquals(footerStateObserver.getLatest(), FooterState.NONE);
  }

  @Test
  public void when_first_page_fetcher_error_show_message() {
    when(fetcher.search(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
        .thenReturn(Single.error(new Exception("message")));

    ImageSearchViewModel viewModel = new ImageSearchViewModel(fetcher, schedulerProvider);

    TestObserver<String> errorObserver = new TestObserver();
    TestObserver<List<Image>> imagesObserver = new TestObserver<>();
    TestObserver<Boolean> progressObserver = new TestObserver<>();
    TestObserver<FooterState> footerStateObserver = new TestObserver<>();

    viewModel.errorMessage.observeForever(errorObserver);
    viewModel.images.observeForever(imagesObserver);
    viewModel.loadProgress.observeForever(progressObserver);
    viewModel.footerState.observeForever(footerStateObserver);

    viewModel.search(UUID.randomUUID().toString());


    assertEquals(errorObserver.getObservedValues().size(), 1);
    assertEquals(errorObserver.getLatest(), "message");
    assertEquals(imagesObserver.getLatest().size(), 0);
    assertEquals(progressObserver.getLatest(), false);
    assertEquals(footerStateObserver.getLatest(), FooterState.NONE);
  }
}
