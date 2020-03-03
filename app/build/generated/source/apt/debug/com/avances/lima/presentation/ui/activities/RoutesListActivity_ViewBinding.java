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
import java.lang.IllegalStateException;
import java.lang.Override;

public class RoutesListActivity_ViewBinding implements Unbinder {
  private RoutesListActivity target;

  @UiThread
  public RoutesListActivity_ViewBinding(RoutesListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RoutesListActivity_ViewBinding(RoutesListActivity target, View source) {
    this.target = target;

    target.ivGoToMap = Utils.findRequiredViewAsType(source, R.id.ivGoToMap, "field 'ivGoToMap'", ImageView.class);
    target.ivBack = Utils.findRequiredViewAsType(source, R.id.ivBack, "field 'ivBack'", ImageView.class);
    target.ivinfografia = Utils.findRequiredViewAsType(source, R.id.ivinfografia, "field 'ivinfografia'", ImageView.class);
    target.rvLugares = Utils.findRequiredViewAsType(source, R.id.rv_lugaress, "field 'rvLugares'", RecyclerView.class);
    target.tvRouteName = Utils.findRequiredViewAsType(source, R.id.tvRouteName, "field 'tvRouteName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RoutesListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivGoToMap = null;
    target.ivBack = null;
    target.ivinfografia = null;
    target.rvLugares = null;
    target.tvRouteName = null;
  }
}
