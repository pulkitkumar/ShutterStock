package com.pulkit.shutterstock.presentation.commons;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

/**
 * Provides schedulers for the app.
 */
public class DefaultSchedulerProvider implements SchedulerProvider {

  @Inject
  public DefaultSchedulerProvider () { }

  @Override
  public Scheduler getBgPool() {
    return Schedulers.io();
  }

  @Override
  public Scheduler mainThread() {
    return AndroidSchedulers.mainThread();
  }

  @Override
  public Scheduler getAppPool() {
    return Schedulers.computation();
  }
}
