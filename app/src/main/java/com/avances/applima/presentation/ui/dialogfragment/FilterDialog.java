package com.avances.applima.presentation.ui.dialogfragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.applima.R;
import com.avances.applima.domain.model.DistritNeighborhood;
import com.avances.applima.domain.model.FilterTag;
import com.avances.applima.presentation.presenter.DistritNeighborhoodPresenter;
import com.avances.applima.presentation.ui.adapters.DistritFilterListDataAdapter;
import com.avances.applima.presentation.ui.fragments.HomeFragment;
import com.avances.applima.presentation.ui.fragments.HomeLoggedFragment;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.avances.applima.presentation.utils.OnOneOffClickListener;
import com.avances.applima.presentation.view.DistritNeighborhoodView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterDialog extends DialogFragment
        implements DistritFilterListDataAdapter.OnDistritHorizontalClickListener, DistritNeighborhoodView {


    // @BindView(R.id.ivClose)
    ImageView ivClose;

    //  @BindView(R.id.tvInteres1)
    TextView tvInteres1;

    //   @BindView(R.id.tvInteres2)
    TextView tvInteres2;

    //  @BindView(R.id.tvInteres3)
    TextView tvInteres3;

    //  @BindView(R.id.tvInteres4)
    TextView tvInteres4;

    //   @BindView(R.id.tvInteres5)
    TextView tvInteres5;

    // @BindView(R.id.tvNose)
    TextView tvNose;

    //   @BindView(R.id.tv1Dia)
    TextView tv1Dia;

    //  @BindView(R.id.tv2Dias)
    TextView tv2Dias;

    //  @BindView(R.id.tvMasDe3Dias)
    TextView tvMasDe3Dias;


    //  @BindView(R.id.rlInteres1)
    RelativeLayout rlInteres1;

    //  @BindView(R.id.rlInteres2)
    RelativeLayout rlInteres2;

    //   @BindView(R.id.rlInteres3)
    RelativeLayout rlInteres3;

    //   @BindView(R.id.rlInteres4)
    RelativeLayout rlInteres4;

    //  @BindView(R.id.rlInteres5)
    RelativeLayout rlInteres5;


    //  @BindView(R.id.rlNose)
    RelativeLayout rlNose;

    //   @BindView(R.id.rl1Dia)
    RelativeLayout rl1Dia;

    // @BindView(R.id.rl2Dias)
    RelativeLayout rl2Dias;

    // @BindView(R.id.rlMasDe3Dias)
    RelativeLayout rlMasDe3Dias;


    //  @BindView(R.id.btnAplicar)
    Button btnAplicar;

    //  @BindView(R.id.rvDistrits)
    RecyclerView rvDistrits;

    // @BindView(R.id.transparent_linear_filter)
    LinearLayout transparent_linear_filter;


    public static boolean interes1Pressed=false, interes2Pressed=false, interes3Pressed=false, interes4Pressed=false, interes5Pressed=false;
    public static boolean distritPressed1, distritPressed2, distritPressed3, distritPressed4, distritPressed5;

    public static boolean nosePressed=false, oneDayPressed=false, twoDaysPressed=false, moreThan3=false;

    ImageView ivDistrit1, ivDistrit2, ivDistrit3, ivDistrit4, ivDistrit5;
    ImageView ivDistrit1_on, ivDistrit2_on, ivDistrit3_on, ivDistrit4_on, ivDistrit5_on;

    TextView tvDistrit1, tvDistrit2, tvDistrit3, tvDistrit4, tvDistrit5;
    List<String> filters;
    DistritNeighborhoodPresenter distritNeighborhoodPresenter;
    List<DistritNeighborhood> distrits;
    OnOneOffClickListener onOneOffClickListener;

    DistritNeighborhood distritNeighborhoodSelected;
    String[] distritSelectedNames = new String[10000];
    Boolean[] distritPressed = new Boolean[10000];
    // List<String> distritSelectedNames= new ArrayList<>();

    public static DistritFilterListDataAdapter.OnDistritHorizontalClickListener mlistenerDistritHorizontal;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.filter_dialog, new LinearLayout(getActivity()), false);


        // injectView(view);
        initUI(view);

     //   setFields();

        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);

        builder.setContentView(view);
        return builder;

    }


    void setFields() {

     /*
        if (distritPressed1) {
            ivDistrit1_on.setVisibility(View.VISIBLE);
        }

        if (distritPressed2) {
            ivDistrit2_on.setVisibility(View.VISIBLE);
        }

        if (distritPressed3) {
            ivDistrit3_on.setVisibility(View.VISIBLE);
        }

        if (distritPressed4) {
            ivDistrit4_on.setVisibility(View.VISIBLE);
        }

        if (distritPressed5) {
            ivDistrit5_on.setVisibility(View.VISIBLE);
        }
*/




        if (interes1Pressed) {

            rlInteres1.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
            tvInteres1.setTextColor(Color.WHITE);
        }

        if (interes2Pressed) {
            rlInteres2.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
            tvInteres1.setTextColor(Color.WHITE);
        }

        if (interes3Pressed) {
            rlInteres3.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
            tvInteres1.setTextColor(Color.WHITE);
        }

        if (interes4Pressed) {
            rlInteres4.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
            tvInteres1.setTextColor(Color.WHITE);
        }

        if (interes5Pressed) {
            rlInteres5.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
            tvInteres1.setTextColor(Color.WHITE);
        }

        if (nosePressed) {
            rlNose.setBackgroundResource(R.drawable.shape_filter_permanency_left_on);
            tvNose.setTextColor(Color.WHITE);
        }

        if (oneDayPressed) {
            rl1Dia.setBackgroundResource(R.drawable.shape_filter_permenency_middle_on);
            tv1Dia.setTextColor(Color.WHITE);
        }

        if (twoDaysPressed) {
            rl2Dias.setBackgroundResource(R.drawable.shape_filter_permenency_middle_on);
            tv2Dias.setTextColor(Color.WHITE);
        }

        if (moreThan3) {
            rlMasDe3Dias.setBackgroundResource(R.drawable.shape_filter_permanency_rigth_on);
            tvMasDe3Dias.setTextColor(Color.WHITE);
        }
    }

    void juntartags() {


        for (int i = 0; i < distritPressed.length; i++) {
            boolean alreadyExist = false;

            if (distritPressed[i] != null && distritPressed[i]) {
                if (Helper.getUserAppPreference(getContext()).isLogged()) {

                    if (HomeLoggedFragment.tags.size() > 0) {
                        String newTag = distritSelectedNames[i].toLowerCase();
                        for (int j = 0; j < HomeLoggedFragment.tags.size(); j++) {
                            if (newTag.equals(HomeLoggedFragment.tags.get(j))) {
                                alreadyExist = true;
                            }
                        }
                        if (!alreadyExist) {
                            HomeLoggedFragment.tags.add(newTag);
                        }
                    } else {
                        String newTag = distritSelectedNames[i].toLowerCase();
                        HomeLoggedFragment.tags.add(newTag);
                    }
                } else {
                    if (HomeFragment.tags.size() > 0) {
                        String newTag = distritSelectedNames[i].toLowerCase();
                        for (int j = 0; j < HomeFragment.tags.size(); j++) {
                            if (newTag.equals(HomeFragment.tags.get(j))) {
                                alreadyExist = true;
                            }
                        }
                        if (!alreadyExist) {
                            HomeFragment.tags.add(new FilterTag(newTag, false));
                        }
                    } else {
                        String newTag = distritSelectedNames[i].toLowerCase();
                        HomeFragment.tags.add(new FilterTag(newTag, false));
                    }
                }
            }


        }
/*
        if (distritPressed1) {

            boolean alreadyExist = false;


            if (Helper.getUserAppPreference(getContext()).isLogged()) {

                if (HomeLoggedFragment.tags.size() > 0) {
                    String newTag = tvDistrit1.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (newTag.equals(HomeLoggedFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(newTag);
                    }
                } else {
                    String newTag = tvDistrit1.getText().toString().toLowerCase();
                    HomeLoggedFragment.tags.add(newTag);
                }

            } else {
                if (HomeFragment.tags.size() > 0) {
                    String newTag = tvDistrit1.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (newTag.equals(HomeFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(newTag);
                    }
                } else {
                    String newTag = tvDistrit1.getText().toString().toLowerCase();
                    HomeFragment.tags.add(newTag);
                }

            }


        }

        if (distritPressed2) {

            boolean alreadyExist = false;

            if (Helper.getUserAppPreference(getContext()).isLogged()) {
                if (HomeLoggedFragment.tags.size() > 0) {
                    String newTag = tvDistrit2.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (newTag.equals(HomeLoggedFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(newTag);
                    }
                } else {
                    String newTag = tvDistrit2.getText().toString().toLowerCase();
                    HomeLoggedFragment.tags.add(newTag);
                }

            } else {
                if (HomeFragment.tags.size() > 0) {
                    String newTag = tvDistrit2.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (newTag.equals(HomeFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(newTag);
                    }
                } else {
                    String newTag = tvDistrit2.getText().toString().toLowerCase();
                    HomeFragment.tags.add(newTag);
                }

            }


        }

        if (distritPressed3) {

            boolean alreadyExist = false;

            if (Helper.getUserAppPreference(getContext()).isLogged()) {
                if (HomeLoggedFragment.tags.size() > 0) {
                    String newTag = tvDistrit3.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (newTag.equals(HomeLoggedFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(newTag);
                    }
                } else {
                    String newTag = tvDistrit3.getText().toString().toLowerCase();
                    HomeLoggedFragment.tags.add(newTag);
                }
            } else {
                if (HomeFragment.tags.size() > 0) {
                    String newTag = tvDistrit3.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (newTag.equals(HomeFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(newTag);
                    }
                } else {
                    String newTag = tvDistrit3.getText().toString().toLowerCase();
                    HomeFragment.tags.add(newTag);
                }
            }


        }

        if (distritPressed4) {

            boolean alreadyExist = false;


            if (Helper.getUserAppPreference(getContext()).isLogged()) {
                if (HomeLoggedFragment.tags.size() > 0) {
                    String newTag = tvDistrit4.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (newTag.equals(HomeLoggedFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(newTag);
                    }
                } else {
                    String newTag = tvDistrit4.getText().toString().toLowerCase();
                    HomeLoggedFragment.tags.add(newTag);
                }
            } else {
                if (HomeFragment.tags.size() > 0) {
                    String newTag = tvDistrit4.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (newTag.equals(HomeFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(newTag);
                    }
                } else {
                    String newTag = tvDistrit4.getText().toString().toLowerCase();
                    HomeFragment.tags.add(newTag);
                }
            }


        }

        if (distritPressed5) {

            boolean alreadyExist = false;

            if (Helper.getUserAppPreference(getContext()).isLogged()) {
                if (HomeLoggedFragment.tags.size() > 0) {
                    String newTag = tvDistrit5.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (newTag.equals(HomeLoggedFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(newTag);
                    }
                } else {
                    String newTag = tvDistrit5.getText().toString().toLowerCase();
                    HomeLoggedFragment.tags.add(newTag);
                }
            } else {
                if (HomeFragment.tags.size() > 0) {
                    String newTag = tvDistrit5.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (newTag.equals(HomeFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(newTag);
                    }
                } else {
                    String newTag = tvDistrit5.getText().toString().toLowerCase();
                    HomeFragment.tags.add(newTag);
                }
            }


        }
*/

        if (interes1Pressed) {

            boolean alreadyExist = false;

            if (Helper.getUserAppPreference(getContext()).isLogged()) {
                if (HomeLoggedFragment.tags.size() > 0) {
                    String newTag = tvInteres1.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (newTag.equals(HomeLoggedFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(newTag);
                    }
                } else {
                    String newTag = tvInteres1.getText().toString().toLowerCase();
                    HomeLoggedFragment.tags.add(newTag);
                }
            } else {
                if (HomeFragment.tags.size() > 0) {
                    String newTag = tvInteres1.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (newTag.equals(HomeFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(new FilterTag(newTag, false));
                    }
                } else {
                    String newTag = tvInteres1.getText().toString().toLowerCase();
                    HomeFragment.tags.add(new FilterTag(newTag, false));
                }
            }


        }

        if (interes2Pressed) {

            boolean alreadyExist = false;

            if (Helper.getUserAppPreference(getContext()).isLogged()) {
                if (HomeLoggedFragment.tags.size() > 0) {
                    String newTag = tvInteres2.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (newTag.equals(HomeLoggedFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(newTag);
                    }
                } else {
                    String newTag = tvInteres2.getText().toString().toLowerCase();
                    HomeLoggedFragment.tags.add(newTag);
                }
            } else {
                if (HomeFragment.tags.size() > 0) {
                    String newTag = tvInteres2.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (newTag.equals(HomeFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(new FilterTag(newTag, false));
                    }
                } else {
                    String newTag = tvInteres2.getText().toString().toLowerCase();
                    HomeFragment.tags.add(new FilterTag(newTag, false));
                }
            }


        }

        if (interes3Pressed) {

            boolean alreadyExist = false;

            if (Helper.getUserAppPreference(getContext()).isLogged()) {
                if (HomeLoggedFragment.tags.size() > 0) {
                    String newTag = tvInteres3.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (newTag.equals(HomeLoggedFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(newTag);
                    }
                } else {
                    String newTag = tvInteres3.getText().toString().toLowerCase();
                    HomeLoggedFragment.tags.add(newTag);
                }
            } else {
                if (HomeFragment.tags.size() > 0) {
                    String newTag = tvInteres3.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (newTag.equals(HomeFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(new FilterTag(newTag, false));
                    }
                } else {
                    String newTag = tvInteres3.getText().toString().toLowerCase();
                    HomeFragment.tags.add(new FilterTag(newTag, false));
                }
            }


        }

        if (interes4Pressed) {

            boolean alreadyExist = false;

            if (Helper.getUserAppPreference(getContext()).isLogged()) {
                if (HomeLoggedFragment.tags.size() > 0) {
                    String newTag = tvInteres4.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (newTag.equals(HomeLoggedFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(newTag);
                    }
                } else {
                    String newTag = tvInteres4.getText().toString().toLowerCase();
                    HomeLoggedFragment.tags.add(newTag);
                }
            } else {
                if (HomeFragment.tags.size() > 0) {
                    String newTag = tvInteres4.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (newTag.equals(HomeFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(new FilterTag(newTag, false));
                    }
                } else {
                    String newTag = tvInteres4.getText().toString().toLowerCase();
                    HomeFragment.tags.add(new FilterTag(newTag, false));
                }
            }


        }

        if (interes5Pressed) {
            boolean alreadyExist = false;


            if (Helper.getUserAppPreference(getContext()).isLogged()) {
                if (HomeLoggedFragment.tags.size() > 0) {
                    String newTag = tvInteres5.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (newTag.equals(HomeLoggedFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(newTag);
                    }
                } else {
                    String newTag = tvInteres5.getText().toString().toLowerCase();
                    HomeLoggedFragment.tags.add(newTag);
                }
            } else {
                if (HomeFragment.tags.size() > 0) {
                    String newTag = tvInteres5.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (newTag.equals(HomeFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(new FilterTag(newTag, false));
                    }
                } else {
                    String newTag = tvInteres5.getText().toString().toLowerCase();
                    HomeFragment.tags.add(new FilterTag(newTag, false));
                }
            }


        }


    }



    void initUI(View v) {


        ivClose = (ImageView) v.findViewById(R.id.ivClose);
        tvInteres1 = (TextView) v.findViewById(R.id.tvInteres1);
        tvInteres2 = (TextView) v.findViewById(R.id.tvInteres2);
        tvInteres3 = (TextView) v.findViewById(R.id.tvInteres3);
        tvInteres4 = (TextView) v.findViewById(R.id.tvInteres4);
        tvInteres5 = (TextView) v.findViewById(R.id.tvInteres5);
        tvNose = (TextView) v.findViewById(R.id.tvNose);
        tv1Dia = (TextView) v.findViewById(R.id.tv1Dia);
        tv2Dias = (TextView) v.findViewById(R.id.tv2Dias);
        tvMasDe3Dias = (TextView) v.findViewById(R.id.tvMasDe3Dias);

        rlInteres1 = (RelativeLayout) v.findViewById(R.id.rlInteres1);
        rlInteres2 = (RelativeLayout) v.findViewById(R.id.rlInteres2);
        rlInteres3 = (RelativeLayout) v.findViewById(R.id.rlInteres3);
        rlInteres4 = (RelativeLayout) v.findViewById(R.id.rlInteres4);
        rlInteres5 = (RelativeLayout) v.findViewById(R.id.rlInteres5);
        rlNose = (RelativeLayout) v.findViewById(R.id.rlNose);
        rl1Dia = (RelativeLayout) v.findViewById(R.id.rl1Dia);
        rl2Dias = (RelativeLayout) v.findViewById(R.id.rl2Dias);
        rlMasDe3Dias = (RelativeLayout) v.findViewById(R.id.rlMasDe3Dias);

        btnAplicar = (Button) v.findViewById(R.id.btnAplicar);

        rvDistrits = (RecyclerView) v.findViewById(R.id.rvDistrits);
        transparent_linear_filter = (LinearLayout) v.findViewById(R.id.transparent_linear_filter);

        realClickEvents();

        ivClose.setOnClickListener(onOneOffClickListener);
        transparent_linear_filter.setOnClickListener(onOneOffClickListener);
        btnAplicar.setOnClickListener(onOneOffClickListener);

        tvInteres1.setOnClickListener(onOneOffClickListener);
        tvInteres2.setOnClickListener(onOneOffClickListener);
        tvInteres3.setOnClickListener(onOneOffClickListener);
        tvInteres4.setOnClickListener(onOneOffClickListener);
        tvInteres5.setOnClickListener(onOneOffClickListener);

        tvNose.setOnClickListener(onOneOffClickListener);
        tv1Dia.setOnClickListener(onOneOffClickListener);
        tv2Dias.setOnClickListener(onOneOffClickListener);
        tvMasDe3Dias.setOnClickListener(onOneOffClickListener);

        mlistenerDistritHorizontal = this;

        distritNeighborhoodPresenter = new DistritNeighborhoodPresenter();
        distritNeighborhoodPresenter.addView(this);
        distritNeighborhoodPresenter.getDistritNeighborhoods(Constants.STORE.DB);

/*
        interes1Pressed = false;
        interes2Pressed = false;
        interes3Pressed = false;
        interes4Pressed = false;
        interes5Pressed = false;

        interes1Pressed = false;
        interes2Pressed = false;
        interes3Pressed = false;
        interes4Pressed = false;
        interes5Pressed = false;*/

     /*   distritPressed1 = false;
        distritPressed2 = false;
        distritPressed3 = false;
        distritPressed4 = false;
        distritPressed5 = false;*/

        filters = new ArrayList<>();


    }


    void realClickEvents() {
        onOneOffClickListener = new OnOneOffClickListener() {
            @Override
            public void onSingleClick(View v) {

                switch (v.getId()) {
                    case R.id.ivClose:
                        dismiss();
                        break;
                    case R.id.btnAplicar:
                        dismiss();
                        break;
                    case R.id.transparent_linear_filter:
                        dismiss();
                        break;
                    case R.id.tvInteres1:
                        if (interes1Pressed) {
                            tvInteres1.setTextColor(Color.BLACK);
                            rlInteres1.setBackgroundResource(R.drawable.shape_home_filter_interes_off);
                            interes1Pressed = false;
                        } else {
                            tvInteres1.setTextColor(Color.WHITE);
                            rlInteres1.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                            interes1Pressed = true;
                        }
                        break;
                    case R.id.tvInteres2:
                        if (interes2Pressed) {
                            tvInteres2.setTextColor(Color.BLACK);
                            rlInteres2.setBackgroundResource(R.drawable.shape_home_filter_interes_off);
                            interes2Pressed = false;
                        } else {
                            tvInteres2.setTextColor(Color.WHITE);
                            rlInteres2.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                            interes2Pressed = true;
                        }
                        break;
                    case R.id.tvInteres3:
                        if (interes3Pressed) {
                            tvInteres3.setTextColor(Color.BLACK);
                            rlInteres3.setBackgroundResource(R.drawable.shape_home_filter_interes_off);
                            interes3Pressed = false;
                        } else {
                            tvInteres3.setTextColor(Color.WHITE);
                            rlInteres3.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                            interes3Pressed = true;
                        }
                        break;
                    case R.id.tvInteres4:
                        if (interes4Pressed) {
                            tvInteres4.setTextColor(Color.BLACK);
                            rlInteres4.setBackgroundResource(R.drawable.shape_home_filter_interes_off);
                            interes4Pressed = false;
                        } else {
                            tvInteres4.setTextColor(Color.WHITE);
                            rlInteres4.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                            interes4Pressed = true;
                        }
                        break;
                    case R.id.tvInteres5:
                        if (interes5Pressed) {
                            tvInteres5.setTextColor(Color.BLACK);
                            rlInteres5.setBackgroundResource(R.drawable.shape_home_filter_interes_off);
                            interes5Pressed = false;
                        } else {
                            tvInteres5.setTextColor(Color.WHITE);
                            rlInteres5.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                            interes5Pressed = true;
                        }
                        break;

                    case R.id.tvNose:
                        if (nosePressed) {
                            tvNose.setTextColor(Color.BLACK);
                            rlNose.setBackgroundResource(R.drawable.shape_filter_permanency_left_off);
                            nosePressed = false;
                        } else {
                            tvNose.setTextColor(Color.WHITE);
                            rlNose.setBackgroundResource(R.drawable.shape_filter_permanency_left_on);
                            nosePressed = true;
                        }
                        break;

                    case R.id.tv1Dia:
                        if (oneDayPressed) {
                            tv1Dia.setTextColor(Color.BLACK);
                            rl1Dia.setBackgroundResource(R.drawable.shape_filter_permenency_middle_off);
                            oneDayPressed = false;
                        } else {
                            tv1Dia.setTextColor(Color.WHITE);
                            rl1Dia.setBackgroundResource(R.drawable.shape_filter_permenency_middle_on);
                            oneDayPressed = true;
                        }
                        break;

                    case R.id.tv2Dias:
                        if (twoDaysPressed) {
                            tv2Dias.setTextColor(Color.BLACK);
                            rl2Dias.setBackgroundResource(R.drawable.shape_filter_permenency_middle_off);
                            twoDaysPressed = false;
                        } else {
                            tv2Dias.setTextColor(Color.WHITE);
                            rl2Dias.setBackgroundResource(R.drawable.shape_filter_permenency_middle_on);
                            twoDaysPressed = true;
                        }
                        break;

                    case R.id.tvMasDe3Dias:
                        if (moreThan3) {
                            tvMasDe3Dias.setTextColor(Color.BLACK);
                            rlMasDe3Dias.setBackgroundResource(R.drawable.shape_filter_permanency_rigth_off);
                            moreThan3 = false;
                        } else {
                            tvMasDe3Dias.setTextColor(Color.WHITE);
                            rlMasDe3Dias.setBackgroundResource(R.drawable.shape_filter_permanency_rigth_on);
                            moreThan3 = true;
                        }
                        break;
                }
            }
        };
    }


    protected void injectView(View view) {
        ButterKnife.bind(getActivity(), view);
    }


    @Override
    public void onDismiss(DialogInterface dialog) {

        juntartags();
        sendCallback();

    }

    @Override
    public void onDistritHorizontalClicked(View v, Integer position) {


        distrits.get(position).setFilterShowed(true);
        distritNeighborhoodSelected = distrits.get(position);


        for (int i = 0; i < distrits.size(); i++) {

            if (distritNeighborhoodSelected == distrits.get(i)) {

                if (distritPressed[i] != null && distritPressed[i]) {
                    distritSelectedNames[i] = "";
                    distritPressed[i] = false;
                } else {
                    distritSelectedNames[i] = distritNeighborhoodSelected.getDistrit();
                    distritPressed[i] = true;
                }

            }

        }


        // Integer posicion = position + 1;

    /*    if (position == 0) {

            if (distritPressed1) {
                distritPressed1 = false;
               // distritSelectedNames[0]="";
               // distritPressed[0]=false;
            } else {
                distritPressed1 = true;
               // distritSelectedNames[0]=distritNeighborhoodSelected.getDistrit();
              //  distritPressed[0]=true;
            }

        }*/
/*
        if (posicion == 2) {
            if (distritPressed2) {
                distritPressed2 = false;
            } else {
                distritPressed2 = true;
            }
        }

        if (posicion == 3) {
            if (distritPressed3) {
                distritPressed3 = false;
            } else {
                distritPressed3 = true;
            }
        }

        if (posicion == 4) {
            if (distritPressed4) {
                distritPressed4 = false;
            } else {
                distritPressed4 = true;
            }
        }

        if (posicion == 5) {
            if (distritPressed5) {
                distritPressed5 = false;
            } else {
                distritPressed5 = true;
            }
        }*/
    }

    @Override
    public void distritNeighborhoodListLoaded(List<DistritNeighborhood> distritNeighborhoods) {

        distrits = new ArrayList<>();
        distrits = distritNeighborhoods;

        DistritFilterListDataAdapter itemListDataAdapter = new DistritFilterListDataAdapter(mlistenerDistritHorizontal, getContext(), distrits);

        rvDistrits.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 3);
        rvDistrits.setLayoutManager(manager);
        rvDistrits.setAdapter(itemListDataAdapter);

    }

    @Override
    public void distritCreated(String message) {

    }

    @Override
    public void distritUpdated(String message) {

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

    public interface CierraDialogFilter {
        public void onClose_Filter(Boolean close, Context context);
    }


    void sendCallback() {
        // Activity ahhh = getActivity();
        //   GastosFragment dda = (GastosFragment)getFragmentManager().findFragmentById(R.id.containerView);
        //  List<Fragment> ah=dda.getChildFragmentManager().getFragments();
        //  Fragment ahhh=ah.get(0);

        Fragment ahhh = null;
        if (Helper.getUserAppPreference(getContext()).isLogged()) {
            ahhh = new HomeLoggedFragment();
        } else {
            ahhh = new HomeFragment();
        }


        if (ahhh instanceof CierraDialogFilter) {
            ((CierraDialogFilter) ahhh).onClose_Filter(true, getContext());
        }

    }


    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {

            //numero de pixeles que tendra de ancho
            // int width = 700;
            int width = ViewGroup.LayoutParams.MATCH_PARENT;

            //la altura se ajustara al contenido
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            dialog.getWindow().setLayout(width, height);

            //se lo asigno a mi dialogfragment

            //con esto hago que sea invicible
            dialog.getWindow().getAttributes().alpha = 1f;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        dismiss();
    }


}
