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
import com.avances.applima.presentation.utils.OnOneOffClickListener;
import com.avances.applima.presentation.view.DistritNeighborhoodView;
import com.avances.applima.presentation.view.InterestView;
import com.avances.applima.presentation.view.PlaceView;
import com.avances.applima.presentation.view.RouteView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements
        PlaceView, RouteView, DistritNeighborhoodView, InterestView,
        DistritHorizontalListDataAdapter.OnDistritHorizontalClickListener,
        RoutesHorizontalListDataAdapter.OnRutasTematicasHorizontalClickListener,
        PlacesHorizontalListDataAdapter.OnImperdiblesHorizontalClickListener,
        TabHome.GoList, TagHorizontalListDataAdapter.OnTagClickListener,
        FilterDialog.CierraDialogFilter {

    OnOneOffClickListener onOneOffClickListener;

    @BindView(R.id.btnSedarch)
    ImageView ivFilter;

    @BindView(R.id.btnMoreImperdibles)
    TextView btnMoreImperdibles;

    @BindView(R.id.btnMoreTematicas)
    TextView btnMoreTematicas;

    @BindView(R.id.editTextSearchCode)
    TextView etBuscador;

    public static RecyclerView rvDistritos, rvLugares, rvTags, rvMejoresRutas;

    public static List<DistritNeighborhood> distritNeighborhoods;
    Context mContext;

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        x = inflater.inflate(R.layout.home_fragment, null);

        injectView(x);

        initUI(x);

        loadPresenter();

        validateComeWithDistritDetailPlaceDetail();

        return x;
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


    private void initUI(View v) {


        onOneOffClickListener=new OnOneOffClickListener() {
            @Override
            public void onSingleClick(View v) {

                switch (v.getId()) {
                    case R.id.btnMoreImperdibles:
                        sendCallbackImperdibles();
                        break;
                    case R.id.btnMoreTematicas:
                        sendCallBackRutasTematicas();
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

        btnMoreImperdibles.setOnClickListener(onOneOffClickListener);
        btnMoreTematicas.setOnClickListener(onOneOffClickListener);
        ivFilter.setOnClickListener(onOneOffClickListener);
        etBuscador.setOnClickListener(onOneOffClickListener);

        rvDistritos = (RecyclerView) v.findViewById(R.id.rv_distritos);
        rvLugares = (RecyclerView) v.findViewById(R.id.rv_lugares);
        rvTags = (RecyclerView) v.findViewById(R.id.rvTags);
        rvMejoresRutas = (RecyclerView) v.findViewById(R.id.rv_mejoresrutas);

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
        RoutesHorizontalListDataAdapter routesHorizontalListDataAdapter = new RoutesHorizontalListDataAdapter(mlistenerRutasTematicasHorizontal, getContext(), routes);

        rvMejoresRutas.setHasFixedSize(true);
        rvMejoresRutas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvMejoresRutas.setAdapter(routesHorizontalListDataAdapter);
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


    public interface GoToImperdiblesFragment {
        public void goToImperdibles();
    }

    public interface GoToRutasTematicasFragment {
        public void goToRutasTematicas();
    }

    public interface GoToBuscador {
        public void goToBuscador();
    }


    void sendCallbackImperdibles() {
        Activity ahhh = getActivity();

        if (ahhh instanceof GoToImperdiblesFragment) {
            ((GoToImperdiblesFragment) ahhh).goToImperdibles();
        }

    }


    void sendCallBackRutasTematicas() {
        Activity ahhh = getActivity();

        if (ahhh instanceof GoToRutasTematicasFragment) {
            ((GoToRutasTematicasFragment) ahhh).goToRutasTematicas();
        }
    }


    void sendCallbackBuscador() {
        Activity ahhh = getActivity();

        if (ahhh instanceof GoToBuscador) {
            ((GoToBuscador) ahhh).goToBuscador();
        }

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


        TagHorizontalListDataAdapter itemListDataAdapter = new TagHorizontalListDataAdapter(getContext(), tags, mlistenerTag);

        rvTags.setHasFixedSize(true);
        rvTags.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvTags.setAdapter(itemListDataAdapter);

        for (DistritNeighborhood distritNeighborhood : distritNeighborhoods) {

            String longTags = distritNeighborhood.getTags();

            for (String tagInserted : tags) {

                if (longTags.toLowerCase().contains(tagInserted.toLowerCase())) {
                    distritNeighborhoodsFilter.add(distritNeighborhood);
                }
            }

        }

/*
        for (DistritNeighborhood distritNeighborhood : distritNeighborhoods) {
            List<String> tagesDistrit = distritNeighborhood.getTagList();

            Set<String> set = new HashSet<>(tagesDistrit);
            tagesDistrit.clear();
            tagesDistrit.addAll(set);

            for (String mTag : tagesDistrit) {

                for (String tagInserted : tags) {
                    if (mTag.toLowerCase().contains(tagInserted)) {
                        
                        distritNeighborhoodsFilter.add(distritNeighborhood);
                    }
                }
            }
        }
*/
/*
        for (Route route : routes) {
            List<String> tagesDistrit = route.getTagList();

            Set<String> set = new HashSet<>(tagesDistrit);
            tagesDistrit.clear();
            tagesDistrit.addAll(set);

            for (String mTag : tagesDistrit) {
                for (String tagInserted : tags) {
                    if (mTag.toLowerCase().contains(tagInserted)) {
                        routesFilter.add(route);
                    }
                }
            }
        }
*/
        for (Route route : routes) {

            String longTags = route.getTags();

            for (String tagInserted : tags) {

                if (longTags.toLowerCase().contains(tagInserted.toLowerCase())) {
                    routesFilter.add(route);
                }
            }

        }

/*
        for (Place place : places) {
            List<String> tagesDistrit = place.getTextTagsList();

            Set<String> set = new HashSet<>(tagesDistrit);
            tagesDistrit.clear();
            tagesDistrit.addAll(set);

            for (String mTag : tagesDistrit) {
                for (String tagInserted : tags) {
                    if (mTag.toLowerCase().contains(tagInserted)) {
                        placesFilter.add(place);
                    }
                }
            }
        }
*/

        for (Place place : places) {

            String longTags = place.getTextTags();

            for (String tagInserted : tags) {

                if (longTags.toLowerCase().contains(tagInserted.toLowerCase())) {
                    placesFilter.add(place);
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


    void loadFilterHomeFragment() {

   /*     FragmentManager fragmentManager =getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_out_down);
        PruebasFragment accountFragment = new PruebasFragment();
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();

*/

        FilterDialog df = new FilterDialog();
        // df.setArguments(args);
        df.show(getFragmentManager(), "ClientDetail");
    }


}