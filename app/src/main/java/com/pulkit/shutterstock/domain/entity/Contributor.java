package com.pulkit.shutterstock.domain.entity;

/**
 * Immutable data class to represent ShutterStock Contributor.
 */
public class Contributor {

  private final String id;

  public Contributor(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
