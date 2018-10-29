package com.pulkit.shutterstock.presentation;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil.ItemCallback;
import com.pulkit.shutterstock.domain.entity.ShutterData;
import java.util.Objects;

/**
 * Immutable Data class to represent Image to be shown on UI.
 * Any domain object that needs to be shown as an image in the UI should be converted into this class.
 * Conversions can be added with different constructors.
 */
public class Image implements Parcelable {

  public static final ItemCallback<Image> DIFF_CALLBACK = new ItemCallback<Image>() {
    @Override
    public boolean areItemsTheSame(@NonNull Image image, @NonNull Image newImage) {
      return image.equals(newImage);
    }

    @Override
    public boolean areContentsTheSame(@NonNull Image image, @NonNull Image newImage) {
      return image.deepEquals(newImage);
    }
  };

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
