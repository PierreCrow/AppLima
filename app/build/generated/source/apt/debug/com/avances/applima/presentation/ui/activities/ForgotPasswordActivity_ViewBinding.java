// Generated code from Butter Knife. Do not modify!
package com.avances.applima.presentation.ui.activities;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.applima.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ForgotPasswordActivity_ViewBinding implements Unbinder {
  private ForgotPasswordActivity target;

  @UiThread
  public ForgotPasswordActivity_ViewBinding(ForgotPasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ForgotPasswordActivity_ViewBinding(ForgotPasswordActivity target, View source) {
    this.target = target;

    target.tiEmail = Utils.findRequiredViewAsType(source, R.id.tiEmail, "field 'tiEmail'", TextInputLayout.class);
    target.etEmail = Utils.findRequiredViewAsType(source, R.id.etEmail, "field 'etEmail'", TextInputEditText.class);
    target.ivClose = Utils.findRequiredViewAsType(source, R.id.ivClose, "field 'ivClose'", ImageView.class);
    target.ivRecoveryPassword = Utils.findRequiredViewAsType(source, R.id.ivRecoveryPassword, "field 'ivRecoveryPassword'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ForgotPasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tiEmail = null;
    target.etEmail = null;
    target.ivClose = null;
    target.ivRecoveryPassword = null;
  }
}
