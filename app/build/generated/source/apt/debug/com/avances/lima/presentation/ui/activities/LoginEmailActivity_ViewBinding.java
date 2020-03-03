// Generated code from Butter Knife. Do not modify!
package com.avances.lima.presentation.ui.activities;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.lima.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginEmailActivity_ViewBinding implements Unbinder {
  private LoginEmailActivity target;

  @UiThread
  public LoginEmailActivity_ViewBinding(LoginEmailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginEmailActivity_ViewBinding(LoginEmailActivity target, View source) {
    this.target = target;

    target.ivClose = Utils.findRequiredViewAsType(source, R.id.ivClose, "field 'ivClose'", ImageView.class);
    target.ivContinue = Utils.findRequiredViewAsType(source, R.id.ivContinue, "field 'ivContinue'", ImageView.class);
    target.tvOlvidasteContrasena = Utils.findRequiredViewAsType(source, R.id.tvOlvidasteContrasena, "field 'tvOlvidasteContrasena'", AppCompatTextView.class);
    target.tiEmail = Utils.findRequiredViewAsType(source, R.id.tiEmail, "field 'tiEmail'", TextInputLayout.class);
    target.tiPass = Utils.findRequiredViewAsType(source, R.id.tiPass, "field 'tiPass'", TextInputLayout.class);
    target.etEmail = Utils.findRequiredViewAsType(source, R.id.etEmail, "field 'etEmail'", TextInputEditText.class);
    target.etPass = Utils.findRequiredViewAsType(source, R.id.etPass, "field 'etPass'", TextInputEditText.class);
    target.ivPass = Utils.findRequiredViewAsType(source, R.id.ivPass, "field 'ivPass'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginEmailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivClose = null;
    target.ivContinue = null;
    target.tvOlvidasteContrasena = null;
    target.tiEmail = null;
    target.tiPass = null;
    target.etEmail = null;
    target.etPass = null;
    target.ivPass = null;
  }
}
