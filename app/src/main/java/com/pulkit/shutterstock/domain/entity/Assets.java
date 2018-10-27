package com.pulkit.shutterstock.domain.entity;

public class Assets {

  private final Asset preview;
  private final Asset smallThumb;
  private final Asset largeThumb;
  private final Asset hugeThumb;

  public Assets(Asset preview, Asset smallThumb,
      Asset largeThumb, Asset hugeThumb) {
    this.preview = preview;
    this.smallThumb = smallThumb;
    this.largeThumb = largeThumb;
    this.hugeThumb = hugeThumb;
  }

  public Asset getPreview() {
    return preview;
  }

  public Asset getSmallThumb() {
    return smallThumb;
  }

  public Asset getLargeThumb() {
    return largeThumb;
  }

  public Asset getHugeThumb() {
    return hugeThumb;
  }
}
