package com.pulkit.shutterstock.presentation.image;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import com.pulkit.shutterstock.app.di.scope.ActivityScope;
import com.pulkit.shutterstock.domain.ShutterSearcher;
import com.pulkit.shutterstock.domain.entity.ShutterPage;
import com.pulkit.shutterstock.presentation.commons.FooterState;
import com.pulkit.shutterstock.presentation.commons.OneTimeLiveData;
import com.pulkit.shutterstock.presentation.commons.SchedulerProvider;
import com.pulkit.shutterstock.presentation.image.entity.Image;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.processors.PublishProcessor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 * Any search image ui component should use this as view model.
 */
public class ImageSearchViewModel extends ViewModel {

  public static final int INITIAL_PAGE_NUMBER = 1;
  public static final int PAGE_SIZE = 20;
  private static final String EMPTY = "";

  private final ShutterSearcher searcher;
  private final SchedulerProvider schedulerProvider;
  private final PublishProcessor<Boolean> paginator = PublishProcessor.create();
  private List<Image> uiImages = new ArrayList<>();
  private String query = EMPTY;
  private int pageNumber = INITIAL_PAGE_NUMBER;
  private Disposable disposable;

  public final MutableLiveData<List<Image>> images = new MutableLiveData<>();
  public final OneTimeLiveData<String> errorMessage = new OneTimeLiveData<>();
  public final MutableLiveData<Boolean> loadProgress = new MutableLiveData<>();
  public final MutableLiveData<FooterState> footerState = new MutableLiveData<>();

  public ImageSearchViewModel(ShutterSearcher searcher, SchedulerProvider provider) {
    this.searcher = searcher;
    this.schedulerProvider = provider;
    this.disposable = bindSearch();
    images.setValue(Collections.emptyList());
    hideProgress();
  }

  @MainThread
  public void search(String query) {
    this.query = query;
    pageNumber = INITIAL_PAGE_NUMBER;
    uiImages = new ArrayList<>();
    images.setValue(Collections.emptyList());
    hideProgress();
    paginator.onNext(true);
  }

  @MainThread
  public void loadNextPage() {
    paginator.onNext(true);
  }

  private Disposable bindSearch() {
    return paginator
        .filter(__ -> (query != null && !EMPTY.equals(query)))
        .observeOn(schedulerProvider.getBgPool())
        .doOnNext(__ -> showProgress())
        .switchMapSingle(__ -> searcher.search(query, pageNumber, PAGE_SIZE)
            .onErrorReturn(e -> {
              // handle error state here and return an empty list.
              errorMessage.postValue(e.getMessage());
              if (pageNumber > INITIAL_PAGE_NUMBER) {
                footerState.postValue(FooterState.ERROR);
              } else {
                loadProgress.setValue(false);
              }
              return ShutterPage.emptyPage();
            }))
        .observeOn(schedulerProvider.getAppPool())
        .switchMapSingle(this::mapToUiState)
        .observeOn(schedulerProvider.mainThread())
        .subscribe(imagesForPage -> {
          if (imagesForPage.size() > 0) {
            hideProgress();
            pageNumber++;
            uiImages.addAll(imagesForPage);
            images.setValue(Collections.unmodifiableList(uiImages));
          }
        });
  }

  @MainThread
  private void hideProgress() {
    loadProgress.setValue(false);
    footerState.setValue(FooterState.NONE);
  }

  private void showProgress() {
    if (pageNumber == INITIAL_PAGE_NUMBER) {
      loadProgress.postValue(true);
      footerState.postValue(FooterState.NONE);
    } else {
      loadProgress.postValue(false);
      footerState.postValue(FooterState.LOADING);
    }
  }

  private Single<List<Image>> mapToUiState(ShutterPage shutterPage) {
    return Observable.fromIterable(shutterPage.getData()).map(Image::new).toList();
  }

  @Override
  protected void onCleared() {
    super.onCleared();
    if (!disposable.isDisposed()) {
      disposable.dispose();
    }
  }

  @ActivityScope
  public static class Factory implements ViewModelProvider.Factory {

    private final ShutterSearcher searcher;
    private final SchedulerProvider provider;

    @Inject
    public Factory(ShutterSearcher searcher, SchedulerProvider provider) {
      this.searcher = searcher;
      this.provider = provider;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
      if (modelClass.isAssignableFrom(ImageSearchViewModel.class)) {
        return (T) new ImageSearchViewModel(searcher, provider);
      } else {
        throw new IllegalStateException("Can not provide view model for " + modelClass.getName());
      }
    }
  }
}
