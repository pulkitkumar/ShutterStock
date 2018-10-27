package com.pulkit.shutterstock.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

  public static final int INITIAL_PAGE = 1;
  // two rows (of size 3) buffer items before callback.
  public static final int BUFFER_ITEMS = 6;
  private static final int LOADING_ELEMENT = 1;

  private final GridLayoutManager layoutManager;
  private boolean loading = true;
  private int page = INITIAL_PAGE;
  private int previousTotalItemCount = 0;

  public EndlessScrollListener(GridLayoutManager layoutManager) {
    this.layoutManager = layoutManager;
  }

  @Override
  public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);
    if (dy > 0) { // only if it is scrolled down.
      int totalItemCount = layoutManager.getItemCount();
      int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

      // when new items are added set loading false and update item count.
      if (loading && (totalItemCount > previousTotalItemCount + LOADING_ELEMENT)) { // added 1 for loading element.
        loading = false;
        previousTotalItemCount = totalItemCount;
      }

      // increment page number and call callback function when reaching towards the end of the list.
      if (!loading && (lastVisibleItemPosition + BUFFER_ITEMS) > totalItemCount) {
        page++;
        onLoadMore();
        loading = true;
      }
    }
  }

  // to be called when we are making a new search.
  public void reset() {
    this.page = INITIAL_PAGE;
    this.previousTotalItemCount = 0;
    this.loading = true;
  }

  // callback function with page number. Will be called only once per page.
  public abstract void onLoadMore();

}
