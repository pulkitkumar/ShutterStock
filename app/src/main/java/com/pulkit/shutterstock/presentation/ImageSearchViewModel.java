package com.pulkit.shutterstock.presentation;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.pulkit.shutterstock.domain.ShutterSearcher;
import javax.inject.Inject;

public class ImageSearchViewModel extends ViewModel {

  private final ShutterSearcher searcher;
  private final SchedulerProvider schedulerProvider;

  public ImageSearchViewModel(ShutterSearcher searcher, SchedulerProvider provider) {
    this.searcher = searcher;
    this.schedulerProvider = provider;
  }


  public static class Factory implements ViewModelProvider.Factory {
    private final ShutterSearcher searcher;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public Factory(ShutterSearcher searcher, SchedulerProvider schedulerProvider) {
      this.searcher = searcher;
      this.schedulerProvider = schedulerProvider;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
      if (modelClass.isAssignableFrom(ImageSearchViewModel.class)) {
        return (T) new ImageSearchViewModel(searcher, schedulerProvider);
      } else {
        throw new RuntimeException("Can not assign a view model for class "+ modelClass.getName());
      }
    }
  }
}
