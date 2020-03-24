// Generated code from Butter Knife. Do not modify!
package com.avances.lima.presentation.ui.fragments;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.lima.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PlaceImageTwoFragment_ViewBinding implements Unbinder {
  private PlaceImageTwoFragment target;

  @UiThread
  public PlaceImageTwoFragment_ViewBinding(PlaceImageTwoFragment target, View source) {
    this.target = target;

    target.place_image_view = Utils.findRequiredViewAsType(source, R.id.place_image_view, "field 'place_image_view'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PlaceImageTwoFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.place_image_view = null;
  }
}
