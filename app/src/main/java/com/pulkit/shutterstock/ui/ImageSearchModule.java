package com.pulkit.shutterstock.ui;

import android.arch.lifecycle.ViewModelProvider;
import com.pulkit.shutterstock.presentation.ImageSearchViewModel;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class ImageSearchModule {

  @Binds
  public abstract ViewModelProvider.Factory provideFactory(ImageSearchViewModel.Factory factory);
}
