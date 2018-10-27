package com.pulkit.shutterstock.app;

import com.pulkit.shutterstock.app.di.component.AppComponent;
import com.pulkit.shutterstock.app.di.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class ShutterApplication extends DaggerApplication {

  private AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    appComponent = DaggerAppComponent.builder().app(this).build();
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
  }

  @Override
  public void onTrimMemory(int level) {
    super.onTrimMemory(level);
  }

  @Override
  protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    return appComponent;
  }

}
