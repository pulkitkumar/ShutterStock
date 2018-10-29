package com.pulkit.shutterstock.ui;

import static com.pulkit.shutterstock.presentation.Image.DIFF_CALLBACK;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.recyclerview.extensions.AsyncListDiffer;
import android.support.v7.util.AdapterListUpdateCallback;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.pulkit.shutterstock.R;
import com.pulkit.shutterstock.app.PicassoCacheStrategyWrapper;
import com.pulkit.shutterstock.presentation.Image;
import com.pulkit.shutterstock.presentation.commons.FooterState;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.List;

/**
 * Recycler view adapter to show image list with a bottom state for loading
 */
public class ImageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  public static final int FOOTER_ELEMENT_COUNT = 1;

  private static final int TYPE_IMAGE = 101;
  private static final int TYPE_ERROR = 102;
  private static final int TYPE_LOADING = 103;

  public static SpanSizeLookup getSpanLookup(ImageListAdapter adapter) {
    return new SpanSizeLookup() {
      @Override
      public int getSpanSize(int p) {
        return adapter.getItemViewType(p) == TYPE_IMAGE ? 1 : 2;
      }
    };
  }

  private final PicassoCacheStrategyWrapper cacheStrategy;
  private final AsyncListDiffer<Image> helper;
  private FooterState footer;
  private OnClickListener retryListener;

  public ImageListAdapter(PicassoCacheStrategyWrapper cacheStrategy) {
    this.cacheStrategy = cacheStrategy;
    footer = FooterState.NONE;
    this.helper = new AsyncListDiffer(new AdapterListUpdateCallback(this),
        new AsyncDifferConfig
            .Builder(DIFF_CALLBACK)
            .build());
  }

  public void setRetryClickListener(OnClickListener listener) {
    this.retryListener = listener;
  }

  public void updateList(List<Image> newList) {
    if (newList.size() > 0) {
      helper.submitList(newList);
    } else {
      helper.submitList(null);
    }
  }

  public void setFooter(FooterState f) {
    FooterState old = footer;
    this.footer = f;
    if (old == FooterState.NONE && footer != FooterState.NONE) {
      notifyItemInserted(helper.getCurrentList().size() + FOOTER_ELEMENT_COUNT);
    } else if (old != FooterState.NONE) {
      if (footer == FooterState.NONE) {
        notifyItemRemoved(helper.getCurrentList().size() + FOOTER_ELEMENT_COUNT);
      } else {
        notifyItemChanged(helper.getCurrentList().size() + FOOTER_ELEMENT_COUNT);
      }
    }
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (viewType == TYPE_IMAGE) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_image, parent, false);
      return new ImageVH(cacheStrategy, view);
    } else if (viewType == TYPE_LOADING) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.footer_progress, parent, false);
      return new ProgressFooterVH(view);
    } else if (viewType == TYPE_ERROR) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.footer_error_with_retry, parent, false);
      return new ErrorRetryFooterVH(view);
    } else {
      throw new IllegalStateException("Unhandled type " + viewType);
    }
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    if (getItemViewType(position) == TYPE_IMAGE) {
      ((ImageVH) holder).bind(helper.getCurrentList().get(position));
    } else if (getItemViewType(position) == TYPE_ERROR) {
      ((ErrorRetryFooterVH) holder).bind(retryListener);
    }
  }

  @Override
  public int getItemCount() {
    if (footer == FooterState.NONE) {
      return helper.getCurrentList().size();
    } else {
      return helper.getCurrentList().size() + FOOTER_ELEMENT_COUNT;
    }
  }

  @Override
  public int getItemViewType(int position) {
    if (position < helper.getCurrentList().size()) {
      return TYPE_IMAGE;
    } else {
      if (footer == FooterState.ERROR) {
        return TYPE_ERROR;
      } else if (footer == FooterState.LOADING) {
        return TYPE_LOADING;
      } else {
        throw new IllegalStateException("Adapter size can't be bigger if footer is None");
      }
    }
  }

  public static class ImageVH extends RecyclerView.ViewHolder {

    private final ImageView imageView;
    private final PicassoCacheStrategyWrapper cacheStrategy;

    public ImageVH(PicassoCacheStrategyWrapper appMemoryState, @NonNull View itemView) {
      super(itemView);
      this.cacheStrategy = appMemoryState;
      imageView = itemView.findViewById(R.id.image);
    }

    private void bind(Image image) {
      RequestCreator requestCreator = Picasso.get()
          .load(image.getUrl())
          .placeholder(R.drawable.ic_placeholder)
          .fit()
          .centerCrop();
      if (cacheStrategy.isNoCacheStrategy()) {
        requestCreator.memoryPolicy(MemoryPolicy.NO_STORE);
      }
      requestCreator.into(imageView);
    }
  }

  public static class ProgressFooterVH extends RecyclerView.ViewHolder {

    public ProgressFooterVH(@NonNull View itemView) {
      super(itemView);
    }
  }

  public static class ErrorRetryFooterVH extends RecyclerView.ViewHolder {

    private final Button retry;

    public ErrorRetryFooterVH(@NonNull View itemView) {
      super(itemView);
      retry = itemView.findViewById(R.id.retry);
    }

    private void bind(OnClickListener listener) {
      if (listener != null) {
        retry.setVisibility(View.VISIBLE);
        retry.setOnClickListener(listener);
      } else {
        retry.setVisibility(View.GONE);
      }
    }
  }
}
