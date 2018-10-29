package com.pulkit.shutterstock.app;

import android.app.Activity;
import com.pulkit.shutterstock.app.di.component.AppComponent;
import com.pulkit.shutterstock.app.di.component.DaggerAppComponent;
import com.pulkit.shutterstock.presentation.commons.SchedulerProvider;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DispatchingAndroidInjector;
import javax.inject.Inject;

public class ShutterApplication extends DaggerApplication {

  private AppComponent appComponent = DaggerAppComponent.builder().app(this).build();
  @Inject
  DispatchingAndroidInjector<Activity> activityInjector;
  @Inject
  AppMemoryState appMemoryState;
  @Inject
  SchedulerProvider schedulerProvider;

  @Override
  public void onCreate() {
    super.onCreate();
  }


  @Override
  public void onTrimMemory(int level) {
    super.onTrimMemory(level);
    if (level == TRIM_MEMORY_RUNNING_LOW || level == TRIM_MEMORY_RUNNING_CRITICAL ||
        level == TRIM_MEMORY_COMPLETE) {
      appMemoryState.setLow();
      Picasso old = Picasso.get();
      Picasso.setSingletonInstance(new Picasso.Builder(this)
          .memoryCache(new LruCache(1))
          // no cache needed as no cache strategy to be used if app memory state is low.
          .build());
      old.shutdown(); // release cache to get more memory
    }
  }

  @Override
  protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    return appComponent;
  }

}
