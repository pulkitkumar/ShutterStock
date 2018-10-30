package com.pulkit.shutterstock.di;

import com.google.gson.Gson;
import com.pulkit.shutterstock.presentation.commons.SampleData;
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
  SampleData testDataFetcher(Gson gson) {
    return new SampleData(gson);
  }
}
