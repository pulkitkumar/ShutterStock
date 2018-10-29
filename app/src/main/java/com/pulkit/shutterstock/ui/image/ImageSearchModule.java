package com.pulkit.shutterstock.ui.image;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;
import com.pulkit.shutterstock.app.PicassoCacheStrategyWrapper;
import com.pulkit.shutterstock.app.di.scope.ActivityScope;
import com.pulkit.shutterstock.presentation.image.ImageSearchViewModel;
import com.pulkit.shutterstock.ui.commons.LinearLayoutEndlessScrollListener;
import com.pulkit.shutterstock.ui.commons.OnScrollListenerWithCallback;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Maps/ Provides dependencies for {@link ImageSearchActivity}
 */
@Module
public abstract class ImageSearchModule {

  @Provides
  @ActivityScope
  static LayoutManager layoutManager(ImageSearchActivity activity, ImageListAdapter adapter) {
    GridLayoutManager layoutManager = new GridLayoutManager(activity, 2);
    layoutManager.setSpanSizeLookup(ImageListAdapter.getSpanLookup(adapter));
    return layoutManager;
  }

  @Provides
  @ActivityScope
  static OnScrollListenerWithCallback provideScrollListener(LayoutManager layoutManager) {
    return new LinearLayoutEndlessScrollListener((LinearLayoutManager) layoutManager);
  }

  @Provides
  @ActivityScope
  static ImageListAdapter adapter(PicassoCacheStrategyWrapper picassoCacheStrategyWrapper) {
    return new ImageListAdapter(picassoCacheStrategyWrapper);
  }

  @Binds
  @ActivityScope
  public abstract ViewModelProvider.Factory provideFactory(ImageSearchViewModel.Factory factory);

}
