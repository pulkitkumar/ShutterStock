package com.pulkit.shutterstock.presentation.commons;

import io.reactivex.Scheduler;

/**
 * Interface to provide schedulers for all Rx thread changes.
 */
public interface SchedulerProvider {

  Scheduler getBgPool();

  Scheduler mainThread();

  Scheduler getAppPool();
}
