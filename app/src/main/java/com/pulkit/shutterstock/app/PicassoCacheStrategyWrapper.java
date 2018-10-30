package com.pulkit.shutterstock.app;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This class is to handle no cache strategy for picasso in low memory conditions.
 */
@Singleton
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
