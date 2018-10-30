package com.pulkit.shutterstock.utils;

import com.pulkit.shutterstock.presentation.commons.SchedulerProvider;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.TestScheduler;

public class TestSchedulerProvider implements SchedulerProvider {

  private final TestScheduler testScheduler;

  public TestSchedulerProvider(TestScheduler testScheduler) {
    this.testScheduler = testScheduler;
  }

  @Override
  public Scheduler getBgPool() {
    return testScheduler;
  }

  @Override
  public Scheduler mainThread() {
    return AndroidSchedulers.mainThread();
  }

  @Override
  public Scheduler getAppPool() {
    return testScheduler;
  }
}
