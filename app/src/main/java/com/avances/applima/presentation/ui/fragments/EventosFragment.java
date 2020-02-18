package com.avances.applima.presentation.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.applima.R;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.domain.model.Event;
import com.avances.applima.presentation.presenter.EventPresenter;
import com.avances.applima.presentation.ui.activities.EditProfileActivity;
import com.avances.applima.presentation.ui.activities.PreferencesActivity;
import com.avances.applima.presentation.ui.adapters.EventosVerticalListDataAdapter;
import com.avances.applima.presentation.ui.dialogfragment.EventDetail;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.view.EventView;

import java.util.ArrayList;
import java.util.List;

public class EventosFragment extends BaseFragment implements
        View.OnClickListener,
        EventosVerticalListDataAdapter.OnItemClickListener, EventView {


    TextView btnMenosImperdibles;
    RecyclerView rv_imperdibles;

    private EventosVerticalListDataAdapter.OnItemClickListener mlistener;
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

        EventDetail df = new EventDetail();
        df.setArguments(bundle);
        df.show(getFragmentManager(), "EventDetail");
    }

    @Override
    public void eventListLoaded(List<Event> mEvents) {

        events = mEvents;

        EventosVerticalListDataAdapter routesHorizontalDataAdapter = new EventosVerticalListDataAdapter(mlistener, getContext(), events,getActivity());


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
