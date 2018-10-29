package com.pulkit.shutterstock.app;

import javax.inject.Inject;

public class PicassoCacheStrategyWrapper {

  private boolean isNoCacheStrategy;

  @Inject
  public PicassoCacheStrategyWrapper() {
    isNoCacheStrategy = false;
  }

  public void setNoCacheStrategy() {
    isNoCacheStrategy = true;
  }

  public boolean isNoCacheStrategy() {
    return isNoCacheStrategy;
  }
}
