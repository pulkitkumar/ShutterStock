package com.pulkit.shutterstock.ui;

import android.support.v7.util.DiffUtil;
import com.pulkit.shutterstock.presentation.Image;
import java.util.List;

public class ImageDiffUtilCallback extends DiffUtil.Callback {

  private final List<Image> oldList;
  private final List<Image> newList;

  ImageDiffUtilCallback(List<Image> oldList, List<Image> newList) {
    this.oldList = oldList;
    this.newList = newList;
  }

  @Override
  public int getOldListSize() {
    return oldList.size();
  }

  @Override
  public int getNewListSize() {
    return newList.size();
  }

  @Override
  public boolean areItemsTheSame(int pOld, int pNew) {
    return oldList.get(pOld).equals(newList.get(pNew));
  }

  @Override
  public boolean areContentsTheSame(int pOld, int pNew) {
    return oldList.get(pOld).deepEquals(newList.get(pNew));
  }
}
