package com.pulkit.shutterstock.presentation.commons;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TestObserver<T> implements Observer<T> {

  private List<T> observedValues = new ArrayList<>();

  @Override
  public void onChanged(@Nullable T t) {
    observedValues.add(t);
  }

  public List<T> getObservedValues() {
    return observedValues;
  }

  public T getLatest() {
    return observedValues.get(observedValues.size() -1);
  }
}
