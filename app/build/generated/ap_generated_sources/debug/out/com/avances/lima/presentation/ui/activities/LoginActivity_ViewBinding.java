// Generated code from Butter Knife. Do not modify!
package com.avances.lima.presentation.ui.activities;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.lima.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target, View source) {
    this.target = target;

    target.ivClose = Utils.findRequiredViewAsType(source, R.id.ivClose, "field 'ivClose'", ImageView.class);
    target.tvIniciarSesionLoginActivity = Utils.findRequiredViewAsType(source, R.id.tvIniciarSesionLoginActivity, "field 'tvIniciarSesionLoginActivity'", TextView.class);
    target.tvRegistroLoginActivity = Utils.findRequiredViewAsType(source, R.id.tvRegistroLoginActivity, "field 'tvRegistroLoginActivity'", TextView.class);
    target.rlGoogle = Utils.findRequiredViewAsType(source, R.id.rlGoogle, "field 'rlGoogle'", RelativeLayout.class);
    target.rlFacebook = Utils.findRequiredViewAsType(source, R.id.rlFacebooko, "field 'rlFacebook'", RelativeLayout.class);
    target.cbPoliticas = Utils.findRequiredViewAsType(source, R.id.cbPoliticas, "field 'cbPoliticas'", CheckBox.class);
    target.cbTerminos = Utils.findRequiredViewAsType(source, R.id.cbTerminos, "field 'cbTerminos'", CheckBox.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivClose = null;
    target.tvIniciarSesionLoginActivity = null;
    target.tvRegistroLoginActivity = null;
    target.rlGoogle = null;
    target.rlFacebook = null;
    target.cbPoliticas = null;
    target.cbTerminos = null;
  }
}
