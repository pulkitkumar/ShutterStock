package com.pulkit.shutterstock.domain.entity;

/**
 * Immutable data class to represent ShutterStock Asset.
 */
public class Asset {

  private final int height;
  private final int width;
  private final String url;

  public Asset(int height, int width, String url) {
    this.height = height;
    this.width = width;
    this.url = url;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public String getUrl() {
    return url;
  }
}
