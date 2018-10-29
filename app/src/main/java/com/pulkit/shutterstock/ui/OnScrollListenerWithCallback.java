package com.pulkit.shutterstock.ui;

import android.support.v7.widget.RecyclerView;

public abstract class OnScrollListenerWithCallback extends RecyclerView.OnScrollListener {

  public interface ReachedBottomCallback {
    void onReachedBottom();
  }

  ReachedBottomCallback callback;

  public void setCallback(ReachedBottomCallback callback) {
    this.callback = callback;
  }

  abstract void reset();
}
