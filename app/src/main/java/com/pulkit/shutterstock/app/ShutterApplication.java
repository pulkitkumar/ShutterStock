package com.pulkit.shutterstock.app;

import com.pulkit.shutterstock.app.di.component.AppComponent;
import com.pulkit.shutterstock.app.di.component.DaggerAppComponent;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import javax.inject.Inject;

public class ShutterApplication extends DaggerApplication {

  private AppComponent appComponent = DaggerAppComponent.builder().app(this).build();

  @Inject
  AppMemoryState appMemoryState;

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
      Picasso.setSingletonInstance(new Picasso.Builder(this).memoryCache(new LruCache(calculateLowMemoryCacheSize())).build());
      old.shutdown(); // release cache to get more memory
    }
  }

  private int calculateLowMemoryCacheSize() {
    return (int) Runtime.getRuntime().freeMemory() / 50; // 2% of the available memory
  }

  @Override
  protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    return appComponent;
  }

}
