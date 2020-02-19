package com.avances.applima.presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.avances.applima.R;
import com.avances.applima.domain.model.Route;
import com.avances.applima.presentation.utils.Helper;

import java.util.ArrayList;
import java.util.List;

public class RoutesVerticalListDataAdapter extends RecyclerView.Adapter<RoutesVerticalListDataAdapter.ViewHolderPlace> {


    private List<Route> items = new ArrayList<>();

    public OnRutasTematicasVerticalClickListener mlistener;
    private Context mContext;

    public interface OnRutasTematicasVerticalClickListener {
        void onRutasTematicasVerticalClicked(View v, Integer position);
    }

    public void add(Route item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }


    @Override
    public ViewHolderPlace onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ruta_tematica_vertical_list, parent, false);
        ViewHolderPlace rvMainAdapterViewHolder = new ViewHolderPlace(view);

        return rvMainAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderPlace holder, int position) {
        Route route = items.get(position);

        holder.tvTitle.setText(route.getRouteName());
        Helper.urlToImageView(route.getImage(),holder.ivRouteImage,mContext);
        Helper.urlToImageView(route.getIconImage(),holder.ivRouteIcon,mContext);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolderPlace extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvTitle;
        protected ImageView ivRouteImage,ivRouteIcon;

        public ViewHolderPlace(View v) {
            super(v);
            this.tvTitle = (TextView) v.findViewById(R.id.tvTitle);

            this.ivRouteImage = (ImageView) v.findViewById(R.id.ivRouteImage);
            this.ivRouteIcon = (ImageView) v.findViewById(R.id.ivRouteIcon);
            v.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            mlistener.onRutasTematicasVerticalClicked(v,this.getPosition());


        }
    }


    public RoutesVerticalListDataAdapter(OnRutasTematicasVerticalClickListener mlistener, Context context, List<Route> item) {
        this.items = item;
        this.mlistener = mlistener;
        this.mContext = context;
    }


}


