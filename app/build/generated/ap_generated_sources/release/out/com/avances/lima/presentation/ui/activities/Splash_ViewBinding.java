// Generated code from Butter Knife. Do not modify!
package com.avances.lima.presentation.ui.activities;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.lima.R;
import com.avances.lima.presentation.utils.TextureVideoView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Splash_ViewBinding implements Unbinder {
  private Splash target;

  @UiThread
  public Splash_ViewBinding(Splash target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Splash_ViewBinding(Splash target, View source) {
    this.target = target;

    target.btnEmpezar = Utils.findRequiredViewAsType(source, R.id.btnEmpezar, "field 'btnEmpezar'", Button.class);
    target.lltextSplash = Utils.findRequiredViewAsType(source, R.id.lltextSplash, "field 'lltextSplash'", LinearLayout.class);
    target.vvVideo = Utils.findRequiredViewAsType(source, R.id.videoView, "field 'vvVideo'", TextureVideoView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Splash target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnEmpezar = null;
    target.lltextSplash = null;
    target.vvVideo = null;
  }
}
