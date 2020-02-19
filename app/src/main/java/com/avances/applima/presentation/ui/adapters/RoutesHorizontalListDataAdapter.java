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

import java.util.List;

public class RoutesHorizontalListDataAdapter extends RecyclerView.Adapter<RoutesHorizontalListDataAdapter.SingleItemRowHolder> {


    private List<Route> itemsList;
    private Context mContext;
    public OnRutasTematicasHorizontalClickListener mlistener;

    public RoutesHorizontalListDataAdapter(OnRutasTematicasHorizontalClickListener mlistener, Context context, List<Route> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.mlistener=mlistener;
    }

    public interface OnRutasTematicasHorizontalClickListener {
        void onRutasTematicasHorizontalClicked(View v, Integer position);
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(mContext);


        View v;
        if(Helper.getUserAppPreference(mContext).isLogged())
        {
            v = inflater.inflate(R.layout.item_rutasparati_home, viewGroup, false);
          //  v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rutasparati_home, null);
        }
        else
        {
            v = inflater.inflate(R.layout.item_rutas_tematicas_home, viewGroup, false);
           // v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rutas_tematicas_home, null);
        }



        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        Route route = itemsList.get(i);

        holder.tvTitle.setText(route.getRouteName());
       Helper.urlToImageView(route.getImage(),holder.ivRouteImage,mContext);
        Helper.urlToImageView(route.getIconImage(),holder.ivRouteIcon,mContext);


    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvTitle;
        protected ImageView ivRouteImage,ivRouteIcon;



        public SingleItemRowHolder(View view) {
            super(view);

            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);

            this.ivRouteImage = (ImageView) view.findViewById(R.id.ivRouteImage);
            this.ivRouteIcon = (ImageView) view.findViewById(R.id.ivRouteIcon);

            view.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            mlistener.onRutasTematicasHorizontalClicked(view,this.getPosition());
        //    Intent intent= new Intent(mContext, RoutesListActivity.class);
         //   mContext.startActivity(intent);

        }
    }

}
