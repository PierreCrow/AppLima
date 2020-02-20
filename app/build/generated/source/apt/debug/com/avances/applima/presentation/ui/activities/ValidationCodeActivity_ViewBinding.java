// Generated code from Butter Knife. Do not modify!
package com.avances.applima.presentation.ui.activities;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.applima.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ValidationCodeActivity_ViewBinding implements Unbinder {
  private ValidationCodeActivity target;

  @UiThread
  public ValidationCodeActivity_ViewBinding(ValidationCodeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ValidationCodeActivity_ViewBinding(ValidationCodeActivity target, View source) {
    this.target = target;

    target.ivClose = Utils.findRequiredViewAsType(source, R.id.ivClose, "field 'ivClose'", ImageView.class);
    target.ivContinue = Utils.findRequiredViewAsType(source, R.id.ivContinue, "field 'ivContinue'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ValidationCodeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivClose = null;
    target.ivContinue = null;
  }
}
