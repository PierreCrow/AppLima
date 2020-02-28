package com.avances.applima.presentation.ui.adapters;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.avances.applima.R;
import com.avances.applima.domain.model.DistritNeighborhood;
import com.avances.applima.presentation.utils.Helper;

import java.util.List;


public class DistritHorizontalListDataAdapter extends RecyclerView.Adapter<DistritHorizontalListDataAdapter.SingleItemRowHolder> {


    private List<DistritNeighborhood> itemsList;
    private Context mContext;

    Integer counter = 0;
    Integer imageOne = 0, imageTwo = 1, imageThree = 2, imageViewing;
    public OnDistritHorizontalClickListener mlistener;


    public DistritHorizontalListDataAdapter(OnDistritHorizontalClickListener mlistener, Context context, List<DistritNeighborhood> itemsList) {
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
        if (Helper.getUserAppPreference(mContext).isLogged()) {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_distrit_home_logged_in, null);
        } else {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_distrit_home, null);
        }

        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        DistritNeighborhood dbDistritNeighborhood = itemsList.get(i);
        holder.tvTitle.setText(dbDistritNeighborhood.getDistrit());
        holder.tvSubTittle.setText(dbDistritNeighborhood.getShortDescription());

        Helper.urlToImageView(dbDistritNeighborhood.getImageList().get(0), holder.imagen1, mContext);
        Helper.urlToImageView(dbDistritNeighborhood.getImageList().get(1), holder.imagen2, mContext);
        Helper.urlToImageView(dbDistritNeighborhood.getImageList().get(2), holder.imagen3, mContext);

        holder.imagen1.setAlpha(0.8f);
        holder.imagen2.setAlpha(0.8f);
        holder.imagen3.setAlpha(0.8f);

        startTimer(holder);
    }


    @Override
    public int getItemCount() {

        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        protected TextView tvTitle, tvSubTittle;
        protected ImageView imagen1, imagen2, imagen3;


        public SingleItemRowHolder(View view) {
            super(view);

            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.tvSubTittle = (TextView) view.findViewById(R.id.tvSubTittle);
            this.imagen1 = (ImageView) view.findViewById(R.id.imagen1);
            this.imagen2 = (ImageView) view.findViewById(R.id.imagen2);
            this.imagen3 = (ImageView) view.findViewById(R.id.imagen3);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mlistener.onDistritHorizontalClicked(view, this.getPosition());
        }
    }


    void startTimer(SingleItemRowHolder holder) {

        imageViewing = imageOne;

        CountDownTimer cTimer = null;
        cTimer = new CountDownTimer(1000000000, 6000) {
            public void onTick(long millisUntilFinished) {


                switch (imageViewing) {
                    case 0:
                        holder.imagen1.setVisibility(View.VISIBLE);
                        holder.imagen2.setVisibility(View.GONE);
                        holder.imagen3.setVisibility(View.GONE);
                        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(holder.imagen1, "alpha", 0, 1);
                        objectAnimator.setDuration(1000);
                        objectAnimator.setStartDelay(0);
                        objectAnimator.start();
                        imageViewing = imageTwo;
                        break;

                    case 1:

                        holder.imagen2.setVisibility(View.VISIBLE);
                        holder.imagen3.setVisibility(View.GONE);
                        holder.imagen1.setVisibility(View.GONE);
                        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(holder.imagen2, "alpha", 0, 1);
                        objectAnimator2.setDuration(1000);
                        objectAnimator2.setStartDelay(0);
                        objectAnimator2.start();
                        imageViewing = imageThree;
                        break;

                    case 2:
                        holder.imagen3.setVisibility(View.VISIBLE);
                        holder.imagen2.setVisibility(View.GONE);
                        holder.imagen2.setVisibility(View.GONE);
                        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(holder.imagen3, "alpha", 0, 1);
                        objectAnimator3.setDuration(1000);
                        objectAnimator3.setStartDelay(0);
                        objectAnimator3.start();
                        imageViewing = imageOne;
                        break;
                }
            }

            public void onFinish() {
            }
        };
        cTimer.start();
    }

}