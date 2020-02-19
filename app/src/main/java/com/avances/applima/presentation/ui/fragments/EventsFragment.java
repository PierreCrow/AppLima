package com.avances.applima.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.applima.R;
import com.avances.applima.domain.model.Event;
import com.avances.applima.presentation.presenter.EventPresenter;
import com.avances.applima.presentation.ui.activities.EditProfileActivity;
import com.avances.applima.presentation.ui.activities.PreferencesActivity;
import com.avances.applima.presentation.ui.adapters.EventsVerticalListDataAdapter;
import com.avances.applima.presentation.ui.dialogfragment.EventDetailDialog;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.view.EventView;

import java.util.List;

public class EventsFragment extends BaseFragment implements
        View.OnClickListener,
        EventsVerticalListDataAdapter.OnItemClickListener, EventView {


    TextView btnMenosImperdibles;
    RecyclerView rv_imperdibles;

    private EventsVerticalListDataAdapter.OnItemClickListener mlistener;
    EventPresenter eventPresenter;
    List<Event> events;


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.llEditarPerfil:
                next(EditProfileActivity.class, getContext(), null);
                break;
            case R.id.llPreferencias:
                next(PreferencesActivity.class, getContext(), null);
                break;
            case R.id.llValoraApp:

                break;
            case R.id.llCerrarSesion:

                break;

        }
    }


    @Override
    public void onPause() {
        super.onPause();


    }


    void loadPresenter() {
        eventPresenter = new EventPresenter();
        eventPresenter.addView(this);
        eventPresenter.getEvents(Constants.STORE.DB);
    }

    void initUI(View v) {

        rv_imperdibles = (RecyclerView) v.findViewById(R.id.rv_eventos);
        mlistener = this;

    }


    void clickEvents() {


    }


    void GoBack() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //   fragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_out_down);
        HomeFragment accountFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View x = inflater.inflate(R.layout.eventos_fragment, null);


        initUI(x);

        loadPresenter();

        clickEvents();


        return x;

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

        EventsVerticalListDataAdapter routesHorizontalDataAdapter = new EventsVerticalListDataAdapter(mlistener, getContext(), events,getActivity());


        rv_imperdibles.setHasFixedSize(true);
        rv_imperdibles.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv_imperdibles.setAdapter(routesHorizontalDataAdapter);
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
}
