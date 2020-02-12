package com.avances.applima.presentation.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.applima.R;
import com.avances.applima.data.datasource.db.model.DbDistritNeighborhood;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.presentation.ui.adapters.DistritHorizontalListDataAdapter;
import com.avances.applima.presentation.ui.adapters.ImperdiblesHorizontalListDataAdapter;
import com.avances.applima.presentation.ui.adapters.RutasTematicasHorizontalListDataAdapter;
import com.avances.applima.presentation.ui.dialogfragment.FilterDialog;

import java.util.ArrayList;

public class HomeLoggedInFragment extends BaseFragment {

    ArrayList<DbDistritNeighborhood> dbDistritNeighborhoods;
    ArrayList<DbPlace> dbPlaces;
    RecyclerView rvDistritos, rvLugares, rvMejoresRutas;

    ImageView ivFilter;

    TextView btnMoreImperdibles, btnMoreTematicas;

    EditText etBuscador;


    public interface GoToImperdiblesFragment {
        public void goToImperdibles();
    }

    public interface GoToBuscador {
        public void goToBuscador();
    }

    void sendCallback() {
        Activity ahhh = getActivity();
        //   GastosFragment dda = (GastosFragment)getFragmentManager().findFragmentById(R.id.containerView);
        //  List<Fragment> ah=dda.getChildFragmentManager().getFragments();
        //  Fragment ahhh=ah.get(0);

        if (ahhh instanceof GoToImperdiblesFragment) {
            ((GoToImperdiblesFragment) ahhh).goToImperdibles();
        }

    }


    void sendCallbackBuscador() {
        Activity ahhh = getActivity();
        //   GastosFragment dda = (GastosFragment)getFragmentManager().findFragmentById(R.id.containerView);
        //  List<Fragment> ah=dda.getChildFragmentManager().getFragments();
        //  Fragment ahhh=ah.get(0);

        if (ahhh instanceof GoToBuscador) {
            ((GoToBuscador) ahhh).goToBuscador();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View x = inflater.inflate(R.layout.home_logged_in_fragment, null);

        initUI(x);

        clickEvents();

        loadShitData();

        return x;

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


    private void initUI(View v) {
        ivFilter = (ImageView) v.findViewById(R.id.btnSedarch);
        rvDistritos = (RecyclerView) v.findViewById(R.id.rv_distritos);
        rvLugares = (RecyclerView) v.findViewById(R.id.rv_lugares);
        rvMejoresRutas = (RecyclerView) v.findViewById(R.id.rv_mejoresrutas);
        btnMoreImperdibles = (TextView) v.findViewById(R.id.btnMoreImperdibles);
        btnMoreTematicas = (TextView) v.findViewById(R.id.btnMoreTematicas);
        etBuscador = (EditText) v.findViewById(R.id.editTextSearchCode);
    }


    void clickEvents() {

        btnMoreImperdibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendCallback();
/*
                FragmentManager fragmentManager =getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
               // fragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_out_down);
                ImperdiblesFragment accountFragment = new ImperdiblesFragment();
                fragmentTransaction.replace(R.id.containerViewHome, accountFragment);
                fragmentTransaction.commit();
*/

            }
        });


        btnMoreTematicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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


    public void loadShitData() {

        dbDistritNeighborhoods = new ArrayList<DbDistritNeighborhood>();
        for (int j = 0; j <= 5; j++) {
            //  dbDistritNeighborhoods.add(new DbDistritNeighborhood("Item " + j, "URL " + j));
        }

   /*     dbDistritNeighborhoods.add(new DbDistritNeighborhood("Miraflores", "URL "));
        dbDistritNeighborhoods.add(new DbDistritNeighborhood("Barranco", "URL "));
        dbDistritNeighborhoods.add(new DbDistritNeighborhood("San Isidro", "URL "));
        dbDistritNeighborhoods.add(new DbDistritNeighborhood("San Borja", "URL "));
        dbDistritNeighborhoods.add(new DbDistritNeighborhood("Miraflores", "URL "));
*/




        dbPlaces = new ArrayList<DbPlace>();
        for (int j = 0; j <= 5; j++) {
         //   dbPlaces.add(new DbPlace("Item " + j, "URL " + j));
        }

     /*   DistritHorizontalListDataAdapter itemListDataAdapter = new DistritHorizontalListDataAdapter(getContext(), dbDistritNeighborhoods);

        rvDistritos.setHasFixedSize(true);
        rvDistritos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvDistritos.setAdapter(itemListDataAdapter);
*/

//        ImperdiblesHorizontalListDataAdapter routesHorizontalDataAdapter = new ImperdiblesHorizontalListDataAdapter(getContext(), dbPlaces);

   //     rvLugares.setHasFixedSize(true);
    //    rvLugares.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    //    rvLugares.setAdapter(routesHorizontalDataAdapter);

/*
        RutasTematicasHorizontalListDataAdapter rutasTematicasHorizontalListDataAdapter = new RutasTematicasHorizontalListDataAdapter(getContext(), dbPlaces);


        rvMejoresRutas.setHasFixedSize(true);
        rvMejoresRutas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvMejoresRutas.setAdapter(rutasTematicasHorizontalListDataAdapter);*/
    }

}