package com.avances.applima.presentation.ui.adapters;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.applima.R;
import com.avances.applima.domain.model.DistritNeighborhood;
import com.avances.applima.presentation.ui.fragments.DistritDetailFragment;
import com.avances.applima.presentation.utils.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DistritFilterListDataAdapter extends RecyclerView.Adapter<DistritFilterListDataAdapter.SingleItemRowHolder> {


    private List<DistritNeighborhood> itemsList;
    private Context mContext;


    public OnDistritHorizontalClickListener mlistener;


    public DistritFilterListDataAdapter(OnDistritHorizontalClickListener mlistener, Context context, List<DistritNeighborhood> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.mlistener=mlistener;
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

        DistritNeighborhood dbDistritNeighborhood = itemsList.get(i);

        Helper.urlToImageView(dbDistritNeighborhood.getImageList().get(0),holder.imagen1,mContext);
        holder.tvDistritName.setText(dbDistritNeighborhood.getDistrit());

    }

    @Override
    public int getItemCount() {

        // return Integer.MAX_VALUE;

        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        ImageView imagen1;
        TextView tvDistritName;


        public SingleItemRowHolder(View view) {
            super(view);

            this.imagen1 = (ImageView) view.findViewById(R.id.ivDistritImage);
            this.tvDistritName = (TextView) view.findViewById(R.id.tvDistritName);

            view.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
           // Integer aa=v.getId();

            mlistener.onDistritHorizontalClicked(view,this.getPosition());

          //  loadDistritDetailFragment();
        }
    }


    void loadDistritDetailFragment() {


        FragmentManager fragmentManager = ((AppCompatActivity) mContext).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DistritDetailFragment accountFragment = new DistritDetailFragment();
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();
    }





}