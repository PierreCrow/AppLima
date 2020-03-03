// Generated code from Butter Knife. Do not modify!
package com.avances.lima.presentation.ui.activities;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.lima.R;
import com.chaos.view.PinView;
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
    target.entryEditText = Utils.findRequiredViewAsType(source, R.id.firstPinView, "field 'entryEditText'", PinView.class);
    target.tvTittle = Utils.findRequiredViewAsType(source, R.id.tvTittle, "field 'tvTittle'", TextView.class);
    target.tvSubTittle1 = Utils.findRequiredViewAsType(source, R.id.tvSubTittle1, "field 'tvSubTittle1'", TextView.class);
    target.tvSubTitle2 = Utils.findRequiredViewAsType(source, R.id.tvSubTittle2, "field 'tvSubTitle2'", TextView.class);
    target.tvQuestion = Utils.findRequiredViewAsType(source, R.id.tvQuestion, "field 'tvQuestion'", TextView.class);
    target.tvContinue = Utils.findRequiredViewAsType(source, R.id.tvContinue, "field 'tvContinue'", TextView.class);
    target.et1 = Utils.findRequiredViewAsType(source, R.id.et1, "field 'et1'", EditText.class);
    target.et2 = Utils.findRequiredViewAsType(source, R.id.et2, "field 'et2'", EditText.class);
    target.et3 = Utils.findRequiredViewAsType(source, R.id.et3, "field 'et3'", EditText.class);
    target.et4 = Utils.findRequiredViewAsType(source, R.id.et4, "field 'et4'", EditText.class);
    target.et5 = Utils.findRequiredViewAsType(source, R.id.et5, "field 'et5'", EditText.class);
    target.llEditTextContainer = Utils.findRequiredViewAsType(source, R.id.llEditTextContainer, "field 'llEditTextContainer'", LinearLayout.class);
    target.llInfoContainer = Utils.findRequiredViewAsType(source, R.id.llInfoContainer, "field 'llInfoContainer'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ValidationCodeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivClose = null;
    target.ivContinue = null;
    target.entryEditText = null;
    target.tvTittle = null;
    target.tvSubTittle1 = null;
    target.tvSubTitle2 = null;
    target.tvQuestion = null;
    target.tvContinue = null;
    target.et1 = null;
    target.et2 = null;
    target.et3 = null;
    target.et4 = null;
    target.et5 = null;
    target.llEditTextContainer = null;
    target.llInfoContainer = null;
  }
}
