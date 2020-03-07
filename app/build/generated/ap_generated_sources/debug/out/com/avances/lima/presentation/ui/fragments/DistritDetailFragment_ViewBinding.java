// Generated code from Butter Knife. Do not modify!
package com.avances.lima.presentation.ui.fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.lima.R;
import com.avances.lima.presentation.utils.TextureVideoView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DistritDetailFragment_ViewBinding implements Unbinder {
  private DistritDetailFragment target;

  @UiThread
  public DistritDetailFragment_ViewBinding(DistritDetailFragment target, View source) {
    this.target = target;

    target.rvImperdibles = Utils.findRequiredViewAsType(source, R.id.rvImperdibles, "field 'rvImperdibles'", RecyclerView.class);
    target.ivBack = Utils.findRequiredViewAsType(source, R.id.ivBack, "field 'ivBack'", ImageView.class);
    target.tvMoreImperdibles = Utils.findRequiredViewAsType(source, R.id.tvMoreImperdibles, "field 'tvMoreImperdibles'", TextView.class);
    target.tvDistritName = Utils.findRequiredViewAsType(source, R.id.tvDistritName, "field 'tvDistritName'", TextView.class);
    target.tvLongDescription = Utils.findRequiredViewAsType(source, R.id.tvLongDescription, "field 'tvLongDescription'", TextView.class);
    target.llIrAMapa = Utils.findRequiredViewAsType(source, R.id.llIrAMapa, "field 'llIrAMapa'", LinearLayout.class);
    target.rlDistritImage = Utils.findRequiredViewAsType(source, R.id.rlDistritImage, "field 'rlDistritImage'", ImageView.class);
    target.vvDistritVideo = Utils.findRequiredViewAsType(source, R.id.vvDistritVideo, "field 'vvDistritVideo'", TextureVideoView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DistritDetailFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvImperdibles = null;
    target.ivBack = null;
    target.tvMoreImperdibles = null;
    target.tvDistritName = null;
    target.tvLongDescription = null;
    target.llIrAMapa = null;
    target.rlDistritImage = null;
    target.vvDistritVideo = null;
  }
}
