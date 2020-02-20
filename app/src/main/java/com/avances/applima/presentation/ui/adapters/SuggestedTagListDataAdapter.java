package com.avances.applima.presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.avances.applima.R;
import com.avances.applima.domain.model.SuggestedTag;

import java.util.List;

public class SuggestedTagListDataAdapter extends RecyclerView.Adapter<SuggestedTagListDataAdapter.SingleItemRowHolder> {

    private List<SuggestedTag> suggestedTags;
    private Context mContext;
    public OnTagClickListener mlistener;

    public SuggestedTagListDataAdapter(Context context, List<SuggestedTag> suggestedTags, OnTagClickListener mlistener) {
        this.suggestedTags = suggestedTags;
        this.mContext = context;
        this.mlistener = mlistener;
    }

    public interface OnTagClickListener {
        void onTagClicked(View v, String tag);
    }

    public void add(SuggestedTag suggestedTag) {
        suggestedTags.add(suggestedTag);
        notifyItemInserted(suggestedTags.size() - 1);
    }

    public void updateList(List<SuggestedTag> list) {
        suggestedTags = list;
        notifyDataSetChanged();
    }


    public void onItemDismiss(int position) {
        if (position != -1 && position < suggestedTags.size()) {
            suggestedTags.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
        }
    }


    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_suggested_tag, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        SuggestedTag place = suggestedTags.get(i);
        holder.nameSuggestedTag.setText(place.getName());
    }


    @Override
    public int getItemCount() {
        return (null != suggestedTags ? suggestedTags.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView nameSuggestedTag;
        protected RelativeLayout rlSuggestedTag;


        public SingleItemRowHolder(View view) {
            super(view);

            this.rlSuggestedTag = (RelativeLayout) view.findViewById(R.id.rlSuggestedTag);
            this.nameSuggestedTag = (TextView) view.findViewById(R.id.nameSuggestedTag);

            rlSuggestedTag.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            mlistener.onTagClicked(view, nameSuggestedTag.getText().toString());
            onItemDismiss(this.getPosition());

        }
    }

}
