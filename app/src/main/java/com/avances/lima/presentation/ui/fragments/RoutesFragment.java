package com.avances.lima.presentation.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.lima.R;
import com.avances.lima.domain.model.Route;
import com.avances.lima.presentation.presenter.RoutePresenter;
import com.avances.lima.presentation.ui.activities.RoutesListActivity;
import com.avances.lima.presentation.ui.adapters.RoutesVerticalListDataAdapter;
import com.avances.lima.presentation.ui.dialogfragment.FilterDialog;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.utils.SingleClick;
import com.avances.lima.presentation.view.RouteView;

import java.util.List;

import butterknife.BindView;

public class RoutesFragment extends BaseFragment implements
        View.OnClickListener, RouteView,
        RoutesVerticalListDataAdapter.OnRutasTematicasVerticalClickListener {


    @BindView(R.id.btnSedarch)
    ImageView ivFilter;
    @BindView(R.id.btnMenosRutasTematicas)
    TextView btnMenosRutasTematicas;

    @BindView(R.id.editTextSearchCode)
    TextView etBuscador;

    // TextView btnMenosRutasTematicas;
    RecyclerView rv_rutastematicas;
    RoutePresenter routePresenter;
    List<Route> routes;
    SingleClick singleClick;

    private RoutesVerticalListDataAdapter.OnRutasTematicasVerticalClickListener mlistener;

    @Override
    public void routeListLoaded(List<Route> mRoutes) {

        this.routes = mRoutes;

        boolean userHasLocation;

        if (Helper.getUserAppPreference(getContext()).isHasLocation()) {
            if (Helper.gpsIsEnabled(getContext())) {
                userHasLocation = true;
            } else {
                userHasLocation = false;
            }
        } else {
            userHasLocation = false;
        }


        RoutesVerticalListDataAdapter routesHorizontalDataAdapter = new RoutesVerticalListDataAdapter(mlistener, getContext(), routes);

    /*    rv_rutastematicas.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        rv_rutastematicas.setLayoutManager(mLayoutManager);
       rv_rutastematicas.setAdapter(routesHorizontalDataAdapter);
*/
        rv_rutastematicas.setHasFixedSize(true);
        rv_rutastematicas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv_rutastematicas.setAdapter(routesHorizontalDataAdapter);
    }

    @Override
    public void routeCreated(String message) {

    }

    @Override
    public void routeUpdated(String message) {

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
    public void onRutasTematicasVerticalClicked(View v, Integer position) {
        Route route = routes.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("route", route);
        next(RoutesListActivity.class, getContext(), bundle);
    }


    public interface GoToTabHomeFragmentFromRutasTematicas {
        public void goToHomeFromRutasTematicas();
    }

    void sendCallback() {
        Activity ahhh = getActivity();
        //   GastosFragment dda = (GastosFragment)getFragmentManager().findFragmentById(R.id.containerView);
        //  List<Fragment> ah=dda.getChildFragmentManager().getFragments();
        //  Fragment ahhh=ah.get(0);

        if (ahhh instanceof GoToTabHomeFragmentFromRutasTematicas) {
            ((GoToTabHomeFragmentFromRutasTematicas) ahhh).goToHomeFromRutasTematicas();
        }

    }


    @Override
    public void onClick(View view) {


    }


    @Override
    public void onPause() {
        super.onPause();


    }

    void initUI(View v) {

        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {

                switch (v.getId()) {
                    case R.id.btnMenosRutasTematicas:
                        sendCallback();
                        break;
                    case R.id.btnSedarch:
                        loadFilterHomeFragment();
                        break;
                    case R.id.editTextSearchCode:
                        sendCallbackBuscador();
                        break;
                }
            }
        };

        btnMenosRutasTematicas.setOnClickListener(singleClick);
        ivFilter.setOnClickListener(singleClick);
        etBuscador.setOnClickListener(singleClick);

       // btnMenosRutasTematicas = (TextView) v.findViewById(R.id.btnMenosRutasTematicas);
        rv_rutastematicas = (RecyclerView) v.findViewById(R.id.rv_rutastematicas);
        mlistener = this;

    }

    void loadFilterHomeFragment() {

        FilterDialog df = new FilterDialog();
        // df.setArguments(args);
        df.show(getFragmentManager(), "ClientDetail");
    }


    void sendCallbackBuscador() {
        Activity ahhh = getActivity();

        if (ahhh instanceof HomeFragment.GoToBuscador) {
            ((HomeFragment.GoToBuscador) ahhh).goToBuscador();
        }

    }



    void clickEvents() {
        btnMenosRutasTematicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendCallback();

            }
        });


    }


    void GoBack() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //   fragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_out_down);
        TabHome accountFragment = new TabHome();
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();
    }

    void loadPresenter() {
        routePresenter = new RoutePresenter();
        routePresenter.addView(this);
        routePresenter.getRoutes(Constants.STORE.DB);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View x = inflater.inflate(R.layout.rutas_tematicas_fragment, null);

        injectView(x);
        initUI(x);

       // clickEvents();


        loadPresenter();

        return x;

    }


}
