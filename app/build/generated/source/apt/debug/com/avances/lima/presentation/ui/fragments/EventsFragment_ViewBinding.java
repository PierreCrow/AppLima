// Generated code from Butter Knife. Do not modify!
package com.avances.lima.presentation.ui.fragments;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.lima.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EventsFragment_ViewBinding implements Unbinder {
  private EventsFragment target;

  @UiThread
  public EventsFragment_ViewBinding(EventsFragment target, View source) {
    this.target = target;

    target.rvEvents = Utils.findRequiredViewAsType(source, R.id.rvEvents, "field 'rvEvents'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EventsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvEvents = null;
  }
}
