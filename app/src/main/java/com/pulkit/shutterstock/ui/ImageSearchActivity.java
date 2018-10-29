package com.pulkit.shutterstock.ui;

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
import com.pulkit.shutterstock.presentation.ImageSearchViewModel;
import dagger.android.support.DaggerAppCompatActivity;
import javax.inject.Inject;

public class ImageSearchActivity extends DaggerAppCompatActivity {

  @Inject
  ViewModelProvider.Factory factory;
  @Inject
  ImageListAdapter adapter;
  @Inject
  LayoutManager layoutManager;
  @Inject
  OnScrollListenerWithCallback scrollListener;

  private ImageSearchViewModel viewModel;

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

    viewModel.loadProgress.observe(this, progress -> {
      if (progress) {
        progressBar.setVisibility(View.VISIBLE);
      } else {
        progressBar.setVisibility(View.GONE);
      }
    });

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
        return false;
      }
    });
  }

  public void closeSoftKeyboard(View view) {
    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
  }
}
