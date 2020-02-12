package com.avances.applima.presentation.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.avances.applima.R;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.domain.model.Place;
import com.avances.applima.presentation.ui.activities.RoutesMapActivity;
import com.avances.applima.presentation.utils.Helper;

import java.util.ArrayList;
import java.util.List;

public class PlacesMapHorizontalListDataAdapter extends RecyclerView.Adapter<PlacesMapHorizontalListDataAdapter.SingleItemRowHolder> {


    private List<Place> itemsList;
    private Context mContext;
    boolean userHasLocation;
    public OnPlacesMapHorizontalClickListener mlistener;

    public PlacesMapHorizontalListDataAdapter(OnPlacesMapHorizontalClickListener mlistener,Context context, List<Place> itemsList, boolean userHasLocation) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.userHasLocation = userHasLocation;
        this.mlistener = mlistener;
    }

    public interface OnPlacesMapHorizontalClickListener {
        void onPlacesMapHorizontalClicked(View v, Integer position);
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ruta_detail_map, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        Place place = itemsList.get(i);
        holder.tvTitle.setText(place.getTittle());
        holder.tvDetail.setText(place.getDetail());
        Helper.urlToImageView(place.getImageList().get(0), holder.ivPlaceImage, mContext);

        if (userHasLocation) {
            Location userLocation = new Location("");
            userLocation.setLatitude(Double.parseDouble(Helper.getUserAppPreference(mContext).getLat()));
            userLocation.setLongitude(Double.parseDouble(Helper.getUserAppPreference(mContext).getLng()));

            Location placeLocation = new Location("");
            placeLocation.setLatitude(Double.parseDouble(place.getLat()));
            placeLocation.setLongitude(Double.parseDouble(place.getLng()));

            float distanceInMeters = userLocation.distanceTo(placeLocation);
            float Kilometers = distanceInMeters / 1000;

            String kilometers = Helper.convertTwoDecimals(Kilometers);
            holder.tvKilometers.setText(kilometers + "km");
        } else {
            holder.llKilometers.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvTitle, tvDetail, tvKilometers;
        protected LinearLayout llKilometers;
        protected ImageView ivPlaceImage;


        public SingleItemRowHolder(View view) {
            super(view);

            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.tvDetail = (TextView) view.findViewById(R.id.tvDetail);
            this.tvKilometers = (TextView) view.findViewById(R.id.tvKilometers);
            this.ivPlaceImage = (ImageView) view.findViewById(R.id.ivPlaceImage);
            this.llKilometers = (LinearLayout) view.findViewById(R.id.llKilometers);

            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mlistener.onPlacesMapHorizontalClicked(view, this.getPosition());
        }
    }

}
