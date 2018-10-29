package com.pulkit.shutterstock.di;

import com.pulkit.shutterstock.data.NetworkModule;
import com.pulkit.shutterstock.presentation.ImageSearchViewModelTest;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
    NetworkModule.class,
    UnitTestModule.class
})
public interface UnitTestComponent {

  void inject(ImageSearchViewModelTest test);

}
