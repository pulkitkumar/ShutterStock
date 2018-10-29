package com.pulkit.shutterstock.app.di.module;


import com.pulkit.shutterstock.app.di.scope.ActivityScope;
import com.pulkit.shutterstock.ui.ImageSearchActivity;
import com.pulkit.shutterstock.ui.ImageSearchModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

  @ActivityScope
  @ContributesAndroidInjector(modules = {ImageSearchModule.class})
  abstract ImageSearchActivity searchActivityInjector();

}


