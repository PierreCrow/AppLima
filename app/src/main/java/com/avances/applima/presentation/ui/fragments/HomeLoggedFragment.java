package com.avances.applima.presentation.ui.fragments;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.applima.R;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.domain.model.DistritNeighborhood;
import com.avances.applima.domain.model.Interest;
import com.avances.applima.domain.model.Place;
import com.avances.applima.domain.model.Route;
import com.avances.applima.presentation.presenter.DistritNeighborhoodPresenter;
import com.avances.applima.presentation.presenter.InterestPresenter;
import com.avances.applima.presentation.presenter.PlacePresenter;
import com.avances.applima.presentation.presenter.RoutePresenter;
import com.avances.applima.presentation.ui.activities.PlaceDetailActivity;
import com.avances.applima.presentation.ui.activities.RoutesMapActivity;
import com.avances.applima.presentation.ui.adapters.DistritHorizontalListDataAdapter;
import com.avances.applima.presentation.ui.adapters.PlacesHorizontalListDataAdapter;
import com.avances.applima.presentation.ui.adapters.RoutesHorizontalListDataAdapter;
import com.avances.applima.presentation.ui.adapters.TagHorizontalListDataAdapter;
import com.avances.applima.presentation.ui.dialogfragment.FilterDialog;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.avances.applima.presentation.utils.TinyDB;
import com.avances.applima.presentation.view.DistritNeighborhoodView;
import com.avances.applima.presentation.view.InterestView;
import com.avances.applima.presentation.view.PlaceView;
import com.avances.applima.presentation.view.RouteView;

import java.util.ArrayList;
import java.util.List;

public class HomeLoggedFragment extends BaseFragment implements
        PlaceView, RouteView, DistritNeighborhoodView, InterestView,
        DistritHorizontalListDataAdapter.OnDistritHorizontalClickListener,
        RoutesHorizontalListDataAdapter.OnRutasTematicasHorizontalClickListener,
        PlacesHorizontalListDataAdapter.OnImperdiblesHorizontalClickListener,
        TabHome.GoList, TagHorizontalListDataAdapter.OnTagClickListener, FilterDialog.CierraDialogFilter {

    public static List<DistritNeighborhood> distritNeighborhoods;
    public static RecyclerView rvDistritos, rvLugares, rvMejoresRutas, rvTags;
    Context mContext;
    ImageView ivFilter;
    TextView btnMoreImperdibles, btnMoreTematicas;
    TextView etBuscador;
    public static List<Place> places;
    public static List<Route> routes;
    public static List<String> tags = new ArrayList<>();
    public static List<String> filterTags = new ArrayList<>();

    PlacePresenter placePresenter;
    RoutePresenter routePresenter;
    DistritNeighborhoodPresenter distritNeighborhoodPresenter;
    InterestPresenter interestPresenter;

    public static DistritHorizontalListDataAdapter.OnDistritHorizontalClickListener mlistenerDistritHorizontal;
    public static RoutesHorizontalListDataAdapter.OnRutasTematicasHorizontalClickListener mlistenerRutasTematicasHorizontal;
    public static PlacesHorizontalListDataAdapter.OnImperdiblesHorizontalClickListener mlistenerImperdiblesHorizontal;
    public static TagHorizontalListDataAdapter.OnTagClickListener mlistenerTag;

    View x;
    String NEWTAG;
    public static boolean fromTagFilter = false;


    @Override
    public void placeListLoaded(List<Place> places) {

        this.places = places;

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


        PlacesHorizontalListDataAdapter routesHorizontalDataAdapter = new PlacesHorizontalListDataAdapter(mlistenerImperdiblesHorizontal, getContext(), places, userHasLocation);

        rvLugares.setHasFixedSize(true);
        rvLugares.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvLugares.setAdapter(routesHorizontalDataAdapter);

    }

    @Override
    public void placeCreated(String message) {

    }

    @Override
    public void placeUpdated(String message) {

    }

    @Override
    public void distritNeighborhoodListLoaded(List<DistritNeighborhood> distritNeighborhoods) {

        this.distritNeighborhoods = distritNeighborhoods;
        DistritHorizontalListDataAdapter itemListDataAdapter = new DistritHorizontalListDataAdapter(mlistenerDistritHorizontal, getContext(), distritNeighborhoods);

        rvDistritos.setHasFixedSize(true);
        rvDistritos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvDistritos.setAdapter(itemListDataAdapter);
    }

    @Override
    public void distritCreated(String message) {

    }

    @Override
    public void distritUpdated(String message) {

    }

    @Override
    public void routeListLoaded(List<Route> routes) {
        this.routes = routes;


        TinyDB tinydb = new TinyDB(getContext());
        ArrayList<String> misIdRoutesByInterest = new ArrayList<>();
        misIdRoutesByInterest = tinydb.getListString("routesByInterests");

        if (misIdRoutesByInterest != null) {
            if (misIdRoutesByInterest.size() == 0) {
                RoutesHorizontalListDataAdapter routesHorizontalListDataAdapter = new RoutesHorizontalListDataAdapter(mlistenerRutasTematicasHorizontal, getContext(), routes);

                rvMejoresRutas.setHasFixedSize(true);
                rvMejoresRutas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                rvMejoresRutas.setAdapter(routesHorizontalListDataAdapter);
            } else {

                List<Route> newRoutesByInterest = new ArrayList<>();


                for (String mIDRoute : misIdRoutesByInterest) {

                    for (Route route : routes) {
                        if (route.getId().equals(mIDRoute)) {
                            newRoutesByInterest.add(route);
                        }
                    }

                }


                RoutesHorizontalListDataAdapter routesHorizontalListDataAdapter = new RoutesHorizontalListDataAdapter(mlistenerRutasTematicasHorizontal, getContext(), newRoutesByInterest);

                rvMejoresRutas.setHasFixedSize(true);
                rvMejoresRutas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                rvMejoresRutas.setAdapter(routesHorizontalListDataAdapter);
            }


        }
    }


    @Override
    public void routeCreated(String message) {

    }

    @Override
    public void routeUpdated(String message) {

    }

    @Override
    public void interestListLoaded(List<Interest> dbInterests) {

    }

    @Override
    public void interestCreated(String message) {

    }

    @Override
    public void interestUpdated(String message) {

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
    public void onDistritHorizontalClicked(View v, Integer position) {

        DistritNeighborhood distritNeighborhood = distritNeighborhoods.get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable("distritNeighborhood", distritNeighborhood);
        loadDistritDetailFragment(bundle);

    }

    void loadDistritDetailFragment(Bundle bundle) {


        // next(PlaceDetailActivity.class,getContext(),bundle);

        FragmentManager fragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DistritDetailFragment accountFragment = new DistritDetailFragment();
        accountFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();
    }

    void loadPlaceDetailFragment(Bundle bundle) {

     /*   FragmentManager fragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PlaceDetailFragment accountFragment = new PlaceDetailFragment();
        accountFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();*/

        next(PlaceDetailActivity.class, getContext(), bundle);
    }

    @Override
    public void onRutasTematicasHorizontalClicked(View v, Integer position) {
        Route route = routes.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("route", route);
        //  next(RoutesListActivity.class, getContext(), bundle);
        next(RoutesMapActivity.class, getContext(), bundle);

    }

    @Override
    public void onImperdiblesHorizontalClicked(View v, Integer position) {

        Place place = places.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("place", place);
        loadPlaceDetailFragment(bundle);


    }


    @Override
    public void gotoHomeWithList(boolean newTag, String tag) {
        if (newTag) {
            NEWTAG = tag;

            if (!tag.equals("")) {
                tags.add(tag);
                addTagsPrueba();
            }
        }
    }

    @Override
    public void onTagClicked(View v, String tag) {

        //close tag

        String mitag = tag;

        for (int i = 0; i < tags.size(); i++) {
            if (tag.equals(tags.get(i))) {
                tags.remove(i);
            }
        }


        if (tags != null) {
            if (tags.size() == 0) {
                rvTags.setVisibility(View.GONE);
                fromTagFilter = false;
                loadPresenter();
            } else {
                addTagsPrueba();
            }
        }
        //updateListados

    }

    @Override
    public void onClose_Filter(Boolean close, Context context) {

        mContext = context;
        addTagsPrueba();
    }


    public interface GoToImperdiblesLoggedFragment {
        public void goToImperdiblesLogged();
    }

    public interface GoToRutasTematicasLoggedFragment {
        public void goToRutasTematicasLogged();
    }

    public interface GoToBuscadorLogged {
        public void goToBuscadorLogged();
    }

    void sendCallbackImperdibles() {
        Activity ahhh = getActivity();

        if (ahhh instanceof GoToImperdiblesLoggedFragment) {
            ((GoToImperdiblesLoggedFragment) ahhh).goToImperdiblesLogged();
        }

    }


    void sendCallBackRutasTematicas() {
        Activity ahhh = getActivity();

        if (ahhh instanceof GoToRutasTematicasLoggedFragment) {
            ((GoToRutasTematicasLoggedFragment) ahhh).goToRutasTematicasLogged();
        }
    }


    void sendCallbackBuscador() {
        Activity ahhh = getActivity();

        if (ahhh instanceof GoToBuscadorLogged) {
            ((GoToBuscadorLogged) ahhh).goToBuscadorLogged();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        x = inflater.inflate(R.layout.home_logged_in_fragment, null);

        initUI(x);

        clickEvents();

        loadPresenter();

        validateComeWithDistritDetailPlaceDetail();

        return x;

    }


    void validateComeWithDistritDetailPlaceDetail() {
        SharedPreferences preferences = getContext().getSharedPreferences("PlaceDistritView", Context.MODE_PRIVATE);
        boolean fromDistritDetail = preferences.getBoolean("FromDistritDetail", false);
        if (fromDistritDetail) {
            loadDistritDetailFragment(null);
        }
        // String idPlace=preferences.getString("idPlace",place.getId());
    }


    void setEditTextSize(TextView et) {
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) et.getLayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        ;
        params.width = getResources().getDimensionPixelSize(R.dimen.edit_text_search_home_width);
        et.setLayoutParams(params);
    }

    void addTagsPrueba() {

        //  final Animation animScale = AnimationUtils.loadAnimation(getContext(), R.anim.anim_scale_search_home);


        //  etBuscador.startAnimation(animScale);

        fromTagFilter = true;

        rvTags.setVisibility(View.VISIBLE);

        //   next(MainActivity.class,getActivity(),null);

        boolean userHasLocation;

        Context context = getContext();

        if (Helper.getUserAppPreference(mContext).isHasLocation()) {
            if (Helper.gpsIsEnabled(mContext)) {
                userHasLocation = true;
            } else {
                userHasLocation = false;
            }
        } else {
            userHasLocation = false;
        }


        List<DistritNeighborhood> distritNeighborhoodsFilter = new ArrayList<>();
        List<Place> placesFilter = new ArrayList<>();
        List<Route> routesFilter = new ArrayList<>();


    //    TagHorizontalListDataAdapter itemListDataAdapter = new TagHorizontalListDataAdapter(getContext(), tags, mlistenerTag);

        rvTags.setHasFixedSize(true);
        rvTags.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
     //   rvTags.setAdapter(itemListDataAdapter);


        for (DistritNeighborhood distritNeighborhood : distritNeighborhoods) {
            List<String> tagesDistrit = distritNeighborhood.getTagList();

            for (String mTag : tagesDistrit) {

                for (String tagInserted : tags) {
                    if (mTag.toLowerCase().contains(tagInserted)) {

                        distritNeighborhoodsFilter.add(distritNeighborhood);
                    }
                }
            }
        }


        for (Route route : routes) {
            List<String> tagesDistrit = route.getTagList();

            for (String mTag : tagesDistrit) {
                for (String tagInserted : tags) {
                    if (mTag.toLowerCase().contains(tagInserted)) {
                        routesFilter.add(route);
                    }
                }
            }
        }


        for (Place place : places) {
            List<String> tagesDistrit = place.getTextTagsList();

            for (String mTag : tagesDistrit) {
                for (String tagInserted : tags) {
                    if (mTag.toLowerCase().contains(tagInserted)) {
                        placesFilter.add(place);
                    }
                }
            }
        }


  /*      //---------------- for filterss

        if(filterTags.size()!=0)
        {
            for (DistritNeighborhood distritNeighborhood : distritNeighborhoods) {

                for (String tagInserted : filterTags) {
                    if (distritNeighborhood.getDistrit().equals(tagInserted)) {
                        distritNeighborhoodsFilter.add(distritNeighborhood);
                    }
                }
            }
        }
*/


        //------------------------


        DistritHorizontalListDataAdapter distritHorizontalListDataAdapter = new DistritHorizontalListDataAdapter(mlistenerDistritHorizontal, mContext, distritNeighborhoodsFilter);

        rvDistritos.setHasFixedSize(true);
        rvDistritos.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvDistritos.setAdapter(distritHorizontalListDataAdapter);


        RoutesHorizontalListDataAdapter routesHorizontalListDataAdapter = new RoutesHorizontalListDataAdapter(mlistenerRutasTematicasHorizontal, mContext, routesFilter);

        rvMejoresRutas.setHasFixedSize(true);
        rvMejoresRutas.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvMejoresRutas.setAdapter(routesHorizontalListDataAdapter);


        PlacesHorizontalListDataAdapter placesHorizontalListDataAdapter = new PlacesHorizontalListDataAdapter(mlistenerImperdiblesHorizontal, mContext, placesFilter, userHasLocation);

        rvLugares.setHasFixedSize(true);
        rvLugares.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvLugares.setAdapter(placesHorizontalListDataAdapter);


    }


    void loadPresenter() {

        if (!fromTagFilter) {
            placePresenter = new PlacePresenter();
            placePresenter.addView(this);
            placePresenter.getPlaces(Constants.STORE.DB);

            routePresenter = new RoutePresenter();
            routePresenter.addView(this);
            routePresenter.getRoutes(Constants.STORE.DB);

            interestPresenter = new InterestPresenter();
            interestPresenter.addView(this);
            interestPresenter.getInterests(Constants.STORE.DB);

            distritNeighborhoodPresenter = new DistritNeighborhoodPresenter();
            distritNeighborhoodPresenter.addView(this);
            distritNeighborhoodPresenter.getDistritNeighborhoods(Constants.STORE.DB);
        }
    }


    void loadFilterHomeFragment() {

        FilterDialog df = new FilterDialog();
        // df.setArguments(args);
        df.show(getFragmentManager(), "ClientDetail");
    }


    private void initUI(View v) {
        ivFilter = (ImageView) v.findViewById(R.id.btnSedarch);
        rvDistritos = (RecyclerView) v.findViewById(R.id.rv_distritos);
        rvLugares = (RecyclerView) v.findViewById(R.id.rv_lugares);
        rvTags = (RecyclerView) v.findViewById(R.id.rvTags);
        rvMejoresRutas = (RecyclerView) v.findViewById(R.id.rv_mejoresrutas);
        btnMoreImperdibles = (TextView) v.findViewById(R.id.btnMoreImperdibles);
        btnMoreTematicas = (TextView) v.findViewById(R.id.btnMoreTematicas);
        etBuscador = (TextView) v.findViewById(R.id.editTextSearchCode);

        mContext = getContext();

        setEditTextSize(etBuscador);

        ivFilter.setVisibility(View.VISIBLE);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivFilter, "alpha", 0, 1);
        objectAnimator.setDuration(1000);
        objectAnimator.setStartDelay(0);
        objectAnimator.start();

        mlistenerDistritHorizontal = this;
        mlistenerRutasTematicasHorizontal = this;
        mlistenerImperdiblesHorizontal = this;
        mlistenerTag = this;

        if (tags != null) {
            if (tags.size() == 0) {
                rvTags.setVisibility(View.GONE);
            } else {
                addTagsPrueba();
            }
        }

    }


    void clickEvents() {

        btnMoreImperdibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCallbackImperdibles();
            }
        });


        btnMoreTematicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCallBackRutasTematicas();
            }
        });


        ivFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   loadFilterHomeFragment();
            }
        });


        etBuscador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCallbackBuscador();
            }
        });
    }

}