// Generated code from Butter Knife. Do not modify!
package com.avances.lima.presentation.ui.fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.lima.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AccountFragment_ViewBinding implements Unbinder {
  private AccountFragment target;

  @UiThread
  public AccountFragment_ViewBinding(AccountFragment target, View source) {
    this.target = target;

    target.llEditarPerfil = Utils.findRequiredViewAsType(source, R.id.llEditarPerfil, "field 'llEditarPerfil'", LinearLayout.class);
    target.llCerrarSesion = Utils.findRequiredViewAsType(source, R.id.llCerrarSesion, "field 'llCerrarSesion'", LinearLayout.class);
    target.llPreferencias = Utils.findRequiredViewAsType(source, R.id.llPreferencias, "field 'llPreferencias'", LinearLayout.class);
    target.llValoraApp = Utils.findRequiredViewAsType(source, R.id.llValoraApp, "field 'llValoraApp'", LinearLayout.class);
    target.tvUserName = Utils.findRequiredViewAsType(source, R.id.tvUserName, "field 'tvUserName'", TextView.class);
    target.ivUserImage = Utils.findRequiredViewAsType(source, R.id.ivUserImage, "field 'ivUserImage'", ImageView.class);
    target.lineOne = Utils.findRequiredView(source, R.id.lineOne, "field 'lineOne'");
    target.lineTwo = Utils.findRequiredView(source, R.id.lineTwo, "field 'lineTwo'");
    target.lineThree = Utils.findRequiredView(source, R.id.lineThree, "field 'lineThree'");
  }

  @Override
  @CallSuper
  public void unbind() {
    AccountFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llEditarPerfil = null;
    target.llCerrarSesion = null;
    target.llPreferencias = null;
    target.llValoraApp = null;
    target.tvUserName = null;
    target.ivUserImage = null;
    target.lineOne = null;
    target.lineTwo = null;
    target.lineThree = null;
  }
}
