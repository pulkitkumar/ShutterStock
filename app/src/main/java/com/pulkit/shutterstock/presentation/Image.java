package com.pulkit.shutterstock.presentation;

import android.os.Parcel;
import android.os.Parcelable;
import com.pulkit.shutterstock.domain.entity.ShutterData;
import java.util.Objects;

public class Image implements Parcelable {

  private final String id;
  private final String url;

  public Image(ShutterData data) {
    this.id = data.getId();
    this.url = data.getAssets().getHugeThumb().getUrl();
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Image image = (Image) o;
    return Objects.equals(id, image.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public boolean deepEquals(Image image) {
    return Objects.equals(id, image.id) && Objects.equals(url, image.url);
  }
}
