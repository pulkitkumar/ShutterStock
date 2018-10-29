package com.pulkit.shutterstock.ui;

import static com.pulkit.shutterstock.ui.ImageListAdapter.LOADING_ELEMENT_COUNT;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import javax.inject.Inject;

public class LinearLayoutEndlessScrollListener extends OnScrollListenerWithCallback {

  // two rows (of size 3) buffer items before callback.
  public static final int BUFFER_ITEMS = 6;

  private final LinearLayoutManager layoutManager;
  private boolean loading = true;
  private int previousTotalItemCount = 0;

  @Inject
  public LinearLayoutEndlessScrollListener(LinearLayoutManager layoutManager) {
    this.layoutManager = layoutManager;
  }

  @Override
  public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);
    if (dy > 0) { // only if it is scrolled down.
      int totalItemCount = layoutManager.getItemCount();
      int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

      // when new items are added set loading false and update item count.
      if (loading && (totalItemCount > previousTotalItemCount + LOADING_ELEMENT_COUNT)) { // added 1 for loading element.
        loading = false;
        previousTotalItemCount = totalItemCount;
      }

      // call callback function when reaching towards the end of the list.
      if (!loading && (lastVisibleItemPosition + BUFFER_ITEMS) > totalItemCount) {
        if (callback != null) {
          callback.onReachedBottom();
        }
        loading = true;
      }
    }
  }

  // to be called when we are making a new search.
  @Override
  public void reset() {
    this.previousTotalItemCount = 0;
    this.loading = true;
  }

}
