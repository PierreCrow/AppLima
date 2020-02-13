package com.avances.applima.presentation.ui.fragments;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.avances.applima.R;
import com.avances.applima.presentation.ui.adapters.ImperdiblesVerticalListDataAdapter;
import com.avances.applima.presentation.utils.Helper;

public class BuscadorFragment extends BaseFragment implements
        View.OnClickListener {


    TextView tvCancel;
    ImageView ivSearch;
    String tag;
    EditText editTextSearch;


    private ImperdiblesVerticalListDataAdapter.OnImperdiblesVerticalListClickListener mlistener;


    public interface GoHome {
        public void gotoHome(boolean search);
    }

    public interface GoList {
        public void gotoHomeWithList(String tag);
    }

    void sendCallback() {
        Activity ahhh = getActivity();
        //   GastosFragment dda = (GastosFragment)getFragmentManager().findFragmentById(R.id.containerView);
        //  List<Fragment> ah=dda.getChildFragmentManager().getFragments();
        //  Fragment ahhh=ah.get(0);

        if (ahhh instanceof GoHome) {
            ((GoHome) ahhh).gotoHome(true);
        }

    }


    void sendCallbackPruevba() {
        Fragment ahhh = new HomeFragment();
        //   GastosFragment dda = (GastosFragment)getFragmentManager().findFragmentById(R.id.containerView);
        //  List<Fragment> ah=dda.getChildFragmentManager().getFragments();
        //  Fragment ahhh=ah.get(0);

        if (ahhh instanceof GoList) {
            ((GoList) ahhh).gotoHomeWithList(tag);
        }

    }


    @Override
    public void onClick(View view) {


    }


    @Override
    public void onPause() {
        super.onPause();


    }

    void setEditTextSize(EditText et) {
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) et.getLayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        ;
        params.width = getResources().getDimensionPixelSize(R.dimen.edit_text_search_tag_width);
        et.setLayoutParams(params);
    }

    private void resize(View view, float scaleX, float scaleY) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) (view.getWidth() * scaleX);
        layoutParams.height = (int) (view.getHeight() * scaleY);
        view.setLayoutParams(layoutParams);
    }

    void initUI(View v) {

        final Animation animScale = AnimationUtils.loadAnimation(getContext(), R.anim.anim_scale_search_buscador);

        final Animation animTranslate = AnimationUtils.loadAnimation(getContext(), R.anim.translate_search_icon_left);


        tvCancel = (TextView) v.findViewById(R.id.tvCancel);
        ivSearch = (ImageView) v.findViewById(R.id.ivSearch);
        editTextSearch = (EditText) v.findViewById(R.id.editTextSearch);


        editTextSearch.startAnimation(animScale);
        editTextSearch.setPadding(50, 0, 0, 0);
        // resize(editTextSearch,1.0f,0.8f);
        //   setEditTextSize(editTextSearch);



        ivSearch.setVisibility(View.GONE);

        //   ivSearch.startAnimation(animTranslate);


        tvCancel.setVisibility(View.VISIBLE);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tvCancel, "alpha", 0, 1);
        objectAnimator.setDuration(1000);
        objectAnimator.setStartDelay(0);
        objectAnimator.start();


        tag = "";

        // HomeFragment.tags.add("holita");
    }


    void clickEvents() {

        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    boolean alreadyExist=false;

                    Bundle bundle = new Bundle();
                    bundle.putBoolean("newTag", true);
                    bundle.putString("tag", tag);

                    tag = editTextSearch.getText().toString();

                    if (!tag.equals("")) {



                        if(Helper.getUserAppPreference(getContext()).isLogged())
                        {
                            if(HomeLoggedInFragment.tags.size()>0)
                            {
                                // String newTag=tag;
                                for(int i=0;i<HomeLoggedInFragment.tags.size();i++)
                                {
                                    if(tag.equals(HomeLoggedInFragment.tags.get(i)))
                                    {
                                        alreadyExist=true;
                                    }
                                }

                                if(!alreadyExist)
                                {
                                    HomeLoggedInFragment.tags.add(tag);
                                }
                            }
                            else
                            {
                                String newTag=tag;
                                HomeLoggedInFragment.tags.add(newTag);
                            }
                        }
                        else
                        {
                            if(HomeFragment.tags.size()>0)
                            {
                                // String newTag=tag;
                                for(int i=0;i<HomeFragment.tags.size();i++)
                                {
                                    if(tag.equals(HomeFragment.tags.get(i)))
                                    {
                                        alreadyExist=true;
                                    }
                                }

                                if(!alreadyExist)
                                {
                                    HomeFragment.tags.add(tag);
                                }
                            }
                            else
                            {
                                String newTag=tag;
                                HomeFragment.tags.add(newTag);
                            }
                        }



                    }

                    FragmentManager fragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    TabHome accountFragment = new TabHome();
                    accountFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.containerView, accountFragment);
                    fragmentTransaction.commit();
                    return true;
                }
                return false;
            }
        });


        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //    sendCallback();

                //    sendCallbackPruevba();

                FragmentManager fragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                TabHome accountFragment = new TabHome();
                // accountFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.containerView, accountFragment);
                fragmentTransaction.commit();

            }
        });

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //    sendCallback();

                //  sendCallbackPruevba();


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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View x = inflater.inflate(R.layout.buscador_fragment, null);

        initUI(x);

        clickEvents();


        return x;

    }


}
