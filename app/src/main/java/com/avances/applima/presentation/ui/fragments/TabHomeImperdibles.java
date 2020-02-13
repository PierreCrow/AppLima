package com.avances.applima.presentation.ui.fragments;

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

public class TabHomeImperdibles extends BaseFragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 4;
    ImageView ivnHome, ivAccount, ivFavorite, ivDiary;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View x = inflater.inflate(R.layout.tab_home, null);

        initUI(x);

        setViewPagerAndTabs();

        return x;

    }


    private void initUI(View x) {

        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        ivnHome = (ImageView) x.findViewById(R.id.menuHome);
        ivAccount = (ImageView) x.findViewById(R.id.menuAccount);
        ivFavorite = (ImageView) x.findViewById(R.id.menuFavorite);
        ivDiary = (ImageView) x.findViewById(R.id.menuDiary);

    }

    private void setViewPagerAndTabs() {
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));


        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);

                tabLayout.getTabAt(0).setIcon(tabIconsSelected[0]);
                tabLayout.getTabAt(1).setIcon(tabIcons[1]);
                tabLayout.getTabAt(2).setIcon(tabIcons[2]);
                tabLayout.getTabAt(3).setIcon(tabIcons[3]);
            }
        });


        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                switch (tab.getPosition()) {
                    case 0: {

                        if (tabLayout.getSelectedTabPosition() == 0) {
                            tabLayout.getTabAt(0).setIcon(tabIconsSelected[0]);
                            tabLayout.getTabAt(1).setIcon(tabIcons[1]);
                            tabLayout.getTabAt(2).setIcon(tabIcons[2]);
                            tabLayout.getTabAt(3).setIcon(tabIcons[3]);
                        }

                    }
                    case 1: {

                        if (tabLayout.getSelectedTabPosition() == 1) {
                            tabLayout.getTabAt(0).setIcon(tabIcons[0]);
                            tabLayout.getTabAt(1).setIcon(tabIconsSelected[1]);
                            tabLayout.getTabAt(2).setIcon(tabIcons[2]);
                            tabLayout.getTabAt(3).setIcon(tabIcons[3]);
                        }

                    }
                    case 2: {

                        if (tabLayout.getSelectedTabPosition() == 2) {
                            tabLayout.getTabAt(0).setIcon(tabIcons[0]);
                            tabLayout.getTabAt(1).setIcon(tabIcons[1]);
                            tabLayout.getTabAt(2).setIcon(tabIconsSelected[2]);
                            tabLayout.getTabAt(3).setIcon(tabIcons[3]);
                        }


                    }
                    case 3: {

                        if (tabLayout.getSelectedTabPosition() == 3) {
                            tabLayout.getTabAt(0).setIcon(tabIcons[0]);
                            tabLayout.getTabAt(1).setIcon(tabIcons[1]);
                            tabLayout.getTabAt(2).setIcon(tabIcons[2]);
                            tabLayout.getTabAt(3).setIcon(tabIconsSelected[3]);
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
                    return new ImperdiblesFragment();
                }
                case Constants.FRAGMENTS_TABS.FAVORITOS: {
                    return new FavoritosFragment();
                }
                case Constants.FRAGMENTS_TABS.EVENTOS: {
                    return new EventosFragment();
                }
                case Constants.FRAGMENTS_TABS.CUENTA: {
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
