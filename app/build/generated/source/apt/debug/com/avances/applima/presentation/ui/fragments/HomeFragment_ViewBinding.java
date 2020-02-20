// Generated code from Butter Knife. Do not modify!
package com.avances.applima.presentation.ui.fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.applima.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  @UiThread
  public HomeFragment_ViewBinding(HomeFragment target, View source) {
    this.target = target;

    target.ivFilter = Utils.findRequiredViewAsType(source, R.id.btnSedarch, "field 'ivFilter'", ImageView.class);
    target.btnMoreImperdibles = Utils.findRequiredViewAsType(source, R.id.btnMoreImperdibles, "field 'btnMoreImperdibles'", TextView.class);
    target.btnMoreTematicas = Utils.findRequiredViewAsType(source, R.id.btnMoreTematicas, "field 'btnMoreTematicas'", TextView.class);
    target.etBuscador = Utils.findRequiredViewAsType(source, R.id.editTextSearchCode, "field 'etBuscador'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivFilter = null;
    target.btnMoreImperdibles = null;
    target.btnMoreTematicas = null;
    target.etBuscador = null;
  }
}
