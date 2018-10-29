package com.pulkit.shutterstock.app.di.module;

import com.pulkit.shutterstock.BuildConfig;
import com.pulkit.shutterstock.data.qualifiers.BaseUrl;
import com.pulkit.shutterstock.data.qualifiers.Username;
import com.pulkit.shutterstock.data.qualifiers.Password;
import com.pulkit.shutterstock.presentation.commons.DefaultSchedulerProvider;
import com.pulkit.shutterstock.presentation.commons.SchedulerProvider;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public abstract class AppModule {

  @Binds
  @Singleton
  abstract SchedulerProvider providerSchedulerProvider(DefaultSchedulerProvider provider);

  @Provides
  @Singleton
  @BaseUrl
  static String baseUrl() {
    return BuildConfig.BASE_URL;
  }

  @Provides
  @Singleton
  @Username
  static String clientId() {
    return BuildConfig.CLIENT_ID;
  }

  @Provides
  @Singleton
  @Password
  static String clientSecret() {
    return BuildConfig.CLIENT_SECRET;
  }

  @Provides
  @Singleton
  static boolean isDebug() {
    return BuildConfig.DEBUG;
  }
}
