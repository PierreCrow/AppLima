package com.avances.applima.presentation.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.avances.applima.R;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

public class TabHomeRoutesFragment extends BaseFragment {

    @BindView(R.id.menuHome)
    ImageView ivnHome;

    @BindView(R.id.menuAccount)
    ImageView ivAccount;

    @BindView(R.id.menuFavorite)
    ImageView ivFavorite;

    @BindView(R.id.menuDiary)
    ImageView ivDiary;


    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 4;
  //  ImageView ivnHome, ivAccount, ivFavorite, ivDiary;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View x = inflater.inflate(R.layout.tab_home, null);

        injectView(x);

        initUI(x);

        setViewPagerAndTabs();

        return x;
    }


    private void initUI(View x) {

        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
    /*    ivnHome = (ImageView) x.findViewById(R.id.menuHome);
        ivAccount = (ImageView) x.findViewById(R.id.menuAccount);
        ivFavorite = (ImageView) x.findViewById(R.id.menuFavorite);
        ivDiary = (ImageView) x.findViewById(R.id.menuDiary);*/
    }

    private void setViewPagerAndTabs() {
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);

                tabLayout.getTabAt(Constants.FRAGMENTS_TABS.HOME).setIcon(tabIconsSelected[0]);
                tabLayout.getTabAt(Constants.FRAGMENTS_TABS.FAVORITES).setIcon(tabIcons[1]);
                tabLayout.getTabAt(Constants.FRAGMENTS_TABS.EVENTS).setIcon(tabIcons[2]);
                tabLayout.getTabAt(Constants.FRAGMENTS_TABS.ACCOUNT).setIcon(tabIcons[3]);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case Constants.FRAGMENTS_TABS.HOME: {
                        if (tabLayout.getSelectedTabPosition() == Constants.FRAGMENTS_TABS.HOME) {

                            SharedPreferences preferences = getContext().getSharedPreferences("Preference_Profile", Context.MODE_PRIVATE);
                            boolean value = preferences.getBoolean("BackfromProfile", false);

                            if (value) {

                                tabLayout.getTabAt(Constants.FRAGMENTS_TABS.ACCOUNT).select();

                            } else {
                                tabLayout.getTabAt(Constants.FRAGMENTS_TABS.HOME).setIcon(tabIconsSelected[0]);
                                tabLayout.getTabAt(Constants.FRAGMENTS_TABS.FAVORITES).setIcon(tabIcons[1]);
                                tabLayout.getTabAt(Constants.FRAGMENTS_TABS.EVENTS).setIcon(tabIcons[2]);
                                tabLayout.getTabAt(Constants.FRAGMENTS_TABS.ACCOUNT).setIcon(tabIcons[3]);
                            }
                        }
                    }
                    case Constants.FRAGMENTS_TABS.FAVORITES: {

                        if (tabLayout.getSelectedTabPosition() == Constants.FRAGMENTS_TABS.FAVORITES) {
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.HOME).setIcon(tabIcons[0]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.FAVORITES).setIcon(tabIconsSelected[1]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.EVENTS).setIcon(tabIcons[2]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.ACCOUNT).setIcon(tabIcons[3]);
                        }
                    }
                    case Constants.FRAGMENTS_TABS.EVENTS: {

                        if (tabLayout.getSelectedTabPosition() == Constants.FRAGMENTS_TABS.EVENTS) {
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.HOME).setIcon(tabIcons[0]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.FAVORITES).setIcon(tabIcons[1]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.EVENTS).setIcon(tabIconsSelected[2]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.ACCOUNT).setIcon(tabIcons[3]);
                        }
                    }
                    case Constants.FRAGMENTS_TABS.ACCOUNT: {

                        if (tabLayout.getSelectedTabPosition() == Constants.FRAGMENTS_TABS.ACCOUNT) {
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.HOME).setIcon(tabIcons[0]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.FAVORITES).setIcon(tabIcons[1]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.EVENTS).setIcon(tabIcons[2]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.ACCOUNT).setIcon(tabIconsSelected[3]);
                        }
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case Constants.FRAGMENTS_TABS.HOME: {
                    return new RoutesFragment();
                }
                case Constants.FRAGMENTS_TABS.FAVORITES: {
                    return new FavoritesFragment();
                }
                case Constants.FRAGMENTS_TABS.EVENTS: {
                    return new EventsFragment();
                }
                case Constants.FRAGMENTS_TABS.ACCOUNT: {
                    if (Helper.getUserAppPreference(getContext()).isLogged()) {
                        return new AccountFragment();
                    } else {
                        return new SecondsToOfferFragment();
                    }
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return int_items;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }


    private int[] tabIcons = {
            R.drawable.ic_home,
            R.drawable.ic_favorite,
            R.drawable.ic_diary,
            R.drawable.ic_account
    };


    private int[] tabIconsSelected = {
            R.drawable.ic_home_selected,
            R.drawable.ic_favorite_selected,
            R.drawable.ic_diary_selected,
            R.drawable.ic_account_selected
    };

}
