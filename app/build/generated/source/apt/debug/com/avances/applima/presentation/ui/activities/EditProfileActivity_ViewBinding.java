// Generated code from Butter Knife. Do not modify!
package com.avances.applima.presentation.ui.activities;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.applima.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditProfileActivity_ViewBinding implements Unbinder {
  private EditProfileActivity target;

  @UiThread
  public EditProfileActivity_ViewBinding(EditProfileActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditProfileActivity_ViewBinding(EditProfileActivity target, View source) {
    this.target = target;

    target.ivBack = Utils.findRequiredViewAsType(source, R.id.ivClose, "field 'ivBack'", ImageView.class);
    target.ivContinue = Utils.findRequiredViewAsType(source, R.id.ivContinue, "field 'ivContinue'", ImageView.class);
    target.etEmail = Utils.findRequiredViewAsType(source, R.id.etEmail, "field 'etEmail'", TextInputEditText.class);
    target.etPass = Utils.findRequiredViewAsType(source, R.id.etPass, "field 'etPass'", TextInputEditText.class);
    target.etNames = Utils.findRequiredViewAsType(source, R.id.etNames, "field 'etNames'", TextInputEditText.class);
    target.tiEmail = Utils.findRequiredViewAsType(source, R.id.tiEmail, "field 'tiEmail'", TextInputLayout.class);
    target.tiPass = Utils.findRequiredViewAsType(source, R.id.tiPass, "field 'tiPass'", TextInputLayout.class);
    target.tiNames = Utils.findRequiredViewAsType(source, R.id.tiNames, "field 'tiNames'", TextInputLayout.class);
    target.ivPass = Utils.findRequiredViewAsType(source, R.id.ivPass, "field 'ivPass'", ImageView.class);
    target.ivBirthDate = Utils.findRequiredViewAsType(source, R.id.ivBirthDate, "field 'ivBirthDate'", ImageView.class);
    target.spiPaises = Utils.findRequiredViewAsType(source, R.id.spiPaises, "field 'spiPaises'", Spinner.class);
    target.rbMale = Utils.findRequiredViewAsType(source, R.id.rbMale, "field 'rbMale'", RadioButton.class);
    target.rbFemale = Utils.findRequiredViewAsType(source, R.id.rbFemale, "field 'rbFemale'", RadioButton.class);
    target.tvBirthDate = Utils.findRequiredViewAsType(source, R.id.tvBirthDate, "field 'tvBirthDate'", TextView.class);
    target.etDay = Utils.findRequiredViewAsType(source, R.id.etDay, "field 'etDay'", EditText.class);
    target.etMonth = Utils.findRequiredViewAsType(source, R.id.etMonth, "field 'etMonth'", EditText.class);
    target.etYear = Utils.findRequiredViewAsType(source, R.id.etYear, "field 'etYear'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EditProfileActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.ivContinue = null;
    target.etEmail = null;
    target.etPass = null;
    target.etNames = null;
    target.tiEmail = null;
    target.tiPass = null;
    target.tiNames = null;
    target.ivPass = null;
    target.ivBirthDate = null;
    target.spiPaises = null;
    target.rbMale = null;
    target.rbFemale = null;
    target.tvBirthDate = null;
    target.etDay = null;
    target.etMonth = null;
    target.etYear = null;
  }
}
