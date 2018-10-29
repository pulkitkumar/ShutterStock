package com.pulkit.shutterstock.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;
import com.pulkit.shutterstock.app.di.scope.ActivityScope;
import com.pulkit.shutterstock.presentation.ImageSearchViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ImageSearchModule {

  @Provides
  @ActivityScope
  static LayoutManager layoutManager(ImageSearchActivity activity) {
    System.out.println(">>1");
    return new GridLayoutManager(activity, 2);
  }

  @Provides
  @ActivityScope
  static OnScrollListenerWithCallback provideScrollListener(LayoutManager layoutManager) {
    return new LinearLayoutEndlessScrollListener((LinearLayoutManager) layoutManager);
  }

  @Binds
  @ActivityScope
  public abstract ViewModelProvider.Factory provideFactory(ImageSearchViewModel.Factory factory);

}
