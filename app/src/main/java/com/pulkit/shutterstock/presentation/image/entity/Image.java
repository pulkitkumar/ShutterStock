package com.pulkit.shutterstock.presentation.image.entity;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil.ItemCallback;
import com.pulkit.shutterstock.domain.entity.ShutterData;
import java.util.Objects;

/**
 * Immutable Data class to represent Image to be shown on UI.
 * Any domain object that needs to be shown as an image in the UI should be converted into this class.
 * Conversions can be added with different constructors.
 */
public class Image {

  /**
   * Item callback to be used by AsyncListDiffer in any Adapter using this as ui element in list.
   */
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

  public String getId() {
    return id;
  }

  public String getUrl() {
    return url;
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
