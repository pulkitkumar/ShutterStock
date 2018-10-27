package com.pulkit.shutterstock.ui;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.pulkit.shutterstock.R;
import com.pulkit.shutterstock.app.AppMemoryState;
import com.pulkit.shutterstock.presentation.Image;
import com.pulkit.shutterstock.presentation.commons.FooterState;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ImageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int TYPE_IMAGE = 101;
  private static final int TYPE_ERROR = 102;
  private static final int TYPE_LOADING = 103;
  private final List<Image> images;
  private final AppMemoryState appMemory;
  private FooterState footer;

  @Inject
  public ImageListAdapter(AppMemoryState appMemory) {
    this.appMemory = appMemory;
    images = new ArrayList<>();
    footer = FooterState.NONE;
  }

  public void updateList(List<Image> newList) {
    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ImageDiffUtilCallback(images, newList));
    images.clear();
    images.addAll(newList);
    diffResult.dispatchUpdatesTo(this);
  }

  void setFooter(FooterState f) {
    FooterState old = footer;
    this.footer = f;
    if (old == FooterState.NONE && footer != FooterState.NONE) {
      notifyItemInserted(images.size() +1);
    } else if (old != FooterState.NONE && footer == FooterState.NONE){
      notifyItemRemoved(images.size() +1);
    }
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (viewType == TYPE_IMAGE) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
      return new ImageViewHolder(appMemory, view);
    } else {
      return null;
    }
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    if (getItemViewType(position) == TYPE_IMAGE) {
      ((ImageViewHolder)holder).bind(images.get(position));
    }
  }

  @Override
  public int getItemCount() {
    if (footer == FooterState.NONE) {
      return images.size();
    } else {
      return images.size() + 1;
    }
  }

  @Override
  public int getItemViewType(int position) {
    if(position < images.size()) {
      return TYPE_IMAGE;
    } else {
      if (footer == FooterState.ERROR) {
        return TYPE_ERROR;
      } else if (footer == FooterState.LOADING) {
        return TYPE_LOADING;
      } else {
        throw new RuntimeException("Adapter size can't be bigger if footer is None");
      }
    }
  }

  public static class ImageViewHolder extends RecyclerView.ViewHolder {

    private final ImageView imageView;
    private final AppMemoryState appMemoryState;

    public ImageViewHolder(AppMemoryState appMemoryState, @NonNull View itemView) {
      super(itemView);
      this.appMemoryState = appMemoryState;
      imageView = itemView.findViewById(R.id.image);
    }

    private void bind(Image image) {
      RequestCreator requestCreator = Picasso.get()
          .load(image.getUrl())
          .placeholder(R.drawable.ic_placeholder)
          .fit()
          .centerCrop();
      if (appMemoryState.isLow()) {
        requestCreator.memoryPolicy(MemoryPolicy.NO_STORE);
      }
      requestCreator.into(imageView);
    }
  }
}
