package com.avances.lima.presentation.ui.fragments;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.lima.R;
import com.avances.lima.domain.model.FilterTag;
import com.avances.lima.domain.model.SuggestedTag;
import com.avances.lima.presentation.presenter.SuggestedTagPresenter;
import com.avances.lima.presentation.ui.adapters.SuggestedTagListDataAdapter;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.utils.SingleClick;
import com.avances.lima.presentation.view.SuggestedTagView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchFragment extends BaseFragment implements
        SuggestedTagView, SuggestedTagListDataAdapter.OnTagClickListener {

    @BindView(R.id.rvSuggestedTags)
    RecyclerView rvSuggestedTags;

    @BindView(R.id.tvCancel)
    TextView tvCancel;

    @BindView(R.id.editTextSearch)
    EditText editTextSearch;

    String tag;
    SuggestedTagPresenter suggestedTagPresenter;
    List<SuggestedTag> suggestedTags;
    SuggestedTagListDataAdapter suggestedTagListDataAdapter;
    SuggestedTagListDataAdapter.OnTagClickListener mlistener;
    View mView;
    SingleClick singleClick;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View x = inflater.inflate(R.layout.search_fragment, null);

        injectView(x);
        initUI(x);
        loadPresenter();
        return x;
    }


    void initUI(View v) {

        final Animation animScale = AnimationUtils.loadAnimation(getContext(), R.anim.anim_scale_search_buscador);
        mlistener = this;
        onClickListener();
        tvCancel.setOnClickListener(singleClick);

        mView = rvSuggestedTags;

        editTextSearch.startAnimation(animScale);
        editTextSearch.setPadding(50, 0, 0, 0);
        // resize(editTextSearch,1.0f,0.8f);
        //   setEditTextSize(editTextSearch);

        tvCancel.setVisibility(View.VISIBLE);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tvCancel, "alpha", 0, 1);
        objectAnimator.setDuration(1000);
        objectAnimator.setStartDelay(0);
        objectAnimator.start();
        tag = "";
        searchTextChangeListener();

    }


    void loadPresenter() {
        suggestedTagPresenter = new SuggestedTagPresenter();
        suggestedTagPresenter.addView(this);
        suggestedTagPresenter.getSuggestedTags(Constants.STORE.DB);
    }

    private void onClickListener() {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {

                switch (v.getId()) {
                    case R.id.tvCancel:
                        goHome();
                        break;
                }
            }
        };
    }


    @Override
    public void onTagClicked(View v, String mTag) {

        boolean alreadyExist = false;

        Bundle bundle = new Bundle();
        bundle.putBoolean("newTag", true);
        bundle.putString("tag", tag);

        tag = mTag;

        if (!tag.equals("")) {

            if (Helper.getUserAppPreference(getContext()).isLogged()) {
                if (HomeLoggedFragment.tags.size() > 0) {
                    for (int i = 0; i < HomeLoggedFragment.tags.size(); i++) {
                        if (tag.equals(HomeLoggedFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeLoggedFragment.tags.add(tag);
                    }
                } else {
                    String newTag = tag;
                    HomeLoggedFragment.tags.add(newTag);
                }
            } else {
                if (HomeFragment.tags.size() > 0) {
                    for (int i = 0; i < HomeFragment.tags.size(); i++) {
                        if (tag.equals(HomeFragment.tags.get(i))) {
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        HomeFragment.tags.add(new FilterTag(tag, false));
                    }
                } else {
                    String newTag = tag;
                    HomeFragment.tags.add(new FilterTag(newTag, false));
                }
            }

        }

        FragmentManager fragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TabHome accountFragment = new TabHome();
        accountFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();

    }


    @Override
    public void suggestedTagListLoaded(List<SuggestedTag> mSuggestedTags) {

        suggestedTags = mSuggestedTags;
        suggestedTagListDataAdapter = new SuggestedTagListDataAdapter(getContext(), suggestedTags, mlistener);
        rvSuggestedTags.setHasFixedSize(true);


        float witdth = (float) mView.getWidth();
        float prueba = (float) 320;
        int mNoOfColumns = Helper.calculateNoOfColumns(getContext(), prueba);

        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 3);
        // rvSuggestedTags.setLayoutManager(mLayoutManager);
//        rvSuggestedTags.setAdapter(suggestedTagListDataAdapter);

   /*     GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                int pruebafinal=(3 - position % 3);
                return pruebafinal;
            }
        });
*/
        rvSuggestedTags.setLayoutManager(manager);
        rvSuggestedTags.setAdapter(suggestedTagListDataAdapter);

    }


    void searchTextChangeListener() {
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // filter your list from your input
                filter(s.toString());
                //you can use runnable postDelayed like 500 ms to delay search text
            }
        });
    }


    void goHome() {
        FragmentManager fragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TabHome accountFragment = new TabHome();
        // accountFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();
    }


    @Override
    public void suggestedTagCreated(String message) {

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


    void filter(String text) {
        List<SuggestedTag> temp = new ArrayList();
        for (SuggestedTag d : suggestedTags) {
            if (d.getName().contains(text)) {
                temp.add(d);
            }
        }
        suggestedTagListDataAdapter.updateList(temp);
    }


    public interface GoHome {
        public void gotoHome(boolean search);
    }


    @Override
    public void onPause() {
        super.onPause();
    }


}