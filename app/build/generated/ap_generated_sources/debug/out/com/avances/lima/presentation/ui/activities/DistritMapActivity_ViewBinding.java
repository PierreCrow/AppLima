// Generated code from Butter Knife. Do not modify!
package com.avances.lima.presentation.ui.activities;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.lima.R;
import com.mapbox.mapboxsdk.maps.MapView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DistritMapActivity_ViewBinding implements Unbinder {
  private DistritMapActivity target;

  @UiThread
  public DistritMapActivity_ViewBinding(DistritMapActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DistritMapActivity_ViewBinding(DistritMapActivity target, View source) {
    this.target = target;

    target.btnBack = Utils.findRequiredViewAsType(source, R.id.btnBack, "field 'btnBack'", ImageView.class);
    target.mapView = Utils.findRequiredViewAsType(source, R.id.mapView, "field 'mapView'", MapView.class);
    target.rvLugares = Utils.findRequiredViewAsType(source, R.id.rv_lugaress, "field 'rvLugares'", RecyclerView.class);
    target.ivGoToList = Utils.findRequiredViewAsType(source, R.id.ivGoToList, "field 'ivGoToList'", ImageView.class);
    target.tvDistritName = Utils.findRequiredViewAsType(source, R.id.tvDistritName, "field 'tvDistritName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DistritMapActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnBack = null;
    target.mapView = null;
    target.rvLugares = null;
    target.ivGoToList = null;
    target.tvDistritName = null;
  }
}
