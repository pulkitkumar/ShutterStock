package com.pulkit.shutterstock.domain;

import dagger.Binds;
import dagger.Module;
import javax.inject.Singleton;

/**
 * Provides dependencies from domain package to be used in App or User component.
 */
@Module
public abstract class DomainModule {

  @Binds
  @Singleton
  abstract ShutterSearcher searcher(ShutterSearcherImpl shutterSearcher);
}
