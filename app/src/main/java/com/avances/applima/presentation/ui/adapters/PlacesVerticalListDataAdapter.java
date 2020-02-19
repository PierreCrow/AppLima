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

public class PlacesVerticalListDataAdapter extends RecyclerView.Adapter<PlacesVerticalListDataAdapter.ViewHolderPlace> {


    private List<Place> items = new ArrayList<>();

    public OnImperdiblesVerticalListClickListener mlistener;
    private Context mContext;
    boolean userHasLocation;

    public interface OnImperdiblesVerticalListClickListener {
        void onImperdiblesVerticalListClicked(View v, Integer position);
    }

    public void add(Place item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }


    @Override
    public ViewHolderPlace onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_imperdible_vertical_list, parent, false);
        ViewHolderPlace rvMainAdapterViewHolder = new ViewHolderPlace(view);

        return rvMainAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderPlace holder, int position) {
        Place place = items.get(position);
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
        return items.size();
    }

    class ViewHolderPlace extends RecyclerView.ViewHolder implements View.OnClickListener {


        protected TextView tvTitle, tvKilometers;
        protected LinearLayout llKilometers;
        protected ImageView llimage;

        public ViewHolderPlace(View v) {
            super(v);
            this.tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            this.tvKilometers = (TextView) v.findViewById(R.id.tvKilometers);
            this.llKilometers = (LinearLayout) v.findViewById(R.id.llKilometers);
            this.llimage = (ImageView) v.findViewById(R.id.llimage);
            v.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            mlistener.onImperdiblesVerticalListClicked(v, this.getPosition());

        //    Intent intent= new Intent(mContext, DetailPlaceActivity.class);
        //    mContext.startActivity(intent);

/*
            FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //   fragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_out_down);
            PlaceDetailFragment accountFragment = new PlaceDetailFragment();
            fragmentTransaction.replace(R.id.containerView, accountFragment);
            fragmentTransaction.commit();*/
        }
    }


    public PlacesVerticalListDataAdapter(OnImperdiblesVerticalListClickListener mlistener, Context context, List<Place> itemsList, boolean userHasLocation) {
        this.items = itemsList;
        this.mlistener = mlistener;
        this.mContext = context;
        this.userHasLocation=userHasLocation;
    }


}


