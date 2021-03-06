package com.avances.lima.presentation.ui.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.lima.R;
import com.avances.lima.domain.model.Event;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventsVerticalListDataAdapter extends RecyclerView.Adapter<EventsVerticalListDataAdapter.ViewHolderPlace> {

    private List<Event> items = new ArrayList<>();
    public OnItemClickListener mlistener;
    private Context mContext;
    Activity activity;
    public static Event eventClicked;
    public static String completeDate;

    public interface OnItemClickListener {
        void onItemClicked(View v, int position);
    }

    public void add(Event item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    @Override
    public ViewHolderPlace onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_home, parent, false);
        ViewHolderPlace rvMainAdapterViewHolder = new ViewHolderPlace(view);
        return rvMainAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderPlace holder, int position) {
        Event event = items.get(position);
        String dtStart = event.getStartDate();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try {
            Date date = format.parse(dtStart);
            String day = (String) DateFormat.format("dd", date);
            String monthNumber = (String) DateFormat.format("MM", date);
            Integer month = Integer.parseInt(monthNumber) - 1;
            holder.tvDayEvent.setText(day);
            holder.tvMonthEvent.setText(Helper.capitalizeFirstLetter(Helper.getMonthForInt(month)));
        } catch (ParseException e) {

        }
        Helper.urlToImageView(event.getImage(), holder.llEventImage, mContext);
        holder.tvstartdate.setText(event.getStartDate());
        holder.tvfinaldate.setText(event.getFinalDate());
        holder.tvTitle.setText(event.getTittle());
        holder.tvShortDescription.setText(event.getShortDecription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolderPlace extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView tvTitle, tvShortDescription, tvDayEvent, tvMonthEvent, tvstartdate, tvfinaldate;
        protected RelativeLayout rlVerMas;
        protected ImageView ivCalendar;
        protected ImageView llEventImage;

        public ViewHolderPlace(View v) {
            super(v);
            this.tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            this.tvShortDescription = (TextView) v.findViewById(R.id.tvShortDescription);
            this.tvDayEvent = (TextView) v.findViewById(R.id.tvDayEvent);
            this.tvMonthEvent = (TextView) v.findViewById(R.id.tvMonthEvent);
            this.rlVerMas = (RelativeLayout) v.findViewById(R.id.rlVerMas);
            this.ivCalendar = (ImageView) v.findViewById(R.id.ivCalendar);
            this.llEventImage = (ImageView) v.findViewById(R.id.llEventImage);
            this.tvstartdate = (TextView) v.findViewById(R.id.tvstartdate);
            this.tvfinaldate = (TextView) v.findViewById(R.id.tvfinaldate);
            rlVerMas.setOnClickListener(this);
            ivCalendar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            completeDate = tvDayEvent.getText().toString();
            String dtStart = tvstartdate.getText().toString();
            String dtFinal = tvfinaldate.getText().toString();
            String tittle = tvTitle.getText().toString();
            String desxcription = tvShortDescription.getText().toString();
            eventClicked = new Event("", tittle, "", desxcription, "", "", "", true, false, "", dtStart, dtFinal);
            if (v.getId() == R.id.ivCalendar) {
                if (hasCalendarPermission()) {

                    Helper.addCalendarEvent(mContext, tittle, desxcription, dtStart, dtFinal);
                    Toast.makeText(mContext, "Agregado a Calendario", Toast.LENGTH_SHORT).show();
                } else {
                    ActivityCompat.requestPermissions(activity,
                            new String[]{Manifest.permission.WRITE_CALENDAR,
                                    Manifest.permission.READ_CALENDAR},
                            Constants.REQUEST_CODES.REQUEST_CODE_CALENDAR);
                }
            } else {
                mlistener.onItemClicked(v, this.getPosition());
            }

        }
    }

    public EventsVerticalListDataAdapter(OnItemClickListener listener, Context context, List<Event> item, Activity activity) {
        this.items = item;
        this.mlistener = listener;
        this.mContext = context;
        this.activity = activity;
    }

    public boolean hasCalendarPermission() {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED
        ) {
            return false;
        } else {
            return true;
        }
    }

}





