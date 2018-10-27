package com.pulkit.shutterstock.presentation;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {

  private final String id;
  private final String url;

  public Image(String id, String url) {
    this.id = id;
    this.url = url;
  }

  protected Image(Parcel in) {
    id = in.readString();
    url = in.readString();
  }

  public static final Creator<Image> CREATOR = new Creator<Image>() {
    @Override
    public Image createFromParcel(Parcel in) {
      return new Image(in);
    }

    @Override
    public Image[] newArray(int size) {
      return new Image[size];
    }
  };

  public String getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(url);
  }
}
