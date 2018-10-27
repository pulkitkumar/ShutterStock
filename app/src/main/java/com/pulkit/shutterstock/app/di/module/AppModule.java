package com.pulkit.shutterstock.app.di.module;

import com.pulkit.shutterstock.BuildConfig;
import com.pulkit.shutterstock.presentation.SchedulerProvider;
import com.pulkit.shutterstock.presentation.commons.DefaultSchedulerProvider;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
public abstract class AppModule {

  @Binds
  abstract SchedulerProvider providerSchedulerProvider(DefaultSchedulerProvider provider);

  @Provides
  @Singleton
  @Named("baseUrl")
  static String baseUrl() {
    return BuildConfig.BASE_URL;
  }

  @Provides
  @Singleton
  @Named("clientId")
  static String clientId() {
    return BuildConfig.CLIENT_ID;
  }

  @Provides
  @Singleton
  @Named("clientSecret")
  static String clientSecret() {
    return BuildConfig.CLIENT_SECRET;
  }

  @Provides
  @Singleton
  static boolean isDebug() {
    return BuildConfig.DEBUG;
  }
}
