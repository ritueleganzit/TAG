package com.eleganzit.tag.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter   extends FragmentPagerAdapter {
    private final ArrayList<Fragment> mmFragmentTitleList  = new ArrayList<Fragment>();
    private final ArrayList<String> FragmentList= new ArrayList<String>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int i) {
        return mmFragmentTitleList.get(i);
    }

    @Override
    public int getCount() {
        return mmFragmentTitleList.size();
    }
    public void addFrag(Fragment fragment, String title) {
        mmFragmentTitleList.add(fragment);
        FragmentList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentList.get(position);
    }
}
