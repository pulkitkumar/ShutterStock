package com.pulkit.shutterstock.di;

import android.app.Application;
import com.pulkit.shutterstock.TestApplication;
import com.pulkit.shutterstock.app.di.component.AppComponent;
import com.pulkit.shutterstock.app.di.module.ActivityBindingModule;
import com.pulkit.shutterstock.data.NetworkModule;
import com.pulkit.shutterstock.ui.ImageSearchActivityTest;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
    NetworkModule.class,
    TestModule.class,
    AndroidSupportInjectionModule.class,
    ActivityBindingModule.class
})
public interface TestComponent extends AppComponent {

  void inject(TestApplication testApplication);

  void inject(ImageSearchActivityTest imageSearchActivityTest);

  @Component.Builder
  interface Builder {

    @BindsInstance
    TestComponent.Builder app(Application application);

    TestComponent build();
  }
}
