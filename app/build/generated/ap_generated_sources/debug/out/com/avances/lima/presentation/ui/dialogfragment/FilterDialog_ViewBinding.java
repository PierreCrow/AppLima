// Generated code from Butter Knife. Do not modify!
package com.avances.lima.presentation.ui.dialogfragment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.lima.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FilterDialog_ViewBinding implements Unbinder {
  private FilterDialog target;

  @UiThread
  public FilterDialog_ViewBinding(FilterDialog target, View source) {
    this.target = target;

    target.ivClose = Utils.findRequiredViewAsType(source, R.id.ivClose, "field 'ivClose'", ImageView.class);
    target.tvInteres1 = Utils.findRequiredViewAsType(source, R.id.tvInteres1, "field 'tvInteres1'", TextView.class);
    target.tvInteres2 = Utils.findRequiredViewAsType(source, R.id.tvInteres2, "field 'tvInteres2'", TextView.class);
    target.tvInteres3 = Utils.findRequiredViewAsType(source, R.id.tvInteres3, "field 'tvInteres3'", TextView.class);
    target.tvInteres4 = Utils.findRequiredViewAsType(source, R.id.tvInteres4, "field 'tvInteres4'", TextView.class);
    target.tvInteres5 = Utils.findRequiredViewAsType(source, R.id.tvInteres5, "field 'tvInteres5'", TextView.class);
    target.tvPermanencyDay1 = Utils.findRequiredViewAsType(source, R.id.tvPermanencyDay1, "field 'tvPermanencyDay1'", TextView.class);
    target.tvPermanencyDay2 = Utils.findRequiredViewAsType(source, R.id.tvPermanencyDay2, "field 'tvPermanencyDay2'", TextView.class);
    target.tvPermanencyDay3 = Utils.findRequiredViewAsType(source, R.id.tvPermanencyDay3, "field 'tvPermanencyDay3'", TextView.class);
    target.tvPermanencyDay4 = Utils.findRequiredViewAsType(source, R.id.tvPermanencyDay4, "field 'tvPermanencyDay4'", TextView.class);
    target.rlInteres1 = Utils.findRequiredViewAsType(source, R.id.rlInteres1, "field 'rlInteres1'", RelativeLayout.class);
    target.rlInteres2 = Utils.findRequiredViewAsType(source, R.id.rlInteres2, "field 'rlInteres2'", RelativeLayout.class);
    target.rlInteres3 = Utils.findRequiredViewAsType(source, R.id.rlInteres3, "field 'rlInteres3'", RelativeLayout.class);
    target.rlInteres4 = Utils.findRequiredViewAsType(source, R.id.rlInteres4, "field 'rlInteres4'", RelativeLayout.class);
    target.rlInteres5 = Utils.findRequiredViewAsType(source, R.id.rlInteres5, "field 'rlInteres5'", RelativeLayout.class);
    target.rlPermanencyDay1 = Utils.findRequiredViewAsType(source, R.id.rlPermanencyDay1, "field 'rlPermanencyDay1'", RelativeLayout.class);
    target.rlPermanencyDay2 = Utils.findRequiredViewAsType(source, R.id.rlPermanencyDay2, "field 'rlPermanencyDay2'", RelativeLayout.class);
    target.rlPermanencyDay3 = Utils.findRequiredViewAsType(source, R.id.rlPermanencyDay3, "field 'rlPermanencyDay3'", RelativeLayout.class);
    target.rlPermanencyDay4 = Utils.findRequiredViewAsType(source, R.id.rlPermanencyDay4, "field 'rlPermanencyDay4'", RelativeLayout.class);
    target.btnAplicar = Utils.findRequiredViewAsType(source, R.id.btnAplicar, "field 'btnAplicar'", Button.class);
    target.rvDistrits = Utils.findRequiredViewAsType(source, R.id.rvDistrits, "field 'rvDistrits'", RecyclerView.class);
    target.transparent_linear_filter = Utils.findRequiredViewAsType(source, R.id.transparent_linear_filter, "field 'transparent_linear_filter'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FilterDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivClose = null;
    target.tvInteres1 = null;
    target.tvInteres2 = null;
    target.tvInteres3 = null;
    target.tvInteres4 = null;
    target.tvInteres5 = null;
    target.tvPermanencyDay1 = null;
    target.tvPermanencyDay2 = null;
    target.tvPermanencyDay3 = null;
    target.tvPermanencyDay4 = null;
    target.rlInteres1 = null;
    target.rlInteres2 = null;
    target.rlInteres3 = null;
    target.rlInteres4 = null;
    target.rlInteres5 = null;
    target.rlPermanencyDay1 = null;
    target.rlPermanencyDay2 = null;
    target.rlPermanencyDay3 = null;
    target.rlPermanencyDay4 = null;
    target.btnAplicar = null;
    target.rvDistrits = null;
    target.transparent_linear_filter = null;
  }
}
