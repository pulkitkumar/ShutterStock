package com.pulkit.shutterstock.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.pulkit.shutterstock.presentation.ImageSearchViewModel;
import dagger.android.support.DaggerAppCompatActivity;
import javax.inject.Inject;

public class ImageSearchActivity extends DaggerAppCompatActivity {

  @Inject
  ImageSearchViewModel.Factory factory;
  private ImageSearchViewModel viewModel;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewModel = ViewModelProviders.of(this, factory).get(ImageSearchViewModel.class);
  }
}
