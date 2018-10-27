package com.pulkit.shutterstock.domain;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class DomainModule {

  @Provides
  @Singleton
  ShutterSearcher searcher(ShutterRepository repository) {
    return new ShutterSearcher(repository);
  }
}
