package com.pulkit.shutterstock.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
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
  private ImageSearchViewModel viewModel;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);
    viewModel = ViewModelProviders.of(this, factory).get(ImageSearchViewModel.class);

    final ProgressBar progressBar = findViewById(R.id.progressBar);
    final RecyclerView recyclerView = findViewById(R.id.recyclerView);
    final SearchView searchView = findViewById(R.id.searchView);

    GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);
    EndlessScrollListener scrollListener = new EndlessScrollListener(layoutManager) {
      @Override
      public void onLoadMore() {
        viewModel.loadNextPage();
      }
    };

    recyclerView.addOnScrollListener(scrollListener);

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
        return true;
      }

      @Override
      public boolean onQueryTextChange(String s) {
        return false;
      }
    });
  }
}
