package com.pulkit.shutterstock.presentation.commons;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

  Scheduler getBgPool();

  Scheduler mainThread();

  Scheduler getAppPool();
}
