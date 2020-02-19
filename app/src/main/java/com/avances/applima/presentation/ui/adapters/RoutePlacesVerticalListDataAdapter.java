package com.avances.applima.presentation.ui.adapters;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.avances.applima.R;
import com.avances.applima.domain.model.Place;
import com.avances.applima.presentation.utils.Helper;

import java.util.ArrayList;
import java.util.List;

public class RoutePlacesVerticalListDataAdapter extends RecyclerView.Adapter<RoutePlacesVerticalListDataAdapter.ViewHolderPlace> {


    private List<Place> items = new ArrayList<>();

    public OnRutasPlacesVerticalClickListener mlistener;
    private Context mContext;
    boolean userHasLocation;

    public interface OnRutasPlacesVerticalClickListener {
        void onRutasPlacesVerticalClicked(View v, Integer position);
    }

    public void add(Place item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }


    @Override
    public ViewHolderPlace onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ruta_detail_list, parent, false);
        ViewHolderPlace rvMainAdapterViewHolder = new ViewHolderPlace(view);

        return rvMainAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderPlace holder, int position) {
        Place place = items.get(position);

        holder.tvTitle.setText(place.getTittle());
        holder.tvDescription.setText(place.getDetail());
        Helper.urlToImageView(place.getImageList().get(0),holder.llimage,mContext);


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
        return items.size();
    }

    class ViewHolderPlace extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvTitle, tvKilometers,tvDescription;
        protected LinearLayout llKilometers;
        protected ImageView llimage;

        public ViewHolderPlace(View v) {
            super(v);
            this.tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            this.tvDescription = (TextView) v.findViewById(R.id.tvDescription);
            this.tvKilometers = (TextView) v.findViewById(R.id.tvKilometers);
            this.llKilometers = (LinearLayout) v.findViewById(R.id.llKilometers);
            this.llimage = (ImageView) v.findViewById(R.id.llimage);
            v.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

        //    Intent intent= new Intent(mContext, DetailPlaceActivity.class);
        //    mContext.startActivity(intent);


//            DbPlace place = new DbPlace("", "");
            mlistener.onRutasPlacesVerticalClicked(v, this.getPosition());
        }
    }


    public RoutePlacesVerticalListDataAdapter(OnRutasPlacesVerticalClickListener mlistener, Context context, List<Place> item, boolean userHasLocation) {
        this.items = item;
        this.mlistener = mlistener;
        this.mContext = context;
        this.userHasLocation = userHasLocation;
    }


}


