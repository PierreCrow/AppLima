// Generated code from Butter Knife. Do not modify!
package com.avances.lima.presentation.ui.fragments;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.lima.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchFragment_ViewBinding implements Unbinder {
  private SearchFragment target;

  @UiThread
  public SearchFragment_ViewBinding(SearchFragment target, View source) {
    this.target = target;

    target.rvSuggestedTags = Utils.findRequiredViewAsType(source, R.id.rvSuggestedTags, "field 'rvSuggestedTags'", RecyclerView.class);
    target.tvCancel = Utils.findRequiredViewAsType(source, R.id.tvCancel, "field 'tvCancel'", TextView.class);
    target.editTextSearch = Utils.findRequiredViewAsType(source, R.id.editTextSearch, "field 'editTextSearch'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvSuggestedTags = null;
    target.tvCancel = null;
    target.editTextSearch = null;
  }
}
