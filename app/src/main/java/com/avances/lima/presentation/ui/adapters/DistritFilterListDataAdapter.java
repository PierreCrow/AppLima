package com.avances.lima.presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.avances.lima.R;
import com.avances.lima.domain.model.DistritFilter;
import com.avances.lima.domain.model.DistritNeighborhood;
import com.avances.lima.domain.model.FilterTag;
import com.avances.lima.presentation.ui.fragments.HomeFragment;
import com.avances.lima.presentation.ui.fragments.HomeLoggedFragment;
import com.avances.lima.presentation.utils.Helper;

import java.util.List;

public class DistritFilterListDataAdapter extends RecyclerView.Adapter<DistritFilterListDataAdapter.SingleItemRowHolder> {


    private List<DistritFilter> itemsList;
    private Context mContext;
    public OnDistritHorizontalClickListener mlistener;


    public DistritFilterListDataAdapter(OnDistritHorizontalClickListener mlistener, Context context, List<DistritFilter> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.mlistener = mlistener;
    }


    public interface OnDistritHorizontalClickListener {
        void onDistritHorizontalClicked(View v, Integer position);
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_distrit_filter_dialog, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        DistritFilter distritFilter= itemsList.get(i);


        Helper.urlToImageView(distritFilter.getDistritNeighborhood().getImageList().get(0), holder.ivDistritImage, mContext);
        holder.tvDistritName.setText(distritFilter.getDistritNeighborhood().getDistrit());
        holder.tvDistritId.setText(distritFilter.getDistritNeighborhood().getIdCloud());

        if (distritFilter.isPressed()) {
            holder.ivOpacity.setVisibility(View.VISIBLE);
        } else {
            holder.ivOpacity.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {

        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        ImageView ivDistritImage, ivOpacity;
        public TextView tvDistritName,tvDistritId;

        boolean clicked = false;

        public SingleItemRowHolder(View view) {
            super(view);

            this.ivDistritImage = (ImageView) view.findViewById(R.id.ivDistritImage);
            this.ivOpacity = (ImageView) view.findViewById(R.id.ivOpacity);
            this.tvDistritName = (TextView) view.findViewById(R.id.tvDistritName);
            this.tvDistritId = (TextView) view.findViewById(R.id.tvDistritId);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mlistener.onDistritHorizontalClicked(view, this.getPosition());

            if (clicked) {
                ivOpacity.setVisibility(View.INVISIBLE);
                clicked = false;
            } else {
                ivOpacity.setVisibility(View.VISIBLE);
                clicked = true;
            }
        }
    }





}