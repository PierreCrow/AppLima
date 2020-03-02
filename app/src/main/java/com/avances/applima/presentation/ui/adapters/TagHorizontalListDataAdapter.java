package com.avances.applima.presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.avances.applima.R;
import com.avances.applima.domain.model.FilterTag;
import com.avances.applima.domain.model.Place;

import java.util.List;

public class TagHorizontalListDataAdapter extends RecyclerView.Adapter<TagHorizontalListDataAdapter.SingleItemRowHolder> {


    private List<FilterTag> tags;
    private Context mContext;
    public OnTagClickListener mlistener;

    public TagHorizontalListDataAdapter(Context context, List<FilterTag> tags, OnTagClickListener mlistener) {
        this.tags = tags;
        this.mContext = context;
        this.mlistener=mlistener;
    }


    public interface OnTagClickListener {
        void onTagClicked(View v, String tag);
    }

    public void add(FilterTag item){
        tags.add(item);
        notifyItemInserted(tags.size()-1);
    }



    public void removeAt(int position) {
        tags.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, tags.size());
    }


    public void onItemDismiss(int position) {
        if(position!=-1 && position<tags.size())
        {
            tags.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
        }
    }



    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tag_home, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        FilterTag tag = tags.get(i);
        holder.tvTag.setText(tag.getName());

    }

    @Override
    public int getItemCount() {
        return (null != tags ? tags.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView btntag;
        protected TextView tvTag;



        public SingleItemRowHolder(View view) {
            super(view);

           this.btntag = (ImageView) view.findViewById(R.id.btntag);
           this.tvTag = (TextView) view.findViewById(R.id.tvTag);



            btntag.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            mlistener.onTagClicked(view,tvTag.getText().toString());

         //   removeAt(this.getPosition());
            onItemDismiss(this.getPosition());

        }
    }

}
