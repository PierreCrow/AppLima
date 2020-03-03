// Generated code from Butter Knife. Do not modify!
package com.avances.lima.presentation.ui.fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.lima.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SecondsToOfferFragment_ViewBinding implements Unbinder {
  private SecondsToOfferFragment target;

  @UiThread
  public SecondsToOfferFragment_ViewBinding(SecondsToOfferFragment target, View source) {
    this.target = target;

    target.ivClose = Utils.findRequiredViewAsType(source, R.id.ivClose, "field 'ivClose'", ImageView.class);
    target.ivContinue = Utils.findRequiredViewAsType(source, R.id.ivContinue, "field 'ivContinue'", ImageView.class);
    target.btnInteres1 = Utils.findRequiredViewAsType(source, R.id.btnInteres1, "field 'btnInteres1'", TextView.class);
    target.btnInteres2 = Utils.findRequiredViewAsType(source, R.id.btnInteres2, "field 'btnInteres2'", TextView.class);
    target.btnInteres3 = Utils.findRequiredViewAsType(source, R.id.btnInteres3, "field 'btnInteres3'", TextView.class);
    target.btnInteres4 = Utils.findRequiredViewAsType(source, R.id.btnInteres4, "field 'btnInteres4'", TextView.class);
    target.btnInteres5 = Utils.findRequiredViewAsType(source, R.id.btnInteres5, "field 'btnInteres5'", TextView.class);
    target.btnInteres6 = Utils.findRequiredViewAsType(source, R.id.btnInteres6, "field 'btnInteres6'", TextView.class);
    target.rlInteres1 = Utils.findRequiredViewAsType(source, R.id.rlInteres1, "field 'rlInteres1'", RelativeLayout.class);
    target.rlInteres2 = Utils.findRequiredViewAsType(source, R.id.rlInteres2, "field 'rlInteres2'", RelativeLayout.class);
    target.rlInteres3 = Utils.findRequiredViewAsType(source, R.id.rlInteres3, "field 'rlInteres3'", RelativeLayout.class);
    target.rlInteres4 = Utils.findRequiredViewAsType(source, R.id.rlInteres4, "field 'rlInteres4'", RelativeLayout.class);
    target.rlInteres5 = Utils.findRequiredViewAsType(source, R.id.rlInteres5, "field 'rlInteres5'", RelativeLayout.class);
    target.spiPermanency = Utils.findRequiredViewAsType(source, R.id.spiPermanency, "field 'spiPermanency'", Spinner.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SecondsToOfferFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivClose = null;
    target.ivContinue = null;
    target.btnInteres1 = null;
    target.btnInteres2 = null;
    target.btnInteres3 = null;
    target.btnInteres4 = null;
    target.btnInteres5 = null;
    target.btnInteres6 = null;
    target.rlInteres1 = null;
    target.rlInteres2 = null;
    target.rlInteres3 = null;
    target.rlInteres4 = null;
    target.rlInteres5 = null;
    target.spiPermanency = null;
  }
}
