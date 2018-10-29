package com.pulkit.shutterstock.presentation.commons;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This MutableLiveData sends an event to the observer only once.
 * This will be used when we need to send a single live event like error,
 * that need not be shown again on screen rotation or any other re-subscription.
 */
public class OneTimeLiveData<T> extends MutableLiveData<T> {

  private AtomicBoolean isObserved = new AtomicBoolean();

  public OneTimeLiveData() {
    isObserved.set(true);
  }

  @Override
  public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
    super.observe(owner, observer);
    if (!isObserved.get()) {
      observer.onChanged(getValue());
      isObserved.set(true);
    }
  }

  @Override
  public void setValue(T value) {
    super.setValue(value);
    isObserved.set(false);
  }
}
