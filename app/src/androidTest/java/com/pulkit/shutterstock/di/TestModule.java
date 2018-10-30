package com.pulkit.shutterstock.di;

import com.google.gson.Gson;
import com.pulkit.shutterstock.domain.ShutterSearcher;
import com.pulkit.shutterstock.utils.SampleDataReader;
import com.pulkit.shutterstock.utils.TestSchedulerProvider;
import com.pulkit.shutterstock.presentation.commons.SchedulerProvider;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.TestScheduler;
import javax.inject.Singleton;
import org.mockito.Mockito;

@Module
public abstract class TestModule {

  @Provides
  @Singleton
  static ShutterSearcher shutterSearcher() {
    return Mockito.mock(ShutterSearcher.class);
  }

  @Provides
  @Singleton
  static TestScheduler testScheduler() {
    return new TestScheduler();
  }

  @Provides
  @Singleton
  static SchedulerProvider schedulerProvider(TestScheduler testScheduler) {
    return new TestSchedulerProvider(testScheduler);
  }

  @Provides
  @Singleton
  static SampleDataReader reader(Gson gson) {
    return new SampleDataReader(gson);
  }
}
