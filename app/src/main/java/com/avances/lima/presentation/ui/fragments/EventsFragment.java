package com.avances.lima.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.lima.R;
import com.avances.lima.domain.model.Event;
import com.avances.lima.presentation.presenter.EventPresenter;
import com.avances.lima.presentation.ui.activities.MainActivity;
import com.avances.lima.presentation.ui.adapters.EventsVerticalListDataAdapter;
import com.avances.lima.presentation.ui.dialogfragment.EventDetailDialog;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.view.EventView;

import java.util.List;

import butterknife.BindView;

public class EventsFragment extends BaseFragment implements
        EventsVerticalListDataAdapter.OnItemClickListener, EventView {

    @BindView(R.id.rvEvents)
    RecyclerView rvEvents;

    private EventsVerticalListDataAdapter.OnItemClickListener mlistener;
    EventPresenter eventPresenter;
    List<Event> events;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View x = inflater.inflate(R.layout.eventos_fragment, null);
        injectView(x);
        initUI(x);
        loadPresenter();
        return x;
    }

    void loadPresenter() {
        eventPresenter = new EventPresenter();
        eventPresenter.addView(this);
        eventPresenter.getEvents(Constants.STORE.DB);
    }

    void initUI(View v) {
        mlistener = this;
    }

    @Override
    public void onItemClicked(View v, int position) {

        Event event = events.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("event", event);

        EventDetailDialog df = new EventDetailDialog();
        df.setArguments(bundle);
        df.show(getFragmentManager(), "EventDetailDialog");
    }

    @Override
    public void eventListLoaded(List<Event> mEvents) {
        events = mEvents;
        EventsVerticalListDataAdapter routesHorizontalDataAdapter = new EventsVerticalListDataAdapter(mlistener, getContext(), events, getActivity());
        rvEvents.setHasFixedSize(true);
        rvEvents.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvEvents.setAdapter(routesHorizontalDataAdapter);
    }

    @Override
    public void eventCreated(String message) {
    }

    @Override
    public void eventUpdated(String message) {
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showErrorMessage(String message) {
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            MainActivity.FRAGMENT_VIEWING = Constants.FRAGMENTS_TABS.EVENTS;
        }
    }

}
