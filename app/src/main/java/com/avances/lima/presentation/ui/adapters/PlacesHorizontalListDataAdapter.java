package com.avances.lima.presentation.ui.adapters;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.avances.lima.R;
import com.avances.lima.domain.model.Place;
import com.avances.lima.presentation.utils.Helper;

import java.util.List;

public class PlacesHorizontalListDataAdapter extends RecyclerView.Adapter<PlacesHorizontalListDataAdapter.SingleItemRowHolder> {


    private List<Place> itemsList;
    private Context mContext;
    public OnImperdiblesHorizontalClickListener mlistener;
    boolean userHasLocation;

    public PlacesHorizontalListDataAdapter(OnImperdiblesHorizontalClickListener mlistener, Context context, List<Place> itemsList, boolean userHasLocation) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.mlistener = mlistener;
        this.userHasLocation = userHasLocation;
    }

    public interface OnImperdiblesHorizontalClickListener {
        void onImperdiblesHorizontalClicked(View v, Integer position);
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_imperdible_home, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        Place place = itemsList.get(i);
        holder.tvTitle.setText(place.getTittle());
        Helper.urlToImageView(place.getImageList().get(0), holder.llimage, mContext);

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
            holder.tvKilometers.setText(kilometers + " km");
        } else {
            holder.llKilometers.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvTitle, tvKilometers;
        protected LinearLayout llKilometers;
        protected ImageView llimage;

        protected ImageView itemImage;


        public SingleItemRowHolder(View view) {
            super(view);

            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.tvKilometers = (TextView) view.findViewById(R.id.tvKilometers);
            this.llKilometers = (LinearLayout) view.findViewById(R.id.llKilometers);
            this.llimage = (ImageView) view.findViewById(R.id.llimage);
            //   this.itemImage = (ImageView) view.findViewById(R.id.itemImage);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mlistener.onImperdiblesHorizontalClicked(view, this.getPosition());
        }
    }

}
