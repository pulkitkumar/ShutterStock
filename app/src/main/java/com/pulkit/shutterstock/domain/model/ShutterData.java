package com.pulkit.shutterstock.domain.model;

import java.util.Objects;

class ShutterData {

  private final String id;
  private final double aspect;
  private final Asset preview;
  private final Asset smallThumb;
  private final Asset largeThumb;
  private final Asset hugeThumb;
  private final Contributor contributor;
  private final String description;
  private final String imageType;
  private final String mediaType;


  public ShutterData(String id, double aspect, Asset preview,
      Asset smallThumb, Asset largeThumb, Asset hugeThumb,
      Contributor contributor, String description, String imageType, String mediaType) {
    this.id = id;
    this.aspect = aspect;
    this.preview = preview;
    this.smallThumb = smallThumb;
    this.largeThumb = largeThumb;
    this.hugeThumb = hugeThumb;
    this.contributor = contributor;
    this.description = description;
    this.imageType = imageType;
    this.mediaType = mediaType;
  }

  public String getId() {
    return id;
  }

  public double getAspect() {
    return aspect;
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

  public Contributor getContributor() {
    return contributor;
  }

  public String getDescription() {
    return description;
  }

  public String getImageType() {
    return imageType;
  }

  public String getMediaType() {
    return mediaType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShutterData that = (ShutterData) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id);
  }
}
