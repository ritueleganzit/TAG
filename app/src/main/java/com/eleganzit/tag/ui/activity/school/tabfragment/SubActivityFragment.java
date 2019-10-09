package com.eleganzit.tag.ui.activity.school.tabfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.SubFragmentAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubActivityFragment extends Fragment {

RecyclerView rc_supplied_college;
    public SubActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_sub_activity, container, false);
        rc_supplied_college=v.findViewById(R.id.rc_supplied_college);
        rc_supplied_college.setAdapter(new SubFragmentAdapter(getActivity()));

        return v;
    }

}
