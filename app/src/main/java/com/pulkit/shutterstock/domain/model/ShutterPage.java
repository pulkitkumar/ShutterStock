package com.pulkit.shutterstock.domain.model;

import java.util.List;
import java.util.Objects;

public class ShutterPage {

  private final int page;
  private final int perPage;
  private final long totalCount;
  private final String searchId;
  private final List<ShutterData> data;

  public ShutterPage(int page, int perPage, long totalCount, String searchId,
      List<ShutterData> data) {
    this.page = page;
    this.perPage = perPage;
    this.totalCount = totalCount;
    this.searchId = searchId;
    this.data = data;
  }

  public int getPage() {
    return page;
  }

  public int getPerPage() {
    return perPage;
  }

  public long getTotalCount() {
    return totalCount;
  }

  public String getSearchId() {
    return searchId;
  }

  public List<ShutterData> getData() {
    return data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShutterPage that = (ShutterPage) o;
    return Objects.equals(data, that.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data);
  }
}
