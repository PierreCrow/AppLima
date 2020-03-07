// Generated code from Butter Knife. Do not modify!
package com.avances.lima.presentation.ui.fragments;

import android.view.View;
import android.widget.EditText;
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

public class SearchFragment_ViewBinding implements Unbinder {
  private SearchFragment target;

  @UiThread
  public SearchFragment_ViewBinding(SearchFragment target, View source) {
    this.target = target;

    target.rvSuggestedTags = Utils.findRequiredViewAsType(source, R.id.rvSuggestedTags, "field 'rvSuggestedTags'", RecyclerView.class);
    target.tvCancel = Utils.findRequiredViewAsType(source, R.id.tvCancel, "field 'tvCancel'", TextView.class);
    target.editTextSearch = Utils.findRequiredViewAsType(source, R.id.editTextSearch, "field 'editTextSearch'", EditText.class);
    target.tvRecentlyTag1 = Utils.findRequiredViewAsType(source, R.id.tvRecentlyTag1, "field 'tvRecentlyTag1'", TextView.class);
    target.tvRecentlyTag2 = Utils.findRequiredViewAsType(source, R.id.tvRecentlyTag2, "field 'tvRecentlyTag2'", TextView.class);
    target.tvRecentlyTag3 = Utils.findRequiredViewAsType(source, R.id.tvRecentlyTag3, "field 'tvRecentlyTag3'", TextView.class);
    target.rlRecentlyTag1 = Utils.findRequiredViewAsType(source, R.id.rlRecentlyTag1, "field 'rlRecentlyTag1'", RelativeLayout.class);
    target.rlRecentlyTag2 = Utils.findRequiredViewAsType(source, R.id.rlRecentlyTag2, "field 'rlRecentlyTag2'", RelativeLayout.class);
    target.rlRecentlyTag3 = Utils.findRequiredViewAsType(source, R.id.rlRecentlyTag3, "field 'rlRecentlyTag3'", RelativeLayout.class);
    target.tvRecentlySearch = Utils.findRequiredViewAsType(source, R.id.tvRecentlySearch, "field 'tvRecentlySearch'", TextView.class);
    target.llRecentlyTags = Utils.findRequiredViewAsType(source, R.id.llRecentlyTags, "field 'llRecentlyTags'", LinearLayout.class);
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
    target.tvRecentlyTag1 = null;
    target.tvRecentlyTag2 = null;
    target.tvRecentlyTag3 = null;
    target.rlRecentlyTag1 = null;
    target.rlRecentlyTag2 = null;
    target.rlRecentlyTag3 = null;
    target.tvRecentlySearch = null;
    target.llRecentlyTags = null;
  }
}
