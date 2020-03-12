// Generated code from Butter Knife. Do not modify!
package com.avances.lima.presentation.ui.fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.lima.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RoutesFragment_ViewBinding implements Unbinder {
  private RoutesFragment target;

  @UiThread
  public RoutesFragment_ViewBinding(RoutesFragment target, View source) {
    this.target = target;

    target.ivFilter = Utils.findRequiredViewAsType(source, R.id.btnSedarch, "field 'ivFilter'", ImageView.class);
    target.btnMenosRutasTematicas = Utils.findRequiredViewAsType(source, R.id.btnMenosRutasTematicas, "field 'btnMenosRutasTematicas'", TextView.class);
    target.etBuscador = Utils.findRequiredViewAsType(source, R.id.editTextSearchCode, "field 'etBuscador'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RoutesFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivFilter = null;
    target.btnMenosRutasTematicas = null;
    target.etBuscador = null;
  }
}
