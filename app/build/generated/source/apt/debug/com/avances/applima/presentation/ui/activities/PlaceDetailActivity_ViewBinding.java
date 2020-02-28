// Generated code from Butter Knife. Do not modify!
package com.avances.applima.presentation.ui.activities;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.avances.applima.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PlaceDetailActivity_ViewBinding implements Unbinder {
  private PlaceDetailActivity target;

  @UiThread
  public PlaceDetailActivity_ViewBinding(PlaceDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PlaceDetailActivity_ViewBinding(PlaceDetailActivity target, View source) {
    this.target = target;

    target.ivBack = Utils.findRequiredViewAsType(source, R.id.ivBack, "field 'ivBack'", ImageView.class);
    target.llIrAMapa = Utils.findRequiredViewAsType(source, R.id.llIrAMapa, "field 'llIrAMapa'", LinearLayout.class);
    target.llDistance = Utils.findRequiredViewAsType(source, R.id.llDistance, "field 'llDistance'", LinearLayout.class);
    target.tvPlaceName = Utils.findRequiredViewAsType(source, R.id.tvPlaceName, "field 'tvPlaceName'", TextView.class);
    target.tvKilometers = Utils.findRequiredViewAsType(source, R.id.tvKilometers, "field 'tvKilometers'", TextView.class);
    target.tvPlacePhone = Utils.findRequiredViewAsType(source, R.id.tvPlacePhone, "field 'tvPlacePhone'", TextView.class);
    target.tvPlaceWebPage = Utils.findRequiredViewAsType(source, R.id.tvPlaceWebPage, "field 'tvPlaceWebPage'", TextView.class);
    target.tvPlaceAddress = Utils.findRequiredViewAsType(source, R.id.tvPlaceAddress, "field 'tvPlaceAddress'", TextView.class);
    target.tvInterviewedName = Utils.findRequiredViewAsType(source, R.id.tvInterviewedName, "field 'tvInterviewedName'", TextView.class);
    target.tvInterviewedTittle = Utils.findRequiredViewAsType(source, R.id.tvInterviewedTittle, "field 'tvInterviewedTittle'", TextView.class);
    target.llAudio = Utils.findRequiredViewAsType(source, R.id.llAudio, "field 'llAudio'", LinearLayout.class);
    target.llGoToCall = Utils.findRequiredViewAsType(source, R.id.llGoToCall, "field 'llGoToCall'", LinearLayout.class);
    target.llGoToWebPage = Utils.findRequiredViewAsType(source, R.id.llGoToWebPage, "field 'llGoToWebPage'", LinearLayout.class);
    target.llGoToMaps = Utils.findRequiredViewAsType(source, R.id.llGoToMaps, "field 'llGoToMaps'", LinearLayout.class);
    target.ivInterviewedImage = Utils.findRequiredViewAsType(source, R.id.ivInterviewedImage, "field 'ivInterviewedImage'", ImageView.class);
    target.ivShare = Utils.findRequiredViewAsType(source, R.id.ivShare, "field 'ivShare'", ImageView.class);
    target.ivLike = Utils.findRequiredViewAsType(source, R.id.ivLike, "field 'ivLike'", ImageView.class);
    target.ivPlayPause = Utils.findRequiredViewAsType(source, R.id.ivPlayOrPause, "field 'ivPlayPause'", ImageView.class);
    target.tvPlaceDescription = Utils.findRequiredViewAsType(source, R.id.tvPlaceDescription, "field 'tvPlaceDescription'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PlaceDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.llIrAMapa = null;
    target.llDistance = null;
    target.tvPlaceName = null;
    target.tvKilometers = null;
    target.tvPlacePhone = null;
    target.tvPlaceWebPage = null;
    target.tvPlaceAddress = null;
    target.tvInterviewedName = null;
    target.tvInterviewedTittle = null;
    target.llAudio = null;
    target.llGoToCall = null;
    target.llGoToWebPage = null;
    target.llGoToMaps = null;
    target.ivInterviewedImage = null;
    target.ivShare = null;
    target.ivLike = null;
    target.ivPlayPause = null;
    target.tvPlaceDescription = null;
  }
}
