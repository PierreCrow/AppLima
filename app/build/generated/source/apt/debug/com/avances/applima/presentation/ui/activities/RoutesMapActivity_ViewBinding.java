// Generated code from Butter Knife. Do not modify!
package com.avances.applima.presentation.ui.activities;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.applima.R;
import com.mapbox.mapboxsdk.maps.MapView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RoutesMapActivity_ViewBinding implements Unbinder {
  private RoutesMapActivity target;

  @UiThread
  public RoutesMapActivity_ViewBinding(RoutesMapActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RoutesMapActivity_ViewBinding(RoutesMapActivity target, View source) {
    this.target = target;

    target.btnBack = Utils.findRequiredViewAsType(source, R.id.btnBack, "field 'btnBack'", ImageView.class);
    target.mapView = Utils.findRequiredViewAsType(source, R.id.mapView, "field 'mapView'", MapView.class);
    target.ivGoToList = Utils.findRequiredViewAsType(source, R.id.ivGoToList, "field 'ivGoToList'", ImageView.class);
    target.ivinfografia = Utils.findRequiredViewAsType(source, R.id.ivinfografia, "field 'ivinfografia'", ImageView.class);
    target.tvTittle = Utils.findRequiredViewAsType(source, R.id.tvTittle, "field 'tvTittle'", TextView.class);
    target.rvLugares = Utils.findRequiredViewAsType(source, R.id.rv_lugaress, "field 'rvLugares'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RoutesMapActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnBack = null;
    target.mapView = null;
    target.ivGoToList = null;
    target.ivinfografia = null;
    target.tvTittle = null;
    target.rvLugares = null;
  }
}
