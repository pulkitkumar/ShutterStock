package com.pulkit.shutterstock.di;

import com.google.gson.Gson;
import com.pulkit.shutterstock.presentation.commons.SampleDataReader;
import com.pulkit.shutterstock.presentation.commons.SchedulerProvider;
import com.pulkit.shutterstock.presentation.commons.TestSchedulerProvider;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class UnitTestModule {

  @Provides
  @Singleton
  SchedulerProvider schedulerProvider() {
    return new TestSchedulerProvider();
  }

  @Provides
  @Singleton
  SampleDataReader testDataFetcher(Gson gson) {
    return new SampleDataReader(gson);
  }
}
