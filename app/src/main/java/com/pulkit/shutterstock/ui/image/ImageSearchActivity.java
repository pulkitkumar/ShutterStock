package com.pulkit.shutterstock.ui.image;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import com.pulkit.shutterstock.R;
import com.pulkit.shutterstock.presentation.commons.SchedulerProvider;
import com.pulkit.shutterstock.presentation.image.ImageSearchViewModel;
import com.pulkit.shutterstock.ui.commons.OnScrollListenerWithCallback;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.processors.PublishProcessor;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

public class ImageSearchActivity extends DaggerAppCompatActivity {

  public static final int DEBOUNCE_TIMEOUT = 300;
  @Inject
  ViewModelProvider.Factory factory;
  @Inject
  ImageListAdapter adapter;
  @Inject
  LayoutManager layoutManager;
  @Inject
  OnScrollListenerWithCallback scrollListener;
  @Inject
  SchedulerProvider schedulerProvider;

  private ImageSearchViewModel viewModel;
  private PublishProcessor<String> searchTermPublisher = PublishProcessor.create();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);

    viewModel = ViewModelProviders.of(this, factory).get(ImageSearchViewModel.class);

    final ProgressBar progressBar = findViewById(R.id.progressBar);
    final RecyclerView recyclerView = findViewById(R.id.recyclerView);
    final SearchView searchView = findViewById(R.id.searchView);

    recyclerView.setLayoutManager(layoutManager);
    recyclerView.addOnScrollListener(scrollListener);
    recyclerView.setAdapter(adapter);

    scrollListener.setCallback(() -> viewModel.loadNextPage());
    adapter.setRetryClickListener(__ -> viewModel.loadNextPage());

    viewModel.loadProgress.observe(this, progress -> showProgress(progress, progressBar));

    viewModel.footerState.observe(this, adapter::setFooter);
    viewModel.images.observe(this, adapter::updateList);
    viewModel.errorMessage.observe(this,
        message -> Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show());

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String s) {
        scrollListener.reset();
        viewModel.search(s);
        closeSoftKeyboard(searchView);
        return true;
      }

      @Override
      public boolean onQueryTextChange(String s) {
        searchTermPublisher.onNext(s);
        return true;
      }
    });

    searchTermPublisher
        .debounce(DEBOUNCE_TIMEOUT, TimeUnit.MILLISECONDS)
        .distinctUntilChanged()
        .observeOn(schedulerProvider.mainThread())
        .subscribe(s -> {
          scrollListener.reset();
          viewModel.search(s);
        });
  }

  private void showProgress(boolean progress, ProgressBar progressBar) {
      if (progress) {
        progressBar.setVisibility(View.VISIBLE);
      } else {
        progressBar.setVisibility(View.GONE);
      }
  }

  private void closeSoftKeyboard(View view) {
    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
  }
}
