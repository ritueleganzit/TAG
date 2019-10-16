package com.eleganzit.tag.ui.activity.payment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eleganzit.tag.R;
import com.eleganzit.tag.ui.activity.school.tabfragment.AppliedCollegeFragment;
import com.eleganzit.tag.ui.activity.school.tabfragment.SubActivityFragment;
import com.eleganzit.tag.utils.ViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {
    public static ViewPager htab_viewpager;
    TabLayout htab_tabs;

    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_payment, container, false);
        htab_tabs=v.findViewById(R.id.htab_tabs);



        htab_viewpager=v.findViewById(R.id.htab_viewpager);
        setupViewPager(htab_viewpager);

        return v;
    }
    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(
                getChildFragmentManager());

        adapter.addFrag(new PaymentSubFragment(),"Payment");
        adapter.addFrag(new PaymentHistoryFragment(),"Payment History");

        viewPager.setAdapter(adapter);
        htab_tabs.setupWithViewPager(htab_viewpager);
    }



}
