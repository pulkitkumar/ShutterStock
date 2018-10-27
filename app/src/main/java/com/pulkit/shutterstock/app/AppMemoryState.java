package com.pulkit.shutterstock.app;

import javax.inject.Inject;

public class AppMemoryState {

  private boolean isLow;

  @Inject
  public AppMemoryState() {
    isLow = false;
  }

  public void setLow() {
    isLow = true;
  }

  public boolean isLow() {
    return isLow;
  }
}
