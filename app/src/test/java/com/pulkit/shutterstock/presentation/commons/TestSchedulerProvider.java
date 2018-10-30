package com.pulkit.shutterstock.presentation.commons;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Trampoline all Schedulers.
 */
public class TestSchedulerProvider implements SchedulerProvider {

  @Override
  public Scheduler getBgPool() {
    return Schedulers.trampoline();
  }

  @Override
  public Scheduler mainThread() {
    return Schedulers.trampoline();
  }

  @Override
  public Scheduler getAppPool() {
    return Schedulers.trampoline();
  }
}
