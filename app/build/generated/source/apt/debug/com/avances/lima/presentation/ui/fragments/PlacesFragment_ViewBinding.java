// Generated code from Butter Knife. Do not modify!
package com.avances.lima.presentation.ui.fragments;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.lima.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PlacesFragment_ViewBinding implements Unbinder {
  private PlacesFragment target;

  @UiThread
  public PlacesFragment_ViewBinding(PlacesFragment target, View source) {
    this.target = target;

    target.btnMenosImperdibles = Utils.findRequiredViewAsType(source, R.id.llEditarPerfil, "field 'btnMenosImperdibles'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PlacesFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnMenosImperdibles = null;
  }
}
