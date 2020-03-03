// Generated code from Butter Knife. Do not modify!
package com.avances.lima.presentation.ui.fragments;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.lima.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TabHome_ViewBinding implements Unbinder {
  private TabHome target;

  @UiThread
  public TabHome_ViewBinding(TabHome target, View source) {
    this.target = target;

    target.ivnHome = Utils.findRequiredViewAsType(source, R.id.menuHome, "field 'ivnHome'", ImageView.class);
    target.ivAccount = Utils.findRequiredViewAsType(source, R.id.menuAccount, "field 'ivAccount'", ImageView.class);
    target.ivFavorite = Utils.findRequiredViewAsType(source, R.id.menuFavorite, "field 'ivFavorite'", ImageView.class);
    target.ivDiary = Utils.findRequiredViewAsType(source, R.id.menuDiary, "field 'ivDiary'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TabHome target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivnHome = null;
    target.ivAccount = null;
    target.ivFavorite = null;
    target.ivDiary = null;
  }
}