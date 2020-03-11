package com.avances.lima.presentation.ui.dialogfragment;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.lima.R;
import com.avances.lima.domain.model.DistritFilter;
import com.avances.lima.domain.model.DistritNeighborhood;
import com.avances.lima.domain.model.FilterTag;
import com.avances.lima.domain.model.Interest;
import com.avances.lima.domain.model.PermanencyDay;
import com.avances.lima.domain.model.UserPreference;
import com.avances.lima.presentation.presenter.DistritNeighborhoodPresenter;
import com.avances.lima.presentation.presenter.InterestPresenter;
import com.avances.lima.presentation.presenter.PermanencyDayPresenter;
import com.avances.lima.presentation.ui.adapters.DistritFilterListDataAdapter;
import com.avances.lima.presentation.ui.fragments.HomeFragment;
import com.avances.lima.presentation.ui.fragments.HomeLoggedFragment;
import com.avances.lima.presentation.ui.fragments.TabHome;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.utils.SingleClick;
import com.avances.lima.presentation.view.DistritNeighborhoodView;
import com.avances.lima.presentation.view.InterestView;
import com.avances.lima.presentation.view.PermanencyDayView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class FilterDialog extends DialogFragment
        implements DistritFilterListDataAdapter.OnDistritHorizontalClickListener,
        DistritNeighborhoodView, PermanencyDayView, InterestView {


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
    TextView tvPermanencyDay1;

    //   @BindView(R.id.tv1Dia)
    TextView tvPermanencyDay2;

    //  @BindView(R.id.tv2Dias)
    TextView tvPermanencyDay3;

    //  @BindView(R.id.tvMasDe3Dias)
    TextView tvPermanencyDay4;


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
    RelativeLayout rlPermanencyDay1;

    //   @BindView(R.id.rl1Dia)
    RelativeLayout rlPermanencyDay2;

    // @BindView(R.id.rl2Dias)
    RelativeLayout rlPermanencyDay3;

    // @BindView(R.id.rlMasDe3Dias)
    RelativeLayout rlPermanencyDay4;


    //  @BindView(R.id.btnAplicar)
    Button btnAplicar;

    //  @BindView(R.id.rvDistrits)
    RecyclerView rvDistrits;

    // @BindView(R.id.transparent_linear_filter)
    LinearLayout transparent_linear_filter;


    public static boolean interes1Pressed = false, interes2Pressed = false, interes3Pressed = false, interes4Pressed = false, interes5Pressed = false;
    public static boolean distritPressed1, distritPressed2, distritPressed3, distritPressed4, distritPressed5;

    public static boolean permanencyDay1Pressed = false, permanencyDay2Pressed = false, permanencyDay3Pressed = false, permanencyDay4Pressed = false;


    List<String> filters;
    DistritNeighborhoodPresenter distritNeighborhoodPresenter;
    List<DistritNeighborhood> distrits;
    SingleClick singleClick;

    DistritNeighborhood distritNeighborhoodSelected;
    String[] distritSelectedNames = new String[10000];
    Boolean[] distritPressed = new Boolean[10000];


    public static DistritFilterListDataAdapter.OnDistritHorizontalClickListener mlistenerDistritHorizontal;

    public static boolean repeatDistrit = false;

    UserPreference userPreference;
    InterestPresenter interestPresenter;
    PermanencyDayPresenter permanencyDayPresenter;

    List<Interest> interests;
    List<PermanencyDay> permanencyDays;
    public static List<DistritFilter> distritFilters;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.filter_dialog, new LinearLayout(getActivity()), false);


        // injectView(view);
        initUI(view);

        loadPresenter();

        setFields();

        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);

        builder.setContentView(view);
        return builder;

    }

    private void loadPresenter() {
        interestPresenter = new InterestPresenter();
        interestPresenter.addView(this);
        interestPresenter.getInterests(Constants.STORE.DB);

        permanencyDayPresenter = new PermanencyDayPresenter();
        permanencyDayPresenter.addView(this);
        permanencyDayPresenter.getPermanencyDays(Constants.STORE.DB);
    }


    void setFields() {


        for (Interest interest : interests) {

            for (int i = 0; i < 5; i++) {

                if (i == 0) {
                    if (userPreference.getInterest_1().equals(interest.getId())) {
                        rlInteres1.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                        tvInteres1.setTextColor(Color.WHITE);
                    }
                }


                if (i == 1) {
                    if (userPreference.getInterest_2().equals(interest.getId())) {
                        rlInteres2.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                        tvInteres2.setTextColor(Color.WHITE);
                    }
                }


                if (i == 2) {
                    if (userPreference.getInterest_3().equals(interest.getId())) {
                        rlInteres3.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                        tvInteres3.setTextColor(Color.WHITE);
                    }
                }


                if (i == 3) {
                    if (userPreference.getInterest_4().equals(interest.getId())) {
                        rlInteres4.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                        tvInteres4.setTextColor(Color.WHITE);
                    }
                }


                if (i == 4) {
                    if (userPreference.getInterest_5().equals(interest.getId())) {
                        rlInteres5.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                        tvInteres5.setTextColor(Color.WHITE);
                    }
                }


            }

        }


        for (int i = 0; i < permanencyDays.size(); i++) {
            if (i == 0) {
                if (userPreference.getPermanencyDays().equals(permanencyDays.get(i).getId())) {
                    rlPermanencyDay1.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    tvPermanencyDay1.setTextColor(Color.WHITE);
                }
            }


            if (i == 1) {
                if (userPreference.getPermanencyDays().equals(permanencyDays.get(i).getId())) {
                    rlPermanencyDay2.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    tvPermanencyDay2.setTextColor(Color.WHITE);
                }
            }

            if (i == 2) {
                if (userPreference.getPermanencyDays().equals(permanencyDays.get(i).getId())) {
                    rlPermanencyDay3.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    tvPermanencyDay3.setTextColor(Color.WHITE);
                }
            }

            if (i == 3) {
                if (userPreference.getPermanencyDays().equals(permanencyDays.get(i).getId())) {
                    rlPermanencyDay4.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    tvPermanencyDay4.setTextColor(Color.WHITE);
                }
            }

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
                            if (newTag.equals(HomeLoggedFragment.tags.get(j).getName())) {
                                alreadyExist = true;
                            }
                        }
                        if (!alreadyExist) {
                            HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
                        }
                    } else {
                        String newTag = distritSelectedNames[i].toLowerCase();
                        HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
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
                            HomeFragment.tags.add(new FilterTag(newTag, false, true));
                        }
                    } else {
                        String newTag = distritSelectedNames[i].toLowerCase();
                        HomeFragment.tags.add(new FilterTag(newTag, false, true));
                    }
                }
            }


        }


        if (interes1Pressed) {

            boolean alreadyExist = false;

            if (Helper.getUserAppPreference(getContext()).isLogged()) {
                if (HomeLoggedFragment.tags.size() > 0) {
                    String newTag = tvInteres1.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (newTag.equals(HomeLoggedFragment.tags.get(i).getName())) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
                        for (Interest interest : interests) {
                            if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                                userPreference.setInterest_1(interest.getId());
                                Helper.saveUserAppPreference(getContext(), userPreference);
                            }
                        }
                    }
                } else {
                    String newTag = tvInteres1.getText().toString().toLowerCase();
                    HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
                    for (Interest interest : interests) {
                        if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                            UserPreference userPreference = Helper.getUserAppPreference(getContext());
                            userPreference.setInterest_1(interest.getId());
                            Helper.saveUserAppPreference(getContext(), userPreference);
                        }
                    }
                }
            } else {
                if (HomeFragment.tags.size() > 0) {
                    String newTag = tvInteres1.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (newTag.equals(HomeFragment.tags.get(i).getName())) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(new FilterTag(newTag, false, true));
                        for (Interest interest : interests) {
                            if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                                userPreference.setInterest_1(interest.getId());
                                Helper.saveUserAppPreference(getContext(), userPreference);
                            }
                        }
                    }
                } else {
                    String newTag = tvInteres1.getText().toString().toLowerCase();
                    HomeFragment.tags.add(new FilterTag(newTag, false, true));
                    for (Interest interest : interests) {
                        if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                            UserPreference userPreference = Helper.getUserAppPreference(getContext());
                            userPreference.setInterest_1(interest.getId());
                            Helper.saveUserAppPreference(getContext(), userPreference);
                        }
                    }
                }
            }


        }

        if (interes2Pressed) {

            boolean alreadyExist = false;

            if (Helper.getUserAppPreference(getContext()).isLogged()) {
                if (HomeLoggedFragment.tags.size() > 0) {
                    String newTag = tvInteres2.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (newTag.equals(HomeLoggedFragment.tags.get(i).getName())) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
                        for (Interest interest : interests) {
                            if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                                userPreference.setInterest_2(interest.getId());
                                Helper.saveUserAppPreference(getContext(), userPreference);
                            }
                        }
                    }
                } else {
                    String newTag = tvInteres2.getText().toString().toLowerCase();
                    HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
                    for (Interest interest : interests) {
                        if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                            UserPreference userPreference = Helper.getUserAppPreference(getContext());
                            userPreference.setInterest_2(interest.getId());
                            Helper.saveUserAppPreference(getContext(), userPreference);
                        }
                    }
                }
            } else {
                if (HomeFragment.tags.size() > 0) {
                    String newTag = tvInteres2.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (newTag.equals(HomeFragment.tags.get(i).getName())) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(new FilterTag(newTag, false, true));
                        for (Interest interest : interests) {
                            if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                                userPreference.setInterest_2(interest.getId());
                                Helper.saveUserAppPreference(getContext(), userPreference);
                            }
                        }
                    }
                } else {
                    String newTag = tvInteres2.getText().toString().toLowerCase();
                    HomeFragment.tags.add(new FilterTag(newTag, false, true));
                    for (Interest interest : interests) {
                        if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                            UserPreference userPreference = Helper.getUserAppPreference(getContext());
                            userPreference.setInterest_2(interest.getId());
                            Helper.saveUserAppPreference(getContext(), userPreference);
                        }
                    }
                }
            }


        }

        if (interes3Pressed) {

            boolean alreadyExist = false;

            if (Helper.getUserAppPreference(getContext()).isLogged()) {
                if (HomeLoggedFragment.tags.size() > 0) {
                    String newTag = tvInteres3.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (newTag.equals(HomeLoggedFragment.tags.get(i).getName())) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
                        for (Interest interest : interests) {
                            if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                                userPreference.setInterest_3(interest.getId());
                                Helper.saveUserAppPreference(getContext(), userPreference);
                            }
                        }
                    }
                } else {
                    String newTag = tvInteres3.getText().toString().toLowerCase();
                    HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
                    for (Interest interest : interests) {
                        if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                            UserPreference userPreference = Helper.getUserAppPreference(getContext());
                            userPreference.setInterest_3(interest.getId());
                            Helper.saveUserAppPreference(getContext(), userPreference);
                        }
                    }
                }
            } else {
                if (HomeFragment.tags.size() > 0) {
                    String newTag = tvInteres3.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (newTag.equals(HomeFragment.tags.get(i).getName())) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(new FilterTag(newTag, false, true));
                        for (Interest interest : interests) {
                            if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                                userPreference.setInterest_3(interest.getId());
                                Helper.saveUserAppPreference(getContext(), userPreference);
                            }
                        }
                    }
                } else {
                    String newTag = tvInteres3.getText().toString().toLowerCase();
                    HomeFragment.tags.add(new FilterTag(newTag, false, true));
                    for (Interest interest : interests) {
                        if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                            UserPreference userPreference = Helper.getUserAppPreference(getContext());
                            userPreference.setInterest_3(interest.getId());
                            Helper.saveUserAppPreference(getContext(), userPreference);
                        }
                    }
                }
            }


        }

        if (interes4Pressed) {

            boolean alreadyExist = false;

            if (Helper.getUserAppPreference(getContext()).isLogged()) {
                if (HomeLoggedFragment.tags.size() > 0) {
                    String newTag = tvInteres4.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (newTag.equals(HomeLoggedFragment.tags.get(i).getName())) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
                        for (Interest interest : interests) {
                            if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                                userPreference.setInterest_4(interest.getId());
                                Helper.saveUserAppPreference(getContext(), userPreference);
                            }
                        }
                    }
                } else {
                    String newTag = tvInteres4.getText().toString().toLowerCase();
                    HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
                    for (Interest interest : interests) {
                        if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                            UserPreference userPreference = Helper.getUserAppPreference(getContext());
                            userPreference.setInterest_4(interest.getId());
                            Helper.saveUserAppPreference(getContext(), userPreference);
                        }
                    }
                }
            } else {
                if (HomeFragment.tags.size() > 0) {
                    String newTag = tvInteres4.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (newTag.equals(HomeFragment.tags.get(i).getName())) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(new FilterTag(newTag, false, true));
                        for (Interest interest : interests) {
                            if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                                userPreference.setInterest_4(interest.getId());
                                Helper.saveUserAppPreference(getContext(), userPreference);
                            }
                        }
                    }
                } else {
                    String newTag = tvInteres4.getText().toString().toLowerCase();
                    HomeFragment.tags.add(new FilterTag(newTag, false, true));
                    for (Interest interest : interests) {
                        if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                            UserPreference userPreference = Helper.getUserAppPreference(getContext());
                            userPreference.setInterest_4(interest.getId());
                            Helper.saveUserAppPreference(getContext(), userPreference);
                        }
                    }
                }
            }


        }

        if (interes5Pressed) {
            boolean alreadyExist = false;

            if (Helper.getUserAppPreference(getContext()).isLogged()) {
                if (HomeLoggedFragment.tags.size() > 0) {
                    String newTag = tvInteres5.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (newTag.equals(HomeLoggedFragment.tags.get(i).getName())) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
                        for (Interest interest : interests) {
                            if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                                userPreference.setInterest_5(interest.getId());
                                Helper.saveUserAppPreference(getContext(), userPreference);
                            }
                        }
                    }
                } else {
                    String newTag = tvInteres5.getText().toString().toLowerCase();
                    HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
                    for (Interest interest : interests) {
                        if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                            UserPreference userPreference = Helper.getUserAppPreference(getContext());
                            userPreference.setInterest_5(interest.getId());
                            Helper.saveUserAppPreference(getContext(), userPreference);
                        }
                    }
                }
            } else {
                if (HomeFragment.tags.size() > 0) {
                    String newTag = tvInteres5.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (newTag.equals(HomeFragment.tags.get(i).getName())) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(new FilterTag(newTag, false, true));
                        for (Interest interest : interests) {
                            if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                                userPreference.setInterest_5(interest.getId());
                                Helper.saveUserAppPreference(getContext(), userPreference);
                            }
                        }
                    }
                } else {
                    String newTag = tvInteres5.getText().toString().toLowerCase();
                    HomeFragment.tags.add(new FilterTag(newTag, false, true));
                    for (Interest interest : interests) {
                        if (interest.getDetailParameterValue().toLowerCase().equals(newTag)) {
                            UserPreference userPreference = Helper.getUserAppPreference(getContext());
                            userPreference.setInterest_5(interest.getId());
                            Helper.saveUserAppPreference(getContext(), userPreference);
                        }
                    }
                }
            }
        }


        //TIEMPO
        //---------------------------------


        if (permanencyDay1Pressed) {
            boolean alreadyExist = false;

            if (Helper.getUserAppPreference(getContext()).isLogged()) {
                if (HomeLoggedFragment.tags.size() > 0) {
                    String newTag = tvPermanencyDay1.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (newTag.equals(HomeLoggedFragment.tags.get(i).getName())) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
                        for (PermanencyDay permanencyDay : permanencyDays) {
                            if (permanencyDay.getDetailParameterValue().toLowerCase().equals(newTag)) {
                                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                                userPreference.setPermanencyDays(permanencyDay.getId());
                                Helper.saveUserAppPreference(getContext(), userPreference);
                            }
                        }
                    }
                } else {
                    String newTag = tvPermanencyDay1.getText().toString().toLowerCase();
                    HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
                    for (PermanencyDay permanencyDay : permanencyDays) {
                        if (permanencyDay.getDetailParameterValue().toLowerCase().equals(newTag)) {
                            UserPreference userPreference = Helper.getUserAppPreference(getContext());
                            userPreference.setPermanencyDays(permanencyDay.getId());
                            Helper.saveUserAppPreference(getContext(), userPreference);
                        }
                    }
                }
            } else {
                if (HomeFragment.tags.size() > 0) {
                    String newTag = tvPermanencyDay1.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (newTag.equals(HomeFragment.tags.get(i).getName())) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(new FilterTag(newTag, false, true));
                        for (PermanencyDay permanencyDay : permanencyDays) {
                            if (permanencyDay.getDetailParameterValue().toLowerCase().equals(newTag)) {
                                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                                userPreference.setPermanencyDays(permanencyDay.getId());
                                Helper.saveUserAppPreference(getContext(), userPreference);
                            }
                        }
                    }
                } else {
                    String newTag = tvPermanencyDay1.getText().toString().toLowerCase();
                    HomeFragment.tags.add(new FilterTag(newTag, false, true));
                    for (PermanencyDay permanencyDay : permanencyDays) {
                        if (permanencyDay.getDetailParameterValue().toLowerCase().equals(newTag)) {
                            UserPreference userPreference = Helper.getUserAppPreference(getContext());
                            userPreference.setPermanencyDays(permanencyDay.getId());
                            Helper.saveUserAppPreference(getContext(), userPreference);
                        }
                    }
                }
            }
        }

        if (permanencyDay2Pressed) {
            boolean alreadyExist = false;

            if (Helper.getUserAppPreference(getContext()).isLogged()) {
                if (HomeLoggedFragment.tags.size() > 0) {
                    String newTag = tvPermanencyDay2.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (newTag.equals(HomeLoggedFragment.tags.get(i).getName())) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
                        for (PermanencyDay permanencyDay : permanencyDays) {
                            if (permanencyDay.getDetailParameterValue().toLowerCase().equals(newTag)) {
                                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                                userPreference.setPermanencyDays(permanencyDay.getId());
                                Helper.saveUserAppPreference(getContext(), userPreference);
                            }
                        }
                    }
                } else {
                    String newTag = tvPermanencyDay2.getText().toString().toLowerCase();
                    HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
                    for (PermanencyDay permanencyDay : permanencyDays) {
                        if (permanencyDay.getDetailParameterValue().toLowerCase().equals(newTag)) {
                            UserPreference userPreference = Helper.getUserAppPreference(getContext());
                            userPreference.setPermanencyDays(permanencyDay.getId());
                            Helper.saveUserAppPreference(getContext(), userPreference);
                        }
                    }
                }
            } else {
                if (HomeFragment.tags.size() > 0) {
                    String newTag = tvPermanencyDay2.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (newTag.equals(HomeFragment.tags.get(i).getName())) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(new FilterTag(newTag, false, true));
                        for (PermanencyDay permanencyDay : permanencyDays) {
                            if (permanencyDay.getDetailParameterValue().toLowerCase().equals(newTag)) {
                                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                                userPreference.setPermanencyDays(permanencyDay.getId());
                                Helper.saveUserAppPreference(getContext(), userPreference);
                            }
                        }
                    }
                } else {
                    String newTag = tvPermanencyDay2.getText().toString().toLowerCase();
                    HomeFragment.tags.add(new FilterTag(newTag, false, true));
                    for (PermanencyDay permanencyDay : permanencyDays) {
                        if (permanencyDay.getDetailParameterValue().toLowerCase().equals(newTag)) {
                            UserPreference userPreference = Helper.getUserAppPreference(getContext());
                            userPreference.setPermanencyDays(permanencyDay.getId());
                            Helper.saveUserAppPreference(getContext(), userPreference);
                        }
                    }
                }
            }
        }

        if (permanencyDay3Pressed) {
            boolean alreadyExist = false;

            if (Helper.getUserAppPreference(getContext()).isLogged()) {
                if (HomeLoggedFragment.tags.size() > 0) {
                    String newTag = tvPermanencyDay3.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (newTag.equals(HomeLoggedFragment.tags.get(i).getName())) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
                        for (PermanencyDay permanencyDay : permanencyDays) {
                            if (permanencyDay.getDetailParameterValue().toLowerCase().equals(newTag)) {
                                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                                userPreference.setPermanencyDays(permanencyDay.getId());
                                Helper.saveUserAppPreference(getContext(), userPreference);
                            }
                        }
                    }
                } else {
                    String newTag = tvPermanencyDay3.getText().toString().toLowerCase();
                    HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
                    for (PermanencyDay permanencyDay : permanencyDays) {
                        if (permanencyDay.getDetailParameterValue().toLowerCase().equals(newTag)) {
                            UserPreference userPreference = Helper.getUserAppPreference(getContext());
                            userPreference.setPermanencyDays(permanencyDay.getId());
                            Helper.saveUserAppPreference(getContext(), userPreference);
                        }
                    }
                }
            } else {
                if (HomeFragment.tags.size() > 0) {
                    String newTag = tvPermanencyDay3.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (newTag.equals(HomeFragment.tags.get(i).getName())) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(new FilterTag(newTag, false, true));
                        for (PermanencyDay permanencyDay : permanencyDays) {
                            if (permanencyDay.getDetailParameterValue().toLowerCase().equals(newTag)) {
                                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                                userPreference.setPermanencyDays(permanencyDay.getId());
                                Helper.saveUserAppPreference(getContext(), userPreference);
                            }
                        }
                    }
                } else {
                    String newTag = tvPermanencyDay3.getText().toString().toLowerCase();
                    HomeFragment.tags.add(new FilterTag(newTag, false, true));
                    for (PermanencyDay permanencyDay : permanencyDays) {
                        if (permanencyDay.getDetailParameterValue().toLowerCase().equals(newTag)) {
                            UserPreference userPreference = Helper.getUserAppPreference(getContext());
                            userPreference.setPermanencyDays(permanencyDay.getId());
                            Helper.saveUserAppPreference(getContext(), userPreference);
                        }
                    }
                }
            }
        }


        if (permanencyDay4Pressed) {
            boolean alreadyExist = false;

            if (Helper.getUserAppPreference(getContext()).isLogged()) {
                if (HomeLoggedFragment.tags.size() > 0) {
                    String newTag = tvPermanencyDay4.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (newTag.equals(HomeLoggedFragment.tags.get(i).getName())) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
                        for (PermanencyDay permanencyDay : permanencyDays) {
                            if (permanencyDay.getDetailParameterValue().toLowerCase().equals(newTag)) {
                                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                                userPreference.setPermanencyDays(permanencyDay.getId());
                                Helper.saveUserAppPreference(getContext(), userPreference);
                            }
                        }
                    }
                } else {
                    String newTag = tvPermanencyDay4.getText().toString().toLowerCase();
                    HomeLoggedFragment.tags.add(new FilterTag(newTag, false, true));
                    for (PermanencyDay permanencyDay : permanencyDays) {
                        if (permanencyDay.getDetailParameterValue().toLowerCase().equals(newTag)) {
                            UserPreference userPreference = Helper.getUserAppPreference(getContext());
                            userPreference.setPermanencyDays(permanencyDay.getId());
                            Helper.saveUserAppPreference(getContext(), userPreference);
                        }
                    }
                }
            } else {
                if (HomeFragment.tags.size() > 0) {
                    String newTag = tvPermanencyDay4.getText().toString().toLowerCase();
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (newTag.equals(HomeFragment.tags.get(i).getName())) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(new FilterTag(newTag, false, true));
                        for (PermanencyDay permanencyDay : permanencyDays) {
                            if (permanencyDay.getDetailParameterValue().toLowerCase().equals(newTag)) {
                                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                                userPreference.setPermanencyDays(permanencyDay.getId());
                                Helper.saveUserAppPreference(getContext(), userPreference);
                            }
                        }
                    }
                } else {
                    String newTag = tvPermanencyDay4.getText().toString().toLowerCase();
                    HomeFragment.tags.add(new FilterTag(newTag, false, true));
                    for (PermanencyDay permanencyDay : permanencyDays) {
                        if (permanencyDay.getDetailParameterValue().toLowerCase().equals(newTag)) {
                            UserPreference userPreference = Helper.getUserAppPreference(getContext());
                            userPreference.setPermanencyDays(permanencyDay.getId());
                            Helper.saveUserAppPreference(getContext(), userPreference);
                        }
                    }
                }
            }
        }

    }


    void initUI(View v) {

        userPreference = Helper.getUserAppPreference(getContext());

        ivClose = (ImageView) v.findViewById(R.id.ivClose);
        tvInteres1 = (TextView) v.findViewById(R.id.tvInteres1);
        tvInteres2 = (TextView) v.findViewById(R.id.tvInteres2);
        tvInteres3 = (TextView) v.findViewById(R.id.tvInteres3);
        tvInteres4 = (TextView) v.findViewById(R.id.tvInteres4);
        tvInteres5 = (TextView) v.findViewById(R.id.tvInteres5);
        tvPermanencyDay1 = (TextView) v.findViewById(R.id.tvPermanencyDay1);
        tvPermanencyDay2 = (TextView) v.findViewById(R.id.tvPermanencyDay2);
        tvPermanencyDay3 = (TextView) v.findViewById(R.id.tvPermanencyDay3);
        tvPermanencyDay4 = (TextView) v.findViewById(R.id.tvPermanencyDay4);

        rlInteres1 = (RelativeLayout) v.findViewById(R.id.rlInteres1);
        rlInteres2 = (RelativeLayout) v.findViewById(R.id.rlInteres2);
        rlInteres3 = (RelativeLayout) v.findViewById(R.id.rlInteres3);
        rlInteres4 = (RelativeLayout) v.findViewById(R.id.rlInteres4);
        rlInteres5 = (RelativeLayout) v.findViewById(R.id.rlInteres5);
        rlPermanencyDay1 = (RelativeLayout) v.findViewById(R.id.rlPermanencyDay1);
        rlPermanencyDay2 = (RelativeLayout) v.findViewById(R.id.rlPermanencyDay2);
        rlPermanencyDay3 = (RelativeLayout) v.findViewById(R.id.rlPermanencyDay3);
        rlPermanencyDay4 = (RelativeLayout) v.findViewById(R.id.rlPermanencyDay4);

        btnAplicar = (Button) v.findViewById(R.id.btnAplicar);

        rvDistrits = (RecyclerView) v.findViewById(R.id.rvDistrits);
        transparent_linear_filter = (LinearLayout) v.findViewById(R.id.transparent_linear_filter);

        onClickListener();

        ivClose.setOnClickListener(singleClick);
        transparent_linear_filter.setOnClickListener(singleClick);
        btnAplicar.setOnClickListener(singleClick);

        tvInteres1.setOnClickListener(singleClick);
        tvInteres2.setOnClickListener(singleClick);
        tvInteres3.setOnClickListener(singleClick);
        tvInteres4.setOnClickListener(singleClick);
        tvInteres5.setOnClickListener(singleClick);

        tvPermanencyDay1.setOnClickListener(singleClick);
        tvPermanencyDay2.setOnClickListener(singleClick);
        tvPermanencyDay3.setOnClickListener(singleClick);
        tvPermanencyDay4.setOnClickListener(singleClick);

        mlistenerDistritHorizontal = this;

        distritNeighborhoodPresenter = new DistritNeighborhoodPresenter();
        distritNeighborhoodPresenter.addView(this);
        distritNeighborhoodPresenter.getDistritNeighborhoods(Constants.STORE.DB);

        filters = new ArrayList<>();

        rlInteres1.requestFocus();
    }


    void closeFilter() {

        juntartags();
        sendCallback();

        dismiss();
        FragmentManager fragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TabHome accountFragment = new TabHome();
        // accountFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();

    }

    void onClickListener() {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {

                switch (v.getId()) {
                    case R.id.ivClose:
                        dismiss();
                        // closeFilter();
                        break;
                    case R.id.btnAplicar:
                        // dismiss();
                        closeFilter();
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

                    case R.id.tvPermanencyDay1:
                        if (permanencyDay1Pressed) {
                            tvPermanencyDay1.setTextColor(Color.BLACK);
                            rlPermanencyDay1.setBackgroundResource(R.drawable.shape_filter_permanency_left_off);
                            permanencyDay1Pressed = false;
                        } else {
                            tvPermanencyDay1.setTextColor(Color.WHITE);
                            rlPermanencyDay1.setBackgroundResource(R.drawable.shape_filter_permanency_left_on);
                            permanencyDay1Pressed = true;
                        }
                        break;

                    case R.id.tvPermanencyDay2:
                        if (permanencyDay2Pressed) {
                            tvPermanencyDay2.setTextColor(Color.BLACK);
                            rlPermanencyDay2.setBackgroundResource(R.drawable.shape_filter_permenency_middle_off);
                            permanencyDay2Pressed = false;
                        } else {
                            tvPermanencyDay2.setTextColor(Color.WHITE);
                            rlPermanencyDay2.setBackgroundResource(R.drawable.shape_filter_permenency_middle_on);
                            permanencyDay2Pressed = true;
                        }
                        break;

                    case R.id.tvPermanencyDay3:
                        if (permanencyDay3Pressed) {
                            tvPermanencyDay3.setTextColor(Color.BLACK);
                            rlPermanencyDay3.setBackgroundResource(R.drawable.shape_filter_permenency_middle_off);
                            permanencyDay3Pressed = false;
                        } else {
                            tvPermanencyDay3.setTextColor(Color.WHITE);
                            rlPermanencyDay3.setBackgroundResource(R.drawable.shape_filter_permenency_middle_on);
                            permanencyDay3Pressed = true;
                        }
                        break;

                    case R.id.tvPermanencyDay4:
                        if (permanencyDay4Pressed) {
                            tvPermanencyDay4.setTextColor(Color.BLACK);
                            rlPermanencyDay4.setBackgroundResource(R.drawable.shape_filter_permanency_rigth_off);
                            permanencyDay4Pressed = false;
                        } else {
                            tvPermanencyDay4.setTextColor(Color.WHITE);
                            rlPermanencyDay4.setBackgroundResource(R.drawable.shape_filter_permanency_rigth_on);
                            permanencyDay4Pressed = true;
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

     /*   juntartags();
        sendCallback();
     */

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

        if (!repeatDistrit) {
            //  validateInsertTag(distritNeighborhoodSelected.getDistrit());
            repeatDistrit = true;
        }

        for (DistritFilter distritFilter : distritFilters) {
            if (distritNeighborhoodSelected.getIdCloud().equals(distritFilter.getDistritNeighborhood().getIdCloud())) {
                distritFilter.setPressed(true);
            }

        }


    }

    @Override
    public void distritNeighborhoodListLoaded(List<DistritNeighborhood> distritNeighborhoods) {


        if (distritFilters == null) {
            distritFilters = new ArrayList<>();
            for (DistritNeighborhood distritNeighborhood : distritNeighborhoods) {
                distritFilters.add(new DistritFilter(distritNeighborhood, false));
            }
        }

        distrits = new ArrayList<>();
        distrits = distritNeighborhoods;

        DistritFilterListDataAdapter itemListDataAdapter = new DistritFilterListDataAdapter(mlistenerDistritHorizontal, getContext(), distritFilters);

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
    public void interestListLoaded(List<Interest> mIterest) {

        interests = mIterest;

        for (int i = 0; i < interests.size(); i++) {
            if (i == 0) {
                tvInteres1.setText(interests.get(i).getDetailParameterValue());
            }

            if (i == 1) {
                tvInteres2.setText(interests.get(i).getDetailParameterValue());
            }

            if (i == 2) {
                tvInteres3.setText(interests.get(i).getDetailParameterValue());
            }

            if (i == 3) {
                tvInteres4.setText(interests.get(i).getDetailParameterValue());
            }

            if (i == 4) {
                tvInteres5.setText(interests.get(i).getDetailParameterValue());
            }
        }

    }

    @Override
    public void interestCreated(String message) {

    }

    @Override
    public void interestUpdated(String message) {

    }

    @Override
    public void permanencyDayListLoaded(List<PermanencyDay> mPermanencyDays) {

        permanencyDays = mPermanencyDays;

        for (int i = 0; i < permanencyDays.size(); i++) {
            if (i == 0) {
                tvPermanencyDay1.setText(permanencyDays.get(i).getNameParameterValue());
            }

            if (i == 1) {
                tvPermanencyDay2.setText(permanencyDays.get(i).getNameParameterValue());
            }

            if (i == 2) {
                tvPermanencyDay3.setText(permanencyDays.get(i).getNameParameterValue());
            }

            if (i == 3) {
                tvPermanencyDay4.setText(permanencyDays.get(i).getNameParameterValue());
            }
        }


    }

    @Override
    public void permanencyDayCreated(String message) {

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
