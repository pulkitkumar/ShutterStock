package com.pulkit.shutterstock.app.di.component;

import android.app.Application;
import com.pulkit.shutterstock.app.ShutterApplication;
import com.pulkit.shutterstock.app.di.module.ActivityBindingModule;
import com.pulkit.shutterstock.app.di.module.AppModule;
import com.pulkit.shutterstock.data.NetworkModule;
import com.pulkit.shutterstock.domain.DomainModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

@Component(modules = {
    DomainModule.class,
    NetworkModule.class,
    AppModule.class,
    ActivityBindingModule.class,
    AndroidSupportInjectionModule.class
})
@Singleton
public interface AppComponent extends AndroidInjector<ShutterApplication> {

  @Component.Builder
  interface Builder {
    @BindsInstance Builder app(Application application);
    AppComponent build();
  }
}
