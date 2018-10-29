package com.pulkit.shutterstock.domain.entity;

import java.util.Objects;
/**
 * Immutable data class to represent ShutterStock search data.
 */
public class ShutterData {

  private final String id;
  private final double aspect;
  private final Assets assets;
  private final Contributor contributor;
  private final String description;
  private final String imageType;
  private final String mediaType;

  public ShutterData(String id, double aspect, Assets assets,
      Contributor contributor, String description, String imageType, String mediaType) {
    this.id = id;
    this.aspect = aspect;
    this.assets = assets;
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

  public Assets getAssets() {
    return assets;
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
